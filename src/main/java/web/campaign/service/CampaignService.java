package web.campaign.service;

import java.util.List;

import web.campaign.vo.Campaign;
import web.campaign.vo.CampaignDTO;

public interface CampaignService {
	List<CampaignDTO> getCampaign();

	String create(Campaign campaign);
}
