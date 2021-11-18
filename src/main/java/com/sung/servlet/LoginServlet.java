package com.sung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sung.user.User;
import com.sung.user.UserDao;
import com.sung.user.UserDaoFactory;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			UserDao dao = UserDaoFactory.getUserDao();
			User user = dao.getUserByUsername(username);
			if(password.equals(user.getPassword())) {
				out.print("<h1>Successful Login</h1>");
				out.print("<p>Welcome " + user.getUsername() + "</p>");
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				if (user.getStatus().equals("employee")) {
					RequestDispatcher rd = request.getRequestDispatcher("employee.html");
					rd.include(request, response);
				} else if (user.getStatus().equals("manager")) {
					RequestDispatcher rd = request.getRequestDispatcher("manager.html");
					rd.include(request, response);
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<h1>Invalid Login</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		out.close();
	}
}
