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

public class SchoolPage extends HttpServlet{
	private static String file = null;
	private StudentInfo student = null;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		file = config.getInitParameter("student_list");
		if (file == null || file.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param student_info with " + file);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ServletContext sc = getServletContext();
		InputStream is = sc.getResourceAsStream(file);
		FormEntry fe = new FormEntry(is);
		String school = request.getParameter("school");
		String id = ServletUtils.processCookie(request, "id");
		String fName = ServletUtils.processCookie(request, "fName");
		String lName = ServletUtils.processCookie(request, "lName");
		String[] languages = ServletUtils.processCookie(request, "languages").split(",");
		String[] days = ServletUtils.processCookie(request, "availability").split(",");
		String userAction = request.getParameter("userAction");
		ServletUtils.deleteCookies(request, response);
		if(userAction.equals("Cancel")){
			response.sendRedirect(request.getContextPath()+"/welcome");
		}
		else{
			StudentInfo student = new StudentInfo(fName, lName, languages, days, id, school);
			fe.addInfo(id,student);
			ServletUtils.updateSessionInformation(WelcomePage.sessionId, student);
			try {
				String realPath = sc.getRealPath("/");
				realPath = realPath.concat(file.substring(1));
				fe.updateStudentFile(realPath);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/welcome");
			Cookie cookie1 = new Cookie("school", request.getParameter("school"));
			response.addCookie(cookie1);
		}
	}
}
