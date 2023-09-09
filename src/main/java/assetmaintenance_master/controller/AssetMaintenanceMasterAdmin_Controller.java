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
import assetmaintenance_master.model.dto.AssetMaintenanceMaster_DTO;
import assetmaintenance_master.services.I_AssetMaintenanceMasterAdmin_Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/assetMaintenanceMasterAdminMgmt")
public class AssetMaintenanceMasterAdmin_Controller {

	// private static final Logger AssetMaintenancegger =
	// LoggerFactory.getLogger(AssetMaintenance_AssetMaintenance_Lontroller.class);

	@Autowired
	private I_AssetMaintenanceMasterAdmin_Service assetMaintenanceMasterAdminService;

	@PostMapping("/new")
	public ResponseEntity<AssetMaintenanceMaster_DTO> newAssetMaintenance(
			@RequestBody AssetMaintenanceMaster_DTO assetMaintDTO) {
		AssetMaintenanceMaster_DTO assetMaintDTO2 = assetMaintenanceMasterAdminService
				.newAssetMaintenanceMaster(assetMaintDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(assetMaintDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllAssetMaintenance", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAllAssetMaintenanceMasters() {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService
				.getAllAssetMaintenanceMasters();
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectAssetMaintenance", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getSelectAssetMaintenanceMasters(
			@RequestBody ArrayList<Long> assetMaintLoSeqNos) {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService
				.getSelectAssets(assetMaintLoSeqNos);
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsByClasses", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsByClasses(
			@RequestBody ArrayList<Long> aList) {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService
				.getAssetsByClasses(aList);
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsByAssets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsByAssets(@RequestBody ArrayList<Long> aList) {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService
				.getAssetsByAssets(aList);
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsByIds", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsByIds(@RequestBody ArrayList<String> aList) {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService.getAssetsByIds(aList);
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsOK", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsOK() {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService.getAssetsOK();
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsDone", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsDone() {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService.getAssetsDone();
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsNotOK", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsNotOK() {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService.getAssetsNotOK();
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsNotDone", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsNotDone() {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService.getAssetsNotDone();
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsBetweenPlanDTTM/{fr]/[to]", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsBetweenPlanDTTM(@PathVariable String fr,
			@PathVariable String to) {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService
				.getAssetsBetweenPlanDTTM(fr, to);
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssetsBetweenActualDTTM/{fr]/[to]", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMaintenanceMaster_DTO>> getAssetsBetweenActualDTTM(@PathVariable String fr,
			@PathVariable String to) {
		ArrayList<AssetMaintenanceMaster_DTO> assetMaintDTOs = assetMaintenanceMasterAdminService
				.getAssetsBetweenActualDTTM(fr, to);
		return new ResponseEntity<>(assetMaintDTOs, HttpStatus.OK);
	}

	@PutMapping("/updAssetMaintenance")
	public void updateAssetMaintenance(@RequestBody AssetMaintenanceMaster_DTO assetMaintDTO) {
		assetMaintenanceMasterAdminService.updAssetMaintenanceMaster(assetMaintDTO);
	}

	@DeleteMapping("/delSelectAssets")
	public void deleteSelectAssets(@RequestBody ArrayList<Long> aList) {
		assetMaintenanceMasterAdminService.delSelectAssets(aList);
	}

	@DeleteMapping("/delAssetsByClasses")
	public void delAssetsByClasses(@RequestBody ArrayList<Long> aList) {
		assetMaintenanceMasterAdminService.delAssetsByClasses(aList);
	}

	@DeleteMapping("/delAssetsByAssets")
	public void delAssetsByAssets(@RequestBody ArrayList<Long> aList) {
		assetMaintenanceMasterAdminService.delAssetsByAssets(aList);
	}

	@DeleteMapping("/delAssetsByIds")
	public void delAssetsByIds(@RequestBody ArrayList<String> sList) {
		assetMaintenanceMasterAdminService.delAssetsByIds(sList);
	}

	@DeleteMapping("/delAssetsOK")
	public void delAssetsOK() {
		assetMaintenanceMasterAdminService.delAssetsOK();
	}

	@DeleteMapping("/delAssetsDone")
	public void delAssetsDone() {
		assetMaintenanceMasterAdminService.delAssetsDone();
	}

	@DeleteMapping("/delAssetsNotOK")
	public void delAssetsNotOK() {
		assetMaintenanceMasterAdminService.delAssetsNotOK();
	}

	@DeleteMapping("/delAssetsNotDone")
	public void delAssetsNotDone() {
		assetMaintenanceMasterAdminService.delAssetsNotDone();
	}

	@DeleteMapping("/delAssetsBetweenPlanDTTM/[fr]/[to]")
	public void delAssetsBetweenPlanDTTM(@PathVariable String fr, @PathVariable String to) {
		assetMaintenanceMasterAdminService.delAssetsBetweenPlanDTTM(fr, to);
	}

	@DeleteMapping("/delAssetsBetweenActualDTTM/[fr]/[to]")
	public void delAssetsBetweenActualDTTM(@PathVariable String fr, @PathVariable String to) {
		assetMaintenanceMasterAdminService.delAssetsBetweenActualDTTM(fr, to);
	}

	@DeleteMapping("/delAllAssetMaintenance")
	public void deleteAllAssetMaintenances() {
		assetMaintenanceMasterAdminService.delAllAssetMaintenanceMasters();
		;
	}
}