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
public class PendingServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserDao dao = UserDaoFactory.getUserDao();
        try {
        	HttpSession session = request.getSession(false);
			User user = (User)session.getAttribute("user");
			if (!user.getStatus().equals("manager")) {
				throw new Exception();
			}
			List<Ticket> list;
			list = dao.getTickets("pending");
			out.println("<h1 style='text-align:center'>Ticket Pending</h1>");
			out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
	        out.print("<div class='container'>");
	        out.println("<input type=\"button\" class=\"btn btn-outline-dark\" text-center\" onclick=\"window.location.href='manager.html'\" value=\"Back\">\n");
			if (list.isEmpty()) {
				out.println("<p>There are no pending requests at this time</p>");
			} else {
		        out.print("<table class='table table-dark'>");
		        out.print("<thead>");
		        out.print("<tr>");
		        out.print("<th scope=\"col\">Id</th>");
		        out.print("<th scope=\"col\">User Id</th>");
		        out.print("<th scope=\"col\">Value</th>");
		        out.print("<th scope=\"col\">Status</th>");
		        out.print("<th scope=\"col\"></th>");
		        out.print("<th scope=\"col\">Actions</th>");
		        out.print("</tr>");
		        out.print("</thead>");
		        out.print("<tbody>");
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
				out.print("</tbody>");
				out.print("</table>");
			}
	        out.print("</div>");
        } catch (Exception e) {
        	e.printStackTrace();
			out.println("<script>");
			out.println("alert('There has been an error retrieving requests');");
			out.println("</script>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
        }
		out.close();
	}
}
