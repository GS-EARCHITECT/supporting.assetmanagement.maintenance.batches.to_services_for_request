package assetmaintenance_class_master.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ASSET_MAINTENANCE_CLASS_MASTER database table.
 * 
 */
@Embeddable
public class AssetMaintenanceClassMasterPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "MAINTENANCE_CLASS_SEQ_NO")
	private Long maintenanceClassSeqNo;

	@Column(name = "PARTY_SEQ_NO")
	private Long partySeqNo;

	@Column(name = "RESOURCE_SEQ_NO")
	private Long resourceSeqNo;

	public AssetMaintenanceClassMasterPK() {
	}

	public Long getMaintenanceClassSeqNo() {
		return this.maintenanceClassSeqNo;
	}

	public void setMaintenanceClassSeqNo(Long maintenanceClassSeqNo) {
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
	}

	public Long getPartySeqNo() {
		return this.partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}

	public Long getResourceSeqNo() {
		return this.resourceSeqNo;
	}

	public void setResourceSeqNo(Long resourceSeqNo) {
		this.resourceSeqNo = resourceSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssetMaintenanceClassMasterPK)) {
			return false;
		}
		AssetMaintenanceClassMasterPK castOther = (AssetMaintenanceClassMasterPK) other;
		return (this.maintenanceClassSeqNo == castOther.maintenanceClassSeqNo)
				&& (this.partySeqNo == castOther.partySeqNo) && (this.resourceSeqNo == castOther.resourceSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.maintenanceClassSeqNo ^ (this.maintenanceClassSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.partySeqNo ^ (this.partySeqNo >>> 32)));
		hash = hash * prime + ((int) (this.resourceSeqNo ^ (this.resourceSeqNo >>> 32)));

		return hash;
	}

	public AssetMaintenanceClassMasterPK(Long maintenanceClassSeqNo, Long partySeqNo, Long resourceSeqNo) {
		super();
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
		this.partySeqNo = partySeqNo;
		this.resourceSeqNo = resourceSeqNo;
	}

}