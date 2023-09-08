package assetmain_rule_master.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import assetmain_rule_master.model.master.AssetMainRuleMaster;

@Repository("assetMainRuleMasterAdminRepo")
public interface AssetMainRuleMasterAdmin_Repo extends JpaRepository<AssetMainRuleMaster, Long> 
{}