package assetmaintenance_class_master.model.dto;

import java.io.Serializable;

public class AssetMaintenanceClassMaster_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4709426494733970453L;
	private Long maintenanceClassSeqNo;
	private Long partySeqNo;
	private Long resourceSeqNo;

	public Long getMaintenanceClassSeqNo() {
		return maintenanceClassSeqNo;
	}

	public void setMaintenanceClassSeqNo(Long maintenanceClassSeqNo) {
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
	}

	public Long getPartySeqNo() {
		return partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}

	public Long getResourceSeqNo() {
		return resourceSeqNo;
	}

	public void setResourceSeqNo(Long resourceSeqNo) {
		this.resourceSeqNo = resourceSeqNo;
	}

	public AssetMaintenanceClassMaster_DTO(Long maintenanceClassSeqNo, Long partySeqNo, Long resourceSeqNo) {
		super();
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
		this.partySeqNo = partySeqNo;
		this.resourceSeqNo = resourceSeqNo;
	}

	public AssetMaintenanceClassMaster_DTO() {
		super();
	}

}