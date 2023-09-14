package common.repo;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.master.AssetDateDetail;
import common.master.AssetDateDetailPK;

@Repository("assetDateDetailsPublicReadRepo")
public interface AssetDateDetailsPublicRead_Repo extends JpaRepository<AssetDateDetail, AssetDateDetailPK> 
{
	@Query(value = "SELECT * FROM ASSET_Date_details a order by asset_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDateDetail> getAllAssetDateDetails();
	
	@Query(value = "SELECT * FROM ASSET_Date_details a WHERE a.asset_seq_no in :ids order by asset_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDateDetail> getSelectAssetDateDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);	

	@Query(value = "SELECT dttm FROM ASSET_Date_details a WHERE (a.asset_seq_no = :id and a.date_seq_no = :dt and rownum=1)", nativeQuery = true)
	Timestamp getAssetDateDetail(@Param("id") Long id, @Param("dt") Long dt);
}