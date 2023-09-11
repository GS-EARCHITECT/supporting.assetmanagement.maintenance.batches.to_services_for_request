package common.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the ASSET_MAINTENANCE_MASTER database table.
 * 
 */
@Entity
@Table(name = "ASSET_MAINTENANCE_MASTER")
public class AssetMaintenanceMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_maintenance_seq_no")
	@SequenceGenerator(name = "asset_maintenance_seq_no", sequenceName = "asset_maintenance_seq_no", allocationSize = 1)
	@Column(name = "ASSET_MAINTENANCE_SEQ_NO")
	private Long assetMaintenanceSeqNo;

	@Column(name = "AC_DTTM_FR")
	private Timestamp acDttmFr;

	@Column(name = "AC_DTTM_TO")
	private Timestamp acDttmTo;

	@Column(name = "ASSET_SEQ_NO")
	private Long assetSeqNo;

	@Column(name = "DONEFLAG")
	private Character doneflag;

	@Column(name = "MAINTENANCE_CLASS_SEQ_NO")
	private Long maintenanceClassSeqNo;

	@Column(name = "MAINTENANCE_ID")
	private String maintenanceId;

	@Column(name = "OKFLAG")
	private Character okflag;

	@Column(name = "PL_DTTM_FR")
	private Timestamp plDttmFr;

	@Column(name = "PL_DTTM_TO")
	private Timestamp plDttmTo;

	public AssetMaintenanceMaster() {
	}

	public Long getAssetMaintenanceSeqNo() {
		return this.assetMaintenanceSeqNo;
	}

	public void setAssetMaintenanceSeqNo(Long assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}

	public Timestamp getAcDttmFr() {
		return this.acDttmFr;
	}

	public void setAcDttmFr(Timestamp acDttmFr) {
		this.acDttmFr = acDttmFr;
	}

	public Timestamp getAcDttmTo() {
		return this.acDttmTo;
	}

	public void setAcDttmTo(Timestamp acDttmTo) {
		this.acDttmTo = acDttmTo;
	}

	public Long getAssetSeqNo() {
		return this.assetSeqNo;
	}

	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public Long getMaintenanceClassSeqNo() {
		return this.maintenanceClassSeqNo;
	}

	public void setMaintenanceClassSeqNo(Long maintenanceClassSeqNo) {
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
	}

	public String getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Timestamp getPlDttmFr() {
		return this.plDttmFr;
	}

	public void setPlDttmFr(Timestamp plDttmFr) {
		this.plDttmFr = plDttmFr;
	}

	public Timestamp getPlDttmTo() {
		return this.plDttmTo;
	}

	public void setPlDttmTo(Timestamp plDttmTo) {
		this.plDttmTo = plDttmTo;
	}

	public Character getDoneflag() {
		return doneflag;
	}

	public void setDoneflag(Character doneflag) {
		this.doneflag = doneflag;
	}

	public Character getOkflag() {
		return okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetMaintenanceSeqNo == null) ? 0 : assetMaintenanceSeqNo.hashCode());
		result = prime * result + ((assetSeqNo == null) ? 0 : assetSeqNo.hashCode());
		result = prime * result + ((maintenanceClassSeqNo == null) ? 0 : maintenanceClassSeqNo.hashCode());
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
		if (assetSeqNo == null) {
			if (other.assetSeqNo != null)
				return false;
		} else if (!assetSeqNo.equals(other.assetSeqNo))
			return false;
		if (maintenanceClassSeqNo == null) {
			if (other.maintenanceClassSeqNo != null)
				return false;
		} else if (!maintenanceClassSeqNo.equals(other.maintenanceClassSeqNo))
			return false;
		return true;
	}

	public AssetMaintenanceMaster(Long assetMaintenanceSeqNo, Timestamp acDttmFr, Timestamp acDttmTo, Long assetSeqNo,
			Character doneflag, Long maintenanceClassSeqNo, String maintenanceId, Character okflag, Timestamp plDttmFr,
			Timestamp plDttmTo) {
		super();
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
		this.acDttmFr = acDttmFr;
		this.acDttmTo = acDttmTo;
		this.assetSeqNo = assetSeqNo;
		this.doneflag = doneflag;
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
		this.maintenanceId = maintenanceId;
		this.okflag = okflag;
		this.plDttmFr = plDttmFr;
		this.plDttmTo = plDttmTo;
	}

}