import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// alert message inform user log out is success
		out.println("<script>");
		out.println("alert('Successfully Logged Out');");
		out.println("</script>");
		HttpSession session = request.getSession(false);
		session.removeAttribute("user");
		// display login page after log out
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.include(request, response);
		out.close();
	}
}
