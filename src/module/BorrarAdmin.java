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

public class BorrarAdmin extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {			
			String cadenaUsuarios = request.getParameter("usuarios"); // Lista de usuarios
			String[] lineasSeparadas = cadenaUsuarios.split("\\r?\\n");
			List<String> lista = new ArrayList<>();	//crear la lista en la que meter los alumnos
			int numIntentados = 0;
			String listaFallidos = "";
			for (String linea : lineasSeparadas) {	// unUsuario = [nia, nombre]
				if(linea.matches(".*\\d+.*") || linea.matches(".*[a-zA-Z]+.*")) { //comprobar si no es solo espacios
					linea = linea.replaceAll("\\s+", " ");	//Eliminar espacios duplicados
					linea = linea.trim();	//Quitar (si existen) espacio inicial y final)
					String[] usuariosLinea = linea.split(" ");
					for(String unUsuario : usuariosLinea) {
						numIntentados++;
						unUsuario = unUsuario.trim();	//Quitar (si existen) espacio inicial y final)
						lista.add(unUsuario);
						}
					}
				}
			List<String> listaInexistentes = InterfazAdministrador.eliminarListaAdmin(lista);
			int eliminados = numIntentados - listaInexistentes.size();
			if(eliminados == 1) {
				request.setAttribute("successMessage", "¡Genial! Se ha borrado correctamente 1 administrador<br>");
			}
			else if(eliminados > 1) {
				request.setAttribute("successMessage", "¡Genial! Se han borrado correctamente " + eliminados + " administradores<br>");
			}
			
			if(listaInexistentes.size() > 0) {
				for(String admin : listaInexistentes) {
					listaFallidos = listaFallidos + admin + "<br>";
				}
			}
			int incorrectos = numIntentados - eliminados;
			if(incorrectos == 1) {
				request.setAttribute("errorMessage", "Ups. No se ha podido borrar el siguiente administrador porque no se ha encontrado en el sistema:<br>");
				request.setAttribute("errorLista", listaFallidos);
			}
			if(incorrectos > 1) {
				request.setAttribute("errorMessage", "Ups. No se han podido borrar los siguientes " + incorrectos +" administradores porque no se ha encontrado en el sistema:<br>");
				request.setAttribute("errorLista", listaFallidos);
			}
			
			List<AlumnoBean> listaAlumnos = new ArrayList<>();
			InterfazAlumno.obtenerTodosAlumnos(listaAlumnos);
			request.setAttribute("LISTA_AL",listaAlumnos);
			
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if(admin != null){
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_borrar_admin.jsp");
						dispatcher.forward(request, response);
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
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_borrar_admin.jsp");
						dispatcher.forward(request, response);
				}
			else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
