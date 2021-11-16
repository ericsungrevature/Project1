package com.sung.user;

public class UserDaoFactory {
	private static UserDao userDao;
	public static UserDao getUserDao() {
		if (userDao == null)
			userDao = new UserDaoImpl();
		return userDao;
	}
}
