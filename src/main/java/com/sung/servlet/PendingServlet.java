package com.sung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sung.user.Ticket;
import com.sung.user.UserDao;
import com.sung.user.UserDaoFactory;

@SuppressWarnings("serial")
public class PendingServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Ticket Pending</h1>");
		UserDao dao = UserDaoFactory.getUserDao();
		List<Ticket> list;
		out.print("<table>");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th>Id</th>");
		out.print("<th>User Id</th>");
		out.print("<th>Value</th>");
		out.print("<th>Status</th>");
		out.print("</tr>");
		out.print("</thead>");
		out.print("<tbody>");
		list = dao.getTickets("pending");
		for (Ticket tikt : list) {
			out.print("<tr>");
			out.print("<td>" + tikt.getId() + "</td>");
			out.print("<td>" + tikt.getUserId() + "</td>");
			out.print("<td>" + tikt.getValueString() + "</td>");
			out.print("<td>" + tikt.getStatus() + "</td>");
			out.print("<td><a href=\"authorizeservlet?"
					+ "tikt_id=" + tikt.getId()
					+ "&user_id=" + tikt.getUserId()
					+ "&tikt_value=" + tikt.getValue()
					+ "&tikt_status=approved"
					+ "\">Approve</a></td>");
			out.print("<td><a href=\"authorizeservlet?"
					+ "tikt_id=" + tikt.getId()
					+ "&user_id=" + tikt.getUserId()
					+ "&tikt_value=" + tikt.getValue()
					+ "&tikt_status=rejected"
					+ "\">Reject</a></td>");
			out.print("</tr>");
		}
		out.print("<a href=\"manager.html\">Back</a><br>");
		out.print("</tbody>");
		out.print("</table>");
		out.close();
	}
}
