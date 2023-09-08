package assetmaintenance_detail.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ASSET_MAIN_SCHD_MASTER database table.
 * 
 */
@Entity
@Table(name="ASSET_MAIN_SCHD_MASTER")
public class AssetMainSchdMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssetMainSchdMasterPK id;

	@Column(name="IS_PROCESSED")
	private Character isProcessed;

	@Column(name="LAPSE_DAYS")
	private Integer lapseDays;

	@Column(name="LAST_RUN_DTTM")
	private Timestamp lastRunDttm;

	@Column(name="LAST_RUN_NO")
	private Integer lastRunNo;

	@Column(name="NO_OF_OCCURENCES")
	private BigDecimal noOfOccurences;

	@Column(name="REMINDER_AFTER_DAYS")
	private BigDecimal reminderAfterDays;
	
	@Column(name="REMARK")
	private String remark;

	@Column(name="STATUS")
	private String status;

	public AssetMainSchdMasterPK getId() {
		return id;
	}

	public void setId(AssetMainSchdMasterPK id) {
		this.id = id;
	}

	public Character getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Character isProcessed) {
		this.isProcessed = isProcessed;
	}

	public Integer getLapseDays() {
		return lapseDays;
	}

	public void setLapseDays(Integer lapseDays) {
		this.lapseDays = lapseDays;
	}

	public Timestamp getLastRunDttm() {
		return lastRunDttm;
	}

	public void setLastRunDttm(Timestamp lastRunDttm) {
		this.lastRunDttm = lastRunDttm;
	}

	public Integer getLastRunNo() {
		return lastRunNo;
	}

	public void setLastRunNo(Integer lastRunNo) {
		this.lastRunNo = lastRunNo;
	}

	public BigDecimal getNoOfOccurences() {
		return noOfOccurences;
	}

	public void setNoOfOccurences(BigDecimal noOfOccurences) {
		this.noOfOccurences = noOfOccurences;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getReminderAfterDays() {
		return reminderAfterDays;
	}

	public void setReminderAfterDays(BigDecimal reminderAfterDays) {
		this.reminderAfterDays = reminderAfterDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AssetMainSchdMaster(AssetMainSchdMasterPK id, Character isProcessed, Integer lapseDays,
			Timestamp lastRunDttm, Integer lastRunNo, BigDecimal noOfOccurences, String remark,
			BigDecimal reminderAfterDays, String status) {
		super();
		this.id = id;
		this.isProcessed = isProcessed;
		this.lapseDays = lapseDays;
		this.lastRunDttm = lastRunDttm;
		this.lastRunNo = lastRunNo;
		this.noOfOccurences = noOfOccurences;
		this.remark = remark;
		this.reminderAfterDays = reminderAfterDays;
		this.status = status;
	}

	public AssetMainSchdMaster() {
		super();
	}
	
}