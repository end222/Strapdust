package module;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ComprobarUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			//Map<String, String> errors = new HashMap<String,String>();
			
			String nombreUsuario = request.getParameter("username");
			String password = request.getParameter("pass");
			int resultado = InterfazAlumno.comprobarPassword(nombreUsuario, password);
			if(resultado == 0) {
				HttpSession session = request.getSession(true);
				AlumnoBean al = InterfazAlumno.obtenerUnAlumno(nombreUsuario);
				session.setAttribute("alumno", nombreUsuario);
				request.setAttribute("AlumnoBean",al);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/alumno.jsp");
				dispatcher.forward(request, response);
			}
			else if(resultado == 3) {
				HttpSession session = request.getSession(true);
				AdministradorBean admin = InterfazAdministrador.obtenerUnAdmin(nombreUsuario);
				session.setAttribute("admin", nombreUsuario);
				request.setAttribute("AdministradorBean",admin);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/administrador.jsp");
				dispatcher.forward(request, response);
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
