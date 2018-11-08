package module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class CargarCuestionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String ID = request.getParameter("cartel");
			List<Pregunta> lista = new ArrayList<>();
			InterfazPregunta.obtenerPreguntas(ID, lista);
			String json = new Gson().toJson(lista);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		catch (Throwable Exception) {
			System.out.println(Exception);
		}
	}
}
