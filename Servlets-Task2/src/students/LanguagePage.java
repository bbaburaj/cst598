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

public class LanguagePage extends HttpServlet{
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
		System.out.println("Hello in get language");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>");
		out.println("<h1>Select Days Available</h1>");
		out.println("<form name=\"weekForm\" action=\"week\" method=\"post\">");
		out.println("<label>Available On:</label><br>");
		out.println("<input type=\"checkbox\" name=\"availability\" value=\"Monday\">Monday<br>");
		out.println("<input type=\"checkbox\" name=\"availability\" value=\"Tuesday\">Tuesday<br>");
		out.println("<input type=\"checkbox\" name=\"availability\" value=\"Wednesday\">Wednesday<br>");
		out.println("<input type=\"checkbox\" name=\"availability\" value=\"Thursday\">Thursday<br>");
		out.println("<input type=\"checkbox\" name=\"availability\" value=\"Friday\">Friday<br>");
		out.println("<input type=\"checkbox\" name=\"availability\" value=\"Saturday\">Saturday<br>");
		out.println("<input type=\"checkbox\" name=\"availability\" value=\"Sunday\">Sunday<br>");
		out.println("<a href=\"Language.html\"> Previous </a><br><br>");
		out.println("<a href=\"javascript:document.weekForm.submit();\">Next</a>");
		out.println("</BODY></HTML>");
		WelcomePage.student.setLanguagesKnown(request.getParameterValues("languages"));
	}
}
