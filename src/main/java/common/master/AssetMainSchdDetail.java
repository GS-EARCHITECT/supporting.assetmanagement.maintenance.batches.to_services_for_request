package common.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ASSET_MAIN_SCHD_DETAILS database table.
 * 
 */
@Entity
@Table(name = "ASSET_MAIN_SCHD_DETAILS")
public class AssetMainSchdDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssetMainSchdDetailPK id;

	@Column(name = "DONEFLAG")
	private Character doneflag;

	@Column(name = "OKFLAG")
	private Character okflag;

	public AssetMainSchdDetail() {
	}

	public AssetMainSchdDetailPK getId() {
		return this.id;
	}

	public void setId(AssetMainSchdDetailPK id) {
		this.id = id;
	}

	public Character getDoneflag() {
		return this.doneflag;
	}

	public void setDoneflag(Character doneflag) {
		this.doneflag = doneflag;
	}

	public Character getOkflag() {
		return this.okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
	}

	public AssetMainSchdDetail(AssetMainSchdDetailPK id, Character doneflag, Character okflag) {
		super();
		this.id = id;
		this.doneflag = doneflag;
		this.okflag = okflag;
	}

}