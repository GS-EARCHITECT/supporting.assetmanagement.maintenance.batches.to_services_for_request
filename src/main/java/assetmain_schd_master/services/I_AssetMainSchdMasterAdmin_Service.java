package assetmain_schd_master.services;

import java.util.ArrayList;
import assetmain_schd_master.model.dto.AssetMainSchdMaster_DTO;
import assetmain_schd_master.model.master.AssetMainSchdMasterPK;

public interface I_AssetMainSchdMasterAdmin_Service
{
		public AssetMainSchdMaster_DTO newAssetMainSchd(AssetMainSchdMaster_DTO lMaster); 
		public ArrayList<AssetMainSchdMaster_DTO> getAllAssetMainSchds();
		public ArrayList<AssetMainSchdMaster_DTO> getSelectSchds(ArrayList<AssetMainSchdMasterPK> ids);
		public void updAssetMainSchd(AssetMainSchdMaster_DTO lMaster);
		public void delAllAssetMainSchds();
		public void delSelectSchds(ArrayList<AssetMainSchdMasterPK> ids);
}