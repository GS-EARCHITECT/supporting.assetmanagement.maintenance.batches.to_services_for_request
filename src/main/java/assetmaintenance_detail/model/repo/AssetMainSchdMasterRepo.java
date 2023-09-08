package assetmaintenance_detail.model.repo;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import assetmaintenance_detail.model.master.AssetMainSchdMaster;

@Repository("assetMainSchdMasterRepo")
public interface AssetMainSchdMasterRepo extends CrudRepository<AssetMainSchdMaster, Long> 
{
	  
	  @Query(value="SELECT * FROM ASSET_MAIN_SCHD_MASTER a WHERE LAST_RUN_NO <> NO_OF_OCCURENCES  order by ASSET_MAINTENANCE_SEQ_NO",nativeQuery = true)
	  ArrayList<AssetMainSchdMaster> getSchdsByNotProcessed();

	  @Query(value="SELECT * FROM ASSET_MAIN_SCHD_MASTER a WHERE ASSET_MAINTENANCE_SEQ_NO in :seqNos order by ASSET_MAINTENANCE_SEQ_NO",nativeQuery = true)
	  ArrayList<AssetMainSchdMaster> getSelectSchds(@Param("seqNos") ArrayList<Long> seqNos);
	    
	  @Query(value="update ASSET_MAIN_SCHD_MASTER set IS_PROCESSED = 'Y' where ASSET_MAINTENANCE_SEQ_NO in :seqNos",nativeQuery = true)
	  void updateSchdProcessed(@Param("seqNos") ArrayList<Long> seqNos);
	  
	  @Query(value="update ASSET_MAIN_SCHD_MASTER set LAST_RUN_DTTM = :lastDtTm where ASSET_MAINTENANCE_SEQ_NO=:seqNo",nativeQuery = true)
	  void updateLastDtTm(@Param("seqNo") Integer seqNo, @Param("lastDtTm") Timestamp lastDtTm);
	  
	  @Query(value="DELETE FROM ASSET_MAIN_SCHD_MASTER a WHERE where ASSET_MAINTENANCE_SEQ_NO in :seqNos",nativeQuery = true)
	  void delSelectSchds(@Param("seqNos") ArrayList<Long> seqNos);     
	
}