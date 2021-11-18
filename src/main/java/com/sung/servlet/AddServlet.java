package com.sung.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
public class AddServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<a href=\"employee.html\">Back</a><br>");
		out.println("<h1>Ticket Sent</h1>");
		double value = Double.parseDouble(request.getParameter("value"));
		UserDao dao = UserDaoFactory.getUserDao();
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		Ticket tikt = new Ticket();
		tikt.setUserId(user.getId());
		tikt.setValue(value);
		tikt.setStatus("pending");
		dao.addTicket(tikt);
		out.close();
	}
}
