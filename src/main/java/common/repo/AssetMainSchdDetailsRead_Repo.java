package common.repo;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.master.AssetMainSchdDetail;
import common.master.AssetMainSchdDetailPK;

@Repository("assetMainSchdDetailsReadRepo")
public interface AssetMainSchdDetailsRead_Repo extends JpaRepository<AssetMainSchdDetail, AssetMainSchdDetailPK> 
{	
	
	@Query(value = "select schedule_no from ASSET_MAIN_SCHD_DETAILS where ((RESSRVPRD_SEQ_NO = :rid) and (asset_SEQ_NO = :aid) and (rule_SEQ_NO = :sid))",nativeQuery = true) 
	Long getScheduleNo(@Param("aid") Long aid, @Param("rid") Long rid, @Param("sid") Long sid);
	
	@Query(value="select coalesce(count(*),0) from ASSET_MAIN_SCHD_DETAILS b  where ((b.RESSRVPRD_SEQ_NO = :rid) and (b.asset_SEQ_NO = :aid) and (b.rule_SEQ_NO = :sid))",nativeQuery = true)
	Float getCountOfSchdDetailsByResSrvProds(@Param("rid") Long rid, @Param("aid") Long aid, @Param("sid") Long sid);
	
	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO in :ids order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdDetail> getSelectSchdDetailsByResSrvProds(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO = :id order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdDetail> getSelectSchdDetailsByResSrvProd(@Param("id") Long id);
	
	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b where b.asset_SEQ_NO in :ids order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdDetail> getSelectSchdDetailsByAssets(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value="select * from ASSET_MAIN_SCHD_DETAILS b where b.asset_SEQ_NO = :id order by RESSRVPRD_SEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<AssetMainSchdDetail> getSelectSchdDetailsByAsset(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMainSchdDetail> getAssetSchdDetailsOK();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMainSchdDetail> getAssetSchdDetailsDone();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMainSchdDetail> getAssetSchdDetailsNotOK();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMainSchdDetail> getAssetSchdDetailsNotDone();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where (upper(trim(doneflag))<> 'Y' and (SCHEDULE_NO IS NULL or SCHEDULE_NO <0) ) ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMainSchdDetail> getAssetSchdDetailsNotScheduled();

	@Query(value = "SELECT * FROM ASSET_MAIN_SCHD_DETAILS where (upper(trim(okflag)) = 'Y' and (upper(trim(doneflag))<> 'Y') and (SCHEDULE_NO IS NOT NULL or SCHEDULE_NO >0) ) ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMainSchdDetail> getAssetSchdDetailsOkScheduled();

} 

