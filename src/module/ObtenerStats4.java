package module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ObtenerStats4 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			List<String> lista = new ArrayList<>();
			String nombreGrupo = request.getParameter("grupo");
			int id = InterfazCartel.obtenerCartelId(nombreGrupo);
			InterfazEstadisticas.obtenerRespuestas(id, lista);
			request.setAttribute("lista", lista);
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
