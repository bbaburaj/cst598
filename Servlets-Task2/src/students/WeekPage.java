package students;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ServletUtils;

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
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>");
		out.println("<h1>Enter Your High School</h1>");
		out.println("<form action=\"school\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"id\" value=\""+request.getParameter("id")+"\">");
		out.println("<input type=\"hidden\" name=\"fName\" value=\""+request.getParameter("fName")+"\">");
		out.println("<input type=\"hidden\" name=\"lName\" value=\""+request.getParameter("lName")+"\">");
		out.println("<input type=\"hidden\" name=\"languages\" value=\""+request.getParameter("languages")+"\">");
		out.println("<input type=\"hidden\" name=\"availability\" value=\""+ServletUtils.getString(request.getParameterValues("availability"))+"\">");
		out.println("<label>High School:</label><br>");
		out.println("<input type=\"text\" name=\"school\"><br>");
		out.println("<input type=\"Submit\" name=\"userAction\" value=\"Submit\">");
		out.println("<input type=\"Submit\" name=\"userAction\" value=\"Cancel\"><br>");
		out.println("<a href=\"week\"> Previous </a><br>");
		out.println("</BODY></HTML>");
	}
}
