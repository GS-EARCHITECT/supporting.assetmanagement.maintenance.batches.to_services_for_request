package assetmaintenance_detail.model.detail;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ASSET_MAINTENANCE_DETAILS database table.
 * 
 */
@Embeddable
public class AssetMaintenanceDetailsPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ASSET_MAINTENANCE_SEQ_NO")
	private long assetMaintenanceSeqNo;

	@Column(name="SERVICE_SEQ_NO")
	private long serviceSeqNo;

	@Column(name="ASSET_SEQ_NO")
	private long assetSeqNo;

	public AssetMaintenanceDetailsPK() {
	}
	public long getAssetMaintenanceSeqNo() {
		return this.assetMaintenanceSeqNo;
	}
	public void setAssetMaintenanceSeqNo(long assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}
	public long getServiceSeqNo() {
		return this.serviceSeqNo;
	}
	public void setServiceSeqNo(long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}
	public long getAssetSeqNo() {
		return this.assetSeqNo;
	}
	public void setAssetSeqNo(long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssetMaintenanceDetailsPK)) {
			return false;
		}
		AssetMaintenanceDetailsPK castOther = (AssetMaintenanceDetailsPK)other;
		return 
			(this.assetMaintenanceSeqNo == castOther.assetMaintenanceSeqNo)
			&& (this.serviceSeqNo == castOther.serviceSeqNo)
			&& (this.assetSeqNo == castOther.assetSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.assetMaintenanceSeqNo ^ (this.assetMaintenanceSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.serviceSeqNo ^ (this.serviceSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.assetSeqNo ^ (this.assetSeqNo >>> 32)));
		
		return hash;
	}
}