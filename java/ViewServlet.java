import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ViewServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserDao dao = UserDaoFactory.getUserDao();
		// start try and catch
		try {
			HttpSession session = request.getSession(false);
			User user = (User)session.getAttribute("user");
			List<Ticket> list;
			// start if statement to see who is the user
			if (user.getStatus().equals("employee")) {
				// call the employee ticket list if user is employee
				list = dao.getTickets(user);
				out.println("<h1 style='text-align:center'>Ticket Requests</h1>");
				out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
				out.print("<div class='container'>");
				out.println("<input type=\"button\" class=\"btn btn-outline-dark\" text-center\" onclick=\"window.location.href='employee.html'\" value=\"Back\">\n");
				if (list.isEmpty()) {
					// print message if the table is empty
					out.println("<p>There are no requests made at this time</p>");
				} else {
					// display ticket table for the employee user
					out.print("<table class='table table-dark'>");
					out.print("<thead>");
					out.print("<tr>");
					out.print("<th scope=\"col\">Id</th>");
					out.print("<th scope=\"col\">User Id</th>");
					out.print("<th scope=\"col\">Value</th>");
					out.print("<th scope=\"col\">Description</th>");
					out.print("<th scope=\"col\">Status</th>");
					out.print("</tr>");
					out.print("</thead>");
					out.print("<tbody>");
					for (Ticket tikt : list) {
						// bring the table data from database
						out.print("<tr>");
						out.print("<td>" + tikt.getId() + "</td>");
						out.print("<td>" + tikt.getUserId() + "</td>");
						out.print("<td>" + tikt.getValueString() + "</td>");
						out.print("<td>" + tikt.getDescription() + "</td>");
						out.print("<td>" + tikt.getStatus() + "</td>");
						out.print("</tr>");
					}
					out.print("</tbody>");
					out.print("</table>");
				}
			} else if (user.getStatus().equals("manager")) {
				// call the manager list if user is manager
				list = dao.getTickets();
				out.println("<h1 style='text-align:center'>Ticket Requests</h1>");
				out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
				out.print("<div class='container'>");
				out.println("<input type=\"button\" class=\"btn btn-outline-dark\" text-center\" onclick=\"window.location.href='manager.html'\" value=\"Back\">\n");
				if (list.isEmpty()) {
					// print message if the table is empty
					out.println("<p>There are no requests at this time</p>");
				} else {
					// display ticket table for the manager user
					out.print("<table class='table table-dark'>");
					out.print("<thead>");
					out.print("<tr>");
					out.print("<th scope=\"col\">Id</th>");
					out.print("<th scope=\"col\">User Id</th>");
					out.print("<th scope=\"col\">Value</th>");
					out.print("<th scope=\"col\">Description</th>");
					out.print("<th scope=\"col\">Status</th>");
					out.print("</tr>");
					out.print("</thead>");
					out.print("<tbody>");
					for (Ticket tikt : list) {
						// bring the table data from database
						out.print("<tr>");
						out.print("<td>" + tikt.getId() + "</td>");
						out.print("<td>" + tikt.getUserId() + "</td>");
						out.print("<td>" + tikt.getValueString() + "</td>");
						out.print("<td>" + tikt.getDescription() + "</td>");
						out.print("<td>" + tikt.getStatus() + "</td>");
						out.print("</tr>");
					}
					out.print("</tbody>");
					out.print("</table>");
				}
			}
			out.print("</div>");
			// catch for exception
		} catch (Exception e) {
			e.printStackTrace();
			// display alert message when there is an exception
			out.println("<script>");
			out.println("alert('There has been an error retrieving requests');");
			out.println("</script>");
			// display login page after alert message
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		out.close();
	}
}
