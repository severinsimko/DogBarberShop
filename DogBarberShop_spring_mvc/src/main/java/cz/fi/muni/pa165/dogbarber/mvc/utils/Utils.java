package cz.fi.muni.pa165.dogbarber.mvc.utils;

import javax.servlet.http.HttpSession;

public class Utils {
	public static String redirectAuthorized(HttpSession session, String location){
		if (isAuthorized(session))
            return "redirect:" + location;
        return "/auth/login";
	}
	
	public static boolean isAuthorized(HttpSession session){
		return isAdmin(session) || isAuthUser(session);
	}
	
	public static boolean isUnauthorized(HttpSession session){
		return !isAuthorized(session);
	}
	
	public static boolean isAdmin(HttpSession session){
		return session.getAttribute("admin") != null;
	}
	
	public static boolean isAuthUser(HttpSession session){
		return session.getAttribute("authUser") != null;
	}
}
