package web.campaign.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import core.util.HibernateUtil;
import web.campaign.dao.CampaignDao;
import web.campaign.pojo.Campaign;

public class CampaignDaoImpl implements CampaignDao {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public int insert(Campaign campaign) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(campaign);
		tx.commit();
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
		// mysql 中 upddate_at 應該是修改的時候會自動儲存當下時間
		tx.commit();
		
		return 1;
	}
	
	@Override
	public List<Object[]> selectAll() {
		final StringBuilder sql = new StringBuilder("SELECT");
		sql.append(
				" no, campaign_id, brand, model, sv, tv, file, file_size, is_test_mode, test_list, d.content as download_by, is_enabled, create_at, update_at, is_deleted");
		sql.append(" FROM campaigns c");
		sql.append(" JOIN download_by_ref d");
		sql.append(" ON c.download_by_id = d.download_by_id");
		sql.append(" WHERE is_deleted = 0");
		sql.append(" ORDER BY update_at DESC, no DESC");

		Session session = sessionFactory.openSession();
		NativeQuery<Object[]> nativeQuery = session.createNativeQuery(sql.toString());

		return nativeQuery.getResultList();

	}

	@Override
	public Campaign selectById(Integer campaignNo) {
		Session session = sessionFactory.openSession();
		return session.get(Campaign.class, campaignNo);
	}




}
