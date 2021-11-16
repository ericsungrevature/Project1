package com.sung.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sung.config.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	private Connection connection;

	public UserDaoImpl() {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public void addTicket(Ticket t) throws SQLException {
		String sql = "insert into ticket (username, value, status) values (?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, t.getUsername());
		preparedStatement.setDouble(2, t.getValue());
		preparedStatement.setString(3, t.getStatus());
		preparedStatement.executeUpdate();
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while(resultSet.next()) {
			t.setId(resultSet.getInt(1));
		}
	}

	@Override
	public void updateTicket(Ticket t) throws SQLException {
		String sql = "update ticket set status = ?, where id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, t.getStatus());
		preparedStatement.setInt(2, t.getId());
		preparedStatement.executeUpdate();
	}

	@Override
	public void removeTicket(Ticket t) throws SQLException {
		String sql = "delete from ticket where id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, t.getId());
		preparedStatement.executeUpdate();
	}

	@Override
	public List<Ticket> getTickets() throws SQLException {
		List<Ticket> list = new ArrayList<Ticket>();
		String sql = "select * from ticket";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			Ticket t = new Ticket();
			t.setId(resultSet.getInt(1));
			t.setUsername(resultSet.getString(2));
			t.setValue(resultSet.getDouble(3));
			t.setStatus(resultSet.getString(4));
			list.add(t);
		}
		return list;
	}

	@Override
	public List<Ticket> getTickets(User user) throws SQLException {
		List<Ticket> list = new ArrayList<Ticket>();
		String sql = "select * from ticket where username = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Ticket t = new Ticket();
			t.setId(resultSet.getInt(1));
			t.setUsername(resultSet.getString(2));
			t.setValue(resultSet.getDouble(3));
			t.setStatus(resultSet.getString(4));
			list.add(t);
		}
		return list;
	}

	@Override
	public List<Ticket> getTickets(String status) throws SQLException {
		List<Ticket> list = new ArrayList<Ticket>();
		String sql = "select * from ticket where status = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, status);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Ticket t = new Ticket();
			t.setId(resultSet.getInt(1));
			t.setUsername(resultSet.getString(2));
			t.setValue(resultSet.getDouble(3));
			t.setStatus(resultSet.getString(4));
			list.add(t);
		}
		return list;
	}

	@Override
	public List<Ticket> getTickets(User user, String status) throws SQLException {
		List<Ticket> list = new ArrayList<Ticket>();
		String sql = "select * from ticket where username = ? and status = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, status);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Ticket t = new Ticket();
			t.setId(resultSet.getInt(1));
			t.setUsername(resultSet.getString(2));
			t.setValue(resultSet.getDouble(3));
			t.setStatus(resultSet.getString(4));
			list.add(t);
		}
		return list;
	}

	@Override
	public User getUserByUsername(String username) throws SQLException {
		User user = new User();
		String sql = "select * from user where username = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		if (resultSet != null) {
			user.setId(resultSet.getInt(1));
			user.setUsername(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
			user.setStatus(resultSet.getString(4));
		}
		return user;
	}

}
