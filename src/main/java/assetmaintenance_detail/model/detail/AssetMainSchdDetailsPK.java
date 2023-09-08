package assetmaintenance_detail.model.detail;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ASSET_MAIN_SCHD_DETAILS database table.
 * 
 */
@Embeddable
public class AssetMainSchdDetailsPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ASSET_MAINTENANCE_SEQ_NO")
	private long assetMaintenanceSeqNo;

	@Column(name="RUN_NO")
	private Integer runNo;

	public AssetMainSchdDetailsPK() {
	}
	public long getAssetMaintenanceSeqNo() {
		return this.assetMaintenanceSeqNo;
	}
	public void setAssetMaintenanceSeqNo(long assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}
	public Integer getRunNo() {
		return this.runNo;
	}
	public void setRunNo(Integer runNo) {
		this.runNo = runNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssetMainSchdDetailsPK)) {
			return false;
		}
		AssetMainSchdDetailsPK castOther = (AssetMainSchdDetailsPK)other;
		return 
			(this.assetMaintenanceSeqNo == castOther.assetMaintenanceSeqNo)
			&& (this.runNo == castOther.runNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.assetMaintenanceSeqNo ^ (this.assetMaintenanceSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.runNo ^ (this.runNo >>> 32)));
		
		return hash;
	}
}