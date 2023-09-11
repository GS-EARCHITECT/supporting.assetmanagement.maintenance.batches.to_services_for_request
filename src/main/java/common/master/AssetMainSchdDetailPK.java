package common.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ASSET_MAIN_SCHD_DETAILS database table.
 * 
 */
@Embeddable
public class AssetMainSchdDetailPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "RESSRVPRD_SEQ_NO")
	private Long ressrvprdSeqNo;

	@Column(name = "RULE_SEQ_NO")
	private Long ruleSeqNo;

	@Column(name = "ASSET_SEQ_NO")
	private Long assetSeqNo;

	public AssetMainSchdDetailPK() {
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

	public Long getAssetSeqNo() {
		return this.assetSeqNo;
	}

	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssetMainSchdDetailPK)) {
			return false;
		}
		AssetMainSchdDetailPK castOther = (AssetMainSchdDetailPK) other;
		return (this.ressrvprdSeqNo == castOther.ressrvprdSeqNo) && (this.ruleSeqNo == castOther.ruleSeqNo)
				&& (this.assetSeqNo == castOther.assetSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.ressrvprdSeqNo ^ (this.ressrvprdSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.ruleSeqNo ^ (this.ruleSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.assetSeqNo ^ (this.assetSeqNo >>> 32)));

		return hash;
	}

	public AssetMainSchdDetailPK(Long ressrvprdSeqNo, Long ruleSeqNo, Long assetSeqNo) {
		super();
		this.ressrvprdSeqNo = ressrvprdSeqNo;
		this.ruleSeqNo = ruleSeqNo;
		this.assetSeqNo = assetSeqNo;
	}

}