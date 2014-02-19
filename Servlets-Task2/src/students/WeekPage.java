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

public class WeekPage extends HttpServlet{
	private static String file = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		file = config.getInitParameter("student_list");
		if (file == null || file.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param student_info with " + file);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Hello in get week");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>");
		out.println("<h1>Enter Your High School</h1>");
		out.println("<form action=\"school\" method=\"post\">");
		out.println("<label>High School:</label><br>");
		out.println("<input type=\"text\" name=\"school\"><br>");
		out.println("<input type=\"Submit\" value=\"Submit\">");
		out.println("<input type=\"Submit\" value=\"cancel\"><br>");
		out.println("<a href=\"Week.html\"> Previous </a><br>");
		out.println("</BODY></HTML>");
		WelcomePage.student.setDaysAvailable(request.getParameterValues("availability"));
	}
}
