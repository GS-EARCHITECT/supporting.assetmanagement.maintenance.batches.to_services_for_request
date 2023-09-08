package assetmain_schd_master.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assetmain_schd_master.model.master.AssetMainSchdMaster;
import assetmain_schd_master.model.master.AssetMainSchdMasterPK;

@Repository("assetMainSchdMasterAdminRepo")
public interface AssetMainSchdMasterAdmin_Repo extends JpaRepository<AssetMainSchdMaster, AssetMainSchdMasterPK> 
{}