package assetmaintenance_master.services;

import java.util.ArrayList;

import assetmaintenance_master.model.AssetMaintenanceMasterDTO;

public interface I_AssetMaintenanceMasterService
{
    abstract public AssetMaintenanceMasterDTO newAssetMaintenanceMaster(AssetMaintenanceMasterDTO assetMaintCategoryMasterDTO);
    abstract public ArrayList<AssetMaintenanceMasterDTO> getAllAssetMaintenanceMasters();
    abstract public ArrayList<AssetMaintenanceMasterDTO> getSelectAssetMaintenanceMasters(ArrayList<Integer> assetMaintCategorySeqNos);
    abstract public AssetMaintenanceMasterDTO getAssetMaintenanceMasterById(Integer assetMaintCategorySeqNo);
    abstract public ArrayList<AssetMaintenanceMasterDTO> getAssetMaintenanceMastersByCate(Integer assetMaintCateSeqNo);
    abstract public void updAssetMaintenanceMaster(AssetMaintenanceMasterDTO assetMaintCategoryMasterDTO);
    abstract public void delAssetMaintenanceMaster(Integer assetMaintCategorySeqNo);
    abstract public void delAllAssetMaintenanceMasters();
    abstract public void delSelectAssetMaintenanceMasters(ArrayList<Integer> assetMaintCategorySeqNos);
}