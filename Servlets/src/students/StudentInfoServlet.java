package students;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;


public class StudentInfoServlet extends HttpServlet{
	
	private static String file = null;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		file = config.getInitParameter("student_list");
		if (file == null || file.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param student_info with " + file);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		System.out.println("IN Post..");
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("<HTML><HEAD><TITLE>Hellooo</TITLE></HEAD><BODY>");
		out.println("<h1> Student Info </h1>");
		out.println(req.getQueryString());
		
		String action = req.getParameter("action");
		if (action == null || action.length() == 0) {
			out.println("No Action provided");
			out.println("</BODY></HTML>"); 
			return;
		}
		ServletContext sc = getServletContext();
		InputStream is = sc.getResourceAsStream(file);

		FormEntry studentEntries = new FormEntry(file);
		try {
			if (action.equalsIgnoreCase("listStudents"))	{
				String[] entries = studentEntries.listEntries();
				for (int i = 0; i < entries.length; i++)
					out.println("<b>" + i + ":</b> " + entries[i] + "<br>");
			}
		}catch (Exception exc)
		{
			out.println("<p>Java exception satisfying request</p>");
			exc.printStackTrace();
		}
		out.println("</BODY></HTML>");


	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException	{
		System.out.println("IN GET..");
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("<HTML><HEAD><TITLE>In Get</TITLE></HEAD><BODY>");
		out.println("<h1> Student Info </h1></BODY>");

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
