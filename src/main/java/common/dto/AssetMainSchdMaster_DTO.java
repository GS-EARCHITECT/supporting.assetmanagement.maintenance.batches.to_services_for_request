package common.dto;

import java.io.Serializable;

public class AssetMainSchdMaster_DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6847136660668021016L;
	private Long asetSeqNo;
	private Long ressrvprdSeqNo;
	private Long ruleSeqNo;
	private Integer lapseDays;
	private Integer noOfOccurences;
	private Boolean scheduledFlag;

	public Long getAsetSeqNo() {
		return asetSeqNo;
	}

	public void setAsetSeqNo(Long asetSeqNo) {
		this.asetSeqNo = asetSeqNo;
	}

	public Long getRessrvprdSeqNo() {
		return ressrvprdSeqNo;
	}

	public void setRessrvprdSeqNo(Long ressrvprdSeqNo) {
		this.ressrvprdSeqNo = ressrvprdSeqNo;
	}

	public Long getRuleSeqNo() {
		return ruleSeqNo;
	}

	public void setRuleSeqNo(Long ruleSeqNo) {
		this.ruleSeqNo = ruleSeqNo;
	}

	public Integer getLapseDays() {
		return lapseDays;
	}

	public void setLapseDays(Integer lapseDays) {
		this.lapseDays = lapseDays;
	}

	public Integer getNoOfOccurences() {
		return noOfOccurences;
	}

	public void setNoOfOccurences(Integer noOfOccurences) {
		this.noOfOccurences = noOfOccurences;
	}

	public Boolean getScheduledFlag() {
		return scheduledFlag;
	}

	public void setScheduledFlag(Boolean scheduledFlag) {
		this.scheduledFlag = scheduledFlag;
	}

	public AssetMainSchdMaster_DTO() {
		super();
	}

	public AssetMainSchdMaster_DTO(Long asetSeqNo, Long ressrvprdSeqNo, Long ruleSeqNo, Integer lapseDays,
			Integer noOfOccurences, Boolean scheduledFlag) {
		super();
		this.asetSeqNo = asetSeqNo;
		this.ressrvprdSeqNo = ressrvprdSeqNo;
		this.ruleSeqNo = ruleSeqNo;
		this.lapseDays = lapseDays;
		this.noOfOccurences = noOfOccurences;
		this.scheduledFlag = scheduledFlag;
	}

}