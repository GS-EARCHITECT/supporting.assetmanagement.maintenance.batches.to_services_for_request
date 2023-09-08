package assetmaintenance_detail.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import assetmaintenance_detail.model.dto.AssetMaintenanceDetailsDTO;
import assetmaintenance_detail.service.I_AssetMaintenanceDetailsService;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/assetMainDetails")
public class AssetMaintenance_Details_Controller {

	//private static final Logger AssMainDetailsgger = LoggerFactory.getLogger(AssetMaint_AssetMaint_Lontroller.class);

	@Autowired
	private I_AssetMaintenanceDetailsService assetMaintDetailsServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetMaintenanceDetailsDTO> newAssetMaint(@RequestBody AssetMaintenanceDetailsDTO assetMaintDetailsDTO) {
		AssetMaintenanceDetailsDTO assetMaintDetailsDTO2 = assetMaintDetailsServ.newAssetMaintenanceDetails(assetMaintDetailsDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetMaintDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllAssMainDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceDetailsDTO>> getAllAssetMaintenance_Details() {
		ArrayList<AssetMaintenanceDetailsDTO> assetMaintDetailsDTOs = assetMaintDetailsServ.getAllAssetMaintenanceDetails();
		//AssMainDetailsgger.info("size :"+assetMaintDetailsDTOs.size());
		return new ResponseEntity<>(assetMaintDetailsDTOs, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getSelectAssMainDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceDetailsDTO>> getSelectAssetMaintenance_Details(@RequestBody ArrayList<AssetMaintenanceDetailsDTO> assetMaintDetailsDTOs) {
		ArrayList<AssetMaintenanceDetailsDTO> assetMaintDetailsDTOs2 = assetMaintDetailsServ.getSelectAssetMaintDetails(assetMaintDetailsDTOs);		
		return new ResponseEntity<>(assetMaintDetailsDTOs2, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAssMainDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AssetMaintenanceDetailsDTO> getAssetMAintDetailsByNo(@RequestBody AssetMaintenanceDetailsDTO assetMaintDetailsDTO) 
	{
		AssetMaintenanceDetailsDTO assetMaintDetailsDTO2 = assetMaintDetailsServ.getAssetMaintenanceDetailsById(assetMaintDetailsDTO);
		return new ResponseEntity<>(assetMaintDetailsDTO2, HttpStatus.OK);
	}

	@PutMapping("/updAssetMaint")
	public void updateAssetMaint(@RequestBody AssetMaintenanceDetailsDTO assetMaintDetailsDTO) {
		assetMaintDetailsServ.updAssetMaintenanceDetails(assetMaintDetailsDTO);
	}

	@DeleteMapping("/delAssetMaint")
	public void deleteAssetMaint(@RequestBody AssetMaintenanceDetailsDTO assetMaintDetailsDTO)
	{
	assetMaintDetailsServ.delAssetMaintenanceDetails(assetMaintDetailsDTO);
	}

	@DeleteMapping("/delSelectAssMainDetails")
	public void deleteSelectAssMainDetails(@RequestBody ArrayList<AssetMaintenanceDetailsDTO> AssMainDetailsList) {
		assetMaintDetailsServ.delSelectAssetMaintenanceDetails(AssMainDetailsList);
	}
	
	@DeleteMapping("/delAllAssMainDetails")
	public void deleteAllAssetMaint() {
		assetMaintDetailsServ.delAllAssetMaintenanceDetails();
	}
	}