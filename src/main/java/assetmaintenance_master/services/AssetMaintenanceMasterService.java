package assetmaintenance_master.services;

import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import assetmaintenance_master.model.AssetMaintenanceMaster;
import assetmaintenance_master.model.AssetMaintenanceMasterDTO;
import assetmaintenance_master.model.AssetMaintenanceMasterRepo;

@Service("assetMaintServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMaintenanceMasterService implements I_AssetMaintenanceMasterService 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetMaintenanceMasterService.class);
	
	@Autowired
    private AssetMaintenanceMasterRepo assetMaintRepo;
		
	public AssetMaintenanceMasterDTO newAssetMaintenanceMaster(AssetMaintenanceMasterDTO lMasters) {
		AssetMaintenanceMaster accountMaster = assetMaintRepo.save(this.setAssetMaintenanceMaster(lMasters));
		lMasters = getAssetMaintenanceMasterDTO(accountMaster);
		return lMasters;
	}
	
	public ArrayList<AssetMaintenanceMasterDTO> getAssetMaintenanceMastersByCate(Integer accountCateSeqNo) {
		Optional<ArrayList<AssetMaintenanceMaster>> accountMasters = assetMaintRepo.getAssetMaintenancesByCategory(accountCateSeqNo);
		ArrayList<AssetMaintenanceMasterDTO> lMasters = null;
		
		if (accountMasters.isPresent()) {
			lMasters = accountMasters != null ? this.getAssetMaintenanceMasterDtos(accountMasters.get()) : null;
		}
		return lMasters;
	}
	
	public ArrayList<AssetMaintenanceMasterDTO> getAllAssetMaintenanceMasters() {
		ArrayList<AssetMaintenanceMaster> accountList = (ArrayList<AssetMaintenanceMaster>) assetMaintRepo.findAll();
		ArrayList<AssetMaintenanceMasterDTO> lMasterss = new ArrayList<AssetMaintenanceMasterDTO>();
		lMasterss = accountList != null ? this.getAssetMaintenanceMasterDtos(accountList) : null;
		return lMasterss;
	}

	public ArrayList<AssetMaintenanceMasterDTO> getSelectAssetMaintenanceMasters(ArrayList<Integer> accountSeqNos) {
		ArrayList<AssetMaintenanceMasterDTO> lMasterss = new ArrayList<AssetMaintenanceMasterDTO>();
		Optional<AssetMaintenanceMaster> accountMaster = null;
		AssetMaintenanceMasterDTO AssetMaintenanceMasterDTO = null;

		for (int i = 0; i < accountSeqNos.size(); i++) {
			accountMaster = assetMaintRepo.findById(accountSeqNos.get(i));
			if (accountMaster.isPresent() && accountMaster != null) {
				AssetMaintenanceMasterDTO = this.getAssetMaintenanceMasterDTO(accountMaster.get());
				lMasterss.add(AssetMaintenanceMasterDTO);
			}
		}
		return lMasterss;
	}

	public AssetMaintenanceMasterDTO getAssetMaintenanceMasterById(Integer accountSeqNo) {
		Optional<AssetMaintenanceMaster> accountMaster = assetMaintRepo.findById(accountSeqNo);
		AssetMaintenanceMasterDTO lMasters = null;
		if (accountMaster.isPresent()) {
			lMasters = accountMaster != null ? this.getAssetMaintenanceMasterDTO(accountMaster.get()) : null;
		}
		return lMasters;
	}

	public void updAssetMaintenanceMaster(AssetMaintenanceMasterDTO lMasters) {

		AssetMaintenanceMaster accountMaster = this.setAssetMaintenanceMaster(lMasters);
		if (assetMaintRepo.existsById(lMasters.getAssetMaintenanceSeqNo())) {
			accountMaster.setAssetMaintenanceCateSeqNo(lMasters.getAssetMaintenanceSeqNo());
			assetMaintRepo.save(accountMaster);
		}
	}

	public void delAssetMaintenanceMaster(Integer accountSeqNo) {
		if (assetMaintRepo.existsById(accountSeqNo)) {
			assetMaintRepo.deleteById(accountSeqNo);
		}
	}
	
	public void delAllAssetMaintenanceMasters() {
		assetMaintRepo.deleteAll();
	}

	public void delSelectAssetMaintenanceMasters(ArrayList<Integer> accountSeqNos) {

		for (int i = 0; i < accountSeqNos.size(); i++) {
			if (assetMaintRepo.existsById(accountSeqNos.get(i))) {
				assetMaintRepo.deleteById(accountSeqNos.get(i));
			}

		}

	}

	private ArrayList<AssetMaintenanceMasterDTO> getAssetMaintenanceMasterDtos(ArrayList<AssetMaintenanceMaster> lMasters) {
		AssetMaintenanceMasterDTO lDTO = null;
		ArrayList<AssetMaintenanceMasterDTO> lMasterDTOs = new ArrayList<AssetMaintenanceMasterDTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = new AssetMaintenanceMasterDTO();
			lDTO.setMaintenanceCateSeqNo(lMasters.get(i).getMaintenanceCateSeqNo());						
			lDTO.setAssetMaintenanceSeqNo(lMasters.get(i).getAssetMaintenanceSeqNo());
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetMaintenanceMasterDTO getAssetMaintenanceMasterDTO(AssetMaintenanceMaster lMaster) {
		AssetMaintenanceMasterDTO lDTO = new AssetMaintenanceMasterDTO();
		lDTO.setMaintenanceCateSeqNo(lMaster.getMaintenanceCateSeqNo());						
		lDTO.setAssetMaintenanceSeqNo(lMaster.getAssetMaintenanceSeqNo());		
		return lDTO;
	}

	private AssetMaintenanceMaster setAssetMaintenanceMaster(AssetMaintenanceMasterDTO lDTO) {
		AssetMaintenanceMaster lMaster = new AssetMaintenanceMaster();		
		lMaster.setMaintenanceCateSeqNo(lDTO.getMaintenanceCateSeqNo());
		return lMaster;
	}
	
}