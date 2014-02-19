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

public class SchoolPage extends HttpServlet{
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
		System.out.println("Hello in get school");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ServletContext sc = getServletContext();
		InputStream is = sc.getResourceAsStream(file);
		FormEntry fe = new FormEntry(is);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE> Student Information Record </TITLE></HEAD><BODY>");
		String school = request.getParameter("school");
		WelcomePage.student.setSchool(school);
		out.println(WelcomePage.student.toString()+"<br>");
		fe.addInfo(WelcomePage.student.getStudentId(), WelcomePage.student);
		try {
			String realPath = sc.getRealPath("/");
			realPath = realPath.concat(file.substring(1));
			fe.updateStudentFile(realPath);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</BODY></HTML>");
	}
}
