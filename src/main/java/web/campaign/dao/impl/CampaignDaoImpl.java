package web.campaign.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import web.campaign.dao.CampaignDao;
import web.campaign.entity.Campaign;

@Repository
public class CampaignDaoImpl implements CampaignDao {
	
	@PersistenceContext
	private Session session;

	@Override
	public int insert(Campaign campaign) {
		session.persist(campaign);
		return 1;
	}

	@Override
	public int deleteById(Integer no) {
		Campaign campaign = session.get(Campaign.class, no);
		if(campaign == null || campaign.getIsDeleted()) {
			return 0;
		}
		campaign.setIsDeleted(true);
		return 1;
	}
	
	@Override
	public int update(Campaign newCampaign) {
		Campaign campaign = session.get(Campaign.class, newCampaign.getNo());
		if (campaign == null || campaign.getIsDeleted() == true) {
			return 0;
		}
		campaign.setIsEnabled(newCampaign.getIsEnabled());
		campaign.setBrand(newCampaign.getBrand());
		campaign.setModel(newCampaign.getModel());
		campaign.setSv(newCampaign.getSv());
		campaign.setTv(newCampaign.getTv());
		campaign.setFile(newCampaign.getFile());
		campaign.setFileSize(newCampaign.getFileSize());
		campaign.setIsTestMode(newCampaign.getIsTestMode());
		campaign.setTestList(newCampaign.getTestList());
		campaign.setDownloadById(newCampaign.getDownloadById());
		campaign.setUpdateAt(new Timestamp(System.currentTimeMillis()));
		
		return 1;
	}
	
	@Override
	public List<Object[]> selectAll() {
		final StringBuilder hql = new StringBuilder("SELECT");
		hql.append(
				" c.no, c.campaignId, c.brand, c.model, c.sv, c.tv, c.file, c.fileSize, c.isTestMode, c.testList, d.content as downloadBy, c.isEnabled, c.createAt, c.updateAt, c.isDeleted");
		hql.append(" FROM Campaign c");
		hql.append(" JOIN c.downloadBy d");
		hql.append(" WHERE c.isDeleted = false");
		hql.append(" ORDER BY c.updateAt DESC, c.no DESC");

		Query<Object[]> query = session.createQuery(hql.toString(),Object[].class);

		return query.getResultList();

	}

	@Override
	public Campaign selectById(Integer campaignNo) {
		return session.get(Campaign.class, campaignNo);
	}




}
