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

public class CrearAlumno extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String cadenaUsuarios = request.getParameter("usuarios"); // Lista de usuarios
			String[] usuariosSeparados = cadenaUsuarios.split("\\r?\\n");
			List<Alumno> lista = new ArrayList<>();	//crear la lista en la que meter los alumnos
			int numIntentados = 0;
			String listaFallidos = "";
			for (String unUsuario : usuariosSeparados) {	// unUsuario = [nia, nombre]
				if(unUsuario.matches(".*\\d+.*") || unUsuario.matches(".*[a-zA-Z]+.*")) { //comprobar si no es solo espacios
					numIntentados++;
					unUsuario = unUsuario.replaceAll("\\s+", " ");	//Eliminar espacios duplicados
					unUsuario = unUsuario.trim();	//Quitar (si existen) espacio inicial y final)
					String[] parts = unUsuario.split(" ");
					
					if (parts[0].matches("[0-9]+")) {	//Comprobar si el NIA se compone únicamente de dígitos
						int inicioNombre = unUsuario.indexOf(' ');
						unUsuario = unUsuario.substring(inicioNombre);
						unUsuario = unUsuario.trim();	//Quitar (si existen) espacio inicial y final)
						Alumno al = new Alumno(unUsuario, Integer.parseInt(parts[0])); //crear el Alumno
						lista.add(al);
					}
					else {
						listaFallidos = listaFallidos + unUsuario + "<br>";
					}
				}
			}
			int insertados = InterfazAlumno.anyadirListaAlumnos(lista);
			if(insertados > 0) {
				request.setAttribute("successMessage", "Se han añadido correctamente " + insertados + " alumno/s");
			}
			int fallidos = numIntentados - insertados;
			if(fallidos > 0) {
				request.setAttribute("errorMessage", "Ha fallado la inserción de " + fallidos + " alumno/s:");
				request.setAttribute("errorLista", listaFallidos);
			}
			
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if(admin != null){
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_nuevo_usuario.jsp");
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
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_nuevo_usuario.jsp");
						dispatcher.forward(request, response);
				}
			else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
