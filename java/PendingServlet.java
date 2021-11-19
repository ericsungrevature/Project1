import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PendingServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1 style='text-align:center'>Ticket Pending</h1>");
        UserDao dao = UserDaoFactory.getUserDao();
        List<Ticket> list;
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
        out.print("<div class='container'>");
        out.println("<input type=\"button\" class=\"btn btn-outline-dark\" text-center\" onclick=\"window.location.href='manager.html'\" value=\"Back\">\n");
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
        list = dao.getTickets("pending");
        for (Ticket tikt : list) {
            out.print("<tr>");
            out.print("<td>" + tikt.getId() + "</td>");
            out.print("<td>" + tikt.getUserId() + "</td>");
            out.print("<td>" + tikt.getValueString() + "</td>");
            out.print("<td>" + tikt.getStatus() + "</td>");
//            out.println("<td><input type=\"button\" class=\"btn btn-outline-success\" text-center\" value=\"Approve\" onclick=\"window.location.href='authorizeservlet?"
            out.print("<td><a href=\"authorizeservlet?"
                    + "tikt_id=" + tikt.getId()
                    + "&user_id=" + tikt.getUserId()
                    + "&tikt_value=" + tikt.getValue()
                    + "&tikt_status=approved"
                    + "\">Approve</a></td>");
//            out.println("<input type=\"button\" class=\"btn btn-outline-danger\" text-center\" onclick=\"window.location.href='employee.html'\" value=\"Back\">\n");
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
        out.print("</div>");
        out.close();
    }
}