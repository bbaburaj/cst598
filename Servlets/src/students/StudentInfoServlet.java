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
			String file1 = request.getRequestURI().substring(0,request.getRequestURI().lastIndexOf("\\"));
			System.out.println(file1);
			
			fe.updateStudentFile(file1.concat(file));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


//		res.setContentType("text/html");
//		PrintWriter out= res.getWriter();
//		out.println("<HTML><HEAD><TITLE>Phone List</TITLE></HEAD><BODY>");
//
//		String action = req.getParameter("action");
//		if (action == null || action.length() == 0) {
//			out.println("No Action provided");
//			out.println("</BODY></HTML>"); 
//			return;
//		}
//		// now get the phonebook file as an input stream
//		ServletContext sc = getServletContext();
//		InputStream is = sc.getResourceAsStream(_filename);
//
//		PhoneBook pbook = new PhoneBook(is);
//
//		try {
//			if (action.equalsIgnoreCase("List"))	{
//				String[] entries = pbook.listEntries();
//				for (int i = 0; i < entries.length; i++)
//					out.println("<b>" + i + ":</b> " + entries[i] + "<br>");
//			}
//		}
//		catch (Exception exc)
//		{
//			out.println("<p>Java exception satisfying request</p>");
//			exc.printStackTrace();
//		}
//		out.println("</BODY></HTML>");
	}
}
