package resservprod;

import java.util.concurrent.CompletableFuture;

public interface I_AssetMaintenanceResProdServRulesAssetsBatch_Service
{
// For all ResProdServs - Create Asset Maint Rows For All Rules 	
 public CompletableFuture<Void> runBatch();    
}