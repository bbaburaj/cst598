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

public class NamePage extends HttpServlet{
	private static String file = null;
	private String cookieId = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		file = config.getInitParameter("student_list");
		if (file == null || file.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param student_info with " + file);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String fName = ServletUtils.processCookie(request,"fName");
		String lName = ServletUtils.processCookie(request,"lName");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Information Page</TITLE></HEAD><BODY>");
		out.println("<h1>Enter Your Name</h1>");
		out.println("<form name=\"nameForm\" action=\"name\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"id\" value=\""+request.getParameter("id")+"\">");
		out.println("<label>First Name:</label><input type=\"text\" name=\"fName\" value=\""+fName+"\"><br>"
				+ "<label>Last Name: </label><input type=\"text\" name=\"lName\" value=\""+lName+"\"><br>");
		out.println("<a href=\"welcome\">Previous</a><br>"
				+ "<a href=\"javascript:document.nameForm.submit();\">Next</a>");
		out.println("</FORM></BODY></HTML>");
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
		Cookie cookie1 = new Cookie("fName", request.getParameter("fName"));
		Cookie cookie2 = new Cookie("lName", request.getParameter("lName"));
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
	}
}
