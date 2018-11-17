package module;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AlumnoBean;


public class ObtenerDatosGenerales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			int numCarteles = InterfazCartel.obtenerNumeroCarteles();
			request.setAttribute("numCarteles", numCarteles);
			int numAlumnos = InterfazAlumno.obtenerNumeroAlumnos();
			request.setAttribute("numAlumnos", numAlumnos);
			int numGrupos = InterfazGrupo.obtenerNumeroGrupos();
			request.setAttribute("numGrupos", numGrupos);
			
		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
}
