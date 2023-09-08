package assetmaintenance_detail.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import assetmaintenance_detail.model.detail.AssetMainSchdDetails;
import assetmaintenance_detail.model.detail.AssetMainSchdDetailsPK;
import assetmaintenance_detail.model.detail.AssetMaintenanceDetails;
import assetmaintenance_detail.model.detail.AssetMaintenanceDetailsPK;
import assetmaintenance_detail.model.dto.AssetMaintenanceDetailsDTO;
import assetmaintenance_detail.model.master.AssetMainSchdMaster;
import assetmaintenance_detail.model.repo.AssetMainSchdDetailsRepo;
import assetmaintenance_detail.model.repo.AssetMainSchdMasterRepo;
import assetmaintenance_detail.model.repo.AssetMaintDetailsRepo;
import common.dateUtils.DateUtil;

@Service("assetMaintDetailsServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AssetMaintenanceDetailsService implements I_AssetMaintenanceDetailsService 
{
	private static final Logger logger = LoggerFactory.getLogger(AssetMaintenanceDetailsService.class);
	
	@Autowired
    private AssetMainSchdMasterRepo assetMainSchdMasterRepo;
	
	@Autowired
    private AssetMaintDetailsRepo assetMaintDetailsRepo;
	
	@Autowired
    private AssetMainSchdDetailsRepo assetMainSchdDetailsRepo;
		
	@Scheduled(fixedRate = 3000)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public void ScheduleECS() {
		ArrayList<AssetMainSchdMaster> assetSchdMasters = assetMainSchdMasterRepo.getSchdsByNotProcessed();
		Timestamp currDate = null;
		Long datetime = null;
		Integer runSeqNo = 0;
		Timestamp sysDtTm = null;		
		Integer nextRunNo = 0;
		Timestamp lastDtTm = null;
		AssetMainSchdDetailsPK assetMainSchdDetailsPK = null;
		AssetMainSchdDetails assetMainSchdDetails = null;

		if (assetSchdMasters!=null) 
		{
			for (int i = 0; i < assetSchdMasters.size(); i++) 
			{
				if (!assetSchdMasters.get(i).getLastRunNo().equals(assetSchdMasters.get(i).getNoOfOccurences())) 
				{
					lastDtTm =assetSchdMasters.get(i).getLastRunDttm();
					runSeqNo=assetSchdMasters.get(i).getLastRunNo();
					currDate = DateUtil.addDays(lastDtTm,assetSchdMasters.get(i).getLapseDays(), 0, 0, 0);
					datetime = System.currentTimeMillis();
					sysDtTm = new Timestamp(datetime);
					if (sysDtTm.getTime() > currDate.getTime()) 
					{
						assetMainSchdDetailsPK = new AssetMainSchdDetailsPK();
						assetMainSchdDetails = new AssetMainSchdDetails();
						nextRunNo = assetMainSchdDetailsRepo.getLastRunNoForSchd(assetSchdMasters.get(i).getLastRunNo());
						assetMainSchdDetailsPK.setRunNo(nextRunNo + 1);
						assetMainSchdDetailsPK.setAssetMaintenanceSeqNo(assetSchdMasters.get(i).getId().getAssetMaintenanceSeqNo());
						assetMainSchdDetails.setId(assetMainSchdDetailsPK);						
						assetMainSchdDetails.setRunDate(sysDtTm);
						assetMainSchdDetails.setIsProcessed('Y');
						assetMainSchdDetails.setRemark("");
						assetMainSchdDetails.setStatus("");
						assetMainSchdMasterRepo.updateLastDtTm(runSeqNo, currDate);						
					}
				}

			}
		}
		return;
	}
	
	public AssetMaintenanceDetailsDTO newAssetMaintenanceDetails(AssetMaintenanceDetailsDTO lMaster) 
	{
		Optional<AssetMaintenanceDetails> assetMaintenanceDetails = null;
		AssetMaintenanceDetails assetMaintenanceDetails2 = null;
		AssetMaintenanceDetailsPK assetMaintenanceDetailsPK = new AssetMaintenanceDetailsPK();  		
		assetMaintenanceDetailsPK.setAssetMaintenanceSeqNo(lMaster.getAssetMaintenanceSeqNo());
		assetMaintenanceDetailsPK.setAssetSeqNo(lMaster.getAssetSeqNo());
		assetMaintenanceDetailsPK.setServiceSeqNo(lMaster.getServiceSeqNo());
		assetMaintenanceDetails = assetMaintDetailsRepo.findById(assetMaintenanceDetailsPK); 
		
		if(!assetMaintenanceDetails.isPresent())
		{			
		assetMaintenanceDetails2 = setAssetMaintenanceDetails(lMaster);	
		assetMaintenanceDetails2.setId(assetMaintenanceDetailsPK);
		assetMaintenanceDetails2 = assetMaintDetailsRepo.save(assetMaintenanceDetails2);
		lMaster = getAssetMaintenanceDetailsDTO(assetMaintenanceDetails2);
		}
		return lMaster;
	}

	public ArrayList<AssetMaintenanceDetailsDTO> getAllAssetMaintenanceDetails() {
		ArrayList<AssetMaintenanceDetails> assetMaintList =  (ArrayList<AssetMaintenanceDetails>) assetMaintDetailsRepo.findAll();
		ArrayList<AssetMaintenanceDetailsDTO> lMasterss = new ArrayList<AssetMaintenanceDetailsDTO>();
		lMasterss = assetMaintList != null ? this.getAssetMaintenanceDetailsDtos(assetMaintList) : null;
		return lMasterss;
	}

	
	public ArrayList<AssetMaintenanceDetailsDTO> getSelectAssetMaintDetails(ArrayList<AssetMaintenanceDetailsDTO> assetMaintList) 
	{
		ArrayList<AssetMaintenanceDetailsDTO> lMasters = new ArrayList<AssetMaintenanceDetailsDTO>();
		AssetMaintenanceDetailsPK assetMaintenanceDetailsPK = null;
		Optional<AssetMaintenanceDetails> assetMaintMaster = null;
		AssetMaintenanceDetailsDTO AssetMaintenanceDetailsDTO = null;

		if(assetMaintList!=null)
		{			
		for (int i = 0; i < assetMaintList.size(); i++) 
		{
			assetMaintenanceDetailsPK = new AssetMaintenanceDetailsPK();
			assetMaintenanceDetailsPK.setAssetMaintenanceSeqNo(assetMaintList.get(i).getAssetMaintenanceSeqNo());
			assetMaintenanceDetailsPK.setServiceSeqNo(assetMaintList.get(i).getServiceSeqNo());
			assetMaintenanceDetailsPK.setAssetSeqNo(assetMaintList.get(i).getAssetSeqNo());
			assetMaintMaster = assetMaintDetailsRepo.findById(assetMaintenanceDetailsPK);
			if (assetMaintMaster.isPresent() && assetMaintMaster != null) {
				AssetMaintenanceDetailsDTO = this.getAssetMaintenanceDetailsDTO(assetMaintMaster.get());
				lMasters.add(AssetMaintenanceDetailsDTO);
			}
		}
		}
		return lMasters;
	}

	public AssetMaintenanceDetailsDTO getAssetMaintenanceDetailsById(AssetMaintenanceDetailsDTO assetMaintDetailsDTO) 
	{
		AssetMaintenanceDetailsDTO lMaster = null;
		
		if(assetMaintDetailsDTO!=null)
		{
		Optional<AssetMaintenanceDetails> assetMaintMaster = null;
		AssetMaintenanceDetailsPK assetMaintenanceDetailsPK = null;
		assetMaintenanceDetailsPK = new AssetMaintenanceDetailsPK();
		assetMaintenanceDetailsPK.setAssetMaintenanceSeqNo(assetMaintDetailsDTO.getAssetMaintenanceSeqNo());
		assetMaintenanceDetailsPK.setServiceSeqNo(assetMaintDetailsDTO.getServiceSeqNo());
		assetMaintenanceDetailsPK.setAssetSeqNo(assetMaintDetailsDTO.getAssetSeqNo());
		assetMaintMaster = assetMaintDetailsRepo.findById(assetMaintenanceDetailsPK);
		
		if (assetMaintMaster.isPresent()) 
		{
		lMaster = assetMaintMaster != null ? this.getAssetMaintenanceDetailsDTO(assetMaintMaster.get()) : null;
		}
		}
		return lMaster;
	}

	public void updAssetMaintenanceDetails(AssetMaintenanceDetailsDTO lMaster) 
	{
		if(lMaster!=null)
		{
		AssetMaintenanceDetailsPK assetMaintenanceDetailsPK = null;
		AssetMaintenanceDetails assetMaintMaster =null; 
		assetMaintenanceDetailsPK = new AssetMaintenanceDetailsPK();
		assetMaintenanceDetailsPK.setAssetMaintenanceSeqNo(lMaster.getAssetMaintenanceSeqNo());
		assetMaintenanceDetailsPK.setServiceSeqNo(lMaster.getServiceSeqNo());
		assetMaintenanceDetailsPK.setAssetSeqNo(lMaster.getAssetSeqNo());	
		
		if (assetMaintDetailsRepo.existsById(assetMaintenanceDetailsPK))
		{
			assetMaintMaster = setAssetMaintenanceDetails(lMaster); 
			assetMaintMaster.setId(assetMaintenanceDetailsPK);
			assetMaintDetailsRepo.save(assetMaintMaster);
		}
		}
	}

	public void delAssetMaintenanceDetails(AssetMaintenanceDetailsDTO lMaster) 
	{
		if(lMaster!=null)
		{
		AssetMaintenanceDetailsPK assetMaintenanceDetailsPK = null;		
		assetMaintenanceDetailsPK = new AssetMaintenanceDetailsPK();
		assetMaintenanceDetailsPK.setAssetMaintenanceSeqNo(lMaster.getAssetMaintenanceSeqNo());
		assetMaintenanceDetailsPK.setServiceSeqNo(lMaster.getServiceSeqNo());
		assetMaintenanceDetailsPK.setAssetSeqNo(lMaster.getAssetSeqNo());	
		
		if (assetMaintDetailsRepo.existsById(assetMaintenanceDetailsPK))
		{	
			assetMaintDetailsRepo.deleteById(assetMaintenanceDetailsPK);
		}
		}

	}
	
	public void delAllAssetMaintenanceDetails() {
		assetMaintDetailsRepo.deleteAll();
	}

	public void delSelectAssetMaintenanceDetails(ArrayList<AssetMaintenanceDetailsDTO> assetMaintList) 
	{
		AssetMaintenanceDetailsPK assetMaintenanceDetailsPK = null;
		Optional<AssetMaintenanceDetails> assetMaintMaster = null;
		
		if(assetMaintList!=null)
		{			
		for (int i = 0; i < assetMaintList.size(); i++) 
		{
			assetMaintenanceDetailsPK = new AssetMaintenanceDetailsPK();
			assetMaintenanceDetailsPK.setAssetMaintenanceSeqNo(assetMaintList.get(i).getAssetMaintenanceSeqNo());
			assetMaintenanceDetailsPK.setServiceSeqNo(assetMaintList.get(i).getServiceSeqNo());
			assetMaintenanceDetailsPK.setAssetSeqNo(assetMaintList.get(i).getAssetSeqNo());
			assetMaintMaster = assetMaintDetailsRepo.findById(assetMaintenanceDetailsPK);
			if (assetMaintMaster.isPresent() && assetMaintMaster != null) {				
				assetMaintDetailsRepo.deleteById(assetMaintenanceDetailsPK);
			}
		}
		}

	}

	private ArrayList<AssetMaintenanceDetailsDTO> getAssetMaintenanceDetailsDtos(ArrayList<AssetMaintenanceDetails> lMasters) {
		AssetMaintenanceDetailsDTO lDTO = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ArrayList<AssetMaintenanceDetailsDTO> lMasterDTOs = new ArrayList<AssetMaintenanceDetailsDTO>();
		
		for (int i = 0; i < lMasters.size(); i++)
		{
			lDTO = new AssetMaintenanceDetailsDTO();					
			lDTO.setDateFrom(formatter.format(lMasters.get(i).getDateFrom().toLocalDateTime()));
			lDTO.setDateTo(formatter.format(lMasters.get(i).getDateTo().toLocalDateTime()));
			lDTO.setAssetMaintenanceSeqNo(lMasters.get(i).getId().getAssetMaintenanceSeqNo());
			lDTO.setAssetSeqNo(lMasters.get(i).getId().getAssetSeqNo());
			lDTO.setServiceSeqNo(lMasters.get(i).getId().getServiceSeqNo());
			lDTO.setDuration(lMasters.get(i).getDuration());
			lDTO.setDurationSeqNo(lMasters.get(i).getDurationSeqNo());
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private AssetMaintenanceDetailsDTO getAssetMaintenanceDetailsDTO(AssetMaintenanceDetails lMaster) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		AssetMaintenanceDetailsDTO lDTO = new AssetMaintenanceDetailsDTO();		
		lDTO.setDateFrom(formatter.format(lMaster.getDateFrom().toLocalDateTime()));
		lDTO.setDateTo(formatter.format(lMaster.getDateTo().toLocalDateTime()));
		lDTO.setAssetMaintenanceSeqNo(lMaster.getId().getAssetMaintenanceSeqNo());
		lDTO.setAssetSeqNo(lMaster.getId().getAssetSeqNo());
		lDTO.setServiceSeqNo(lMaster.getId().getServiceSeqNo());
		lDTO.setDuration(lMaster.getDuration());
		lDTO.setDurationSeqNo(lMaster.getDurationSeqNo());
		return lDTO;
	}

	private AssetMaintenanceDetails setAssetMaintenanceDetails(AssetMaintenanceDetailsDTO lDTO) {
		AssetMaintenanceDetails lMaster = new AssetMaintenanceDetails();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = LocalDateTime.parse(lDTO.getDateFrom(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(lDTO.getDateTo(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		lMaster.setDateFrom(ts_Fr);
		lMaster.setDateTo(ts_To);
		lMaster.setDuration(lDTO.getDuration());
		lMaster.setDurationSeqNo(lDTO.getDurationSeqNo());
		return lMaster;
	}
	
}