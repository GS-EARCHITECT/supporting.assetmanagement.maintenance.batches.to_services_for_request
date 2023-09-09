package assetmaintenance_master.model.dto;

import java.io.Serializable;

public class AssetMaintenanceMaster_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431773639726279572L;
	private Long assetMaintenanceSeqNo;
	private String acDttmFr;
	private String acDttmTo;
	private Long assetSeqNo;
	private Character doneflag;
	private Long maintenanceClassSeqNo;
	private String maintenanceId;
	private Character okflag;
	private String plDttmFr;
	private String plDttmTo;

	public Long getAssetMaintenanceSeqNo() {
		return assetMaintenanceSeqNo;
	}

	public void setAssetMaintenanceSeqNo(Long assetMaintenanceSeqNo) {
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
	}

	public String getAcDttmFr() {
		return acDttmFr;
	}

	public void setAcDttmFr(String acDttmFr) {
		this.acDttmFr = acDttmFr;
	}

	public String getAcDttmTo() {
		return acDttmTo;
	}

	public void setAcDttmTo(String acDttmTo) {
		this.acDttmTo = acDttmTo;
	}

	public Long getAssetSeqNo() {
		return assetSeqNo;
	}

	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public Character getDoneflag() {
		return doneflag;
	}

	public void setDoneflag(Character doneflag) {
		this.doneflag = doneflag;
	}

	public Long getMaintenanceClassSeqNo() {
		return maintenanceClassSeqNo;
	}

	public void setMaintenanceClassSeqNo(Long maintenanceClassSeqNo) {
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
	}

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Character getOkflag() {
		return okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
	}

	public String getPlDttmFr() {
		return plDttmFr;
	}

	public void setPlDttmFr(String plDttmFr) {
		this.plDttmFr = plDttmFr;
	}

	public String getPlDttmTo() {
		return plDttmTo;
	}

	public void setPlDttmTo(String plDttmTo) {
		this.plDttmTo = plDttmTo;
	}

	public AssetMaintenanceMaster_DTO(Long assetMaintenanceSeqNo, String acDttmFr, String acDttmTo, Long assetSeqNo,
			Character doneflag, Long maintenanceClassSeqNo, String maintenanceId, Character okflag, String plDttmFr,
			String plDttmTo) {
		super();
		this.assetMaintenanceSeqNo = assetMaintenanceSeqNo;
		this.acDttmFr = acDttmFr;
		this.acDttmTo = acDttmTo;
		this.assetSeqNo = assetSeqNo;
		this.doneflag = doneflag;
		this.maintenanceClassSeqNo = maintenanceClassSeqNo;
		this.maintenanceId = maintenanceId;
		this.okflag = okflag;
		this.plDttmFr = plDttmFr;
		this.plDttmTo = plDttmTo;
	}

	public AssetMaintenanceMaster_DTO() {
		super();
	}

}