package web.campaign.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.campaign.dao.CampaignDao;
import web.campaign.entity.Campaign;

public class CampaignDaoImpl implements CampaignDao {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public int insert(Campaign campaign) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(campaign);
		tx.commit();
		session.close();
		return 1;
	}

	@Override
	public int deleteById(Integer no) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Campaign campaign = session.get(Campaign.class, no);
		if(campaign == null || campaign.getIsDeleted() == true) {
			return 0;
		}
		campaign.setIsDeleted(true);
		tx.commit();
		session.close();
		return 1;
	}
	
	@Override
	public int update(Campaign newCampaign) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
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
		tx.commit();
		session.close();
		return 1;
	}
	
	@Override
	public List<Object[]> selectAll() {
		Session session = sessionFactory.openSession();
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
		Session session = sessionFactory.openSession();
		return session.get(Campaign.class, campaignNo);
	}




}
