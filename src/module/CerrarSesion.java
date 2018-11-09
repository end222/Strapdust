package module;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			request.getSession().invalidate();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
			dispatcher.forward(request, response);
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
