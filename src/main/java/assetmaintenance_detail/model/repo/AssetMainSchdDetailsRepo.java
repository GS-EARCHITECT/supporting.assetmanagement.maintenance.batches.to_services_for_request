package assetmaintenance_detail.model.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import assetmaintenance_detail.model.detail.AssetMainSchdDetails;
import assetmaintenance_detail.model.detail.AssetMainSchdDetailsPK;

@Repository("assetMainSchdDetailsRepo")
public interface AssetMainSchdDetailsRepo extends CrudRepository<AssetMainSchdDetails, AssetMainSchdDetailsPK> 
{	
	
	@Query(value="select COALESCE(max(run_no)+1,0) from ASSET_MAIN_SCHD_DETAILS b  where b.ASSET_MAINTENANCE_SEQ_NO=:seqNo",nativeQuery = true)
	 Integer getLastRunNoForSchd(@Param("seqNo") long seqNo);
	 
} 

