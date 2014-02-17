package students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.servlet.*;
import javax.servlet.http.*;


public class StudentInfoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static String file = null;

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		file = config.getInitParameter("student_list");
		if (file == null || file.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param student_info with " + file);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>"); 
		String id = request.getParameter("id");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String[] lang = request.getParameterValues("languages");
		String[] availability = request.getParameterValues("availability");
		
		ServletContext sc = getServletContext();
		InputStream is = sc.getResourceAsStream(file);
		FormEntry fe = new FormEntry(is);
		fe.addInfo(fName, lName, lang, availability, id);
		try {
			String realPath = sc.getRealPath("/");
			realPath = realPath.concat(file.substring(1));
			fe.updateStudentFile(realPath);
			out.println("<H1> Transaction was successful</H1>");
			out.println("<H1> Total number of entries: "+Integer.toString(fe.getTotalStudents())+"<H1>");
			
		} catch (URISyntaxException e) {
			out.println("<H1> Transaction was unsuccessful</H1>");
		}
		out.println("</BODY></HTML>");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException	{
		System.out.println(request.getRequestURI());
		System.out.println(request.getQueryString());
		System.out.println("Param_Map"+request.getParameterMap());
		String action = request.getParameter("action");
		PrintWriter out= response.getWriter();
		if (action == null || action.length() == 0) {
			out.println("No Action provided");
			out.println("</BODY></HTML>"); 
			return;
		}
	}
}
