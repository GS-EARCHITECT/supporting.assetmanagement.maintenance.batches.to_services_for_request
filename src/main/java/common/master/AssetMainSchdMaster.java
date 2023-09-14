package common.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ASSET_MAIN_SCHD_MASTER database table.
 * 
 */
@Entity
@Table(name = "ASSET_MAIN_SCHD_MASTER")
public class AssetMainSchdMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssetMainSchdMasterPK id;

	@Column(name = "LAPSE_DAYS")
	private Integer lapseDays;

	@Column(name = "NO_OF_OCCURENCES")
	private Integer noOfOccurences;

	@Column(name = "FR_TIME")
	private String frTime;

	@Column(name = "TO_TIME")
	private String toTime;

	public AssetMainSchdMaster() {
	}

	public AssetMainSchdMasterPK getId() {
		return this.id;
	}

	public void setId(AssetMainSchdMasterPK id) {
		this.id = id;
	}

	public Integer getLapseDays() {
		return this.lapseDays;
	}

	public void setLapseDays(Integer lapseDays) {
		this.lapseDays = lapseDays;
	}

	public Integer getNoOfOccurences() {
		return this.noOfOccurences;
	}

	public void setNoOfOccurences(Integer noOfOccurences) {
		this.noOfOccurences = noOfOccurences;
	}

	public String getFrTime() {
		return frTime;
	}

	public void setFrTime(String frTime) {
		this.frTime = frTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public AssetMainSchdMaster(AssetMainSchdMasterPK id, Integer lapseDays, Integer noOfOccurences, String frTime,
			String toTime) {
		super();
		this.id = id;
		this.lapseDays = lapseDays;
		this.noOfOccurences = noOfOccurences;
		this.frTime = frTime;
		this.toTime = toTime;
	}

}