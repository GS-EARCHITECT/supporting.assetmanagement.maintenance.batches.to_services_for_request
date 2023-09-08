package assetmain_rule_master.services;

import java.util.ArrayList;

import assetmain_rule_master.model.dto.AssetMainRuleMaster_DTO;

public interface I_AssetMainRuleMasterAdmin_Service
{
    public AssetMainRuleMaster_DTO newAssetMainRule(AssetMainRuleMaster_DTO assetMainRuleMasterDTO);
    public ArrayList<AssetMainRuleMaster_DTO> getAllAssetMainRules();
    public ArrayList<AssetMainRuleMaster_DTO> getSelectRules(ArrayList<Long> ids);
    public void updAssetMainRule(AssetMainRuleMaster_DTO assetMainRuleMasterDTO);
    public void delAllAssetMainRules();
    public void delSelectRules(ArrayList<Long> ids); 
}