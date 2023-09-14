package common.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the ASSET_MAINTENANCE_SCHD_DETAILS database table.
 * 
 */
@Entity
@Table(name = "ASSET_MAINTENANCE_SCHD_DETAILS")
public class AssetMaintenanceSchdDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAINTENANCE_SCHEDULE_SEQUENCE")
	@SequenceGenerator(name = "MAINTENANCE_SCHEDULE_SEQUENCE", sequenceName = "MAINTENANCE_SCHEDULE_SEQUENCE", allocationSize = 1)
	@Column(name = "SCHEDULE_SEQ_NO")
	private Long scheduleSeqNo;

	@Column(name = "ASSET_SEQ_NO")
	private Long assetSeqNo;

	@Column(name = "ASSET_MAINTENANCE_SEQ_NO")
	private Long assetMaintenanceSeqNo;

	@Column(name = "DONEFLAG")
	private Character doneflag;

	@Column(name = "FR_DTTM")
	private Timestamp frDttm;

	@Column(name = "OKFLAG")
	private Character okflag;

	@Column(name = "RESSRVPRD_SEQ_NO")
	private Long ressrvprdSeqNo;

	@Column(name = "RULE_SEQ_NO")
	private Long ruleSeqNo;

	@Column(name = "RULE_LINE_SEQ_NO")
	private Long ruleLineSeqNo;

	@Column(name = "SCHEDULE_ID")
	private String scheduleId;

	@Column(name = "TO_DTTM")
	private Timestamp toDttm;

	@Column(name = "WIPFLAG")
	private Character wipflag;

	public AssetMaintenanceSchdDetail() {
	}

	public Long getAssetSeqNo() {
		return assetSeqNo;
	}

	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public Long getRuleLineSeqNo() {
		return ruleLineSeqNo;
	}

	public void setRuleLineSeqNo(Long ruleLineSeqNo) {
		this.ruleLineSeqNo = ruleLineSeqNo;
	}

	public Long getScheduleSeqNo() {
		return this.scheduleSeqNo;
	}

	public void setScheduleSeqNo(Long scheduleSeqNo) {
		this.scheduleSeqNo = scheduleSeqNo;
	}

	public Long getAssetMaintenanceSeqNo() {
		return this.assetMaintenanceSeqNo;
	}

	public void setAssetMaintenanceSeqNo(Long assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}

	public Timestamp getFrDttm() {
		return this.frDttm;
	}

	public void setFrDttm(Timestamp frDttm) {
		this.frDttm = frDttm;
	}

	public Long getRessrvprdSeqNo() {
		return this.ressrvprdSeqNo;
	}

	public void setRessrvprdSeqNo(Long ressrvprdSeqNo) {
		this.ressrvprdSeqNo = ressrvprdSeqNo;
	}

	public Long getRuleSeqNo() {
		return this.ruleSeqNo;
	}

	public void setRuleSeqNo(Long ruleSeqNo) {
		this.ruleSeqNo = ruleSeqNo;
	}

	public String getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Timestamp getToDttm() {
		return this.toDttm;
	}

	public void setToDttm(Timestamp toDttm) {
		this.toDttm = toDttm;
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

	public Character getWipflag() {
		return wipflag;
	}

	public void setWipflag(Character wipflag) {
		this.wipflag = wipflag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetMaintenanceSeqNo == null) ? 0 : assetMaintenanceSeqNo.hashCode());
		result = prime * result + ((assetSeqNo == null) ? 0 : assetSeqNo.hashCode());
		result = prime * result + ((ruleSeqNo == null) ? 0 : ruleSeqNo.hashCode());
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
		AssetMaintenanceSchdDetail other = (AssetMaintenanceSchdDetail) obj;
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
		if (ruleSeqNo == null) {
			if (other.ruleSeqNo != null)
				return false;
		} else if (!ruleSeqNo.equals(other.ruleSeqNo))
			return false;
		return true;
	}

	public AssetMaintenanceSchdDetail(Long scheduleSeqNo, Long assetSeqNo, Long assetMaintenanceSeqNo,
			Character doneflag, Timestamp frDttm, Character okflag, Long ressrvprdSeqNo, Long ruleSeqNo,
			Long ruleLineSeqNo, String scheduleId, Timestamp toDttm, Character wipflag) {
		super();
		this.scheduleSeqNo = scheduleSeqNo;
		this.assetSeqNo = assetSeqNo;
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
		this.doneflag = doneflag;
		this.frDttm = frDttm;
		this.okflag = okflag;
		this.ressrvprdSeqNo = ressrvprdSeqNo;
		this.ruleSeqNo = ruleSeqNo;
		this.ruleLineSeqNo = ruleLineSeqNo;
		this.scheduleId = scheduleId;
		this.toDttm = toDttm;
		this.wipflag = wipflag;
	}

}