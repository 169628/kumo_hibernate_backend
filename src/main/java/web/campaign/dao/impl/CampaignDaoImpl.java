package web.campaign.dao.impl;


import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import core.util.HibernateUtil;
import web.campaign.vo.Campaign;
import web.campaign.dao.CampaignDao;


public class CampaignDaoImpl implements CampaignDao {
	private DataSource ds;
	
	public CampaignDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/kumo");
	}

	

	@Override
	public int insertOne(Campaign campaign) {
		StringBuilder sql = new StringBuilder("INSERT INTO");
		
		return 0;
	}



	@Override
	public List<Object[]> selectAll() {
		final StringBuilder sql = new StringBuilder("SELECT");
		sql.append(" no, campaign_id, brand, model, sv, tv, file, file_size, is_test_mode, test_list, d.content as download_by, is_enabled, create_at, update_at, is_deleted");
		sql.append(" FROM campaigns c");
		sql.append(" JOIN download_by_ref d");
		sql.append(" ON c.download_by_id = d.download_by_id");
		sql.append(" WHERE is_deleted = 0");
		sql.append(" ORDER BY update_at DESC, no DESC");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		NativeQuery<Object[]> nativeQuery = session.createNativeQuery(sql.toString());
		List<Object[]> list = nativeQuery.getResultList();
		
		HibernateUtil.shutdown();
		return list;
	}

	

	
	

}
