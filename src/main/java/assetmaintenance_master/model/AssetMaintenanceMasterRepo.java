package assetmaintenance_master.model;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("assetMaintRepo")
public interface AssetMaintenanceMasterRepo extends CrudRepository<AssetMaintenanceMaster, Integer> 
{
@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where ASSET_MAINTENANCE_CATEGORY_SEQ_NO = :assetMaintCateSeqNo ORDER BY ASSET_MAINTENANCE_SEQ_NO",nativeQuery = true) 
Optional<ArrayList<AssetMaintenanceMaster>> getAssetMaintenancesByCategory(@Param(value = "assetMaintCateSeqNo") Integer assetMaintCateSeqNo);
} 

