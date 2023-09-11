package common.repo;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.master.AssetMaintenanceMaster;

@Repository("assetMaintenanceMasterRepo")
public interface AssetMaintenanceMaster_Repo extends JpaRepository<AssetMaintenanceMaster, Long> 
{
@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where MAINTENANCE_CLASS_SEQ_NO in :ids ORDER BY MAINTENANCE_CLASS_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsByClasses(@Param(value = "ids") CopyOnWriteArrayList<Long> ids);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where asset_SEQ_NO in :ids ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsByAssets(@Param(value = "ids") CopyOnWriteArrayList<Long> ids);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where asset_SEQ_NO = :id ORDER BY asset_maintenance_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsByAsset(@Param(value = "id") Long id);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(MAINTENANCE_ID)) in :ids ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsByIds(@Param(value = "ids") CopyOnWriteArrayList<String> ids);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(okflag))='Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsOK();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(doneflag))='Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsDone();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(okflag))<> 'Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsNotOK();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(doneflag))<> 'Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsNotDone();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where WHERE ((:fr BETWEEN PL_DTTM_FR and PL_DTTM_TO) and (:to BETWEEN PL_DTTM_FR and PL_DTTM_TO)) ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsBetweenPlanDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where WHERE ((:fr BETWEEN AC_DTTM_FR and AC_DTTM_TO) and (:to BETWEEN AC_DTTM_FR and AC_DTTM_TO)) ORDER BY asset_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<AssetMaintenanceMaster> getAssetsBetweenActualDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where MAINTENANCE_CLASS_SEQ_NO in :ids",nativeQuery = true) 
void delAssetsByClasses(@Param(value = "ids") CopyOnWriteArrayList<Long> ids);

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where asset_SEQ_NO in :ids",nativeQuery = true) 
void delAssetsByAssets(@Param(value = "ids") CopyOnWriteArrayList<Long> ids);

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where upper(trim(MAINTENANCE_ID)) in :ids",nativeQuery = true) 
void delAssetsByIds(@Param(value = "ids") CopyOnWriteArrayList<String> ids);

@Query(value = "deleteFROM ASSET_MAINTENANCE_MASTER where upper(trim(okflag))='Y'",nativeQuery = true) 
void delAssetsOK();

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where upper(trim(doneflag))='Y'",nativeQuery = true) 
void delAssetsDone();

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where upper(trim(okflag))<> 'Y'",nativeQuery = true) 
void delAssetsNotOK();

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
void delAssetsNotDone();

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where WHERE ((:fr BETWEEN PL_DTTM_FR and PL_DTTM_TO) and (:to BETWEEN PL_DTTM_FR and PL_DTTM_TO))",nativeQuery = true) 
void delAssetsBetweenPlanDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where WHERE ((:fr BETWEEN AC_DTTM_FR and AC_DTTM_TO) and (:to BETWEEN AC_DTTM_FR and AC_DTTM_TO))",nativeQuery = true) 
void delAssetsBetweenActualDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);
} 

