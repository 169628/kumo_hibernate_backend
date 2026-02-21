package web.campaign.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.google.gson.Gson;

import web.campaign.vo.CampaignDTO;
import web.campaign.dao.CampaignDao;
import web.campaign.dao.impl.CampaignDaoImpl;
import web.campaign.pojo.Campaign;
import web.campaign.service.CampaignService;

public class CampaignServiceImpl implements CampaignService{
	private CampaignDao campaignDao;
	
	public CampaignServiceImpl() throws NamingException {
		campaignDao = new CampaignDaoImpl();
	}


	@Override
	public String create(CampaignDTO campaignDTO) {
		Gson gson = new Gson();
		String brand = campaignDTO.getBrand();
		String model = campaignDTO.getModel();
		String sv = campaignDTO.getSv();
		String tv = campaignDTO.getTv();
		
		if (brand == null) {
			return "Brand is required";
		} else if (brand.length() < 2 || brand.length() > 20) {
			return "Brand must be at least 2 digits and maximum 20 digits";
		}
		
		if (model == null) {
			return "Model is required";
		} else if (model.length() < 2 || model.length() > 10) {
			return "Model must be at least 2 digits and maximum 10 digits";
		}
		
		if (sv == null) {
			return "Source version is required";
		} else if (sv.length() < 2 || sv.length() > 10) {
			return "Source version must be at least 2 digits and maximum 10 digits";
		}
		
		if (tv == null) {
			return "Target version is required";
		} else if (tv.length() < 2 || tv.length() > 10) {
			return "Target version must be at least 2 digits and maximum 10 digits";
		}
		
		if (campaignDTO.getFile() == null) {
			return "File is required";
		}
		
		if (campaignDTO.getFileSize() == null) {
			return "FileSize is required";
		}
		
		if (campaignDTO.getDownloadBy() == null) {
			return "DownloadBy is required";
		}
		
		Campaign campaign = new Campaign();
		campaign.setBrand(campaignDTO.getBrand());
		campaign.setModel(campaignDTO.getModel());
		campaign.setSv(campaignDTO.getSv());
		campaign.setTv(campaignDTO.getTv());
		campaign.setFile(campaignDTO.getFile());
		campaign.setFileSize(campaignDTO.getFileSize());
		campaign.setIsTestMode(campaignDTO.getIsTestMode());
		campaign.setTestList(campaignDTO.getTestList());
		campaign.setDownloadById("wifi".equals(campaignDTO.getDownloadBy()) ? 1 : 2);
		
		int count = campaignDao.insert(campaign);
		if (count != 1) {
			return "Create campaign failed";
		}
		return null;			
	}

	@Override
	public List<CampaignDTO> getCampaign() {
		
		List<Object[]> rows = campaignDao.selectAll();
		if(rows == null) {
			return null;
		}
		
		List<CampaignDTO> result = new ArrayList<>();
		
		for(Object[] row : rows ) {
			CampaignDTO campaignDTO = new CampaignDTO();
			campaignDTO.setNo((Integer) row[0]);
			campaignDTO.setCampaignId(((Number) row[1]).longValue());
			campaignDTO.setBrand((String) row[2]);
			campaignDTO.setModel((String) row[3]);
			campaignDTO.setSv((String) row[4]);
			campaignDTO.setTv((String) row[5]);
			campaignDTO.setFile((String) row[6]);
			campaignDTO.setFileSize((Integer) row[7]);
			campaignDTO.setIsTestMode(((Number) row[8]).intValue() == 1);
			campaignDTO.setTestList((String) row[9]);
			campaignDTO.setDownloadBy((String) row[10]);
			campaignDTO.setIsEnabled(((Number) row[11]).intValue() == 1);
			campaignDTO.setCreateAt((Timestamp) row[12]);
			campaignDTO.setUpdateAt((Timestamp) row[13]);
			campaignDTO.setIsDeleted(((Number) row[14]).intValue() == 1);
			
			result.add(campaignDTO);
		}
		
		return result;
	}


