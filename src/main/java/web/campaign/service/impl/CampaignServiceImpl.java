package web.campaign.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import web.campaign.vo.Campaign;
import web.campaign.vo.CampaignDTO;
import web.campaign.dao.CampaignDao;
import web.campaign.dao.impl.CampaignDaoImpl;
import web.campaign.service.CampaignService;

public class CampaignServiceImpl implements CampaignService{
	private CampaignDao campaignDao;
	
	public CampaignServiceImpl() throws NamingException {
		campaignDao = new CampaignDaoImpl();
	}


	@Override
	public String create(Campaign campaign) {
		String sv = campaign.getSv();
		String tv = campaign.getTv();
//		
//		if (campaign.getBrand() == null) {
//			return "Brand is required";
//		}
//		
//		if (campaign.getModel() == null) {
//			return "Model is required";
//		}
//		
//		if (sv == null) {
//			return "Source version is required";
//		} else if (sv.length() < 2 || sv.length() > 10) {
//			return "Source version must be at least 2 digits and maximum 10 digits";
//		}
//		
//		if (tv == null) {
//			return "Target version is required";
//		} else if (tv.length() < 2 || tv.length() > 10) {
//			return "Target version must be at least 2 digits and maximum 10 digits";
//		}
//		
//		if (campaign.getFile() == null) {
//			return "File is required";
//		}
//		
//		if (campaign.getFileSize() == null) {
//			return "FileSize is required";
//		}
//		
//		if (campaign.getDownloadBy() == null) {
//			return "DownloadBy is required";
//		}
//		
//		int count = campaignDao.insertOne(campaign);
//		
		return null;
	}

	@Override
	public List<CampaignDTO> getCampaign() {
		
		List<Object[]> rows = campaignDao.selectAll();
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



}
