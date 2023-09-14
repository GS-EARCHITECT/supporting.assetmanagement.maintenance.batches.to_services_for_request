package asset_main_schd_create_details;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import common.repo.AssetMainSchdDetailsCUD_Repo;
import common.repo.AssetMainSchdDetailsRead_Repo;
import common.repo.AssetMaintenanceSchdDetailsCUD_Repo;
import reactor.core.publisher.Flux;
import common.dto.*;
import common.master.*;

@Service("assetMainCreateDetailsBatchServ")
public class AssetMainSchdCreateDetails_Service implements I_AssetMainSchdCreateDetails_Service {
	private static final Logger logger = LoggerFactory.getLogger(AssetMainSchdCreateDetails_Service.class);

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private AssetMainSchdDetailsRead_Repo assetMainSchdDetailsReadRepo;

	@Autowired
	private AssetMainSchdDetailsCUD_Repo assetMainSchdDetailsCUDRepo;
	
	@Autowired
	private AssetMaintenanceSchdDetailsCUD_Repo assetMaintenanceSchdDetailsCUDRepo;
	
	@Scheduled(fixedRate = 5000)
	public void runService() {
			// Get All Assets Not Scheduled
			CopyOnWriteArrayList<AssetMainSchdDetail> assetMainSchdDetails = assetMainSchdDetailsReadRepo.getAssetSchdDetailsOkScheduled();
			Long sNo = (long) 0;			
			CopyOnWriteArrayList<SchedulerDetail_DTO> schedulerDetail_DTOs = null;
			SchedulerDetail_DTO schedulerDetail_DTO = null;
			AssetMaintenanceSchdDetail assetMaintenanceSchdDetail = null;					
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime dateFr = null;
			LocalDateTime dateTo = null;
			Timestamp ts_Fr = null;
			Timestamp ts_To = null;

			logger.info("-- scheduled data ready for maintenance size : "+assetMainSchdDetails.size());
			
			for (int i = 0; i < assetMainSchdDetails.size(); i++) 
			{
				sNo = assetMainSchdDetails.get(i).getScheduleNo();
				schedulerDetail_DTOs = this.getSchedules(sNo);
				if(schedulerDetail_DTOs!=null)
				{
				for (int j = 0; j < schedulerDetail_DTOs.size(); j++) 
				{
				schedulerDetail_DTO = schedulerDetail_DTOs.get(j);
				dateFr = LocalDateTime.parse(schedulerDetail_DTO.getFrDttm(), formatter);
				dateTo = LocalDateTime.parse(schedulerDetail_DTO.getToDttm(), formatter);
				ts_Fr = Timestamp.valueOf(dateFr);
				ts_To = Timestamp.valueOf(dateTo);
				assetMaintenanceSchdDetail = new AssetMaintenanceSchdDetail();
				assetMaintenanceSchdDetail.setDoneflag('N');
				assetMaintenanceSchdDetail.setFrDttm(ts_Fr);
				assetMaintenanceSchdDetail.setToDttm(ts_To);
				assetMaintenanceSchdDetail.setAssetSeqNo(assetMainSchdDetails.get(i).getId().getAssetSeqNo());
				assetMaintenanceSchdDetail.setRuleLineSeqNo(schedulerDetail_DTO.getRuleLineSeqNo());
				assetMaintenanceSchdDetail.setOkflag('N');
				assetMaintenanceSchdDetail.setRessrvprdSeqNo(assetMainSchdDetails.get(i).getId().getRessrvprdSeqNo());
				assetMaintenanceSchdDetail.setRuleSeqNo(assetMainSchdDetails.get(i).getId().getRuleSeqNo());				
				assetMaintenanceSchdDetail.setWipflag('N');
				assetMaintenanceSchdDetailsCUDRepo.save(assetMaintenanceSchdDetail);				
				assetMainSchdDetailsCUDRepo.setAssetDone(assetMainSchdDetails.get(i).getId().getAssetSeqNo(),assetMainSchdDetails.get(i).getId().getRessrvprdSeqNo(),assetMainSchdDetails.get(i).getId().getRuleSeqNo());
				}	
				}
			}

		return;
	}

	public CopyOnWriteArrayList<SchedulerDetail_DTO> getSchedules(Long sNo)
	{
	
	CopyOnWriteArrayList<SchedulerDetail_DTO> specLst = new CopyOnWriteArrayList<SchedulerDetail_DTO>();
	Flux<SchedulerDetail_DTO> specs = webClient.get().uri("/schedulerPublicReadManagement/getSelectSchedulesForRuleLine/"+sNo).retrieve().bodyToFlux(SchedulerDetail_DTO.class);
	specs.collectList().subscribe(specLst::addAll);
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return specLst;
	}

}