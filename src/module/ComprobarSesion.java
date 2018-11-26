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
				response.sendRedirect("login.jsp");
			}
			else if(alumno != null) {
				if(!InterfazAlumno.existeAlumno(alumno.getNIA())) {
					request.getSession().invalidate();
					response.sendRedirect("login.jsp");
				}
				else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/a_alumno.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				if(!InterfazAdministrador.existeAdmin(admin.getPDI())) {
					request.getSession().invalidate();
					response.sendRedirect("login.jsp");
				}
				else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_administrador.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
