package assetmaintenance_detail.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * The primary key class for the ASSET_MAIN_SCHD_DETAILS database table.
 * 
 */
@Embeddable
public class AssetMainSchdMasterPK implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6833097870749448863L;

	@Column(name="ASSET_MAINTENANCE_SEQ_NO")
	private long assetMaintenanceSeqNo;

	@Column(name="START_ON")
	private Timestamp startOn;

	public long getAssetMaintenanceSeqNo() {
		return assetMaintenanceSeqNo;
	}

	public void setAssetMaintenanceSeqNo(long assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}

	public Timestamp getStartOn() {
		return startOn;
	}

	public void setStartOn(Timestamp startOn) {
		this.startOn = startOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (assetMaintenanceSeqNo ^ (assetMaintenanceSeqNo >>> 32));
		result = prime * result + ((startOn == null) ? 0 : startOn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetMainSchdMasterPK other = (AssetMainSchdMasterPK) obj;
		if (assetMaintenanceSeqNo != other.assetMaintenanceSeqNo)
			return false;
		if (startOn == null) {
			if (other.startOn != null)
				return false;
		} else if (!startOn.equals(other.startOn))
			return false;
		return true;
	}

	public AssetMainSchdMasterPK(long assetMaintenanceSeqNo, Timestamp startOn) {
		super();
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
		this.startOn = startOn;
	}

	public AssetMainSchdMasterPK() {
		super();
	}
}