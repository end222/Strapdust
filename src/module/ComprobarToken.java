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
	
	public static boolean checkPDI(String j) {
		return (j != null && !j.isEmpty());
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String token = request.getParameter("token");
			String license = request.getParameter("license");
			
			if (license.equals("user")) {
			
				int NIA = InterfazAlumno.leerToken(token);
				
				if (checkNIA(NIA)) {
					request.getSession().setAttribute("LICENSE", license);
					request.getSession().setAttribute("DATA", NIA);
					request.getRequestDispatcher("control/password.jsp").forward(request, response);
					
				}
				else {
					request.setAttribute("errorMessage", "El token proporcionado no es correcto");
					RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
				}
	
			}
			else if (license.equals("admin")) {
				
				String PDI = InterfazAdministrador.leerToken(token);
				
				if (checkPDI(PDI)) {
					request.getSession().setAttribute("LICENSE", license);
					request.getSession().setAttribute("DATA", PDI);
					request.getRequestDispatcher("control/password.jsp").forward(request, response);
					
				}
				else {
					request.setAttribute("errorMessage", "El token proporcionado no es correcto");
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			
			String license = (String) request.getSession().getAttribute("LICENSE");
			request.getSession().removeAttribute("LICENSE");
			
			if (license==null) {
				response.sendError(401);
			}
			else {
				String pwd1 = request.getParameter("pass");
				String pwd2 = request.getParameter("confirm");
				
				
				if (pwd1.equals(pwd2) && pwd1.length() >= 4) {
					
					if (license.equals("user")) {
						
						int NIA = (int) request.getSession().getAttribute("DATA");
						request.getSession().removeAttribute("DATA");	
						
						InterfazAlumno.anyadirPassword(NIA, pwd1);
						response.sendRedirect("login.jsp");
	
					
					}
					else if (license.equals("admin")) {
						String PDI = (String) request.getSession().getAttribute("DATA");
						request.getSession().removeAttribute("DATA");	
						InterfazAdministrador.anyadirPassword(PDI, pwd1);
						response.sendRedirect("login.jsp");
						
					}
	
				}
				else {
					//ALgo ha fallado y lanzar error
					if (!pwd1.equals(pwd2)) {
						request.setAttribute("errorMessage", "Las contraseñas no coinciden");
						RequestDispatcher dispatcher=request.getRequestDispatcher("control/password.jsp");
						dispatcher.forward(request, response);
					}
					else {
						request.setAttribute("errorMessage", "Las contraseñas debe tener como mínimo 4 carácteres");
						RequestDispatcher dispatcher=request.getRequestDispatcher("control/password.jsp");
						dispatcher.forward(request, response);
					}
			}
		}
			

		}
		catch (Throwable Exception) {
			response.sendError(500);
			System.out.println(Exception);
		}
	}
}