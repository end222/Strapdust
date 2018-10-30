package module;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComprobarUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String nombreUsuario = request.getParameter("username");
			String password = request.getParameter("pass");
			int resultado = InterfazAlumno.comprobarPassword(nombreUsuario, password);
			if(resultado == 0) {
				response.sendRedirect("control/alumno.jsp");
				Cookie cookieLogin = new Cookie("loginUsuario", nombreUsuario);
			}
			else if(resultado == 3) {
				response.sendRedirect("control/index.html");
				Cookie cookieLoginAdmin = new Cookie("loginAdmin", nombreUsuario);
			}
			else {
				response.sendRedirect("500.html");
			}
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
