package assetmain_rule_master.controller;

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

import assetmain_rule_master.model.dto.AssetMainRuleMaster_DTO;
import assetmain_rule_master.services.I_AssetMainRuleMasterAdmin_Service;

import org.springframework.http.MediaType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/assetMainRuleMgmt")
public class AssetMainRuleMaster_Controller {

	//private static final Logger AssetMainRulegger = LoggerFactory.getLogger(AssetMainRuleRule_AssetMainRuleRule_Lontroller.class);

	@Autowired
	private I_AssetMainRuleMasterAdmin_Service assetMainRuleMasterAdminServ;
	
	@PostMapping("/new")
	public ResponseEntity<AssetMainRuleMaster_DTO> newAssetMainRuleRule(@RequestBody AssetMainRuleMaster_DTO accServPlanRuleDTO) 
	{
		AssetMainRuleMaster_DTO accServPlanRuleDTO2 = assetMainRuleMasterAdminServ.newAssetMainRule(accServPlanRuleDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(accServPlanRuleDTO2, httpHeaders, HttpStatus.CREATED);
	}

	
	@GetMapping(value = "/getAllAssetMainRule", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMainRuleMaster_DTO>> getAllAssetMainRuleMasters() {
		ArrayList<AssetMainRuleMaster_DTO> accServPlanRuleDTOs = assetMainRuleMasterAdminServ.getAllAssetMainRules();
		//AssetMainRulegger.info("size :"+accServPlanRuleDTOs.size());
		return new ResponseEntity<>(accServPlanRuleDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectAssetMainRules", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<AssetMainRuleMaster_DTO>> getSelectAssetMainRuleMasters(@RequestBody ArrayList<Long> ids)
	{
		ArrayList<AssetMainRuleMaster_DTO> accServPlanRuleDTOs = assetMainRuleMasterAdminServ.getSelectRules(ids);		
		return new ResponseEntity<>(accServPlanRuleDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/updAssetMainRuleRule")
	public void updateAccountServRule(@RequestBody AssetMainRuleMaster_DTO assetMainRuleMasterDTO)
	{
		assetMainRuleMasterAdminServ.updAssetMainRule(assetMainRuleMasterDTO);
	}

	@DeleteMapping("/delSelectAssetMainRules")
	public void deleteSelectAssetMainRules(@RequestBody ArrayList<Long> ids)
	{
		assetMainRuleMasterAdminServ.delSelectRules(ids);
	}
	
	@DeleteMapping("/delAllAssetMainRule")
	public void deleteAllAssetMainRuleRules() {
		assetMainRuleMasterAdminServ.delAllAssetMainRules();
	}
	}