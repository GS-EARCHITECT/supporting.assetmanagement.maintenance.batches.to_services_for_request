package assetmain_schd_master.controller;

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
import assetmain_schd_master.model.dto.AssetMainSchdMaster_DTO;
import assetmain_schd_master.model.master.AssetMainSchdMasterPK;
import assetmain_schd_master.services.I_AssetMainSchdMasterAdmin_Service;
import org.springframework.http.MediaType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/assetMainSchdAdminMgmt")
public class AssetMainSchdMasterAdmin_Controller {

	//private static final Logger AssetMainSchdgger = LoggerFactory.getLogger(AssetMainSchdSchd_AssetMainSchdSchd_Lontroller.class);

	@Autowired
	private I_AssetMainSchdMasterAdmin_Service assetMainSchdMasterAdminServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetMainSchdMaster_DTO> newAssetMainSchdSchd(@RequestBody AssetMainSchdMaster_DTO accServPlanSchdDTO) 
	{
		AssetMainSchdMaster_DTO accServPlanSchdDTO2 = assetMainSchdMasterAdminServ.newAssetMainSchd(accServPlanSchdDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(accServPlanSchdDTO2, httpHeaders, HttpStatus.CREATED);
	}

	
	@GetMapping(value = "/getAllAssetMainSchd", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMainSchdMaster_DTO>> getAllAssetMainSchdMasters() {
		ArrayList<AssetMainSchdMaster_DTO> accServPlanSchdDTOs = assetMainSchdMasterAdminServ.getAllAssetMainSchds();
		//AssetMainSchdgger.info("size :"+accServPlanSchdDTOs.size());
		return new ResponseEntity<>(accServPlanSchdDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectAssetMainSchds", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMainSchdMaster_DTO>> getSelectAssetMainSchdMasters(@RequestBody ArrayList<AssetMainSchdMasterPK> ids)
	{
		ArrayList<AssetMainSchdMaster_DTO> accServPlanSchdDTOs = assetMainSchdMasterAdminServ.getSelectSchds(ids);		
		return new ResponseEntity<>(accServPlanSchdDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/updAssetMainSchdSchd")
	public void updateAccountServSchd(@RequestBody AssetMainSchdMaster_DTO assetMainSchdMasterDTO)
	{
		assetMainSchdMasterAdminServ.updAssetMainSchd(assetMainSchdMasterDTO);
	}

	@DeleteMapping("/delSelectAssetMainSchds")
	public void deleteSelectAssetMainSchds(@RequestBody ArrayList<AssetMainSchdMasterPK> ids)
	{
		assetMainSchdMasterAdminServ.delSelectSchds(ids);
	}
	
	@DeleteMapping("/delAllAssetMainSchd")
	public void deleteAllAssetMainSchdSchds() {
		assetMainSchdMasterAdminServ.delAllAssetMainSchds();
	}
	}