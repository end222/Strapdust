package module;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class quizStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			int cartel = Integer.parseInt(request.getParameter("cartel"));
			int acierto1 = Integer.parseInt(request.getParameter("a1"));
			int acierto2 = Integer.parseInt(request.getParameter("a2"));
			int acierto3 = Integer.parseInt(request.getParameter("a3"));
			int ID = InterfazEstadisticas.obtenerIDMax() + 1;
			Estadisticas stats = new Estadisticas(ID, acierto1, acierto2, acierto3, cartel);
			InterfazEstadisticas.anyadirEstadisticas(stats);
			Cartel cart = InterfazCartel.obtenerCartel(cartel);
			request.setAttribute("id",ID);
			request.setAttribute("pregunta",cart.getPregunta());
			request.setAttribute("reto",cart.getReto());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/stats.jsp");
			dispatcher.forward(request, response);
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
