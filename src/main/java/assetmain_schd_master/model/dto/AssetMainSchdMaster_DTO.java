package assetmain_schd_master.model.dto;

import java.io.Serializable;

public class AssetMainSchdMaster_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4254169759118733691L;
	private Integer lapseDays;
	private Integer noOfOccurences;
	private Long ressrvprdSeqNo;
	private Long ruleSeqNo;

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

	public AssetMainSchdMaster_DTO(Integer lapseDays, Integer noOfOccurences, Long ressrvprdSeqNo, Long ruleSeqNo) {
		super();
		this.lapseDays = lapseDays;
		this.noOfOccurences = noOfOccurences;
		this.ressrvprdSeqNo = ressrvprdSeqNo;
		this.ruleSeqNo = ruleSeqNo;
	}

	public AssetMainSchdMaster_DTO() {
		super();
	}

}