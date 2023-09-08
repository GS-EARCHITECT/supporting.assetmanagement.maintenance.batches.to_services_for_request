package assetmaintenance_detail.model.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import assetmaintenance_detail.model.detail.AssetMaintenanceDetails;
import assetmaintenance_detail.model.detail.AssetMaintenanceDetailsPK;

@Repository("assetMaintDetailsRepo")
public interface AssetMaintDetailsRepo extends CrudRepository<AssetMaintenanceDetails, AssetMaintenanceDetailsPK> 
{} 

