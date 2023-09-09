package assetmaintenance_master.services;

import java.util.ArrayList;
import assetmaintenance_master.model.dto.AssetMaintenanceMaster_DTO;

public interface I_AssetMaintenanceMasterAdmin_Service
{
    public AssetMaintenanceMaster_DTO newAssetMaintenanceMaster(AssetMaintenanceMaster_DTO assetMaintCategoryMasterDTO);
    public ArrayList<AssetMaintenanceMaster_DTO> getAllAssetMaintenanceMasters();
    public ArrayList<AssetMaintenanceMaster_DTO> getSelectAssets(ArrayList<Long> aList);
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsByClasses( ArrayList<Long> ids);
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsByAssets( ArrayList<Long> ids);
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsByIds(ArrayList<String> ids);
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsOK();
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsDone();
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsNotOK();
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsNotDone();
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsBetweenPlanDTTM( String fr,  String to);
    public ArrayList<AssetMaintenanceMaster_DTO> getAssetsBetweenActualDTTM( String fr,  String to);
    public void updAssetMaintenanceMaster(AssetMaintenanceMaster_DTO assetMaintCategoryMasterDTO);
    public void delSelectAssets(ArrayList<Long> aList);
    public void delAssetsByClasses(ArrayList<Long> ids);
    public void delAssetsByAssets(ArrayList<Long> ids);
    public void delAssetsByIds(ArrayList<String> ids);
    public void delAssetsOK();
    public void delAssetsDone();
    public void delAssetsNotOK();
    public void delAssetsNotDone();
    public void delAssetsBetweenPlanDTTM( String fr,  String to);
    public void delAssetsBetweenActualDTTM( String fr,  String to);
    public void delAllAssetMaintenanceMasters();
  }