package utility;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import students.StudentInfo;

/**
 * The utility class for all operations invoked by the Servlets
 * @author Barathi
 * @author Pratibha
 */
public class ServletUtils {
	private static Map<String, StudentInfo> savedSessions = new HashMap<String, StudentInfo>();
	public static String[] languageArray = {"java","c#", "c","c++","scala","ada","python","j#","lisp","None of the above"};
	public static String[] daysArray = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday","None of the above"};
	public static String generateUniqueId() throws UnsupportedEncodingException{
		UUID uid = UUID.randomUUID();  
		String id = URLEncoder.encode(uid.toString(),"UTF-8");
		return id;
	}
	
	public static void updateSessionInformation(String id, StudentInfo info){
		savedSessions.put(id, info);
	}
	
	public static StudentInfo getSessionInformation(String sessionid) {
		return savedSessions.get(sessionid);
	}
	
	/**
	 * Convert the array to string with concatenating ","
	 * @param myArray
	 * @return myString
	 */
	public static String getString(String[] myArray){
		if (myArray==null)return "";
		String myString = "";
		for (String s : myArray) {
			myString = myString.concat(s.concat(","));
		}
		return myString.substring(0,myString.length()-1);
	}
	
	/**
	 * Process the cookie information and return the value.
	 * @param req
	 * @param cookieName
	 * @return cookie
	 */
	public static String processCookie(HttpServletRequest req, String cookieName) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	      for (int i = 0; i < cookies.length; i++) {
	        if (cookies[i].getName().equals(cookieName)) {
	          return cookies[i].getValue();
	        }
	      }
	    }
	    return null;
	}
	
	/**
	 * Delete all the cookies except session id cookie.
	 * @param req
	 * @param res
	 */
	public static void deleteCookies(HttpServletRequest req, HttpServletResponse res){
		Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	        	if(!cookie.getName().equals("sessionid")){
	        		cookie.setMaxAge(0);
		            cookie.setValue("");
		            res.addCookie(cookie);
	        	}
	        }
	    }
	    
	}
	
	/**
	 * Fetch existing cookies. In order to replace them if needed.
	 * @param req
	 * @param cookieName
	 * @return cookie
	 */
	public static Cookie fetchExistingCookie(HttpServletRequest req, String cookieName) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	      for (int i = 0; i < cookies.length; i++) {
	        if (cookies[i].getName().equals(cookieName)) {
	          return cookies[i];
	        }
	      }
	    }
	    return null;
	}
	
	/**
	 * Prepopulate the checkboxes as checked or unchecked based on the cookie information.
	 * @param out
	 * @param selectedValues
	 * @param name
	 * @param firstRun
	 */
	public static void prePopulateCheckBox(PrintWriter out, String[] selectedValues, String name, boolean firstRun){
		List<String> selectedValuesList = Arrays.asList(selectedValues);	
		String[] testArray = (name.equals("languages"))?languageArray:daysArray;
		for(String s:testArray){
			String op = "<input type=\"checkbox\" name=\""+name+"\" value=\""+s+"\"";
			if(selectedValuesList.contains(s) && !firstRun){
				op = op+" checked>";
			}else{
				op = op+">";
			}
			
			out.println(op+s+"<br>");
		}
	}
	
	/**
	 * Method either create a new cookie or update the value of an existing cookie
	 * @param request
	 * @param name
	 * @param value
	 * @return cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String name, String value){
		Cookie cookie =  fetchExistingCookie(request,name);
		if(cookie!=null)
		{
			cookie.setValue(value);
		}
		else
		{
			cookie = new Cookie(name, value);
		}
		return cookie;
		
	}
	

}
