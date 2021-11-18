package com.sung.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Successful Logout</h1>");
		HttpSession session = request.getSession(false);
		session.removeAttribute("user");
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.include(request, response);
		out.close();
	}
}
