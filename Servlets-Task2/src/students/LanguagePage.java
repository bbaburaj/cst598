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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String savedLanguages = ServletUtils.processCookie(request,"languages");
		String[] languages = null;
		boolean firstRun = false;
		if(savedLanguages == null){
			languages = ServletUtils.languageArray;
			firstRun = true;
		}else{
			languages = savedLanguages.split(",");
		}
		out.println("<HTML><HEAD><TITLE> Information Page</TITLE></HEAD><BODY>");
		out.println("<h1>Enter Languages</h1>");
		out.println("<form name=\"languageForm\" action=\"language\" method=\"post\" >");
		out.println("<label>Languages Known:</label><br>");
		ServletUtils.prePopulateCheckBox(out, languages, "languages",firstRun);
		out.println("<a href=\"name\" > Previous </a><br><br>");
		out.println("<a href=\"javascript:document.languageForm.submit();\" >Next</a>");
		out.println("</FORM></BODY></HTML>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
		if(ServletUtils.getString(request.getParameterValues("languages"))!=null){
		Cookie cookie1 = new Cookie("languages", ServletUtils.getString(request.getParameterValues("languages")));
		response.addCookie(cookie1);
		}
	}
}
