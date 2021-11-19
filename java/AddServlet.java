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
		PrintWriter out = response.getWriter();
		out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
		out.print("<style>.container{text-align:center; padding:20px}</style>");
		out.print("<div class='container'>");
		out.println("<h1>Your Reimbursement Ticket Sent Successfully!</h1>");
		out.println("<p>Please wait for the authorization</p>");
		out.print("<input type=\"button\" class=\"btn btn-primary text-center\" onclick=\"window.location.href='employee.html'\" value=\"Go Back to Menu\">\n");
		out.print("</div>");
		double value = Double.parseDouble(request.getParameter("value"));
		UserDao dao = UserDaoFactory.getUserDao();
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
//		out.print(user.getId());
		Ticket tikt = new Ticket();
		tikt.setUserId(user.getId());
		tikt.setValue(value);
		tikt.setStatus("pending");
		dao.addTicket(tikt);
		out.close();
	}
}
