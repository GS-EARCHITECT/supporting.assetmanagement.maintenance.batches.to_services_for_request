package assetmaintenance_master.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ASSET_MAINTENANCE_MASTER database table.
 * 
 */
@Entity
@Table(name="ASSET_MAINTENANCE_MASTER")
public class AssetMaintenanceMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_maintenance_seq_no")
	@SequenceGenerator(name = "asset_maintenance_seq_no", sequenceName="asset_maintenance_seq_no", allocationSize = 1)
	@Column(name="ASSET_MAINTENANCE_SEQ_NO")
	private Integer assetMaintenanceSeqNo;
	
	@Column(name="MAINTENANCE_CATE_SEQ_NO")
	private Integer maintenanceCateSeqNo;

	public Integer getAssetMaintenanceSeqNo() {
		return assetMaintenanceSeqNo;
	}

	public void setAssetMaintenanceCateSeqNo(Integer assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}

	public Integer getMaintenanceCateSeqNo() {
		return maintenanceCateSeqNo;
	}

	public void setMaintenanceCateSeqNo(Integer maintenanceCateSeqNo) {
		this.maintenanceCateSeqNo = maintenanceCateSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetMaintenanceSeqNo == null) ? 0 : assetMaintenanceSeqNo.hashCode());
		result = prime * result + ((maintenanceCateSeqNo == null) ? 0 : maintenanceCateSeqNo.hashCode());
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
		AssetMaintenanceMaster other = (AssetMaintenanceMaster) obj;
		if (assetMaintenanceSeqNo == null) {
			if (other.assetMaintenanceSeqNo != null)
				return false;
		} else if (!assetMaintenanceSeqNo.equals(other.assetMaintenanceSeqNo))
			return false;
		if (maintenanceCateSeqNo == null) {
			if (other.maintenanceCateSeqNo != null)
				return false;
		} else if (!maintenanceCateSeqNo.equals(other.maintenanceCateSeqNo))
			return false;
		return true;
	}

	public AssetMaintenanceMaster(Integer assetMaintenanceSeqNo, Integer maintenanceCateSeqNo) {
		super();
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
		this.maintenanceCateSeqNo = maintenanceCateSeqNo;
	}

	public AssetMaintenanceMaster() {
		super();
	}

}