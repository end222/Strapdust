package module;

import java.util.Random;


import java.io.IOException;


import Bean.AlumnoBean;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			//Map<String, String> errors = new HashMap<String,String>();
			String NIA = request.getParameter("username");
			
			AlumnoBean candidato;
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
	
					if (InterfazAlumno.anyadirToken(NIA, token)) {
						//Token insertado satisfactoriamente en la tabla de tokens
						String correo = NIA + "@unizar.es";
						
						String web = "http://localhost:8080/Sistemas/ComprobarToken.html?token=";
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
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
