package more;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/timeout")
public class SessionTimeout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = session.getId();

		Integer maxInactiveInternal = session.getMaxInactiveInterval();
		System.out.println("default timeout:" + maxInactiveInternal);

		session.setMaxInactiveInterval(10);

		maxInactiveInternal = session.getMaxInactiveInterval();
		System.out.println("updated timeout:" + maxInactiveInternal);

		session.setAttribute("name", "Hasan Cerit");
		String name = (String) session.getAttribute("name");
		System.out.println(name);

		name = (String) session.getAttribute("name");
		System.out.println(name);

		
		PrintWriter pw = resp.getWriter();
		pw.print("<html><body><table>");
		pw.print("<tr><td>Session ID</td>");
		pw.print("<td>" + id + "</td>");
		pw.print("</table></body></html>");

	}
}
