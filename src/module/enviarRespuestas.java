package module;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class enviarRespuestas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String respuesta = request.getParameter("respuesta");
			int edad = Integer.parseInt(request.getParameter("edad"));
			int unizar = Integer.parseInt(request.getParameter("uni"));
			int id = Integer.parseInt(request.getParameter("idStat"));
			Estadisticas stat = InterfazEstadisticas.obtenerEstadisticas(id);
			stat.completar(respuesta, edad, unizar);
			InterfazEstadisticas.anyadirEstadisticas(stat);
			response.sendRedirect("index");
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