	@Override
	public CampaignDTO getOneCampaign(Integer campaignNo) {
		if(campaignNo == null) {
			return null;
		}
		
		Campaign campaign = campaignDao.selectById(campaignNo);
		if(campaign == null || campaign.getIsDeleted() == true) {
			return null;
		}
		
		CampaignDTO campaignDTO = new CampaignDTO();
		campaignDTO.setNo(campaign.getNo());
		campaignDTO.setCampaignId(campaign.getCampaignId());
		campaignDTO.setBrand(campaign.getBrand());
		campaignDTO.setModel(campaign.getModel());
		campaignDTO.setSv(campaign.getSv());
		campaignDTO.setTv(campaign.getTv());
		campaignDTO.setFile(campaign.getFile());
		campaignDTO.setFileSize(campaign.getFileSize());
		campaignDTO.setIsTestMode(campaign.getIsTestMode());
		campaignDTO.setTestList(campaign.getTestList());
		campaignDTO.setDownloadBy(campaign.getDownloadById() == 1 ? "wifi" : "user");
		campaignDTO.setIsEnabled(campaign.getIsEnabled());
		campaignDTO.setCreateAt(campaign.getCreateAt());
		campaignDTO.setUpdateAt(campaign.getUpdateAt());
		campaignDTO.setIsDeleted(campaign.getIsDeleted());
		
		return campaignDTO;

	}


	@Override
	public String delete(CampaignDTO campaignDTO) {
		if(campaignDTO.getNo() == null) {
			return "campaign no is required";
		}
		int count = campaignDao.deleteById(campaignDTO.getNo());
		if (count != 1) {
			return "Delete campaign failed";
		}
		return null;
	}


	@Override
	public String put(Integer campaignNo, CampaignDTO campaignDTO) {
		Gson gson = new Gson();
		String brand = campaignDTO.getBrand();
		String model = campaignDTO.getModel();
		String sv = campaignDTO.getSv();
		String tv = campaignDTO.getTv();
		
		if(campaignNo == null) {
			return "Campaign no is required";
		}
		
		if (brand == null) {
			return "Brand is required";
		} else if (brand.length() < 2 || brand.length() > 20) {
			return "Brand must be at least 2 digits and maximum 20 digits";
		}
		
		if (model == null) {
			return "Model is required";
		} else if (model.length() < 2 || model.length() > 10) {
			return "Model must be at least 2 digits and maximum 10 digits";
		}
		
		if (sv == null) {
			return "Source version is required";
		} else if (sv.length() < 2 || sv.length() > 10) {
			return "Source version must be at least 2 digits and maximum 10 digits";
		}
		
		if (tv == null) {
			return "Target version is required";
		} else if (tv.length() < 2 || tv.length() > 10) {
			return "Target version must be at least 2 digits and maximum 10 digits";
		}
		
		if (campaignDTO.getFile() == null) {
			return "File is required";
		}
		
		if (campaignDTO.getFileSize() == null) {
			return "FileSize is required";
		}
		
		if (campaignDTO.getDownloadBy() == null) {
			return "DownloadBy is required";
		}
		
		Campaign campaign = new Campaign();
		campaign.setNo(campaignNo);
		campaign.setIsEnabled(campaignDTO.getIsEnabled());
		campaign.setBrand(campaignDTO.getBrand());
		campaign.setModel(campaignDTO.getModel());
		campaign.setSv(campaignDTO.getSv());
		campaign.setTv(campaignDTO.getTv());
		campaign.setFile(campaignDTO.getFile());
		campaign.setFileSize(campaignDTO.getFileSize());
		campaign.setIsTestMode(campaignDTO.getIsTestMode());
		campaign.setTestList(gson.toJson(campaignDTO.getTestList()));
		campaign.setDownloadById("wifi".equals(campaignDTO.getDownloadBy()) ? 1 : 2);
		
		int count = campaignDao.update(campaign);
		if (count != 1) {
			return "Update campaign failed";
		}
		return null;	
	}



}
