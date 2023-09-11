package asset_schd_details.model.repo;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.master.AssetMaintenanceSchdDetail;

@Repository("assetMainSchdDetailsReadRepo")
public interface AssetMainSchdDetailsRead_Repo extends JpaRepository<AssetMaintenanceSchdDetail, Long> 
{	
	@Query(value="select coalesce(count(*),0) from ASSET_MAIN_SCHD_DETAILS b  where ((b.RESSRVPRD_SEQ_NO = :rid) and (b.asset_SEQ_NO = :aid) and (b.rule_SEQ_NO = :sid))",nativeQuery = true)
	Float getCountOfSchdDetailsByResSrvProds(@Param("rid") Long rid, @Param("aid") Long aid, @Param("sid") Long sid);
	
	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO in :ids order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getSelectSchdDetailsByResSrvProds(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO = :id order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getSelectSchdDetailsByResSrvProd(@Param("id") Long id);
	
	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b where b.asset_SEQ_NO in :ids order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getSelectSchdDetailsByAssets(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b where b.asset_SEQ_NO = :id order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getSelectSchdDetailsByAsset(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetSchdDetailsOK();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetSchdDetailsDone();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetSchdDetailsNotOK();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetSchdDetailsNotDone();

} 

