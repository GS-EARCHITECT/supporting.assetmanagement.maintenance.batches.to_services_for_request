package asset_main_schd_request;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import common.master.AssetMaintenanceSchdDetail;
import common.master.ServiceRequestMaster;
import common.master.ServiceRequestMaster_DTO;
import common.repo.AssetMaintenanceSchdDetailsCUD_Repo;
import common.repo.AssetMaintenanceSchdDetailsRead_Repo;
import common.repo.AssetMaster_Repo;
import common.repo.AssetResServPartyDetails_Repo;

@Service("assetMaintenanceSchdDoServiceServ")
public class AssetMainSchdDoService_Service implements I_AssetMainSchdDoService_Service {
	private static final Logger logger = LoggerFactory.getLogger(AssetMainSchdDoService_Service.class);

	@Autowired
	private AssetMaintenanceSchdDetailsRead_Repo assetMainSchdDetailsReadRepo;

	@Autowired
	private AssetMaintenanceSchdDetailsCUD_Repo assetMainSchdDetailsCUDRepo;

	@Autowired
	private AssetResServPartyDetails_Repo assetResServPartyDetailsRepo;

	@Autowired
	private AssetMaster_Repo assetMasterRepo;

	@Value("${topic.name.request}")
	private String topicmyRequest;

	@Value("${topic.name.requestresponse}")
	private String myRequestResponse;

	@Autowired
	private KafkaTemplate<String, ServiceRequestMaster_DTO> kafkaTemplateRequest;

	@Scheduled(fixedRate = 3000)
	public void runBatch() {
		// For Each Asset - Get All Res Prod Servs
		CopyOnWriteArrayList<AssetMaintenanceSchdDetail> assetMaintenanceSchdDetails = assetMainSchdDetailsReadRepo
				.getAssetsNotWIP();

		AssetMaintenanceSchdDetail assetMaintenanceSchdDetail = null;

		if (assetMaintenanceSchdDetails != null && assetMaintenanceSchdDetails.size() > 0) {
			logger.info("running for size :"+assetMaintenanceSchdDetails.size());
			for (int i = 0; i < assetMaintenanceSchdDetails.size(); i++) {
				assetMaintenanceSchdDetail = assetMaintenanceSchdDetails.get(i);
				sendToServiceManager(assetMaintenanceSchdDetail);
			}
		}
		return;
	}

	public synchronized void sendToServiceManager(AssetMaintenanceSchdDetail assetMaintenanceSchdDetail) {
		ServiceRequestMaster_DTO serviceRequestMaster_DTO = new ServiceRequestMaster_DTO();
		Long ownerSeqNo = assetMasterRepo.getPartyForAsset(assetMaintenanceSchdDetail.getAssetSeqNo());
		Long partySeqNo = assetResServPartyDetailsRepo.getPartyForResProd(assetMaintenanceSchdDetail.getRessrvprdSeqNo());
		serviceRequestMaster_DTO.setFrPartySeqNo(ownerSeqNo);
		serviceRequestMaster_DTO.setReferenceSeqNo(assetMaintenanceSchdDetail.getScheduleSeqNo());
		serviceRequestMaster_DTO.setToPartySeqNo(partySeqNo);
		
		ListenableFuture<SendResult<String, ServiceRequestMaster_DTO>> future = kafkaTemplateRequest.send(topicmyRequest,serviceRequestMaster_DTO);
		future.addCallback(new ListenableFutureCallback<SendResult<String, ServiceRequestMaster_DTO>>() {

			@Override
			public void onSuccess(final SendResult<String, ServiceRequestMaster_DTO> message) 
			{
				logger.info("ruleline :" + message.getProducerRecord().value().getReferenceSeqNo());
				assetMainSchdDetailsCUDRepo.setAssetWIP(message.getProducerRecord().value().getReferenceSeqNo());
	//			logger.info("updated schedule for ruleline :" + message.getProducerRecord().value().getReferenceSeqNo());
			}

			@Override
			public void onFailure(final Throwable throwable) {
				logger.error("unable to send message= ", throwable);
			}
		});
		return;
	}
}
