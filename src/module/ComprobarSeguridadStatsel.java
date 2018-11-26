package module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AlumnoBean;


public class ComprobarSeguridadStatsel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			List<TituloGrupo> lista = new ArrayList<>();
			InterfazCartel.obtenerTodosTituloGrupo(lista);
			request.setAttribute("LISTA_TG",lista);
			
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if(admin != null){
				if(!InterfazAdministrador.existeAdmin(admin.getPDI())) {
					request.getSession().invalidate();
					response.sendRedirect("login.jsp");
				}
				else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_statsel.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				response.sendRedirect("login.jsp");
			}
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
