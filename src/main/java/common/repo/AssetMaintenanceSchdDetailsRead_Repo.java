package common.repo;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import common.master.AssetMaintenanceSchdDetail;

@Transactional
@Repository("assetMaintenanceSchdDetailsReadRepo")
public interface AssetMaintenanceSchdDetailsRead_Repo extends JpaRepository<AssetMaintenanceSchdDetail, Long> 
{	
	
	@Query(value="select * from ASSET_MAINTENANCE_SCHD_DETAILS b  where b.ASSET_MAINTENANCE_SEQ_NO = :id order by schedule_seq_no",nativeQuery = true)
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getSelectSchedulesByMaintenance(@Param("id") Long id);
	
	@Query(value="select * from ASSET_MAINTENANCE_SCHD_DETAILS b  where upper(trim(b.schedule_id)) in :ids order by schedule_seq_no",nativeQuery = true)
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getSelectSchedulesByScheduleIds(@Param("ids") CopyOnWriteArrayList<String> ids);
	
	@Query(value="select * from ASSET_MAINTENANCE_SCHD_DETAILS b  where b.RESSRVPRD_SEQ_NO in :ids order by schedule_seq_no",nativeQuery = true)
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getSelectSchedulesByResSrvProds(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM ASSET_MAINTENANCE_SCHD_DETAILS where upper(trim(okflag))='Y' ORDER BY schedule_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetsOK();

	@Query(value = "SELECT * FROM ASSET_MAINTENANCE_SCHD_DETAILS where upper(trim(doneflag))='Y' ORDER BY schedule_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetsDone();

	@Query(value = "SELECT * FROM ASSET_MAINTENANCE_SCHD_DETAILS where upper(trim(okflag))<> 'Y' ORDER BY schedule_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetsNotOK();

	@Query(value = "SELECT * FROM ASSET_MAINTENANCE_SCHD_DETAILS where upper(trim(doneflag))<> 'Y' ORDER BY schedule_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetsNotDone();

	@Query(value = "SELECT * FROM ASSET_MAINTENANCE_SCHD_DETAILS where upper(trim(wipflag)) = 'Y' ORDER BY schedule_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetsWIP();
	
	@Query(value = "SELECT * FROM ASSET_MAINTENANCE_SCHD_DETAILS where upper(trim(wipflag))<> 'Y' ORDER BY schedule_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetsNotWIP();
	
	@Query(value = "SELECT * FROM ASSET_MAINTENANCE_SCHD_DETAILS where ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM)) ORDER BY schedule_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<AssetMaintenanceSchdDetail> getAssetsBetweenDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);

		
} 

