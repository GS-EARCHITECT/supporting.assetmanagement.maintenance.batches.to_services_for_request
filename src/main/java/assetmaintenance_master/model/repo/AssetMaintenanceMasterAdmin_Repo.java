package assetmaintenance_master.model.repo;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import assetmaintenance_master.model.master.AssetMaintenanceMaster;

@Repository("assetMaintenanceMasterAdminRepo")
public interface AssetMaintenanceMasterAdmin_Repo extends JpaRepository<AssetMaintenanceMaster, Long> 
{
@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where MAINTENANCE_CLASS_SEQ_NO in :ids ORDER BY MAINTENANCE_CLASS_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsByClasses(@Param(value = "ids") ArrayList<Long> ids);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where asset_SEQ_NO in :ids ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsByAssets(@Param(value = "ids") ArrayList<Long> ids);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(MAINTENANCE_ID)) in :ids ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsByIds(@Param(value = "ids") ArrayList<String> ids);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(okflag))='Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsOK();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(doneflag))='Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsDone();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(okflag))<> 'Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsNotOK();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where upper(trim(doneflag))<> 'Y' ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsNotDone();

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where WHERE ((:fr BETWEEN PL_DTTM_FR and PL_DTTM_TO) and (:to BETWEEN PL_DTTM_FR and PL_DTTM_TO)) ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsBetweenPlanDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);

@Query(value = "SELECT * FROM ASSET_MAINTENANCE_MASTER where WHERE ((:fr BETWEEN AC_DTTM_FR and AC_DTTM_TO) and (:to BETWEEN AC_DTTM_FR and AC_DTTM_TO)) ORDER BY asset_SEQ_NO",nativeQuery = true) 
ArrayList<AssetMaintenanceMaster> getAssetsBetweenActualDTTM(@Param(value = "fr") Timestamp fr, @Param(value = "to") Timestamp to);

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where MAINTENANCE_CLASS_SEQ_NO in :ids",nativeQuery = true) 
void delAssetsByClasses(@Param(value = "ids") ArrayList<Long> ids);

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where asset_SEQ_NO in :ids",nativeQuery = true) 
void delAssetsByAssets(@Param(value = "ids") ArrayList<Long> ids);

@Query(value = "delete FROM ASSET_MAINTENANCE_MASTER where upper(trim(MAINTENANCE_ID)) in :ids",nativeQuery = true) 
void delAssetsByIds(@Param(value = "ids") ArrayList<String> ids);

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

