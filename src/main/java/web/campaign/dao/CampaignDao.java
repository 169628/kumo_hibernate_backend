package web.campaign.dao;

import java.util.List;

import web.campaign.entity.Campaign;



public interface CampaignDao {

	int insert(Campaign campaign);
	
	int deleteById(Integer no);

	int update(Campaign campaign);
	
	List<Object[]> selectAll();
	
	Campaign selectById(Integer campaignNo);

}
