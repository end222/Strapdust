package module;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AlumnoBean;


public class ObtenerAlumnoInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			AlumnoBean alumno = (AlumnoBean) session.getAttribute("AlumnoBean");
			GrupoAlumnos grAl = InterfazGrupo.obtenerGrupoAlumnos(alumno.getGrupo(), alumno.getNIA());
			request.setAttribute("GR_AL", grAl);	
		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
}
