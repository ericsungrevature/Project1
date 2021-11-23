import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class AddServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		// Setup printwriter
		PrintWriter out = response.getWriter();
		// Add link for Bootstrap
		out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
		// add style for container
		out.print("<style>.container{text-align:center; padding:20px}</style>");
		out.print("<div class='container'>");
		// setup dao
		UserDao dao = UserDaoFactory.getUserDao();
		// start the try and catch
		try {
			// Setup the HttpSession
			HttpSession session = request.getSession(false);
			User user = (User)session.getAttribute("user");
			// take value from the user
			double value = Double.parseDouble(request.getParameter("value"));
			// take description from the user
			String description = request.getParameter("description");
			// store the ticket information into database
			Ticket tikt = new Ticket();
			tikt.setUserId(user.getId());
			tikt.setValue(value);
			tikt.setDescription(description);
			tikt.setStatus("pending");
			dao.addTicket(tikt);
			// display message when success
			out.println("<h1>Your Reimbursement Ticket Has Been Sent Successfully!</h1>");
			out.println("<p>Please wait for the authorization</p>");
			// catch and throw error message when user input is not a number
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("<h1>Your Reimbursement Ticket Has Been Sent Incorrectly!</h1>");
			out.println("<p>Please check to be sure your request is formatted correctly</p>");
			// catch and throw error message when request has an error
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h1>Your Reimbursement Ticket Has Been Sent Incorrectly!</h1>");
			out.println("<p>There has been a server error sending your request</p>");
		}
		// button for submitting the ticket
		out.print("<input type=\"button\" class=\"btn btn-primary text-center\" onclick=\"window.location.href='employee.html'\" value=\"Go Back to Menu\">\n");
		out.print("</div>");
		out.close();
	}
}
