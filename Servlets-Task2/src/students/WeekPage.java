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

import utility.ServletUtils;

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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String savedDays = ServletUtils.processCookie(request,"availability");
		String[] days = null;
		boolean firstRun = false;
		if(savedDays == null){
			days = ServletUtils.daysArray;
			firstRun = true;
		}else{
			days = savedDays.split(",");
		}
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>");
		out.println("<h1>Select Days Available</h1>");
		out.println("<form name=\"weekForm\" action=\"week\" method=\"post\">");
		out.println("<label>Available On:</label><br>");
		ServletUtils.prePopulateCheckBox(out, days, "availability",firstRun);
		out.println("<a href=\"language\"> Previous </a><br><br>");
		out.println("<a href=\"javascript:document.weekForm.submit();\">Next</a>");
		out.println("</BODY></HTML>");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String school = ServletUtils.processCookie(request,"school");
		school = ((school == null) ? "" : school); 
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>");
		out.println("<h1>Enter Your High School</h1>");
		out.println("<form action=\"school\" method=\"post\">");
		out.println("<label>High School:</label><br>");
		out.println("<input type=\"text\" name=\"school\" value=\""+school+"\"><br>");
		out.println("<input type=\"Submit\" name=\"userAction\" value=\"Submit\">");
		out.println("<input type=\"Submit\" name=\"userAction\" value=\"Cancel\"><br>");
		out.println("<a href=\"week\"> Previous </a><br>");
		out.println("</BODY></HTML>");
		Cookie cookie1 = ServletUtils.getCookie(request, "availability", ServletUtils.getString(request.getParameterValues("availability")));
		response.addCookie(cookie1);
	}
}
