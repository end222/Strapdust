package module;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AlumnoBean;


public class ComprobarSeguridad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			
			String direccionCorta = request.getParameter("direccion");
			String direccion = "/WEB-INF/control/" + direccionCorta;
			HttpSession session = request.getSession();
			AlumnoBean alumno = (AlumnoBean) session.getAttribute("AlumnoBean");
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if((alumno != null && (direccionCorta.substring(0, 1)).equals("a"))
				|| (admin != null && (direccionCorta.substring(0, 1)).equals("x"))) {
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(direccion);
					dispatcher.forward(request, response);
			}
			else {
				response.sendRedirect("index");
			}
		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
}
