package assetmaintenance_detail.model.detail;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ASSET_MAIN_SCHD_DETAILS database table.
 * 
 */
@Entity
@Table(name="ASSET_MAIN_SCHD_DETAILS")
public class AssetMainSchdDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssetMainSchdDetailsPK id;

	@Column(name="IS_PROCESSED")
	private Character isProcessed;

	@Column(name="REMARK")
	private String remark;

	@Column(name="STATUS")
	private String status;

	@Column(name="RUN_DATE")
	private Timestamp runDate;

	public AssetMainSchdDetailsPK getId() {
		return id;
	}

	public void setId(AssetMainSchdDetailsPK id) {
		this.id = id;
	}

	public Character getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Character isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRunDate() {
		return runDate;
	}

	public void setRunDate(Timestamp runDate) {
		this.runDate = runDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isProcessed == null) ? 0 : isProcessed.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((runDate == null) ? 0 : runDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		AssetMainSchdDetails other = (AssetMainSchdDetails) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isProcessed == null) {
			if (other.isProcessed != null)
				return false;
		} else if (!isProcessed.equals(other.isProcessed))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (runDate == null) {
			if (other.runDate != null)
				return false;
		} else if (!runDate.equals(other.runDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public AssetMainSchdDetails(AssetMainSchdDetailsPK id, Character isProcessed, String remark, String status,
			Timestamp runDate) {
		super();
		this.id = id;
		this.isProcessed = isProcessed;
		this.remark = remark;
		this.status = status;
		this.runDate = runDate;
	}

	public AssetMainSchdDetails() {
		super();
	}
}