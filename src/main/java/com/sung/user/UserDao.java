package com.sung.user;

import java.sql.SQLException;
import java.util.List;

/*All employees in the company can
 * submit requests for reimbursement
 * and view their past tickets and pending requests.*/
/*Finance managers can
 * view all reimbursement requests and past history for all employees in the company.
 * authorize to approve and deny requests for expense reimbursement.*/
//create table user (id integer not null primary key auto_increment, username char(20) unique, password char(20), status enum('employee', 'manager'));
//	create table user (username char(20) primary key, password char(20), status enum('employee', 'manager'));
//create table ticket (id integer not null primary key auto_increment, username char(20), value double, status enum('approved', 'pending', 'rejected'));
//create index idx_user on user (username);
//insert into user (username, password, status) values ('admin', '123', 'manager');
//insert into user (username, password, status) values ('emp', '123', 'employee');
public interface UserDao {
	void addTicket(Ticket tikt);
	void updateTicket(Ticket tikt);
//	void removeTicket(Ticket tikt);
	List<Ticket> getTickets();
	List<Ticket> getTickets(User user);
	List<Ticket> getTickets(String status);
	List<Ticket> getTickets(User user, String status);
//	User getUserById(int id);
	User getUserByUsername(String username);
}
