package assetmaintenance_detail.model.dto;

import java.io.Serializable;

public class AssetMaintenanceDetailsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private long assetMaintenanceSeqNo;
	private long serviceSeqNo;
	private long assetSeqNo;
	private String dateFrom;
	private String dateTo;
	private Float duration;
	private Integer durationSeqNo;
	
	public long getAssetMaintenanceSeqNo() {
		return assetMaintenanceSeqNo;
	}
	public void setAssetMaintenanceSeqNo(long assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}
	public long getServiceSeqNo() {
		return serviceSeqNo;
	}
	public void setServiceSeqNo(long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}
	public long getAssetSeqNo() {
		return assetSeqNo;
	}
	public void setAssetSeqNo(long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AssetMaintenanceDetailsDTO(long assetMaintenanceSeqNo, long serviceSeqNo, long assetSeqNo, String dateFrom,
			String dateTo, Float duration, Integer durationSeqNo) {
		super();
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
		this.serviceSeqNo = serviceSeqNo;
		this.assetSeqNo = assetSeqNo;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.duration = duration;
		this.durationSeqNo = durationSeqNo;
	}
	public AssetMaintenanceDetailsDTO() {
		super();
	}
	
	
}