package asset_schd_details;

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
import common.repo.AssetMainSchdMaster_Repo;
import common.repo.AssetMaster_Repo;
import common.repo.AssetResServPartyDetails_Repo;
import common.master.*;

@Service("assetMainSchdDetailsBatchServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMainSchdDetailsBatch_Service implements I_AssetMainSchdDetailsBatch_Service {
	private static final Logger logger = LoggerFactory.getLogger(AssetMainSchdDetailsBatch_Service.class);

	@Autowired
	private AssetResServPartyDetails_Repo assetResServPartyDetailsRepo;

	@Autowired
	private AssetMainSchdMaster_Repo assetMainSchdMasterRepo;

	@Autowired
	private AssetMainSchdDetailsRead_Repo assetMainSchdDetailsReadRepo;

	@Autowired
	private AssetMainSchdDetailsCUD_Repo assetMainSchdDetailsCUDRepo;
	
	@Autowired
	private AssetMaster_Repo assetMasterRepo;

	@Autowired
	private Executor asyncExecutor;

	@Scheduled(fixedRate = 3000)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public CompletableFuture<Void> runService() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			// For Each Asset - Get All Res Prod Servs
			CopyOnWriteArrayList<AssetMaster> assetMasters = assetMasterRepo.getAllAssets();

			logger.info("Assets Size : " + assetMasters.size());

			if (assetMasters != null && assetMasters.size() > 0) {
				Long resSeqNo = (long) 0;
				Long assSeqNo = (long) 0;
				AssetMainSchdDetail assetMainSchdDetail = null;
				AssetMainSchdDetailPK assetMainSchdDetailPK = null;
				
				// For each Asset
				for (int i = 0; i < assetMasters.size(); i++) 
				{
					logger.info("For Asset : " + assetMasters.get(i).getAssetSeqNo());
					resSeqNo = assetMasters.get(i).getResourceSeqNo();
					assSeqNo = assetMasters.get(i).getAssetSeqNo();

					// Get All Resprod details
					CopyOnWriteArrayList<Long> aList = assetResServPartyDetailsRepo.getResProdNumbersForResource(resSeqNo);
					logger.info("For Asset : " + assSeqNo+" ResProdNos Are : "+aList.size());
					
					if (aList != null && aList.size() > 0) 
					{
						CopyOnWriteArrayList<AssetMainSchdMaster> assetPrdSrvMasters = assetMainSchdMasterRepo.getSelectResProdServsByResProdServs(aList);
						logger.info("For ResProds Rules Are : " + assetPrdSrvMasters.size());

						for (int k = 0; k < assetPrdSrvMasters.size(); k++) 
						{
							if (!this.checkRuleForProdServInSchedules(assSeqNo,assetPrdSrvMasters.get(k).getId().getRessrvprdSeqNo(),assetPrdSrvMasters.get(k).getId().getRuleSeqNo())) 
							{
								assetMainSchdDetail = new AssetMainSchdDetail();
								assetMainSchdDetailPK = new AssetMainSchdDetailPK();
								assetMainSchdDetailPK.setAssetSeqNo(assSeqNo);
								assetMainSchdDetailPK.setRessrvprdSeqNo(assetPrdSrvMasters.get(k).getId().getRessrvprdSeqNo());
								assetMainSchdDetailPK.setRuleSeqNo(assetPrdSrvMasters.get(k).getId().getRuleSeqNo());
								assetMainSchdDetail.setId(assetMainSchdDetailPK);
								assetMainSchdDetail.setDoneflag('N');
								assetMainSchdDetail.setOkflag('N');
								assetMainSchdDetailsCUDRepo.save(assetMainSchdDetail);
								logger.info("Asset Created in Details : " + assSeqNo + "  PrdSrv : "+ assetPrdSrvMasters.get(k).getId().getRessrvprdSeqNo()+"  Rule : "+ assetPrdSrvMasters.get(k).getId().getRuleSeqNo());
							}
						}
					}
				}

			}
			return;
		}, asyncExecutor);
		return future;
	}

	public synchronized boolean checkRuleForProdServInSchedules(Long aSeqNo, Long resPrdSeqNo, Long ruleSeqNo) {
		boolean found = false;
		logger.info("Checking Rules in Schedules for asset : "+aSeqNo+" resprod :"+resPrdSeqNo+" rule:"+ruleSeqNo);
		
		if (assetMainSchdDetailsReadRepo.getCountOfSchdDetailsByResSrvProds(resPrdSeqNo, aSeqNo, ruleSeqNo) > 0) 
		{
		logger.info("found");
		found = true;
		}
		else
		{
		logger.info("Not found");	
		}

		return found;
	}

}