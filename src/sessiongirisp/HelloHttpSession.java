package sessiongirisp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class HelloHttpSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		/* istemci bir istekte(request) bulundugunda , Container unique bir Session ID olusturur ve 
		 * bu Session ID bilgisini cevaba(response) ekler. Daha sonrasinda istemci sonrasindaki isteklerde(request) bu
		 *  Session ID bilgisini de gonderir. ÝD cookie ile taþýnýr.
		 */
		
		HttpSession session = req.getSession();//Eðer req'le gelen bir session id varsa ve bu id serverda bir session ile eþleþiyorsa o sessionu getirir.
											   //Req ile bir session id gelmemiþse ya da bir id gelmiþ fakat eþleþme yapýlamamýþsa yeni session olustur.
		//Eðer yeni bir id(Session) olusturulursa, ServletContainer olusan id'yi cookie kullanarak response ile istemciye gönderir.
		//ServletContainer'ýn yeni bit session olusturma asamalari asagida
		//Sonraki isteklerde de istemci Req ile bu session id'yi yollar.
		if (session.isNew()) {
			pw.print("new session"+session.getId());
		} else {
			pw.print("old session"+session.getId());
		}
	}
}

/*  1-HttpSession objesini olusturma
	2-Unique Session ID olusturma
	3-Cookie objesi olusturma
	4-Session ID ile Cookie objesini iliskilendirme
	5-Cookie’yi , cevaba(response) ekleme gorevlerinden Servlet Container sorumludur.*/
