package resservprod.service;

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
import common.repo.AssetMainSchdDetails_Repo;
import common.repo.AssetMainSchdMaster_Repo;
import common.repo.AssetMaintenanceMaster_Repo;
import common.repo.AssetMaster_Repo;
import common.repo.AssetResServPartyDetails_Repo;
import common.master.*;
import common.dto.*;

@Service("assetMaintenanceResProdServRulesAssetsBatchServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMaintenanceResProdServRulesAssetsBatch_Service
		implements I_AssetMaintenanceResProdServRulesAssetsBatch_Service {
	private static final Logger logger = LoggerFactory
			.getLogger(AssetMaintenanceResProdServRulesAssetsBatch_Service.class);

	@Autowired
	private AssetResServPartyDetails_Repo assetResServPartyDetailsRepo;

	@Autowired
	private AssetMainSchdMaster_Repo assetMainSchdMasterRepo;

	@Autowired
	private AssetMaintenanceMaster_Repo assetMaintenanceMasterRepo;

	@Autowired
	private AssetMainSchdDetails_Repo assetMainSchdDetailsRepo;

	@Autowired
	private AssetMaster_Repo assetMasterRepo;

	@Autowired
	private Executor asyncExecutor;

	//@Scheduled(fixedRate = 3000)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public CompletableFuture<Void> runBatch() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			// For Each Asset - Get All Res Prod Servs
			CopyOnWriteArrayList<AssetMaster> assetMasters = assetMasterRepo.getAllAssets();

			logger.info("Assets Size : " + assetMasters.size());

			if (assetMasters != null && assetMasters.size() > 0) {
				Long resSeqNo = (long) 0;
				Long assSeqNo = (long) 0;
				CopyOnWriteArrayList<AssetMainSchdMaster_DTO> globalAssetMainSchdMaster_DTOs = new CopyOnWriteArrayList<AssetMainSchdMaster_DTO>();
				AssetMainSchdMaster_DTO assetMainSchdMaster_DTO = null;
				CopyOnWriteArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = null;
				CopyOnWriteArrayList<AssetMaintenanceSchdDetail> assetMaintenanceSchdDetails = null;
				CopyOnWriteArrayList<AssetMainSchdMaster_DTO> assetMainSchdMaster_DTOs = null;

				// For each Asset
				for (int i = 0; i < assetMasters.size(); i++) 
				{
					resSeqNo = assetMasters.get(i).getResourceSeqNo();
					assSeqNo = assetMasters.get(i).getAssetSeqNo();
					assetMainSchdMaster_DTOs = new CopyOnWriteArrayList<AssetMainSchdMaster_DTO>();
					assetMaintenanceMasters = assetMaintenanceMasterRepo.getAssetsByAsset(assSeqNo);

					// For each Asset - For each maintenance record
					for (int j = 0; j < assetMaintenanceMasters.size(); j++) 
					{
						// For a maintenance record - add Each Rule in schedule masters buffer
						assetMaintenanceSchdDetails = assetMainSchdDetailsRepo.getSelectSchedulesByMaintenance(assetMaintenanceMasters.get(j).getAssetMaintenanceSeqNo());
						if (assetMaintenanceSchdDetails != null && assetMaintenanceSchdDetails.size() > 0) 
						{
							for (int k = 0; k < assetMaintenanceSchdDetails.size(); k++) 
							{
								if (!this.checkRuleForProdServInSchedules(assetMainSchdMaster_DTOs, assSeqNo, assetMaintenanceSchdDetails.get(k).getRessrvprdSeqNo(), assetMaintenanceSchdDetails.get(k).getRuleSeqNo())) 
								{
									assetMainSchdMaster_DTO = new AssetMainSchdMaster_DTO();
									assetMainSchdMaster_DTO.setAsetSeqNo(assSeqNo);
									assetMainSchdMaster_DTO.setRessrvprdSeqNo(assetMaintenanceSchdDetails.get(k).getRessrvprdSeqNo());
									assetMainSchdMaster_DTO.setRuleSeqNo(assetMaintenanceSchdDetails.get(k).getRuleSeqNo());
									assetMainSchdMaster_DTOs.add(assetMainSchdMaster_DTO);
									logger.info("Local Asset in loop : " + assSeqNo + "  Maintenance Schd Size : "	+ assetMainSchdMaster_DTOs.size());
								}
							}
						}
					}
					assetMainSchdMaster_DTOs = this.checkScheduledForProdServ(assetMainSchdMaster_DTOs, resSeqNo);
					globalAssetMainSchdMaster_DTOs = this.addToGlobalList(assetMainSchdMaster_DTOs,
							globalAssetMainSchdMaster_DTOs);
					logger.info("Local Asset : " + assSeqNo + "  Maintenance Schd Size : "
							+ assetMainSchdMaster_DTOs.size());
					logger.info("Global Asset : " + assSeqNo + "  Maintenance Schd Size : "
							+ globalAssetMainSchdMaster_DTOs.size());
				}
				this.processBatch(globalAssetMainSchdMaster_DTOs);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public synchronized boolean checkRuleForProdServInSchedules(CopyOnWriteArrayList<AssetMainSchdMaster_DTO> aDetails,
			Long aSeqNo, Long resPrdSeqNo, Long ruleSeqNo) {
		boolean found = false;
		logger.info("Checking Rules in SSchedules");
		for (int a = 0; a < aDetails.size(); a++) {
			if (aDetails.get(a).getAsetSeqNo() == aSeqNo && aDetails.get(a).getRessrvprdSeqNo() == resPrdSeqNo
					&& aDetails.get(a).getRuleSeqNo() == ruleSeqNo) {
				found = true;
				logger.info("Found in Schedule Asset : " + aSeqNo + "  ProdServ : " + resPrdSeqNo + "  Rule : "
						+ ruleSeqNo);
			} else {
				logger.info("Not Found in Schedule Asset : " + aSeqNo + "  ProdServ : " + resPrdSeqNo + "  Rule : "
						+ ruleSeqNo);
			}
		}
		return found;
	}

	public synchronized CopyOnWriteArrayList<AssetMainSchdMaster_DTO> checkScheduledForProdServ(
			CopyOnWriteArrayList<AssetMainSchdMaster_DTO> aDetails, Long resSeqNo) {

		CopyOnWriteArrayList<AssetResServPartyDetail> assetResServPartyDetails = assetResServPartyDetailsRepo
				.getDetailsForResource(resSeqNo);
		logger.info("Checking Scheduled For ProdServ List Size : " + assetResServPartyDetails.size());

		CopyOnWriteArrayList<Long> resProdSeqNos = new CopyOnWriteArrayList<Long>();

		for (int d = 0; d < assetResServPartyDetails.size(); d++) 
		{
		resProdSeqNos.add(assetResServPartyDetails.get(d).getRessrvprdSeqNo());
		}

		CopyOnWriteArrayList<AssetMainSchdMaster> assetPrdSrvMasters = assetMainSchdMasterRepo
				.getSelectResProdServsByResProdServs(resProdSeqNos);
		
		logger.info("Checking Scheduled For ProdServ Rules Size : " + assetPrdSrvMasters.size());

		for (int b = 0; b < assetPrdSrvMasters.size(); b++) {
			for (int a = 0; a < aDetails.size(); a++) {
				if (assetPrdSrvMasters.get(b).getId().getRessrvprdSeqNo() == aDetails.get(a).getRessrvprdSeqNo()
						&& assetPrdSrvMasters.get(b).getId().getRuleSeqNo() == aDetails.get(a).getRuleSeqNo()) {
					aDetails.get(a).setScheduledFlag(true);
				} else {
					logger.info("Not yet Scheduled Asset : " + aDetails.get(b).getAsetSeqNo() + "  ProdServ : "
							+ aDetails.get(b).getRessrvprdSeqNo() + "  Rule : " + aDetails.get(b).getRuleSeqNo());
				}

			}
		}
		return aDetails;
	}

	public synchronized CopyOnWriteArrayList<AssetMainSchdMaster_DTO> addToGlobalList(
			CopyOnWriteArrayList<AssetMainSchdMaster_DTO> lDetails,
			CopyOnWriteArrayList<AssetMainSchdMaster_DTO> gDetails) {

		for (int c = 0; c < lDetails.size(); c++) {

			if (!lDetails.get(c).getScheduledFlag()) {
				gDetails.add(lDetails.get(c));
			}
		}
		return gDetails;
	}

	public synchronized void processBatch(CopyOnWriteArrayList<AssetMainSchdMaster_DTO> gDetails) {
	}

	public synchronized void someWork(CopyOnWriteArrayList<AssetMainSchdMaster_DTO> gDetails) {

		/*
		 * Timestamp currDate = null; Long datetime = null; Integer runSeqNo = 0;
		 * Timestamp sysDtTm = null; Integer nextRunNo = 0; Timestamp lastDtTm = null;
		 * AssetMainSchdDetailsPK assetMainSchdDetailsPK = null; AssetMainSchdDetails
		 * assetMainSchdDetails = null;
		 * 
		 * if (assetSchdMasters != null) { for (int i = 0; i < assetSchdMasters.size();
		 * i++) { if
		 * (!assetSchdMasters.get(i).getLastRunNo().equals(assetSchdMasters.get(i).
		 * getNoOfOccurences())) { lastDtTm = assetSchdMasters.get(i).getLastRunDttm();
		 * runSeqNo = assetSchdMasters.get(i).getLastRunNo(); currDate =
		 * DateUtil.addDays(lastDtTm, assetSchdMasters.get(i).getLapseDays(), 0, 0, 0);
		 * datetime = System.currentTimeMillis(); sysDtTm = new Timestamp(datetime); if
		 * (sysDtTm.getTime() > currDate.getTime()) { assetMainSchdDetailsPK = new
		 * AssetMainSchdDetailsPK(); assetMainSchdDetails = new AssetMainSchdDetails();
		 * nextRunNo = assetMainSchdDetailsRepo
		 * .getLastRunNoForSchd(assetSchdMasters.get(i).getLastRunNo());
		 * assetMainSchdDetailsPK.setRunNo(nextRunNo + 1); assetMainSchdDetailsPK
		 * .setAssetMaintenanceSeqNo(assetSchdMasters.get(i).getId().
		 * getAssetMaintenanceSeqNo());
		 * assetMainSchdDetails.setId(assetMainSchdDetailsPK);
		 * assetMainSchdDetails.setRunDate(sysDtTm);
		 * assetMainSchdDetails.setIsProcessed('Y'); assetMainSchdDetails.setRemark("");
		 * assetMainSchdDetails.setStatus("");
		 * assetMainSchdMasterRepo.updateLastDtTm(runSeqNo, currDate); } }
		 * 
		 * } }
		 */
	}
}