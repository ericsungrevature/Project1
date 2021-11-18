package com.sung.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("com/sung/example/hibernate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			Tester e = new Tester("admin", "123");
			session.save(e);
			t.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
