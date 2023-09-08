package assetmain_rule_master.services;

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
import assetmain_rule_master.model.dto.AssetMainRuleMaster_DTO;
import assetmain_rule_master.model.master.AssetMainRuleMaster;
import assetmain_rule_master.model.repo.AssetMainRuleMasterAdmin_Repo;

@Service("assetMainRuleMasterAdminServ")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMainRuleMasterAdmin_Service implements I_AssetMainRuleMasterAdmin_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AssetMainRuleMasterService.class);

	@Autowired
	private AssetMainRuleMasterAdmin_Repo assetMainRuleMasterAdminRepo;

	public AssetMainRuleMaster_DTO newAssetMainRule(AssetMainRuleMaster_DTO lMaster) {
		if (!assetMainRuleMasterAdminRepo.existsById(lMaster.getRuleSeqNo())) {
			lMaster = getAssetMainRuleMaster_DTO(
					assetMainRuleMasterAdminRepo.save(this.setAssetMainRuleMaster(lMaster)));
		}
		return lMaster;
	}

	public ArrayList<AssetMainRuleMaster_DTO> getAllAssetMainRules() {
		ArrayList<AssetMainRuleMaster> assetServicePlanList = (ArrayList<AssetMainRuleMaster>) assetMainRuleMasterAdminRepo
				.findAll();
		ArrayList<AssetMainRuleMaster_DTO> lMasterss = assetServicePlanList != null
				? this.getAssetMainRuleMasterDtos(assetServicePlanList)
				: null;
		return lMasterss;
	}

	public ArrayList<AssetMainRuleMaster_DTO> getSelectRules(ArrayList<Long> ids) {
		ArrayList<AssetMainRuleMaster> assetServicePlanList = (ArrayList<AssetMainRuleMaster>) assetMainRuleMasterAdminRepo
				.findAllById(ids);
		ArrayList<AssetMainRuleMaster_DTO> lMasterss = assetServicePlanList != null
				? this.getAssetMainRuleMasterDtos(assetServicePlanList)
				: null;
		return lMasterss;
	}

	public void updAssetMainRule(AssetMainRuleMaster_DTO lMaster) {

		if (assetMainRuleMasterAdminRepo.existsById(lMaster.getRuleSeqNo())) {
			assetMainRuleMasterAdminRepo.save(this.setAssetMainRuleMaster(lMaster));
		}
		return;
	}

	public void delAllAssetMainRules() {
		assetMainRuleMasterAdminRepo.deleteAll();
	}

	public void delSelectRules(ArrayList<Long> ids) {
		assetMainRuleMasterAdminRepo.deleteAllById(ids);
	}

	private ArrayList<AssetMainRuleMaster_DTO> getAssetMainRuleMasterDtos(ArrayList<AssetMainRuleMaster> lMasters) {
		AssetMainRuleMaster_DTO lDTO = null;
		ArrayList<AssetMainRuleMaster_DTO> lMasterDTOs = new ArrayList<AssetMainRuleMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = this.getAssetMainRuleMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetMainRuleMaster_DTO getAssetMainRuleMaster_DTO(AssetMainRuleMaster lMaster) {
		AssetMainRuleMaster_DTO lDTO = new AssetMainRuleMaster_DTO();
		lDTO.setDateSeqNo(lMaster.getDateSeqNo());
		lDTO.setDaysPlus(lMaster.getDaysPlus());
		lDTO.setDow(lMaster.getDow());
		lDTO.setEom(lMaster.getEom());
		lDTO.setEoy(lMaster.getEoy());
		lDTO.setNoOfOccurences(lMaster.getNoOfOccurences());
		lDTO.setRuleSeqNo(lMaster.getRuleSeqNo());
		return lDTO;
	}

	private AssetMainRuleMaster setAssetMainRuleMaster(AssetMainRuleMaster_DTO lDTO) {
		AssetMainRuleMaster lMaster = new AssetMainRuleMaster();
		lMaster.setDateSeqNo(lDTO.getDateSeqNo());
		lMaster.setDaysPlus(lDTO.getDaysPlus());
		lMaster.setDow(lDTO.getDow());
		lMaster.setEom(lDTO.getEom());
		lMaster.setEoy(lDTO.getEoy());
		lMaster.setNoOfOccurences(lDTO.getNoOfOccurences());
		return lMaster;
	}

}