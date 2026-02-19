package web.campaign.dao;

import java.util.List;

import web.campaign.pojo.Campaign;
import web.campaign.vo.CampaignDTO;



public interface CampaignDao {

	List<Object[]> selectAll();

	int insertOne(Campaign campaign);

	Campaign selectOne(Integer campaignNo);

	int delete(Integer campaignNo);

}
