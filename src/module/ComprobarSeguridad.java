package module;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ComprobarSeguridad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String direccion = "/WEB-INF/" + request.getParameter("direccion");
			String permiso = request.getParameter("permiso");
			HttpSession session = request.getSession();
			String alumno = (String) session.getAttribute("alumno");
			String admin = (String) session.getAttribute("admin");
			if((alumno != null && permiso.equals("alumno")) || (admin != null && permiso == "amdin")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(direccion);
					dispatcher.forward(request, response);
			}
			else {
				response.sendRedirect("/Sistemas/500.html");
			}
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
