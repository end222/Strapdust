package module;

import java.util.Random;


import java.io.IOException;


import Bean.AlumnoBean;
import module.AdministradorBean;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

public class RegistrarUsuario extends HttpServlet{
	
	private static final Random random = new Random();
	private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";

	public static String getToken(int length) {
	    StringBuilder token = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	        token.append(CHARS.charAt(random.nextInt(CHARS.length())));
	    }
	    return token.toString();
	}
	
	public static boolean checkTimestamp(Timestamp toa) {
		return (toa==null);
		
	}
	
	public static boolean checkNIA(int i) {
		return (i!=0);
		
	}
	public static boolean checkPDI(String j) {
		return (j != null && !j.isEmpty());
		
	}
	
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			
			String UNI = request.getParameter("username");
			
			if (StringUtils.isStrictlyNumeric(UNI)) {
				
				AlumnoBean candidato;
				String NIA = UNI;
				candidato = InterfazAlumno.obtenerUnAlumno(NIA);
				
				if(checkNIA(candidato.getNIA())) {
					Timestamp activa = candidato.getFecha();
					//Timestamp nulo = new Timestamp(0);
					boolean novas = checkTimestamp(activa);
					if(novas){
						//La cuenta no se ha dado de alta por lo que se puede registrar en el sistema
						String token;
						//Generamos el token único de usuario
						token = getToken(20);
						int NIAn = Integer.parseInt(NIA);		
						if (InterfazAlumno.anyadirToken(NIAn, token)) {
							//Token insertado satisfactoriamente en la tabla de tokens
							String correo = NIA + "@unizar.es";
							
							String web = "https://erios.ml/ComprobarToken.html?license=user&token=";
							String msg = web+token;
							//Enviamos el mensaje al usuario
							Mailer.send(correo, "Token de registro", msg);
							request.setAttribute("success", "Se ha enviado un mensaje de confimación a tu correo de la universidad");
							RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
							dispatcher.forward(request, response);
						}
						
					}
					else {
						request.setAttribute("errorMessage", "El usuario ya ha sido dado de alta en el sistema");
						RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
						dispatcher.forward(request, response);
						// La cuenta ya estaba en el sistema por lo que no se puede registrar
					}
			}
				else {
					request.setAttribute("errorMessage", "El usuario no se encuentra en la lista de admitidos para participar");
					RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
					//La cuenta ni siquiera está en los usuarios admitidos
				
				}
			}
			// No es un alumno, hay que tratarlo como administrador
			else {
				AdministradorBean candidato;
				String PDI = UNI.toLowerCase();
				candidato = InterfazAdministrador.obtenerUnAdmin(PDI);
				if(checkPDI(candidato.getPDI())) {
					String token;
					//Generamos el token único de usuario
					token = getToken(20);
					if (InterfazAdministrador.anyadirToken(PDI, token)) {
						//Token insertado satisfactoriamente en la tabla de administradores
						String correo = PDI + "@unizar.es";
						
						String web = "https://erios.ml/ComprobarToken.html?license=admin&token=";
						String msg = web+token;
						//Enviamos el mensaje al profesor
						Mailer.send(correo, "Token de registro", msg);
						request.setAttribute("success", "Se ha enviado un mensaje de confimación a tu correo corporativo");
						RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
						dispatcher.forward(request, response);
					}
					else {
						response.sendError(500);
					}
					
				}
				else {
					request.setAttribute("errorMessage", "No se ha encontrado el PDI en la base de datos");
					RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
				}
				
			}
			
				
		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			
		
			HttpSession session = request.getSession();
			AlumnoBean alumno = (AlumnoBean) session.getAttribute("AlumnoBean");
			AdministradorBean admin = (AdministradorBean) session.getAttribute("AdministradorBean");
			String token = getToken(20);
			if((alumno != null )){
				int NIA  = alumno.getNIA();
				if (InterfazAlumno.anyadirToken(NIA, token)) {
					//Token insertado satisfactoriamente en la tabla de tokens
					String correo = NIA + "@unizar.es";
					String web = "https://erios.ml/ComprobarToken.html?license=user&token=";
					String msg = web+token;
					//Enviamos el mensaje al usuario
					Mailer.send(correo, "Token de registro", msg);
					request.setAttribute("success", "Revisa tu correo de la universidad");
					RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
				}
			}
			else if (admin != null){
				String PDI = admin.getPDI();
				if (InterfazAdministrador.anyadirToken(PDI, token)) {
					//Token insertado satisfactoriamente en la tabla de tokens
					String correo = PDI + "@unizar.es";
					String web = "https://erios.ml/ComprobarToken.html?license=admin&token=";
					String msg = web+token;
					//Enviamos el mensaje al usuario
					Mailer.send(correo, "Token de registro", msg);
					request.setAttribute("success", "Revisa tu correo de la universidad");
					RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				response.sendError(401);
			}


		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
}

