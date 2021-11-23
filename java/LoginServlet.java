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

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao dao = UserDaoFactory.getUserDao();
		// start the try and catch
		try {
			User user = dao.getUserByUsername(username);
			// check if the password is correct
			if (password.equals(user.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				out.println("<script>");
				out.println("alert('Successfully Logged In');");
				out.println("</script>");
				out.print("<p style='text-align:center; padding:10px'>Welcome " + user.getUsername() + " ID: " + user.getId() + "</p>");
				// check if the user is employee or manager
				if (user.getStatus().equals("employee")) {
					// display employee menu if user is employee
					RequestDispatcher rd = request.getRequestDispatcher("employee.html");
					rd.include(request, response);
				} else if (user.getStatus().equals("manager")) {
					// display manager menu if user is manager
					RequestDispatcher rd = request.getRequestDispatcher("manager.html");
					rd.include(request, response);
				}
			} else {
				throw new Exception();
			}
			// catch and throw alert message if login is not success
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<script>");
			out.println("alert('Invalid Login, please check username and password');");
			out.println("</script>");
			// display login page if login is not sucess
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		out.close();
	}
}
