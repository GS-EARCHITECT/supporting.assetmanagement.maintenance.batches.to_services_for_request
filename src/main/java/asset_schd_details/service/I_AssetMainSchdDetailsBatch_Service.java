package asset_schd_details.service;

import java.util.concurrent.CompletableFuture;

public interface I_AssetMainSchdDetailsBatch_Service
{
// For all ResProdServs - Create Asset Maint Rows For All Rules 	
 public CompletableFuture<Void> runService();    
}