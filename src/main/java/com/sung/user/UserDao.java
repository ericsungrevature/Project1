package com.sung.user;

import java.sql.SQLException;
import java.util.List;

/*All employees in the company can
 * submit requests for reimbursement
 * and view their past tickets and pending requests.*/
/*Finance managers can
 * view all reimbursement requests and past history for all employees in the company.
 * authorize to approve and deny requests for expense reimbursement.*/
public interface UserDao {
	void addTicket(Ticket t) throws SQLException;
	void updateTicket(Ticket t) throws SQLException;
	void removeTicket(Ticket t) throws SQLException;
	List<Ticket> getTickets() throws SQLException;
	List<Ticket> getTickets(User user) throws SQLException;
	List<Ticket> getTickets(String status) throws SQLException;
	List<Ticket> getTickets(User user, String status) throws SQLException;
	User getUserByUsername(String username) throws SQLException;
}
