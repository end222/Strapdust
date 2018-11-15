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

public class CrearAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String cadenaUsuarios = request.getParameter("usuarios"); // Lista de usuarios
			String[] usuariosSeparados = cadenaUsuarios.split("\\r?\\n");
			List<Administrador> lista = new ArrayList<>(); // crear la lista en la que meter los alumnos
			int numIntentados = 0;
			for (String unUsuario : usuariosSeparados) { // unUsuario = [pdi, nombre]
				if (unUsuario.matches(".*\\d+.*") || unUsuario.matches(".*[a-zA-Z]+.*")) { // comprobar si no es solo
																							// espacios
					numIntentados++;
					unUsuario = unUsuario.replaceAll("\\s+", " "); // Eliminar espacios duplicados
					unUsuario = unUsuario.trim(); // Quitar (si existen) espacio inicial y final)
					String[] parts = unUsuario.split(" ");
					int inicioNombre = unUsuario.indexOf(' ');
					unUsuario = unUsuario.substring(inicioNombre);
					unUsuario = unUsuario.trim(); // Quitar (si existen) espacio inicial y final)
					Administrador admin = new Administrador(unUsuario, parts[0]); // crear el Administrador
					lista.add(admin);
				}
			}

			int insertados = InterfazAdministrador.anyadirListaAdmin(lista);
			if (insertados == 1) {
				request.setAttribute("successMessage", "¡Genial! Se ha añadido correctamente 1 administrador");
			} else if (insertados > 1) {
				request.setAttribute("successMessage",
						"¡Genial! Se han añadido correctamente " + insertados + " administradores");
			}
			int fallidos = numIntentados - insertados;
			if (fallidos == 1) {
				request.setAttribute("errorMessage", "Vaya... ha fallado la inserción de 1 administrador");
			}
			if (fallidos > 1) {
				request.setAttribute("errorMessage", "Vaya... ha fallado la inserción de " + fallidos + " administradores");
			}

			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if (admin != null) {
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/WEB-INF/control/x_nuevo_usuario.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Throwable Exception) {
			request.setAttribute("errorMessage", "El sistema ha fallado");
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if (admin != null) {
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/WEB-INF/control/x_nuevo_usuario.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
