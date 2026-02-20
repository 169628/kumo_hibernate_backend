package web.campaign.dao;

import java.util.List;

import web.campaign.pojo.Campaign;
import web.campaign.vo.CampaignDTO;



public interface CampaignDao {

	int insert(Campaign campaign);
	
	int deleteById(Integer no);

	int update(Campaign campaign);
	
	List<Object[]> selectAll();
	
	Campaign selectById(Integer campaignNo);

}
