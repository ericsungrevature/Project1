
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl implements UserDao {


	Configuration cfg;
	SessionFactory factory;

	public UserDaoImpl() {
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
	}

	@Override
	public void addTicket(Ticket tikt) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		tikt.setId((Integer)session.save(tikt));
		tr.commit();
		session.close();
	}

	@Override
	public void updateTicket(Ticket tikt) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(tikt);
		tr.commit();
		session.close();
	}

//	@Override
//	public void removeTicket(Ticket tikt) {
//		Session session = factory.openSession();
//		Transaction tr = session.beginTransaction();
//		session.delete(tikt);
//		tr.commit();
//		session.close();
//
//		String sql = "delete from ticket where id = ?";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setInt(1, t.getId());
//		preparedStatement.executeUpdate();
//	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Ticket> getTickets() {
		List<Ticket> list = new ArrayList<Ticket>();
		Session session = factory.openSession();
		Criteria cr = session.createCriteria(Ticket.class);
		list = cr.list();
		session.close();
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Ticket> getTickets(User user) {
		List<Ticket> list = new ArrayList<Ticket>();
		Session session = factory.openSession();
		Criteria cr = session.createCriteria(Ticket.class);
		cr.add(Restrictions.eq("user_id", user.getId()));
		list = cr.list();
		session.close();
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Ticket> getTickets(String status) {
		List<Ticket> list = new ArrayList<Ticket>();
		Session session = factory.openSession();
		Criteria cr = session.createCriteria(Ticket.class);
		cr.add(Restrictions.eq("status", status));
		list = cr.list();
		session.close();
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		Session session = factory.openSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("username", username));
		List<User> resultList = cr.list();
		if (resultList != null) {
			user = resultList.get(0);
		}
		session.close();
		return user;
	}
}
