package common.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ASSET_MAIN_RULE_MASTER database table.
 * 
 */
@Entity
@Table(name = "ASSET_MAIN_RULE_MASTER")
public class AssetMainRuleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSET_RULE_SEQUENCE")
	@SequenceGenerator(name = "ASSET_RULE_SEQUENCE", sequenceName = "ASSET_RULE_SEQUENCE", allocationSize = 1)
	@Column(name = "RULE_SEQ_NO")
	private Long ruleSeqNo;

	@Column(name = "DATE_SEQ_NO")
	private Long dateSeqNo;

	@Column(name = "DAYS_PLUS")
	private Long daysPlus;

	@Column(name = "DOW")
	private Integer dow;

	@Column(name = "EOM")
	private Integer eom;

	@Column(name = "EOY")
	private Integer eoy;

	@Column(name = "NO_OF_OCCURENCES")
	private Integer noOfOccurences;

	public AssetMainRuleMaster() {
	}

	public Long getRuleSeqNo() {
		return ruleSeqNo;
	}

	public void setRuleSeqNo(Long ruleSeqNo) {
		this.ruleSeqNo = ruleSeqNo;
	}

	public Long getDateSeqNo() {
		return dateSeqNo;
	}

	public void setDateSeqNo(Long dateSeqNo) {
		this.dateSeqNo = dateSeqNo;
	}

	public Long getDaysPlus() {
		return daysPlus;
	}

	public void setDaysPlus(Long daysPlus) {
		this.daysPlus = daysPlus;
	}

	public Integer getDow() {
		return dow;
	}

	public void setDow(Integer dow) {
		this.dow = dow;
	}

	public Integer getEom() {
		return eom;
	}

	public void setEom(Integer eom) {
		this.eom = eom;
	}

	public Integer getEoy() {
		return eoy;
	}

	public void setEoy(Integer eoy) {
		this.eoy = eoy;
	}

	public Integer getNoOfOccurences() {
		return noOfOccurences;
	}

	public void setNoOfOccurences(Integer noOfOccurences) {
		this.noOfOccurences = noOfOccurences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateSeqNo == null) ? 0 : dateSeqNo.hashCode());
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
		AssetMainRuleMaster other = (AssetMainRuleMaster) obj;
		if (dateSeqNo == null) {
			if (other.dateSeqNo != null)
				return false;
		} else if (!dateSeqNo.equals(other.dateSeqNo))
			return false;
		if (ruleSeqNo == null) {
			if (other.ruleSeqNo != null)
				return false;
		} else if (!ruleSeqNo.equals(other.ruleSeqNo))
			return false;
		return true;
	}

	public AssetMainRuleMaster(Long ruleSeqNo, Long dateSeqNo, Long daysPlus, Integer dow, Integer eom, Integer eoy,
			Integer noOfOccurences) {
		super();
		this.ruleSeqNo = ruleSeqNo;
		this.dateSeqNo = dateSeqNo;
		this.daysPlus = daysPlus;
		this.dow = dow;
		this.eom = eom;
		this.eoy = eoy;
		this.noOfOccurences = noOfOccurences;
	}

}