package assetmaintenance_class_master.model.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import assetmaintenance_class_master.model.master.AssetMaintenanceClassMaster;
import assetmaintenance_class_master.model.master.AssetMaintenanceClassMasterPK;

@Repository("assetMaintenanceClassMasterAdminRepo")
public interface AssetMaintenanceClassMasterAdmin_Repo extends JpaRepository<AssetMaintenanceClassMaster, AssetMaintenanceClassMasterPK> 
{
	@Query(value = "SELECT * FROM ASSET_MASTER a WHERE a.resource_seq_no in :ids order by resource_seq_no", nativeQuery = true)
	ArrayList<AssetMaintenanceClassMaster> getSelectClassesByResources(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_MASTER a WHERE a.party_seq_no in :ids order by party_seq_no", nativeQuery = true)
	ArrayList<AssetMaintenanceClassMaster> getSelectClassesByParties(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "delete FROM ASSET_MASTER a WHERE a.resource_seq_no in :ids", nativeQuery = true)
	void delSelectClassesByResources(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "delete FROM ASSET_MASTER a WHERE a.party_seq_no in :ids", nativeQuery = true)
	void delSelectClassesByParties(@Param("ids") ArrayList<Long> ids);
	
}