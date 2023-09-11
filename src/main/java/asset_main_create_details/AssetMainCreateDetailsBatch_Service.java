package asset_main_create_details;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.repo.AssetMainSchdDetailsCUD_Repo;
import common.repo.AssetMainSchdDetailsRead_Repo;
import common.repo.AssetMainSchdDetails_Repo;
import common.master.*;

@Service("assetMainCreateDetailsBatchServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMainCreateDetailsBatch_Service implements I_AssetMainCreateDetailsBatch_Service {
	private static final Logger logger = LoggerFactory.getLogger(AssetMainCreateDetailsBatch_Service.class);

	@Autowired
	private AssetMainSchdDetailsRead_Repo assetMainSchdDetailsReadRepo;

	@Autowired
	private AssetMainSchdDetailsCUD_Repo assetMainSchdDetailsCUDRepo;

	@Autowired
	private AssetMainSchdDetails_Repo assetMainSchdDetailsRepo;

	@Autowired
	private Executor asyncExecutor;

	@Scheduled(fixedRate = 3000)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public CompletableFuture<Void> runService() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			// Get All Assets Not Scheduled
			CopyOnWriteArrayList<AssetMainSchdDetail> assetMainSchdDetails = assetMainSchdDetailsReadRepo.getAssetSchdDetailsNotDone();
			AssetMaintenanceSchdDetail assetMaintenanceSchdDetail = null;
			AssetMainSchdDetailPK assetMainSchdDetailPK = null;

			for (int i = 0; i < assetMainSchdDetails.size(); i++) 
			{
				logger.info("- Trying To Save Maintenance Schedule -");
				assetMaintenanceSchdDetail = new AssetMaintenanceSchdDetail();
				assetMainSchdDetailPK = new AssetMainSchdDetailPK();
				assetMainSchdDetailPK.setAssetSeqNo(assetMainSchdDetails.get(i).getId().getAssetSeqNo());
				assetMainSchdDetailPK.setRessrvprdSeqNo(assetMainSchdDetails.get(i).getId().getRessrvprdSeqNo());
				assetMainSchdDetailPK.setRuleSeqNo(assetMainSchdDetails.get(i).getId().getRuleSeqNo());
				assetMaintenanceSchdDetail = new AssetMaintenanceSchdDetail();
				assetMaintenanceSchdDetail.setDoneflag('N');
				assetMaintenanceSchdDetail.setOkflag('Y');
				assetMaintenanceSchdDetail.setRessrvprdSeqNo(assetMainSchdDetails.get(i).getId().getRessrvprdSeqNo());
				assetMaintenanceSchdDetail.setRuleSeqNo(assetMainSchdDetails.get(i).getId().getRuleSeqNo());
				assetMaintenanceSchdDetail.setScheduleId("");
				assetMaintenanceSchdDetail.setWipflag('N');
				assetMaintenanceSchdDetail = assetMainSchdDetailsRepo.save(assetMaintenanceSchdDetail);
				assetMainSchdDetailsCUDRepo.setAssetDone(assetMainSchdDetails.get(i).getId().getAssetSeqNo(),assetMainSchdDetails.get(i).getId().getRessrvprdSeqNo(),assetMainSchdDetails.get(i).getId().getRuleSeqNo());
				logger.info("Maintenance Schedule Seq No Is :"+assetMaintenanceSchdDetail.getScheduleSeqNo());
				logger.info("- Added Maintenance Schedule -");
			}

			return;
		}, asyncExecutor);
		return future;
	}

}