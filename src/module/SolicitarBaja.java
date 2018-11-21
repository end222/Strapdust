package module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AlumnoBean;

public class SolicitarBaja extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			AlumnoBean alumno = (AlumnoBean) session.getAttribute("AlumnoBean");
			if (alumno != null) {
				request.getSession().invalidate();
				List<Integer> rmv = new ArrayList<>();
				rmv.add(alumno.getNIA());
				InterfazAlumno.eliminarListaAlumnos(rmv);
				response.sendRedirect("index");
			}
			
			else {
				response.sendError(401);
			}
		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
}
