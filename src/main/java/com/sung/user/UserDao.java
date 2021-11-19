package com.sung.user;

import java.util.List;

/*All employees in the company can
 * submit requests for reimbursement
 * and view their past tickets and pending requests.*/
/*Finance managers can
 * view all reimbursement requests and past history for all employees in the company.
 * authorize to approve and deny requests for expense reimbursement.*/
//create table user (id integer primary key auto_increment, username char(20) unique, password char(20), status enum('employee', 'manager'));
//create table ticket (id integer primary key auto_increment, user_id integer, value double, status enum('approved', 'pending', 'rejected'));
//insert into user (username, password, status) values ('admin', '123', 'manager');
//insert into user (username, password, status) values ('emp', '123', 'employee');
//insert into user (username, password, status) values ('emp2', '123', 'employee');
//create index idx_user on user (username);
public interface UserDao {
	void addTicket(Ticket tikt);
	void updateTicket(Ticket tikt);
	List<Ticket> getTickets();
	List<Ticket> getTickets(User user);
	List<Ticket> getTickets(String status);
	User getUserByUsername(String username);
}
