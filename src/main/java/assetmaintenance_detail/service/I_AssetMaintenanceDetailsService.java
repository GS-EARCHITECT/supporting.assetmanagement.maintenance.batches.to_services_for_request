package assetmaintenance_detail.service;

import java.util.ArrayList;

import assetmaintenance_detail.model.dto.AssetMaintenanceDetailsDTO;

public interface I_AssetMaintenanceDetailsService
{
    abstract public AssetMaintenanceDetailsDTO newAssetMaintenanceDetails(AssetMaintenanceDetailsDTO asssetMaintMasterDTO);
    abstract public ArrayList<AssetMaintenanceDetailsDTO> getAllAssetMaintenanceDetails();
    abstract public ArrayList<AssetMaintenanceDetailsDTO> getSelectAssetMaintDetails(ArrayList<AssetMaintenanceDetailsDTO> accountAssetMaintSeqNos);
    abstract public AssetMaintenanceDetailsDTO getAssetMaintenanceDetailsById(AssetMaintenanceDetailsDTO asssetMaintDTO);    
    abstract public void updAssetMaintenanceDetails(AssetMaintenanceDetailsDTO asssetMaintMasterDTO);
    abstract public void delAssetMaintenanceDetails(AssetMaintenanceDetailsDTO asssetMaintDTO);
    abstract public void delAllAssetMaintenanceDetails();
    abstract public void delSelectAssetMaintenanceDetails(ArrayList<AssetMaintenanceDetailsDTO> assetMaintList);
}