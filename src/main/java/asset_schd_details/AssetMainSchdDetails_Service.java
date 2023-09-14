package asset_schd_details;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import common.repo.AssetMainSchdDetailsCUD_Repo;
import common.repo.AssetMainSchdDetailsRead_Repo;
import common.repo.AssetMainSchdMaster_Repo;
import common.repo.AssetMaster_Repo;
import common.repo.AssetResServPartyDetails_Repo;
import common.master.*;

@Service("assetMainSchdDetailsBatchServ")
public class AssetMainSchdDetails_Service implements I_AssetMainSchdDetails_Service {
	private static final Logger logger = LoggerFactory.getLogger(AssetMainSchdDetails_Service.class);

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

	@Scheduled(fixedRate = 3000)	
	public void runService() {
			// For Each Asset - Get All Res Prod Servs
			CopyOnWriteArrayList<AssetMaster> assetMasters = assetMasterRepo.getAllAssets();

			logger.info("- running to ceate schdule details - ");

			if (assetMasters != null && assetMasters.size() > 0) {
				Long resSeqNo = (long) 0;
				Long assSeqNo = (long) 0;
				AssetMainSchdDetail assetMainSchdDetail = null;
				AssetMainSchdDetailPK assetMainSchdDetailPK = null;
				
				// For each Asset
				for (int i = 0; i < assetMasters.size(); i++) 
				{
					resSeqNo = assetMasters.get(i).getResourceSeqNo();
					assSeqNo = assetMasters.get(i).getAssetSeqNo();

					// Get All Resprod details
					CopyOnWriteArrayList<Long> aList = assetResServPartyDetailsRepo.getResProdNumbersForResource(resSeqNo);
										
					if (aList != null && aList.size() > 0) 
					{
						CopyOnWriteArrayList<AssetMainSchdMaster> assetPrdSrvMasters = assetMainSchdMasterRepo.getSelectResProdServsByResProdServs(aList);
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
								assetMainSchdDetail.setFrTime(assetPrdSrvMasters.get(k).getFrTime());
								assetMainSchdDetail.setToTime(assetPrdSrvMasters.get(k).getToTime());
								assetMainSchdDetailsCUDRepo.save(assetMainSchdDetail);
							}
						}
					}
				}

			}
			return;
	//	}, asyncExecutor);
	//	return future;
	}

	public synchronized boolean checkRuleForProdServInSchedules(Long aSeqNo, Long resPrdSeqNo, Long ruleSeqNo) {
		boolean found = false;
				
		if (assetMainSchdDetailsReadRepo.getCountOfSchdDetailsByResSrvProds(resPrdSeqNo, aSeqNo, ruleSeqNo) > 0) 
		{
		found = true;
		}
		
		return found;
	}

}