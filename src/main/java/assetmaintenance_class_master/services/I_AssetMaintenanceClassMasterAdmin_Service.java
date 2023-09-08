package assetmaintenance_class_master.services;

import java.util.ArrayList;
import assetmaintenance_class_master.model.dto.AssetMaintenanceClassMaster_DTO;
import assetmaintenance_class_master.model.master.AssetMaintenanceClassMasterPK;

public interface I_AssetMaintenanceClassMasterAdmin_Service
{
    public AssetMaintenanceClassMaster_DTO newAssetMaintenanceClass(AssetMaintenanceClassMaster_DTO assetMaintenanceClassMasterDTO);
    public ArrayList<AssetMaintenanceClassMaster_DTO> getAllAssetMaintenanceClasses();
    public ArrayList<AssetMaintenanceClassMaster_DTO> getSelectClasses(ArrayList<AssetMaintenanceClassMasterPK> assetMaintenanceClassMasterPKs);
    public ArrayList<AssetMaintenanceClassMaster_DTO> getSelectClassesByResources(ArrayList<Long> ids);
    public ArrayList<AssetMaintenanceClassMaster_DTO> getSelectClassesByParties(ArrayList<Long> ids);
    public void updAssetMaintenanceClass(AssetMaintenanceClassMaster_DTO assetMaintenanceClassMasterDTO);
    public void delAllAssetMaintenanceClasses();
    public void delSelectClasses(ArrayList<AssetMaintenanceClassMasterPK> assetMaintenanceClassMasterPKs);
    public void delSelectClassesByResources( ArrayList<Long> ids);
    public void delSelectClassesByParties( ArrayList<Long> ids);
}