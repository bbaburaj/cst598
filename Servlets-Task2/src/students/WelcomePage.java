package students;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomePage extends HttpServlet{
	private static String file = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		file = config.getInitParameter("student_list");
		if (file == null || file.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param student_info with " + file);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>");
		String id = request.getParameter("id");
		System.out.println(id);
		
		out.println("</BODY></HTML>");
	}
}
