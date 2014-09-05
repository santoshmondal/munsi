package com.async.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.munsi.pojo.auth.AccessUser;

public class PortalUtil {

	public static String KEY_USER_ID = "userId";
	public static String KEY_USER_ROLE = "role";
	public static String KEY_USER_NAME = "userName";
	public static String KEY_NAME = "name";

	public static String getLoggedUserId(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(KEY_USER_ID);
	}

	public static String getLoggedUserRole(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(KEY_USER_ROLE);
	}

	public static void setLoggedUserInSession(HttpServletRequest request, AccessUser user) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		session = request.getSession(true);
		session.setAttribute(KEY_USER_NAME, user.getUserName());
		session.setAttribute(KEY_NAME, user.getName());
		session.setAttribute(KEY_USER_ROLE, user.getRole());
		session.setAttribute(KEY_USER_ID, user.get_id());
		session.setAttribute("SESSION_SET", true);
	}

	public static void writeAjaxResponse(HttpServletResponse response, String string) throws IOException {
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(string.getBytes(Charset.forName("UTF-8")));
		outputStream.flush();
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Boolean isValidSession(HttpSession session) {
		/*String userId =  (String) session.getAttribute(KEY_USER_ID);
		if( userId == null ){
			return false;
		}
		String userName =  (String) session.getAttribute(KEY_USER_NAME );
		if( userName == null || userName.trim().isEmpty() ){
			return false;
		}
		return true;*/

		Boolean isTrue = (Boolean) session.getAttribute("SESSION_SET");
		if (isTrue != null && isTrue) {
			return true;
		}
		return false;
	}

	public static Boolean isValidRole(HttpSession session, String role) {

		String userRole = (String) session.getAttribute(KEY_USER_ROLE);
		if (userRole == null || userRole.isEmpty()) {
			return false;
		}
		if (!role.equalsIgnoreCase(userRole)) {
			return false;
		}
		return true;
	}

}
