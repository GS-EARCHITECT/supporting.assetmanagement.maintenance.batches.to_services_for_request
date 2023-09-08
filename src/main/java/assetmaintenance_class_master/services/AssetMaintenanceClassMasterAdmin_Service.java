package assetmaintenance_class_master.services;

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
import assetmaintenance_class_master.model.dto.AssetMaintenanceClassMaster_DTO;
import assetmaintenance_class_master.model.master.AssetMaintenanceClassMaster;
import assetmaintenance_class_master.model.master.AssetMaintenanceClassMasterPK;
import assetmaintenance_class_master.model.repo.AssetMaintenanceClassMasterAdmin_Repo;

@Service("assetMaintClassServ")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMaintenanceClassMasterAdmin_Service implements I_AssetMaintenanceClassMasterAdmin_Service 
{
//	private static final Logger logger = LoggerFactory.getLogger(AssetMaintenanceClassMasterService.class);
	
	@Autowired
    private AssetMaintenanceClassMasterAdmin_Repo assetMaintenanceClassMasterAdminRepo;
		
	public AssetMaintenanceClassMaster_DTO newAssetMaintenanceClass(AssetMaintenanceClassMaster_DTO lMaster) 
	{
		AssetMaintenanceClassMasterPK assetMaintenanceClassMasterPK = new AssetMaintenanceClassMasterPK();
		assetMaintenanceClassMasterPK.setMaintenanceClassSeqNo(lMaster.getMaintenanceClassSeqNo());
		assetMaintenanceClassMasterPK.setPartySeqNo(lMaster.getPartySeqNo());
		assetMaintenanceClassMasterPK.setResourceSeqNo(lMaster.getResourceSeqNo());
		
		if(!assetMaintenanceClassMasterAdminRepo.existsById(assetMaintenanceClassMasterPK)) 
		{
		lMaster = getAssetMaintenanceClassMaster_DTO(assetMaintenanceClassMasterAdminRepo.save(this.setAssetMaintenanceClassMaster(lMaster)));
		}
		return lMaster;
	}

	public ArrayList<AssetMaintenanceClassMaster_DTO> getAllAssetMaintenanceClasses() 
	{
		ArrayList<AssetMaintenanceClassMaster> assetServicePlanList = (ArrayList<AssetMaintenanceClassMaster>) assetMaintenanceClassMasterAdminRepo.findAll();
		ArrayList<AssetMaintenanceClassMaster_DTO> lMasterss = assetServicePlanList != null ? this.getAssetMaintenanceClassMasterDtos(assetServicePlanList) : null;
		return lMasterss;
	}
		
	public ArrayList<AssetMaintenanceClassMaster_DTO> getSelectClasses(ArrayList<AssetMaintenanceClassMasterPK> assetMaintenanceClassMasterPKs) 
	{
		ArrayList<AssetMaintenanceClassMaster> assetServicePlanList = (ArrayList<AssetMaintenanceClassMaster>) assetMaintenanceClassMasterAdminRepo.findAllById(assetMaintenanceClassMasterPKs);
		ArrayList<AssetMaintenanceClassMaster_DTO> lMasterss = assetServicePlanList != null ? this.getAssetMaintenanceClassMasterDtos(assetServicePlanList) : null;		
		return lMasterss;
	}

	public ArrayList<AssetMaintenanceClassMaster_DTO> getSelectClassesByResources(ArrayList<Long> ids) 
	{
		ArrayList<AssetMaintenanceClassMaster> assetServicePlanList = (ArrayList<AssetMaintenanceClassMaster>) assetMaintenanceClassMasterAdminRepo.getSelectClassesByResources(ids);
		ArrayList<AssetMaintenanceClassMaster_DTO> lMasterss = assetServicePlanList != null ? this.getAssetMaintenanceClassMasterDtos(assetServicePlanList) : null;
		return lMasterss;
	}
	
	public ArrayList<AssetMaintenanceClassMaster_DTO> getSelectClassesByParties(ArrayList<Long> ids) 
	{
		ArrayList<AssetMaintenanceClassMaster> assetServicePlanList = (ArrayList<AssetMaintenanceClassMaster>) assetMaintenanceClassMasterAdminRepo.getSelectClassesByParties(ids);
		ArrayList<AssetMaintenanceClassMaster_DTO> lMasterss = assetServicePlanList != null ? this.getAssetMaintenanceClassMasterDtos(assetServicePlanList) : null;
		return lMasterss;
	}
	
	public void updAssetMaintenanceClass(AssetMaintenanceClassMaster_DTO lMaster) {

		AssetMaintenanceClassMasterPK assetMaintenanceClassMasterPK = new AssetMaintenanceClassMasterPK();
		assetMaintenanceClassMasterPK.setMaintenanceClassSeqNo(lMaster.getMaintenanceClassSeqNo());
		assetMaintenanceClassMasterPK.setPartySeqNo(lMaster.getPartySeqNo());
		assetMaintenanceClassMasterPK.setResourceSeqNo(lMaster.getResourceSeqNo());
		
		if(assetMaintenanceClassMasterAdminRepo.existsById(assetMaintenanceClassMasterPK)) 
		{
		assetMaintenanceClassMasterAdminRepo.save(this.setAssetMaintenanceClassMaster(lMaster));
		}
		return;
	}

	public void delAllAssetMaintenanceClasses() 
	{
		assetMaintenanceClassMasterAdminRepo.deleteAll();
	}

	public void delSelectClassesByResources(ArrayList<Long> ids) 
	{
		assetMaintenanceClassMasterAdminRepo.delSelectClassesByResources(ids);
	}

	public void delSelectClassesByParties(ArrayList<Long> ids) 
	{
		assetMaintenanceClassMasterAdminRepo.delSelectClassesByParties(ids);
	}
	
	public void delSelectClasses(ArrayList<AssetMaintenanceClassMasterPK> assetMaintenanceClassMasterPKs) 
	{
		assetMaintenanceClassMasterAdminRepo.deleteAllById(assetMaintenanceClassMasterPKs);;
	}

	private ArrayList<AssetMaintenanceClassMaster_DTO> getAssetMaintenanceClassMasterDtos(ArrayList<AssetMaintenanceClassMaster> lMasters) {
		AssetMaintenanceClassMaster_DTO lDTO = null;
		ArrayList<AssetMaintenanceClassMaster_DTO> lMasterDTOs = new ArrayList<AssetMaintenanceClassMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = this.getAssetMaintenanceClassMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetMaintenanceClassMaster_DTO getAssetMaintenanceClassMaster_DTO(AssetMaintenanceClassMaster lMaster) 
	{
		AssetMaintenanceClassMaster_DTO lDTO = new AssetMaintenanceClassMaster_DTO();
		lDTO.setMaintenanceClassSeqNo(lMaster.getId().getMaintenanceClassSeqNo());
		lDTO.setPartySeqNo(lMaster.getId().getPartySeqNo());			
		lDTO.setResourceSeqNo(lMaster.getId().getResourceSeqNo());			
		return lDTO;
	}

	private AssetMaintenanceClassMaster setAssetMaintenanceClassMaster(AssetMaintenanceClassMaster_DTO lDTO) 
	{
		AssetMaintenanceClassMasterPK assetMaintenanceClassMasterPK = new AssetMaintenanceClassMasterPK();
		assetMaintenanceClassMasterPK.setMaintenanceClassSeqNo(lDTO.getMaintenanceClassSeqNo());
		assetMaintenanceClassMasterPK.setPartySeqNo(lDTO.getPartySeqNo());
		assetMaintenanceClassMasterPK.setResourceSeqNo(lDTO.getResourceSeqNo());
		AssetMaintenanceClassMaster lMaster = new AssetMaintenanceClassMaster();	
		lMaster.setId(assetMaintenanceClassMasterPK);
		return lMaster;
	}
	
}