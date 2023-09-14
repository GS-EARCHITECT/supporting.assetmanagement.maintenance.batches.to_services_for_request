package asset_main_schd_init_details;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import common.repo.AssetDateDetailsPublicRead_Repo;
import common.repo.AssetMainRuleMasterPublicRead_Repo;
import common.repo.AssetMainSchdDetailsCUD_Repo;
import common.repo.AssetMainSchdDetailsRead_Repo;
import common.repo.AssetResServPartyDetails_Repo;
import reactor.core.publisher.Mono;
import common.dto.SchedulerMaster_DTO;
import common.master.*;

@Service("assetMainInitDetailsBatchServ")
public class AssetMainSchdInitDetails_Service implements I_AssetMainSchdInitDetails_Service {
	private static final Logger logger = LoggerFactory.getLogger(AssetMainSchdInitDetails_Service.class);

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private AssetMainSchdDetailsRead_Repo assetMainSchdDetailsReadRepo;

	@Autowired
	private AssetMainSchdDetailsCUD_Repo assetMainSchdDetailsCUDRepo;

	@Autowired
	private AssetMainRuleMasterPublicRead_Repo assetMainRuleMasterPublicReadRepo;
	
	@Autowired
	private AssetDateDetailsPublicRead_Repo assetDateDetailsPublicReadRepo;
	
	@Autowired
	private AssetResServPartyDetails_Repo assetResServPartyDetailsRepo;
	
	@Scheduled(fixedRate = 3000)
	public void runService() {

			// Get All Assets Not Scheduled
			CopyOnWriteArrayList<AssetMainSchdDetail> assetMainSchdDetails = assetMainSchdDetailsReadRepo.getAssetSchdDetailsNotScheduled();
			AssetMainSchdDetailPK assetMainSchdDetailPK = null;
			Long sNo = (long) 0; 
			logger.info("- running init details - ");
			for (int i = 0; i < assetMainSchdDetails.size(); i++) 
			{
				
				assetMainSchdDetailPK = new AssetMainSchdDetailPK();
				assetMainSchdDetailPK.setAssetSeqNo(assetMainSchdDetails.get(i).getId().getAssetSeqNo());
				assetMainSchdDetailPK.setRessrvprdSeqNo(assetMainSchdDetails.get(i).getId().getRessrvprdSeqNo());
				assetMainSchdDetailPK.setRuleSeqNo(assetMainSchdDetails.get(i).getId().getRuleSeqNo());
				sNo = this.createSchedules(assetMainSchdDetails.get(i));
				if(sNo>0)
				{
				assetMainSchdDetailsCUDRepo.setScheduleNo(assetMainSchdDetails.get(i).getId().getAssetSeqNo(),assetMainSchdDetails.get(i).getId().getRessrvprdSeqNo(),assetMainSchdDetails.get(i).getId().getRuleSeqNo(),sNo);
				}
			}

		return;
	}

	public synchronized Long createSchedules(AssetMainSchdDetail assetMainSchdDetail)
	{
	Long ruleSeqNo = assetMainSchdDetail.getId().getRuleSeqNo();
	Long dateSeqNo = assetMainRuleMasterPublicReadRepo.getDateFromRule(ruleSeqNo);	
	Timestamp frdttm = assetDateDetailsPublicReadRepo.getAssetDateDetail(assetMainSchdDetail.getId().getAssetSeqNo(),dateSeqNo);
	String frTime = assetMainSchdDetail.getFrTime();
	String toTime = assetMainSchdDetail.getToTime();
	Long compSeqNo = assetResServPartyDetailsRepo.getPartyForResProd(assetMainSchdDetail.getId().getRessrvprdSeqNo());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	String frdttmStr = formatter.format(frdttm.toLocalDateTime());
	String todttmStr = frdttmStr;	
	Long ruleLineSeqNo = (long) 0;	
	SchedulerMaster_DTO schdDTO = new SchedulerMaster_DTO();
	schdDTO.setCompanySeqNo(compSeqNo);
	schdDTO.setTargetSeqNo(assetMainSchdDetail.getId().getRessrvprdSeqNo()+"-"+assetMainSchdDetail.getId().getAssetSeqNo());
	schdDTO.setFromDttm(frdttmStr);
	schdDTO.setToDttm(todttmStr);
	schdDTO.setRuleSeqNo(ruleSeqNo);
	schdDTO.setScheduleData("10,4");
	schdDTO.setScheduledFlag('n');
	schdDTO.setFrtm(frTime);
	schdDTO.setTotm(toTime);

	Mono<SchedulerMaster_DTO> schedDTO1 = webClient.post().uri("/schedulerPublicCUDManagement/new")
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body(Mono.just(schdDTO), SchedulerMaster_DTO.class).retrieve().bodyToMono(SchedulerMaster_DTO.class);
	SchedulerMaster_DTO specDTO3 = schedDTO1.block();
	if(specDTO3.getRuleLineSeqNo()!=null && specDTO3.getRuleLineSeqNo()>0)
	{
	ruleLineSeqNo = specDTO3.getRuleLineSeqNo();
	}
	else
	{
	ruleLineSeqNo = (long) -1;	
	}
	return ruleLineSeqNo;
	}
	
}