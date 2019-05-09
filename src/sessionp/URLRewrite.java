package sessionp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/encode")
public class URLRewrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*E�er taray�c�n�n cookie �zelli�i kapal� ise bize req ile bir id yollamaz.
		 * biz burda getSession() metodunu �agirdigimiz icin her seferinde yeni bir session olusur.
		 * ServletContainer bu session id'yi yine resp ile yollar fakat browserda �zellik kapali oldugu icin bu id tutulmaz
		 * ve istemci tekrar req attiginda da bu id bize gelemez ve dolayisiyla burada getSession() metoduyla her istek geldiginde
		 * yeni session olusur.
		 */
		
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		pw.write("URL Rewrite ID:"+session.getId());
		session.setAttribute("isim","Hasan");
		
		/* �stek geldi
		 * req ile id gelmedi�inden yeni bir session olustu
		 * bu sessiona attr verdik.
		 * 
		 * Bu session'a ve session attribute'e GetSessionData servletinden ulsamak icin;
		 * Browserda bu url'e degil http://localhost:8080/Session/getdata
		 * Bu url'e istek atacagiz http://localhost:8080/Session/getdata;jsessionid=<YENI OLUSAN SESS�ON ID>
		 * GetSessionData servletinde getSession() cagirildiginda broese cerezleri kapali old icin session id'yi
		 * direkt url'den alacak ve bu sayede session'a ve attr degerine erismis olacagiz.
		 */
		
		/*Bir diger yol ise resp.encodeURL'dir. Bu metod parametre olarak verdi�imiz url'in sonuna session id ekler.*/
		String newUrl = resp.encodeURL("http://localhost:8080/Session/getdata"); // newURL = http://localhost:8080/Session/getdata;jsessionid=<YENI OLUSAN SESS�ON ID>
		/*newURL stringini a href gibi taglerde kullanarak y�nlendirme yapabiliriz*/
		/*Eger sendRedirect icinde bir url icin encode yapmak istiyorsak(yani sonuna session id eklemek), redirectUrlEncode(URL) methodu kullan�lmal�d�r.*/
	
		/*encodeUrl veya redirectEncodeUrl gibi metodlar cagirildiginda, istek atan browser cerezleri aktifse bu metodlar hic bir sey yapmaz.*/
	}
}
