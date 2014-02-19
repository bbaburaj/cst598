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

public class NamePage extends HttpServlet{
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
		System.out.println("Hello in get name");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Information Page</TITLE></HEAD><BODY>");
		out.println("<h1>Enter Languages</h1>");
		out.println("<form name=\"languageForm\" action=\"language\" method=\"post\" >");
		out.println("<label>Languages Known:</label><br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"C\" >C<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"C++\" >C++<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"C#\" >C#<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"Java\" >Java<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"J#\" >J#<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"Python\" >Python<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"scala\" >scala<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"lisp\" >lisp<br>");
		out.println("<input type=\"checkbox\" name=\"languages\" value=\"ada\" >ada<br>");
		out.println("<a href=\"Name.html\" > Previous </a><br><br>");
		out.println("<a href=\"javascript:document.languageForm.submit();\" >Next</a>");
		out.println("</FORM></BODY></HTML>");
		System.out.println(request.getParameter("fName")+" "+request.getParameter("lName"));
		WelcomePage.student.setFirstName(request.getParameter("fName"));
		WelcomePage.student.setLastName(request.getParameter("lName"));
	}
}
