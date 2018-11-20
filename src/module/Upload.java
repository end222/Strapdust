package module;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Bean.AlumnoBean;

@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Part filePart = request.getPart("file");
	    Pregunta preg1 = new Pregunta(1, 0, String.valueOf(request.getParameter("enunciado1")), String.valueOf(request.getParameter("pregunta11")), String.valueOf(request.getParameter("pregunta12")), String.valueOf(request.getParameter("pregunta13")), (request.getParameter("respuesta11") != null) ? 1 : 0, (request.getParameter("respuesta12") != null) ? 1 : 0, (request.getParameter("respuesta13") != null) ? 1 : 0, String.valueOf(request.getParameter("explicacion1")));
	    Pregunta preg2 = new Pregunta(2, 0, String.valueOf(request.getParameter("enunciado2")), String.valueOf(request.getParameter("pregunta21")), String.valueOf(request.getParameter("pregunta22")), String.valueOf(request.getParameter("pregunta23")), (request.getParameter("respuesta21") != null) ? 1 : 0, (request.getParameter("respuesta22") != null) ? 1 : 0, (request.getParameter("respuesta23") != null) ? 1 : 0, String.valueOf(request.getParameter("explicacion2")));
	    Pregunta preg3 = new Pregunta(3, 0, String.valueOf(request.getParameter("enunciado3")), String.valueOf(request.getParameter("pregunta31")), String.valueOf(request.getParameter("pregunta12")), String.valueOf(request.getParameter("pregunta33")), (request.getParameter("respuesta31") != null) ? 1 : 0, (request.getParameter("respuesta32") != null) ? 1 : 0, (request.getParameter("respuesta33") != null) ? 1 : 0, String.valueOf(request.getParameter("explicacion3")));
	    Cartel cartel = new Cartel(0, String.valueOf(request.getParameter("titulo")), String.valueOf(request.getParameter("noticia")), "", String.valueOf(request.getParameter("opinion")), String.valueOf(request.getParameter("reto")), (request.getParameter("options").equals("on")) ? 1 : 0, new Timestamp(0), String.valueOf(request.getParameter("enlace")));
	    HttpSession session = request.getSession();
		AlumnoBean alumno = (AlumnoBean) session.getAttribute("AlumnoBean");
		String grupo = alumno.getGrupo();
	    int id = InterfazCartel.obtenerCartelId(grupo);
	    int idCartel = InterfazCartel.subirCartel(cartel, preg1, preg2, preg3, id, grupo);
	    if(!filePart.getSubmittedFileName().equals("")) {
		    InputStream fileContent = filePart.getInputStream();
		    String fileName = String.valueOf(idCartel) + ".png"; // MSIE fix.
		    String absolutePath = getServletContext().getRealPath("/images");
		    File file = new File(absolutePath, fileName);
		    if(file.exists()) file.delete();
		    Files.copy(fileContent, file.toPath());
	    }
	    response.sendRedirect("ComprobarSeguridad.do?direccion=a_alumno.jsp");
	}
}