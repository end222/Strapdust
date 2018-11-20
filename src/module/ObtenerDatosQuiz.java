package module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AlumnoBean;

public class ObtenerDatosQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AlumnoBean alumno = (AlumnoBean) session.getAttribute("AlumnoBean");
		String grupo = alumno.getGrupo();
	    Pregunta preg1 = new Pregunta();
	    Pregunta preg2 = new Pregunta();
	    Pregunta preg3 = new Pregunta();
	    Cartel cartel = new Cartel();
		List<Pregunta> lista = new ArrayList<>();
	    InterfazCartel.obtenerCartelByGrupo(grupo, cartel, lista);
	    if (grupo != null) {
			preg1 = lista.get(0);
			preg2 = lista.get(1);
			preg3 = lista.get(2);
		    request.setAttribute("Cartel", cartel);
		    request.setAttribute("Preg1", preg1);
		    request.setAttribute("Preg2", preg2);
		    request.setAttribute("Preg3", preg3);
		    request.setAttribute("correcta11", (preg1.getCorrecta1() == 1) ? "checked" : "");
		    request.setAttribute("correcta12", (preg1.getCorrecta2() == 1) ? "checked" : "");
		    request.setAttribute("correcta13", (preg1.getCorrecta3() == 1) ? "checked" : "");
		    request.setAttribute("correcta21", (preg2.getCorrecta1() == 1) ? "checked" : "");
		    request.setAttribute("correcta22", (preg2.getCorrecta2() == 1) ? "checked" : "");
		    request.setAttribute("correcta23", (preg2.getCorrecta3() == 1) ? "checked" : "");
		    request.setAttribute("correcta31", (preg3.getCorrecta1() == 1) ? "checked" : "");
		    request.setAttribute("correcta32", (preg3.getCorrecta2() == 1) ? "checked" : "");
		    request.setAttribute("correcta33", (preg3.getCorrecta3() == 1) ? "checked" : "");
		    request.setAttribute("requiredImg", (cartel.getImagen().equals("")) ? "required" : "");
	    }
	    else {
	    	 request.setAttribute("np", "disabled");
	    }
	    
	    

	}
}