package assetmaintenance_detail.model.detail;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ASSET_MAINTENANCE_DETAILS database table.
 * 
 */
@Entity
@Table(name="ASSET_MAINTENANCE_DETAILS")
public class AssetMaintenanceDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssetMaintenanceDetailsPK id;

	@Column(name="DATE_FROM")
	private Timestamp dateFrom;

	@Column(name="DATE_TO")
	private Timestamp dateTo;

	@Column(name="DURATION")
	private Float duration;

	@Column(name="DURATION_SEQ_NO")
	private Integer durationSeqNo;

	public AssetMaintenanceDetailsPK getId() {
		return id;
	}

	public void setId(AssetMaintenanceDetailsPK id) {
		this.id = id;
	}

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTo() {
		return dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public Integer getDurationSeqNo() {
		return durationSeqNo;
	}

	public void setDurationSeqNo(Integer durationSeqNo) {
		this.durationSeqNo = durationSeqNo;
	}

	public AssetMaintenanceDetails(AssetMaintenanceDetailsPK id, Timestamp dateFrom, Timestamp dateTo, Float duration,
			Integer durationSeqNo) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.duration = duration;
		this.durationSeqNo = durationSeqNo;
	}

	public AssetMaintenanceDetails() {
		super();
	}
}