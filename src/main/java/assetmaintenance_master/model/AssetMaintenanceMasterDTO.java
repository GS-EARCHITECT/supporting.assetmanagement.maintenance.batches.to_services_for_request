package assetmaintenance_master.model;

import java.io.Serializable;


public class AssetMaintenanceMasterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2039123843453389958L;
	private Integer assetMaintenanceSeqNo;
	private Integer maintenanceCateSeqNo;
	
	public Integer getAssetMaintenanceSeqNo() {
		return assetMaintenanceSeqNo;
	}
	public void setAssetMaintenanceSeqNo(Integer assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}
	public Integer getMaintenanceCateSeqNo() {
		return maintenanceCateSeqNo;
	}
	public void setMaintenanceCateSeqNo(Integer maintenanceCateSeqNo) {
		this.maintenanceCateSeqNo = maintenanceCateSeqNo;
	}
	public AssetMaintenanceMasterDTO(Integer assetMaintenanceSeqNo, Integer maintenanceCateSeqNo) {
		super();
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
		this.maintenanceCateSeqNo = maintenanceCateSeqNo;
	}
	public AssetMaintenanceMasterDTO() {
		super();
	}
	
}