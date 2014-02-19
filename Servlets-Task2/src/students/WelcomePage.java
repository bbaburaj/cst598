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
	public static StudentInfo student = new StudentInfo();
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
		System.out.println("Hello in get");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Information Page</TITLE></HEAD><BODY>");
		out.println("<h1>Enter Your Name</h1> <form name=\"nameForm\" action=\"name\" method=\"post\">");
		out.println("<label>First Name:</label><input type=\"text\" name=\"fName\"><br><label>Last Name: </label><input type=\"text\" name=\"lName\"><br>");
		out.println("<a href=\"Welcome.html\"> Previous </a><br><a href=\"javascript:document.nameForm.submit();\">Next</a>");
		out.println("</FORM></BODY></HTML>");
		student.setStudentId(request.getParameter("id"));
	}
}
