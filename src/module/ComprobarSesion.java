package module;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AlumnoBean;

public class ComprobarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			AlumnoBean alumno = (AlumnoBean) session.getAttribute("AlumnoBean");
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if (alumno == null && admin == null) {
				response.sendRedirect("/Sistemas/access/login.jsp");
			}
			else if(alumno != null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/alumno.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/administrador.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
