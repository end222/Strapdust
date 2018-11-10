package module;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			List<Cartel> lista = new ArrayList<>();
			InterfazCartel.obtenerTodosCarteles(lista);
			request.setAttribute("LISTA",lista);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
			dispatcher.forward(request, response);
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
