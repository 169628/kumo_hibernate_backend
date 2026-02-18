package app;

import java.lang.annotation.Native;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import core.util.HibernateUtil;
import web.campaign.vo.Campaign;

public class TestApp {
	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		Campaign campaign = session.get(Campaign.class,6);
//		System.out.println(campaign.getFileSize());
//		HibernateUtil.shutdown();
		
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
		for(Object[] item : list) {
			System.out.println(item[0]);
		}
		HibernateUtil.shutdown();
		
		
	}
}
