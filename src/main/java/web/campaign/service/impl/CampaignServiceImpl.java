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
	public String create(CampaignDTO campaign) {
		Gson gson = new Gson();
		String sv = campaign.getSv();
		String tv = campaign.getTv();
		
		if (campaign.getBrand() == null) {
			return "Brand is required";
		}
		
		if (campaign.getModel() == null) {
			return "Model is required";
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
		
		if (campaign.getFile() == null) {
			return "File is required";
		}
		
		if (campaign.getFileSize() == null) {
			return "FileSize is required";
		}
		
		if (campaign.getDownloadBy() == null) {
			return "DownloadBy is required";
		}
		
		Campaign newCampaign = new Campaign();
		newCampaign.setBrand(campaign.getBrand());
		newCampaign.setModel(campaign.getModel());
		newCampaign.setSv(campaign.getSv());
		newCampaign.setTv(campaign.getTv());
		newCampaign.setFile(campaign.getFile());
		newCampaign.setFileSize(campaign.getFileSize());
		newCampaign.setIsTestMode(campaign.getIsTestMode());
		newCampaign.setTestList(gson.toJson(campaign.getTestList()));
		newCampaign.setDownloadById("wifi".equals(campaign.getDownloadBy()) ? 1 : 2);
		
		int count = campaignDao.insertOne(newCampaign);
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
			CampaignDTO campaign = new CampaignDTO();
			campaign.setNo((Integer) row[0]);
			campaign.setCampaignId(((Number) row[1]).longValue());
			campaign.setBrand((String) row[2]);
			campaign.setModel((String) row[3]);
			campaign.setSv((String) row[4]);
			campaign.setTv((String) row[5]);
			campaign.setFile((String) row[6]);
			campaign.setFileSize((Integer) row[7]);
			campaign.setIsTestMode(((Number) row[8]).intValue() == 1);
			campaign.setTestList((String) row[9]);
			campaign.setDownloadBy((String) row[10]);
			campaign.setIsEnabled(((Number) row[11]).intValue() == 1);
			campaign.setCreateAt((Timestamp) row[12]);
			campaign.setUpdateAt((Timestamp) row[13]);
			campaign.setIsDeleted(((Number) row[14]).intValue() == 1);
			
			result.add(campaign);
		}
		
		return result;
	}


	@Override
	public CampaignDTO getOneCampaign(Integer campaignNo) {
		if(campaignNo == null) {
			return null;
		}
		
		Campaign selectResult = campaignDao.selectOne(campaignNo);
		if(selectResult == null) {
			return null;
		}
		
		CampaignDTO campaign = new CampaignDTO();
		campaign.setNo(selectResult.getNo());
		campaign.setCampaignId(selectResult.getCampaignId());
		campaign.setBrand(selectResult.getBrand());
		campaign.setModel(selectResult.getModel());
		campaign.setSv(selectResult.getSv());
		campaign.setTv(selectResult.getTv());
		campaign.setFile(selectResult.getFile());
		campaign.setFileSize(selectResult.getFileSize());
		campaign.setIsTestMode(selectResult.getIsTestMode());
		campaign.setTestList(selectResult.getTestList());
		campaign.setDownloadBy(selectResult.getDownloadById() == 1 ? "wifi" : "user");
		campaign.setIsEnabled(selectResult.getIsEnabled());
		campaign.setCreateAt(selectResult.getCreateAt());
		campaign.setUpdateAt(selectResult.getUpdateAt());
		campaign.setIsDeleted(selectResult.getIsDeleted());
		
		return campaign;

	}


	@Override
	public String delete(CampaignDTO campaign) {
		if(campaign.getNo() == null) {
			return "campaign no is required";
		}
		int count = campaignDao.delete(campaign.getNo());
		if (count != 1) {
			return "Delete campaign failed";
		}
		return null;
	}



}
