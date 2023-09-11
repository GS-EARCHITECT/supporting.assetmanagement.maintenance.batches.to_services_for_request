package common.repo;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.master.AssetMainSchdMaster;
import common.master.AssetMainSchdMasterPK;

@Repository("assetMainSchdMasterRepo")
public interface AssetMainSchdMaster_Repo extends JpaRepository<AssetMainSchdMaster, AssetMainSchdMasterPK> 
{
	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_MASTER order by RESSRVPRD_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdMaster> getAllResProdServs();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_MASTER where resource_seq_no in :ids order by RESSRVPRD_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdMaster> getSelectResProdServsByResources(@Param("ids") CopyOnWriteArrayList<Long> ids);;

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_MASTER where resource_seq_no = :id order by RESSRVPRD_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdMaster> getSelectResProdServsByResource(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_MASTER where RESSRVPRD_SEQ_NO in :ids order by RESSRVPRD_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdMaster> getSelectResProdServsByResProdServs(@Param("ids") CopyOnWriteArrayList<Long> ids);;

}