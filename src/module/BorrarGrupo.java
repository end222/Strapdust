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

public class BorrarGrupo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {			
			String cadenaGrupos = request.getParameter("grupos"); // Lista de grupos
			String[] lineasSeparadas = cadenaGrupos.split("\\r?\\n");
			List<String> lista = new ArrayList<>();	//crear la lista en la que meter los grupos
			int numIntentados = 0;
			String listaFallidos = "";
			for (String linea : lineasSeparadas) {	// unUsuario = [nia, nombre]
				if(linea.matches(".*\\d+.*") || linea.matches(".*[a-zA-Z]+.*")) { //comprobar si no es solo espacios
					linea = linea.replaceAll("\\s+", " ");	//Eliminar espacios duplicados
					linea = linea.trim();	//Quitar (si existen) espacio inicial y final)
					String[] gruposLinea = linea.split(" ");
					for(String unGrupo : gruposLinea) {
						numIntentados++;
						unGrupo = unGrupo.trim();	//Quitar (si existen) espacio inicial y final)
						lista.add(unGrupo);
						}
					}
				}
			List<String> listaInexistentes = InterfazGrupo.eliminarListaGrupo(lista);
			int eliminados = numIntentados - listaInexistentes.size();
			if(eliminados == 1) {
				request.setAttribute("successMessage", "¡Genial! Se ha borrado correctamente 1 grupo<br>");
			}
			else if(eliminados > 1) {
				request.setAttribute("successMessage", "¡Genial! Se han borrado correctamente " + eliminados + " grupos<br>");
			}
			
			if(listaInexistentes.size() > 0) {
				for(String grupo : listaInexistentes) {
					listaFallidos = listaFallidos + grupo + "<br>";
				}
			}
			int incorrectos = numIntentados - eliminados;
			if(incorrectos == 1) {
				request.setAttribute("errorMessage", "Ups. No se ha podido borrar el siguiente grupo porque no se ha encontrado en el sistema:<br>");
				request.setAttribute("errorLista", listaFallidos);
			}
			if(incorrectos > 1) {
				request.setAttribute("errorMessage", "Ups. No se han podido borrar los siguientes " + incorrectos +" grupos porque no se ha encontrado en el sistema:<br>");
				request.setAttribute("errorLista", listaFallidos);
			}
			
			List<GrupoAlumnos> listaGrupos = new ArrayList<>();
			InterfazGrupo.obtenerTodosGrupoAlumnos(listaGrupos);
			request.setAttribute("LISTA_GR",listaGrupos);
			
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if(admin != null){
					if(!InterfazAdministrador.existeAdmin(admin.getPDI())) {
						request.getSession().invalidate();
						response.sendRedirect("login.jsp");
					}
					else {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_borrar_grupos.jsp");
						dispatcher.forward(request, response);
					}
				}
			else {
				response.sendRedirect("login.jsp");
			}
		}
		catch (Throwable Exception) {
			request.setAttribute("errorMessage", "El sistema ha fallado");
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if(admin != null){
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_borrar_grupos.jsp");
						dispatcher.forward(request, response);
				}
			else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
