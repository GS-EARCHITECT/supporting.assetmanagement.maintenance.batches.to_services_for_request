package common.master;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceRequestMaster_DTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 75511478156110074L;
	private Long requestSeqNo;
	private Long frPartySeqNo;
	private Long referenceSeqNo;
	private String remark;
	private Timestamp requestDttm;
	private Character status;
	private Long toPartySeqNo;

	public Long getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public Long getFrPartySeqNo() {
		return frPartySeqNo;
	}

	public void setFrPartySeqNo(Long frPartySeqNo) {
		this.frPartySeqNo = frPartySeqNo;
	}

	public Long getReferenceSeqNo() {
		return referenceSeqNo;
	}

	public void setReferenceSeqNo(Long referenceSeqNo) {
		this.referenceSeqNo = referenceSeqNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getRequestDttm() {
		return requestDttm;
	}

	public void setRequestDttm(Timestamp requestDttm) {
		this.requestDttm = requestDttm;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Long getToPartySeqNo() {
		return toPartySeqNo;
	}

	public void setToPartySeqNo(Long toPartySeqNo) {
		this.toPartySeqNo = toPartySeqNo;
	}

	public ServiceRequestMaster_DTO(Long requestSeqNo, Long frPartySeqNo, Long referenceSeqNo, String remark,
			Timestamp requestDttm, Character status, Long toPartySeqNo) {
		super();
		this.requestSeqNo = requestSeqNo;
		this.frPartySeqNo = frPartySeqNo;
		this.referenceSeqNo = referenceSeqNo;
		this.remark = remark;
		this.requestDttm = requestDttm;
		this.status = status;
		this.toPartySeqNo = toPartySeqNo;
	}

	public ServiceRequestMaster_DTO() {
		super();
	}

}