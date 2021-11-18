package com.sung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sung.user.Ticket;
import com.sung.user.User;
import com.sung.user.UserDao;
import com.sung.user.UserDaoFactory;

@SuppressWarnings("serial")
public class ViewServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Ticket Requests</h1>");
		UserDao dao = UserDaoFactory.getUserDao();
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
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
		if (user.getStatus().equals("employee")) {
			list = dao.getTickets(user);
			out.print("<a href=\"employee.html\">Back</a><br>");
			for (Ticket tikt : list) {
				out.print("<tr>");
				out.print("<td>" + tikt.getId() + "</td>");
				out.print("<td>" + tikt.getUserId() + "</td>");
				out.print("<td>" + tikt.getValueString() + "</td>");
				out.print("<td>" + tikt.getStatus() + "</td>");
				out.print("</tr>");
			}
		} else if (user.getStatus().equals("manager")) {
			list = dao.getTickets();
			out.print("<a href=\"manager.html\">Back</a><br>");
			for (Ticket tikt : list) {
				out.print("<tr>");
				out.print("<td>" + tikt.getId() + "</td>");
				out.print("<td>" + tikt.getUserId() + "</td>");
				out.print("<td>" + tikt.getValueString() + "</td>");
				out.print("<td>" + tikt.getStatus() + "</td>");
				out.print("</tr>");
			}
		}
		out.print("</tbody>");
		out.print("</table>");
		out.close();
	}
}
