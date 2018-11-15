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

public class BorrarAlumno extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {			
			String cadenaUsuarios = request.getParameter("usuarios"); // Lista de usuarios
			String[] lineasSeparadas = cadenaUsuarios.split("\\r?\\n");
			List<Integer> lista = new ArrayList<>();	//crear la lista en la que meter los alumnos
			int numIntentados = 0;
			int numIncorrectos = 0;
			String listaFallidos = "";
			for (String linea : lineasSeparadas) {	// unUsuario = [nia, nombre]
				if(linea.matches(".*\\d+.*") || linea.matches(".*[a-zA-Z]+.*")) { //comprobar si no es solo espacios
					linea = linea.replaceAll("\\s+", " ");	//Eliminar espacios duplicados
					linea = linea.trim();	//Quitar (si existen) espacio inicial y final)
					String[] usuariosLinea = linea.split(" ");
					for(String unUsuario : usuariosLinea) {
						numIntentados++;
						if (unUsuario.matches("[0-9]+")) {	//Comprobar si el NIA se compone únicamente de dígitos
							unUsuario = unUsuario.trim();	//Quitar (si existen) espacio inicial y final)
							lista.add(Integer.parseInt(unUsuario));
						}
						else {
							listaFallidos = listaFallidos + unUsuario + "<br>";
							numIncorrectos++;
						}
					}
				}
			}
			List<Integer> listaInexistentes = InterfazAlumno.eliminarListaAlumnos(lista);
			int eliminados = numIntentados - numIncorrectos - listaInexistentes.size();
			if(eliminados == 1) {
				request.setAttribute("successMessage", "¡Genial! Se ha borrado correctamente 1 alumno<br>");
			}
			else if(eliminados > 1) {
				request.setAttribute("successMessage", "¡Genial! Se han borrado correctamente " + eliminados + " alumnos<br>");
			}
			
			if(listaInexistentes.size() > 0) {
				for(Integer alumno : listaInexistentes) {
					listaFallidos = listaFallidos + String.valueOf(alumno) + "<br>";
				}
			}
			int incorrectos = numIntentados - eliminados;
			if(incorrectos == 1) {
				request.setAttribute("errorMessage", "Ups. No se ha podido borrar el siguiente alumnno porque no se ha encontrado en el sistema:<br>");
				request.setAttribute("errorLista", listaFallidos);
			}
			if(incorrectos > 1) {
				request.setAttribute("errorMessage", "Ups. No se han podido borrar los siguientes " + incorrectos +" alumnnos porque no se ha encontrado en el sistema:<br>");
				request.setAttribute("errorLista", listaFallidos);
			}
			
			List<AlumnoBean> listaAlumnos = new ArrayList<>();
			InterfazAlumno.obtenerTodosAlumnos(listaAlumnos);
			request.setAttribute("LISTA_AL",listaAlumnos);
			
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if(admin != null){
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_borrar_usuarios.jsp");
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
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_borrar_usuarios.jsp");
						dispatcher.forward(request, response);
				}
			else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
