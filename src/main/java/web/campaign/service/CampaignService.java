package web.campaign.service;

import java.util.List;

import web.campaign.vo.CampaignDTO;

public interface CampaignService {
	List<CampaignDTO> getCampaign();

	String create(CampaignDTO campaign);

	CampaignDTO getOneCampaign(Integer campaignNo);

	String delete(CampaignDTO campaign);


}
