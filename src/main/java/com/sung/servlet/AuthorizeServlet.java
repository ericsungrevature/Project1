package com.sung.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sung.user.Ticket;
import com.sung.user.UserDao;
import com.sung.user.UserDaoFactory;

@SuppressWarnings("serial")
public class AuthorizeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserDao dao = UserDaoFactory.getUserDao();
		try {
			int tikt_id = Integer.parseInt(request.getParameter("tikt_id"));
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			double tikt_value = Double.parseDouble(request.getParameter("tikt_value"));
			String tikt_status = request.getParameter("tikt_status");
			Ticket tikt = new Ticket();
			tikt.setId(tikt_id);
			tikt.setUserId(user_id);
			tikt.setValue(tikt_value);
			tikt.setStatus(tikt_status);
			dao.updateTicket(tikt);
			out.println("<script>");
	        out.println("alert('Ticket Status Updated Successfully!');");
	        out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<script>");
	        out.println("alert('There Was an Issue Updating Ticket Status!');");
	        out.println("</script>");
		}
		RequestDispatcher rd = request.getRequestDispatcher("pendingservlet");
		rd.include(request, response);
	}
}
