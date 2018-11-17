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

public class CrearGrupo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String grupo = request.getParameter("grupo"); // Lista de usuarios
			if(grupo.matches(".*\\d+.*") || grupo.matches(".*[a-zA-Z]+.*")) { //Si grupo no es vacío
				grupo.trim();
				if(!grupo.matches(".*\\s+.*")) {
					String cadenaUsuarios = request.getParameter("usuarios"); // Lista de usuarios
					String[] lineasSeparadas = cadenaUsuarios.split("\\r?\\n");
					List<String> lista = new ArrayList<>();	//crear la lista en la que meter los alumnos
					lista.add(grupo);
					int numInvalidos = 0;
					String listaFallidos = "";
					for (String linea : lineasSeparadas) {	// unUsuario = [nia, nombre]
						if(linea.matches(".*\\d+.*") || linea.matches(".*[a-zA-Z]+.*")) { //comprobar si no es solo espacios
							linea = linea.replaceAll("\\s+", " ");	//Eliminar espacios duplicados
							linea = linea.trim();	//Quitar (si existen) espacio inicial y final)
							String[] usuariosLinea = linea.split(" ");
							for(String unUsuario : usuariosLinea) {
								if (unUsuario.matches("[0-9]+")) {	//Comprobar si el NIA se compone únicamente de dígitos
									unUsuario = unUsuario.trim();	//Quitar (si existen) espacio inicial y final)
									lista.add(unUsuario);
								}
								else {
									listaFallidos = listaFallidos + unUsuario + "<br>";
									numInvalidos++;
								}
							}
						}
					}
					if(numInvalidos == 0) {
						if(lista.size() > 1) {
							List<String> listaErrores = InterfazGrupo.crearNuevoGrupo(lista);
							if(listaErrores.isEmpty()) {//Si todo ha ido bien
								request.setAttribute("successMessage", "¡Genial! Se ha creado el grupo correctamente");
							}
							else {
								if(listaErrores.get(0).equals("grupo_existente")) {//El grupo ya existe
									request.setAttribute("errorMessage", "Ups. Parece que ya existe un grupo con ese nombre");
								}
								else {//El fallo se debe a que hay alumnos que no existen
									int numInexistentes = 0;
									String listaInexistentes = "";
									for(String unUsuario : listaErrores) {
										numInexistentes++;
										listaInexistentes = listaInexistentes + unUsuario + "<br>";
									}
									if(numInexistentes == 1) {
										request.setAttribute("errorMessage", "Ups. No se ha podido crear el grupo porque el siguiente alumno introducido no existe:<br>");
										request.setAttribute("errorLista", listaInexistentes);
									}
									else {
										request.setAttribute("errorMessage", "Ups. No se ha podido crear el grupo porque los " + numInexistentes + " siguientes alumnos introducidos no existen:<br>");
										request.setAttribute("errorLista", listaInexistentes);
									}
									
								}
							}
						}
						else {	//No se ha introducido ningun alumno
							request.setAttribute("errorMessage", "No puede crear un grupo sin alumnos...<br>");
						}
						
					}
					//Si se han introducido alumnos no válidos
					else if(numInvalidos == 1){
						request.setAttribute("errorMessage", "Ups. No se ha podido crear el grupo porque el siguiente alumno introducido no es válido:<br>");
						request.setAttribute("errorLista", listaFallidos);
					}
					else {
						request.setAttribute("errorMessage", "Ups. No se ha podido crear el grupo porque los " + numInvalidos +" siguientes alumnnos introducidos no son válidos:<br>");
						request.setAttribute("errorLista", listaFallidos);
					}
				}
				else {
					request.setAttribute("errorMessage", "No ha funcionado. Recuerde que el nombre de grupo no debe contener espacios.<br>");
				}	
			}
			else {
				request.setAttribute("errorMessage", "No ha funcionado. La próxima vez introduzca un nombre de grupo.<br>");
			}
			
			
			HttpSession session = request.getSession();
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			if(admin != null){
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_nuevo_grupo.jsp");
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
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/control/x_nuevo_grupo.jsp");
						dispatcher.forward(request, response);
				}
			else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
