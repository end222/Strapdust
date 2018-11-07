package module;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComprobarToken extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3063438190435345077L;

	public static boolean checkToken(String token){
		return (token==null);
	}
	
	public static boolean checkNIA(int i) {
		return (i!=0);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String token = request.getParameter("token");
			int NIA = InterfazAlumno.leerToken(token);
			
			if (checkNIA(NIA)) {
				request.setAttribute("NIA", NIA);
				request.setAttribute("TOKEN", token);
				request.getRequestDispatcher("control/password.jsp").forward(request, response);
				
			}
			else {
				request.setAttribute("errorMessage", "El token proporcionado no es correcto");
				RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}

		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String NIA = request.getParameter("NIA");
			String token = request.getParameter("TOKEN");
			String pwd1 = request.getParameter("pass");
			String pwd2 = request.getParameter("confirm");
			int NYAN = Integer.parseInt(NIA);
			if (pwd1.equals(pwd2) && InterfazAlumno.leerToken(token) == NYAN) {
				InterfazAlumno.anyadirPassword(NYAN, pwd1);
				response.sendRedirect("login.jsp");
			}
			else {
				//ALgo ha fallado y lanzar error
				if (!pwd1.equals(pwd2)) {
					request.setAttribute("NIA", NIA);
					request.setAttribute("TOKEN", token);
					request.setAttribute("errorMessage", "Las contraseñas no coinciden");
					RequestDispatcher dispatcher=request.getRequestDispatcher("control/password.jsp");
					dispatcher.forward(request, response);
				}
				else {
					request.setAttribute("errorMessage", "El token proporcionado no es correcto");
					RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
				}
			}
		
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}