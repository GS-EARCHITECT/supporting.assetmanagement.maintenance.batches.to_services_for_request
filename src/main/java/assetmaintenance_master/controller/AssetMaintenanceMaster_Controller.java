package assetmaintenance_master.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import assetmaintenance_master.model.AssetMaintenanceMasterDTO;
import assetmaintenance_master.services.I_AssetMaintenanceMasterService;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/assetMaint")
public class AssetMaintenanceMaster_Controller {

	//private static final Logger AssetMaintenancegger = LoggerFactory.getLogger(AssetMaintenance_AssetMaintenance_Lontroller.class);

	@Autowired
	private I_AssetMaintenanceMasterService assetMaintServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetMaintenanceMasterDTO> newAssetMaintenance(@RequestBody AssetMaintenanceMasterDTO assetMaintDTO) {
		AssetMaintenanceMasterDTO assetMaintDTO2 = assetMaintServ.newAssetMaintenanceMaster(assetMaintDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetMaintDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getByCate/{assetMaintSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMasterDTO>> getAssetMaintenancesByCategory(@PathVariable Integer assetMaintSeqNo) {
		ArrayList<AssetMaintenanceMasterDTO> assetMaintDTOs = assetMaintServ.getAssetMaintenanceMastersByCate(assetMaintSeqNo);		
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAssetMaintenance", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMasterDTO>> getAllAssetMaintenanceMasters() {
		ArrayList<AssetMaintenanceMasterDTO> assetMaintDTOs = assetMaintServ.getAllAssetMaintenanceMasters();
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectAssetMaintenance", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMasterDTO>> getSelectAssetMaintenanceMasters(@RequestBody ArrayList<Integer> assetMaintLoSeqNos) {
		ArrayList<AssetMaintenanceMasterDTO> assetMaintDTOs = assetMaintServ.getSelectAssetMaintenanceMasters(assetMaintLoSeqNos);		
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAssetMaintenance/{AssetMaintenanceSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AssetMaintenanceMasterDTO> getAssetMaintenanceByNo(@PathVariable String AssetMaintenanceSeqNo) {
		AssetMaintenanceMasterDTO assetMaintDTO = assetMaintServ.getAssetMaintenanceMasterById(Integer.parseInt(AssetMaintenanceSeqNo));
		return new ResponseEntity<>(assetMaintDTO, HttpStatus.OK);
	}

	@PutMapping("/updAssetMaintenance")
	public void updateAssetMaintenance(@RequestBody AssetMaintenanceMasterDTO assetMaintDTO) {
		assetMaintServ.updAssetMaintenanceMaster(assetMaintDTO);
	}

	@DeleteMapping("/delAssetMaintenance/{AssetMaintenanceSeqNo}")
	public void deleteAssetMaintenance(@PathVariable String AssetMaintenanceSeqNo) {
		assetMaintServ.delAssetMaintenanceMaster(Integer.parseInt(AssetMaintenanceSeqNo));
	}

	@DeleteMapping("/delSelectAssetMaintenance")
	public void deleteSelectAssetMaintenance(@RequestBody ArrayList<Integer> AssetMaintenanceSeqNoList) {
		assetMaintServ.delSelectAssetMaintenanceMasters(AssetMaintenanceSeqNoList);
	}
	
	@DeleteMapping("/delAllAssetMaintenance")
	public void deleteAllAssetMaintenances() {
		assetMaintServ.delAllAssetMaintenanceMasters();;
	}
	}