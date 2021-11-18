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
//			User user = new User();
//			user.setUsername("admin");
//			user.setPassword("123");
			if(password.equals(user.getPassword())) {
				out.print("<h1>Successful Login</h1>");
				out.print("<p>Welcome " + user.getUsername() + "</p>");
//				Cookie cookie = new Cookie("username", username);
//				response.addCookie(cookie);
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("<h1>Invalid Login</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		out.close();
	}
}
