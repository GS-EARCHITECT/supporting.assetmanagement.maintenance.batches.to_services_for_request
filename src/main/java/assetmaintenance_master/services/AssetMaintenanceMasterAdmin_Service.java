package assetmaintenance_master.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import assetmaintenance_master.model.dto.AssetMaintenanceMaster_DTO;
import assetmaintenance_master.model.master.AssetMaintenanceMaster;
import assetmaintenance_master.model.repo.AssetMaintenanceMasterAdmin_Repo;

@Service("assetMaintenanceMasterAdminServ")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMaintenanceMasterAdmin_Service implements I_AssetMaintenanceMasterAdmin_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(AssetMaintenanceMasterService.class);
	
	@Autowired
    private AssetMaintenanceMasterAdmin_Repo assetMaintenanceMasterAdminRepo;
		
	public AssetMaintenanceMaster_DTO newAssetMaintenanceMaster(AssetMaintenanceMaster_DTO lMaster) 
	{
		if(assetMaintenanceMasterAdminRepo.existsById(lMaster.getAssetMaintenanceSeqNo()))
		{
		lMaster = getAssetMaintenanceMaster_DTO(assetMaintenanceMasterAdminRepo.save(this.setAssetMaintenanceMaster(lMaster)));
		}
		return lMaster;
	}
	
	public ArrayList<AssetMaintenanceMaster_DTO> getAllAssetMaintenanceMasters() 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = (ArrayList<AssetMaintenanceMaster>) assetMaintenanceMasterAdminRepo.findAll();
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}

	public ArrayList<AssetMaintenanceMaster_DTO> getSelectAssets(ArrayList<Long> aList) 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = (ArrayList<AssetMaintenanceMaster>) assetMaintenanceMasterAdminRepo.findAllById(aList);
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}
	
	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsByClasses(ArrayList<Long> aList) 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsByClasses(aList);
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}

	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsByAssets(ArrayList<Long> aList) 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsByAssets(aList);
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}
	
	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsByIds(ArrayList<String> ids) 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsByIds(ids);
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}
	
	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsOK() 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsOK();
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}

	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsDone() 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsDone();
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}
	
	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsNotOK() 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsNotOK();
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}

	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsNotDone() 
	{
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsNotDone();
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}

	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsBetweenPlanDTTM( String fr,  String to) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
		LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsBetweenPlanDTTM(ts_Fr, ts_To);
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}
	
	public ArrayList<AssetMaintenanceMaster_DTO> getAssetsBetweenActualDTTM( String fr,  String to) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
		LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		ArrayList<AssetMaintenanceMaster> assetMaintenanceMasters = assetMaintenanceMasterAdminRepo.getAssetsBetweenActualDTTM(ts_Fr, ts_To);
		ArrayList<AssetMaintenanceMaster_DTO> lMasterss = assetMaintenanceMasters != null ? this.getAssetMaintenanceMasterDtos(assetMaintenanceMasters) : null;
		return lMasterss;
	}

	public void updAssetMaintenanceMaster(AssetMaintenanceMaster_DTO lMaster) 
	{
		
		if (assetMaintenanceMasterAdminRepo.existsById(lMaster.getAssetMaintenanceSeqNo())) 
		{
		AssetMaintenanceMaster assetMaintenanceMaster = this.setAssetMaintenanceMaster(lMaster);
		assetMaintenanceMaster.setAssetMaintenanceSeqNo(lMaster.getAssetMaintenanceSeqNo());
		assetMaintenanceMasterAdminRepo.save(assetMaintenanceMaster);
		}
		return;
	}

	public void delAllAssetMaintenanceMasters() 
	{
		assetMaintenanceMasterAdminRepo.deleteAll();
	}

	public void delSelectAssets(ArrayList<Long> aList) 
	{
		assetMaintenanceMasterAdminRepo.deleteAllById(aList);
	}

	public void delAssetsByClasses(ArrayList<Long> ids) 
	{
		assetMaintenanceMasterAdminRepo.delAssetsByClasses(ids);
	}

	public void delAssetsByAssets(ArrayList<Long> ids) 
	{
		assetMaintenanceMasterAdminRepo.delAssetsByAssets(ids);
	}

	public void delAssetsByIds(ArrayList<String> ids) 
	{
		assetMaintenanceMasterAdminRepo.delAssetsByIds(ids);
	}
	
	public void delAssetsOK()
	{
		assetMaintenanceMasterAdminRepo.delAssetsOK();
	}
	
    public void delAssetsDone()
    {
		assetMaintenanceMasterAdminRepo.delAssetsDone();;
	}
    
    public void delAssetsNotOK()
    {
		assetMaintenanceMasterAdminRepo.delAssetsNotOK();
	}
    
    public void delAssetsNotDone()
    {
		assetMaintenanceMasterAdminRepo.delAssetsNotDone();
	}
    
    
    public void delAssetsBetweenPlanDTTM( String fr,  String to)
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
		LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateOn);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		assetMaintenanceMasterAdminRepo.delAssetsBetweenPlanDTTM(ts_Fr, ts_To);
	}
    
    public void delAssetsBetweenActualDTTM( String fr,  String to)
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	LocalDateTime dateOn = LocalDateTime.parse(fr, formatter);
	LocalDateTime dateTo = LocalDateTime.parse(to, formatter);
	Timestamp ts_Fr = Timestamp.valueOf(dateOn);
	Timestamp ts_To = Timestamp.valueOf(dateTo);
	assetMaintenanceMasterAdminRepo.delAssetsBetweenActualDTTM(ts_Fr, ts_To);
    }
    
    

	private ArrayList<AssetMaintenanceMaster_DTO> getAssetMaintenanceMasterDtos(ArrayList<AssetMaintenanceMaster> lMasters) {
		AssetMaintenanceMaster_DTO lDTO = null;
		ArrayList<AssetMaintenanceMaster_DTO> lMasterDTOs = new ArrayList<AssetMaintenanceMaster_DTO>();

		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = this.getAssetMaintenanceMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetMaintenanceMaster_DTO getAssetMaintenanceMaster_DTO(AssetMaintenanceMaster lMaster) 
	{
		AssetMaintenanceMaster_DTO lDTO = new AssetMaintenanceMaster_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");				
		lDTO.setAssetSeqNo(lMaster.getAssetSeqNo());
		lDTO.setPlDttmFr(formatter.format(lMaster.getPlDttmFr().toLocalDateTime()));
		lDTO.setAcDttmFr(formatter.format(lMaster.getAcDttmFr().toLocalDateTime()));
		lDTO.setAssetMaintenanceSeqNo(lMaster.getAssetMaintenanceSeqNo());
		lDTO.setPlDttmTo(formatter.format(lMaster.getPlDttmTo().toLocalDateTime()));
		lDTO.setAcDttmTo(formatter.format(lMaster.getAcDttmTo().toLocalDateTime()));
		lDTO.setDoneflag(lMaster.getDoneflag());
		lDTO.setMaintenanceClassSeqNo(lMaster.getMaintenanceClassSeqNo());
		lDTO.setMaintenanceId(lMaster.getMaintenanceId());
		lDTO.setOkflag(lMaster.getOkflag());
		return lDTO;
	}

	private AssetMaintenanceMaster setAssetMaintenanceMaster(AssetMaintenanceMaster_DTO lDTO) {
		AssetMaintenanceMaster lMaster = new AssetMaintenanceMaster();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime pldateFr = null;
		LocalDateTime pldateTo = null;
		LocalDateTime acdateFr = null;
		LocalDateTime acdateTo = null;
		pldateFr = LocalDateTime.parse(lDTO.getPlDttmFr(), formatter);
		pldateTo = LocalDateTime.parse(lDTO.getPlDttmTo(), formatter);
		acdateFr = LocalDateTime.parse(lDTO.getAcDttmFr(), formatter);
		acdateTo = LocalDateTime.parse(lDTO.getAcDttmTo(), formatter);
		Timestamp plts_Fr = Timestamp.valueOf(pldateFr);
		Timestamp plts_To = Timestamp.valueOf(pldateTo);
		Timestamp acts_Fr = Timestamp.valueOf(acdateFr);
		Timestamp acts_To = Timestamp.valueOf(acdateTo);
		lMaster.setPlDttmFr(plts_Fr);
		lMaster.setAcDttmFr(acts_Fr);
		lMaster.setPlDttmTo(plts_To);
		lMaster.setAcDttmTo(acts_To);
		lMaster.setAssetSeqNo(lDTO.getAssetSeqNo());
		lMaster.setAssetSeqNo(lDTO.getAssetSeqNo());
		lMaster.setDoneflag(lDTO.getDoneflag());
		lMaster.setMaintenanceClassSeqNo(lDTO.getMaintenanceClassSeqNo());
		lMaster.setMaintenanceId(lDTO.getMaintenanceId());
		lMaster.setOkflag(lDTO.getOkflag());
		return lMaster;
	}
	
}