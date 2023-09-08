package assetmain_schd_master.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ASSET_MAIN_SCHD_MASTER database table.
 * 
 */
@Embeddable
public class AssetMainSchdMasterPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "RESSRVPRD_SEQ_NO")
	private Long ressrvprdSeqNo;

	@Column(name = "RULE_SEQ_NO")
	private Long ruleSeqNo;

	public AssetMainSchdMasterPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssetMainSchdMasterPK)) {
			return false;
		}
		AssetMainSchdMasterPK castOther = (AssetMainSchdMasterPK) other;
		return (this.ressrvprdSeqNo == castOther.ressrvprdSeqNo) && (this.ruleSeqNo == castOther.ruleSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.ressrvprdSeqNo ^ (this.ressrvprdSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.ruleSeqNo ^ (this.ruleSeqNo >>> 32)));

		return hash;
	}

	public AssetMainSchdMasterPK(Long ressrvprdSeqNo, Long ruleSeqNo) {
		super();
		this.ressrvprdSeqNo = ressrvprdSeqNo;
		this.ruleSeqNo = ruleSeqNo;
	}

}