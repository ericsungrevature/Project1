package com.sung.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;

import com.sung.config.ConnectionFactory;

public class UserDaoImpl implements UserDao {

//	private Connection connection;
	Configuration cfg;
	SessionFactory factory;

	public UserDaoImpl() {
//		this.connection = ConnectionFactory.getConnection();
		cfg = new Configuration();
		cfg.configure("com/sung/user/hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
	}

	@Override
	public void addTicket(Ticket tikt) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		tikt.setId((Integer)session.save(tikt));
		tr.commit();
		session.close();
//		String sql = "insert into ticket (username, value, status) values (?, ?, ?)";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//		preparedStatement.setString(1, t.getUsername());
//		preparedStatement.setDouble(2, t.getValue());
//		preparedStatement.setString(3, t.getStatus());
//		preparedStatement.executeUpdate();
//		ResultSet resultSet = preparedStatement.getGeneratedKeys();
//		while(resultSet.next()) {
//			t.setId(resultSet.getInt(1));
//		}
	}

	@Override
	public void updateTicket(Ticket tikt) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(tikt);
		tr.commit();
		session.close();
//		String sql = "update ticket set status = ?, where id = ?";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setString(1, t.getStatus());
//		preparedStatement.setInt(2, t.getId());
//		preparedStatement.executeUpdate();
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
//		String sql = "select * from ticket";
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement.executeQuery(sql);
//		while (resultSet.next()) {
//			Ticket t = new Ticket();
//			t.setId(resultSet.getInt(1));
//			t.setUsername(resultSet.getString(2));
//			t.setValue(resultSet.getDouble(3));
//			t.setStatus(resultSet.getString(4));
//			list.add(t);
//		}
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
//		String sql = "select * from ticket where username = ?";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setString(1, user.getUsername());
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			Ticket t = new Ticket();
//			t.setId(resultSet.getInt(1));
//			t.setUsername(resultSet.getString(2));
//			t.setValue(resultSet.getDouble(3));
//			t.setStatus(resultSet.getString(4));
//			list.add(t);
//		}
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
//		String sql = "select * from ticket where status = ?";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setString(1, status);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			Ticket t = new Ticket();
//			t.setId(resultSet.getInt(1));
//			t.setUsername(resultSet.getString(2));
//			t.setValue(resultSet.getDouble(3));
//			t.setStatus(resultSet.getString(4));
//			list.add(t);
//		}
		return list;
	}

//	@Override
//	public List<Ticket> getTickets(User user, String status) {
//		List<Ticket> list = new ArrayList<Ticket>();
//		String sql = "select * from ticket where username = ? and status = ?";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setString(1, user.getUsername());
//		preparedStatement.setString(2, status);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			Ticket t = new Ticket();
//			t.setId(resultSet.getInt(1));
//			t.setUsername(resultSet.getString(2));
//			t.setValue(resultSet.getDouble(3));
//			t.setStatus(resultSet.getString(4));
//			list.add(t);
//		}
//		return list;
//	}

//	@Override
//	public User getUserById(int id) {
//		User user = new User();
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		user = session.get(User.class, id);
//		t.commit();
//		session.close();
//		return user;
//	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		user = session.get(User.class, username);
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("username", username));
		List<User> resultList = cr.list();
		if (resultList != null) {
			user = resultList.get(0);
		}
//		t.commit();
		session.close();
//		String sql = "select * from user where username = ?";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setString(1, username);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		resultSet.next();
//		if (resultSet != null) {
//			user.setId(resultSet.getInt(1));
//			user.setUsername(resultSet.getString(2));
//			user.setPassword(resultSet.getString(3));
//			user.setStatus(resultSet.getString(4));
//		}
		return user;
	}

}
