package assetmaintenance_class_master.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import assetmaintenance_class_master.model.dto.AssetMaintenanceClassMaster_DTO;
import assetmaintenance_class_master.model.master.AssetMaintenanceClassMasterPK;
import assetmaintenance_class_master.services.I_AssetMaintenanceClassMasterAdmin_Service;

@RestController
@RequestMapping("/assetMaintClassMgmt")
public class AssetMaintenanceClassMaster_Controller {

	//private static final Logger AssetMaintenanceClassgger = LoggerFactory.getLogger(AssetMaintenanceClassClass_AssetMaintenanceClassClass_Lontroller.class);

	@Autowired
	private I_AssetMaintenanceClassMasterAdmin_Service assetMaintClassMasterAdminServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetMaintenanceClassMaster_DTO> newAssetMaintenanceClassClass(@RequestBody AssetMaintenanceClassMaster_DTO accServPlanClassDTO) 
	{
		AssetMaintenanceClassMaster_DTO accServPlanClassDTO2 = assetMaintClassMasterAdminServ.newAssetMaintenanceClass(accServPlanClassDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(accServPlanClassDTO2, httpHeaders, HttpStatus.CREATED);
	}

	
	@GetMapping(value = "/getAllAssetMaintenanceClass", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceClassMaster_DTO>> getAllAssetMaintenanceClassMasters() {
		ArrayList<AssetMaintenanceClassMaster_DTO> accServPlanClassDTOs = assetMaintClassMasterAdminServ.getAllAssetMaintenanceClasses();
		//AssetMaintenanceClassgger.info("size :"+accServPlanClassDTOs.size());
		return new ResponseEntity<>(accServPlanClassDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectAssetMaintenanceClasses", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceClassMaster_DTO>> getSelectAssetMaintenanceClassMasters(@RequestBody ArrayList<AssetMaintenanceClassMasterPK> assetMaintenanceClassMasterPKs)
	{
		ArrayList<AssetMaintenanceClassMaster_DTO> accServPlanClassDTOs = assetMaintClassMasterAdminServ.getSelectClasses(assetMaintenanceClassMasterPKs);		
		return new ResponseEntity<>(accServPlanClassDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectAssetMaintenanceClassesByParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceClassMaster_DTO>> getSelectAssetMaintenanceClassMastersByParties(@RequestBody ArrayList<Long> ids)
	{
		ArrayList<AssetMaintenanceClassMaster_DTO> accServPlanClassDTOs = assetMaintClassMasterAdminServ.getSelectClassesByParties(ids);		
		return new ResponseEntity<>(accServPlanClassDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectAssetMaintenanceClassesByResources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceClassMaster_DTO>> getSelectAssetMaintenanceClassMastersByResources(@RequestBody ArrayList<Long> ids)
	{
		ArrayList<AssetMaintenanceClassMaster_DTO> accServPlanClassDTOs = assetMaintClassMasterAdminServ.getSelectClassesByResources(ids);		
		return new ResponseEntity<>(accServPlanClassDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/updAssetMaintenanceClassClass")
	public void updateAccountServClass(@RequestBody AssetMaintenanceClassMaster_DTO assetMaintenanceClassMasterDTO)
	{
		assetMaintClassMasterAdminServ.updAssetMaintenanceClass(assetMaintenanceClassMasterDTO);
	}

	@DeleteMapping("/delSelectAssetMaintenanceClasses")
	public void deleteSelectAssetMaintenanceClasses(@RequestBody ArrayList<AssetMaintenanceClassMasterPK> assetMaintenanceClassMasterPKs)
	{
		assetMaintClassMasterAdminServ.delSelectClasses(assetMaintenanceClassMasterPKs);
	}
	
	@DeleteMapping("/delSelectAssetMaintenanceClassesByParties")
	public void deleteSelectAssetMaintenanceClassesByParties(@RequestBody ArrayList<Long> ids)
	{
		assetMaintClassMasterAdminServ.delSelectClassesByParties(ids);
	}

	@DeleteMapping("/delSelectAssetMaintenanceClassesByResources")
	public void deleteSelectAssetMaintenanceClassesByResources(@RequestBody ArrayList<Long> ids)
	{
		assetMaintClassMasterAdminServ.delSelectClassesByResources(ids);
	}

	
	@DeleteMapping("/delAllAssetMaintenanceClass")
	public void deleteAllAssetMaintenanceClassClasss() {
		assetMaintClassMasterAdminServ.delAllAssetMaintenanceClasses();
	}
	}