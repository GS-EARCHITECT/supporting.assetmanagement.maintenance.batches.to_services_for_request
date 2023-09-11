package asset_schd_details.model.repo;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.master.AssetMainSchdDetail;
import common.master.AssetMainSchdDetailPK;

@Repository("assetMainSchdDetailsCUDRepo")
public interface AssetMainSchdDetailsCUD_Repo extends JpaRepository<AssetMainSchdDetail, AssetMainSchdDetailPK> 
{
	
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set okflag = 'Y' where b.RESSRVPRD_SEQ_NO = :id",nativeQuery = true) 
	void setAssetOK();
	
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set okflag = 'Y' where b.RESSRVPRD_SEQ_NO in :ids",nativeQuery = true) 
	void setAssetsOK(@Param("ids") ArrayList<Long> ids);

	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set doneflag = 'Y' where b.RESSRVPRD_SEQ_NO = :id",nativeQuery = true) 
	void setAssetDone();
	
	@Query(value = "update ASSET_MAIN_SCHD_DETAILS set doneflag = 'Y' where b.RESSRVPRD_SEQ_NO in :ids",nativeQuery = true) 
	void setAssetsDone(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))='Y'",nativeQuery = true) 
	void setAssetsDone();

	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))<> 'Y'",nativeQuery = true) 
	void setAssetsNotOK();

	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
	void setAssetsNotDone();

	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO in :ids ",nativeQuery = true)
	void delSelectSchdDetailsByResSrvProds(@Param("ids") ArrayList<Long> ids);

	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO = :id ",nativeQuery = true)
	void delSelectSchdDetailsByResSrvProd(@Param("id") Long id);
	
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b where b.asset_SEQ_NO in :ids ",nativeQuery = true)
	void delSelectSchdDetailsByAssets(@Param("ids") ArrayList<Long> ids);
	
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b where b.asset_SEQ_NO = :id ",nativeQuery = true)
	void delSelectSchdDetailsByAsset(@Param("id") Long id);
	
	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsOK();

	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))='Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsDone();

	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsNotOK();

	@Query(value = "delete from ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))<> 'Y' ORDER BY RESSRVPRD_SEQ_NO",nativeQuery = true) 
	void delAssetSchdDetailsNotDone();

	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where b.ASSET_MAIN_SEQ_NO in :ids ",nativeQuery = true)
	void delSelectSchedulesByMaintenance(@Param("ids") ArrayList<Long> ids);
	
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where upper(trim(b.schedule_id)) in :ids",nativeQuery = true)
	void delSelectSchedulesByScheduleIds(@Param("ids") ArrayList<String> ids);
	
	@Query(value="delete from ASSET_MAIN_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO in :ids",nativeQuery = true)
	void delSelectSchedulesByResSrvProds(@Param("ids") ArrayList<Long> ids);

	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))='Y'",nativeQuery = true) 
	void delAssetsOK();

	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))='Y'",nativeQuery = true) 
	void delAssetsDone();

	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(okflag))<> 'Y'",nativeQuery = true) 
	void delAssetsNotOK();

	@Query(value = "delete FROM ASSET_MAIN_SCHD_DETAILS where upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
	void delAssetsNotDone();

} 

