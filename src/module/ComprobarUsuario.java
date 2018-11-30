package module;

import java.io.IOException;
import Bean.AlumnoBean;

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
			String nombreUsuario = request.getParameter("username");
			String password = request.getParameter("pass");
			int resultado = InterfazAlumno.comprobarPassword(nombreUsuario, password);
			if(resultado == 0) {
				HttpSession session = request.getSession(true);
				AlumnoBean al = InterfazAlumno.obtenerUnAlumno(nombreUsuario);
				session.setAttribute("AlumnoBean",al);
				response.sendRedirect("ComprobarSeguridad.do?direccion=a_alumno");
			}
			else if(resultado == 3) {
				HttpSession session = request.getSession(true);
				AdministradorBean admin = InterfazAdministrador.obtenerUnAdmin(nombreUsuario);
				session.setAttribute("AdministradorBean",admin);
				response.sendRedirect("ComprobarSeguridad.do?direccion=x_administrador");
			}
			else {
				request.setAttribute("errorMessage", "El usuario o la contraseña no son correctos");
				RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
}
