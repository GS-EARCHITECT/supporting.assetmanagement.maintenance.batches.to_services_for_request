package assetmain_schd_master.services;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import assetmain_schd_master.model.dto.AssetMainSchdMaster_DTO;
import assetmain_schd_master.model.master.AssetMainSchdMaster;
import assetmain_schd_master.model.master.AssetMainSchdMasterPK;
import assetmain_schd_master.model.repo.AssetMainSchdMasterAdmin_Repo;

@Service("assetMainSchdMasterAdminServ")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMainSchdMasterAdmin_Service implements I_AssetMainSchdMasterAdmin_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetMainSchdMasterService.class);

	@Autowired
	private AssetMainSchdMasterAdmin_Repo assetMainSchdMasterAdminRepo;

	public AssetMainSchdMaster_DTO newAssetMainSchd(AssetMainSchdMaster_DTO lMaster) {
		AssetMainSchdMasterPK assetMainSchdMasterPK = new AssetMainSchdMasterPK();
		assetMainSchdMasterPK.setRessrvprdSeqNo(lMaster.getRessrvprdSeqNo());
		assetMainSchdMasterPK.setRuleSeqNo(lMaster.getRuleSeqNo());

		if (!assetMainSchdMasterAdminRepo.existsById(assetMainSchdMasterPK)) {
			lMaster = getAssetMainSchdMaster_DTO(
					assetMainSchdMasterAdminRepo.save(this.setAssetMainSchdMaster(lMaster)));
		}
		return lMaster;
	}

	public ArrayList<AssetMainSchdMaster_DTO> getAllAssetMainSchds() {
		ArrayList<AssetMainSchdMaster> assetServicePlanList = (ArrayList<AssetMainSchdMaster>) assetMainSchdMasterAdminRepo
				.findAll();
		ArrayList<AssetMainSchdMaster_DTO> lMasterss = assetServicePlanList != null
				? this.getAssetMainSchdMasterDtos(assetServicePlanList)
				: null;
		return lMasterss;
	}

	public ArrayList<AssetMainSchdMaster_DTO> getSelectSchds(ArrayList<AssetMainSchdMasterPK> ids) {
		ArrayList<AssetMainSchdMaster> assetServicePlanList = (ArrayList<AssetMainSchdMaster>) assetMainSchdMasterAdminRepo
				.findAllById(ids);
		ArrayList<AssetMainSchdMaster_DTO> lMasterss = assetServicePlanList != null
				? this.getAssetMainSchdMasterDtos(assetServicePlanList)
				: null;
		return lMasterss;
	}

	public void updAssetMainSchd(AssetMainSchdMaster_DTO lMaster) {
		AssetMainSchdMasterPK assetMainSchdMasterPK = new AssetMainSchdMasterPK();
		assetMainSchdMasterPK.setRessrvprdSeqNo(lMaster.getRessrvprdSeqNo());
		assetMainSchdMasterPK.setRuleSeqNo(lMaster.getRuleSeqNo());

		if (assetMainSchdMasterAdminRepo.existsById(assetMainSchdMasterPK)) {
			assetMainSchdMasterAdminRepo.save(this.setAssetMainSchdMaster(lMaster));
		}
		return;
	}

	public void delAllAssetMainSchds() {
		assetMainSchdMasterAdminRepo.deleteAll();
	}

	public void delSelectSchds(ArrayList<AssetMainSchdMasterPK> ids) {
		assetMainSchdMasterAdminRepo.deleteAllById(ids);
	}

	private ArrayList<AssetMainSchdMaster_DTO> getAssetMainSchdMasterDtos(ArrayList<AssetMainSchdMaster> lMasters) {
		AssetMainSchdMaster_DTO lDTO = null;
		ArrayList<AssetMainSchdMaster_DTO> lMasterDTOs = new ArrayList<AssetMainSchdMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = this.getAssetMainSchdMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetMainSchdMaster_DTO getAssetMainSchdMaster_DTO(AssetMainSchdMaster lMaster) {
		AssetMainSchdMaster_DTO lDTO = new AssetMainSchdMaster_DTO();
		lDTO.setLapseDays(lMaster.getLapseDays());
		lDTO.setNoOfOccurences(lMaster.getNoOfOccurences());
		lDTO.setRessrvprdSeqNo(lMaster.getId().getRessrvprdSeqNo());
		lDTO.setRuleSeqNo(lMaster.getId().getRuleSeqNo());
		return lDTO;
	}

	private AssetMainSchdMaster setAssetMainSchdMaster(AssetMainSchdMaster_DTO lDTO) {
		AssetMainSchdMaster lMaster = new AssetMainSchdMaster();
		AssetMainSchdMasterPK assetMainSchdMasterPK = new AssetMainSchdMasterPK();
		assetMainSchdMasterPK.setRessrvprdSeqNo(lDTO.getRessrvprdSeqNo());
		assetMainSchdMasterPK.setRuleSeqNo(lDTO.getRuleSeqNo());
		lMaster.setLapseDays(lDTO.getLapseDays());
		lMaster.setNoOfOccurences(lDTO.getNoOfOccurences());
		lMaster.setId(assetMainSchdMasterPK);
		return lMaster;
	}

}