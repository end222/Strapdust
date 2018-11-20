package module;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.AdministradorBean;

public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if (admin != null) {
				List<CartelGrupoAlumnos> lista = new ArrayList<>();
				InterfazCartel.obtenerTodosCartelGrupoAlumnos(lista);
				request.setAttribute("LISTA",lista);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_carteles.jsp");
				dispatcher.forward(request, response);
				
			}
			
			List<Cartel> lista = new ArrayList<>();
			InterfazCartel.obtenerTodosCarteles(lista);
			request.setAttribute("LISTA",lista);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
			dispatcher.forward(request, response);
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
