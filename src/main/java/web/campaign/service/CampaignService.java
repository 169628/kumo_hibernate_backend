package web.campaign.service;

import java.util.List;

import web.campaign.vo.CampaignDTO;

public interface CampaignService {
	

	String create(CampaignDTO campaign);

	List<CampaignDTO> getCampaign();

	CampaignDTO getOneCampaign(Integer campaignNo);

	String delete(CampaignDTO campaign);

	String put(Integer campaignNo, CampaignDTO campaign);


}
