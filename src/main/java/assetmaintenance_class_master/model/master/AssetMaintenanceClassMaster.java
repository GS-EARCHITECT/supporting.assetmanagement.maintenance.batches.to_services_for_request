package assetmaintenance_class_master.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ASSET_MAINTENANCE_CLASS_MASTER database table.
 * 
 */
@Entity
@Table(name = "ASSET_MAINTENANCE_CLASS_MASTER")
public class AssetMaintenanceClassMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssetMaintenanceClassMasterPK id;

	public AssetMaintenanceClassMaster() {
	}

	public AssetMaintenanceClassMasterPK getId() {
		return this.id;
	}

	public void setId(AssetMaintenanceClassMasterPK id) {
		this.id = id;
	}

	public AssetMaintenanceClassMaster(AssetMaintenanceClassMasterPK id) {
		super();
		this.id = id;
	}

}