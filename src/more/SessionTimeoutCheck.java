package more;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/check")
public class SessionTimeoutCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = session.getId();
		String name = (String) session.getAttribute("name");

		// bu sesssion attribute SessionTimeOut servlet'inde eklendi. 10 saniye
		// sonra session timeout olur ve bu attribute e ulasim saglanamaz.
		PrintWriter pw = resp.getWriter();
		pw.print("<html><body><table>");
		pw.print("<tr><td>Session ID</td>");
		pw.print("<td>" + id + "</td>");
		pw.print("<tr><td>Name</td>");
		pw.print("<td>" + name + "</td>");
		pw.print("</table></body></html>");
	}
}
