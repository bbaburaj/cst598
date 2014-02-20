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
	
	public static String getString(String[] myArray){
		System.out.println("["+myArray+"]");
		String myString = "";
		for (String s : myArray) {
			myString = myString.concat(s.concat(","));
		}
		return myString.substring(0,myString.length()-1);
	}
	
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
