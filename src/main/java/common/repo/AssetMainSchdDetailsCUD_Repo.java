package common.repo;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import common.master.AssetMainSchdDetail;
import common.master.AssetMainSchdDetailPK;

@Transactional(propagation=Propagation.REQUIRES_NEW)
@Repository("assetMainSchdDetailsCUDRepo")
public interface AssetMainSchdDetailsCUD_Repo extends JpaRepository<AssetMainSchdDetail, AssetMainSchdDetailPK> 
{
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set schedule_no = :sno where ((RESSRVPRD_SEQ_NO = :rid) and (asset_SEQ_NO = :aid) and (rule_SEQ_NO = :sid))",nativeQuery = true) 
	void setScheduleNo(@Param("aid") Long aid, @Param("rid") Long rid, @Param("sid") Long sid, @Param("sno") Long sno);
	
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set okflag = 'Y' where ((RESSRVPRD_SEQ_NO = :rid) and (asset_SEQ_NO = :aid) and (rule_SEQ_NO = :sid))",nativeQuery = true) 
	void setAssetOK(@Param("aid") Long aid, @Param("rid") Long rid, @Param("sid") Long sid);
	
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set okflag = 'N' where ((RESSRVPRD_SEQ_NO = :rid) and (asset_SEQ_NO = :aid) and (rule_SEQ_NO = :sid))",nativeQuery = true) 
	void setAssetNotOK(@Param("aid") Long aid, @Param("rid") Long rid, @Param("sid") Long sid);
		
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set okflag = 'Y' where RESSRVPRD_SEQ_NO in :ids",nativeQuery = true) 
	void setAssetsOK(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set okflag = 'N' where RESSRVPRD_SEQ_NO in :ids",nativeQuery = true) 
	void setAssetsNotOK(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set doneflag = 'Y' where ((RESSRVPRD_SEQ_NO = :rid) and (asset_SEQ_NO = :aid) and (rule_SEQ_NO = :sid))",nativeQuery = true) 
	void setAssetDone(@Param("aid") Long aid, @Param("rid") Long rid, @Param("sid") Long sid);
		
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set doneflag = 'N' where ((RESSRVPRD_SEQ_NO = :rid) and (asset_SEQ_NO = :aid) and (rule_SEQ_NO = :sid))",nativeQuery = true) 
	void setAssetNotDone(@Param("aid") Long aid, @Param("rid") Long rid, @Param("sid") Long sid);
	
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set doneflag = 'Y' where RESSRVPRD_SEQ_NO in :ids",nativeQuery = true) 
	void setAssetsDone(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Modifying
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set doneflag = 'N' where RESSRVPRD_SEQ_NO in :ids",nativeQuery = true) 
	void setAssetsNotDone(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Modifying
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where RESSRVPRD_SEQ_NO in :ids ",nativeQuery = true)
	void delSelectSchdDetailsByResSrvProds(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Modifying
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where RESSRVPRD_SEQ_NO = :id ",nativeQuery = true)
	void delSelectSchdDetailsByResSrvProd(@Param("id") Long id);
	
	@Modifying
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b where asset_SEQ_NO in :ids ",nativeQuery = true)
	void delSelectSchdDetailsByAssets(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Modifying
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b where asset_SEQ_NO = :id ",nativeQuery = true)
	void delSelectSchdDetailsByAsset(@Param("id") Long id);
	
	@Modifying
	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsOK();

	@Modifying
	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsDone();

	@Modifying
	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsNotOK();

	@Modifying
	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsNotDone();

	@Modifying
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where ASSET_MAIN_SEQ_NO in :ids ",nativeQuery = true)
	void delSelectSchedulesByMaintenance(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Modifying
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where upper(trim(schedule_id)) in :ids",nativeQuery = true)
	void delSelectSchedulesByScheduleIds(@Param("ids") CopyOnWriteArrayList<String> ids);
	
	@Modifying
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where RESSRVPRD_SEQ_NO in :ids",nativeQuery = true)
	void delSelectSchedulesByResSrvProds(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Modifying
	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))='Y'",nativeQuery = true) 
	void delAssetsOK();

	@Modifying
	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))='Y'",nativeQuery = true) 
	void delAssetsDone();

	@Modifying
	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))<> 'Y'",nativeQuery = true) 
	void delAssetsNotOK();

	@Modifying
	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
	void delAssetsNotDone();

} 

