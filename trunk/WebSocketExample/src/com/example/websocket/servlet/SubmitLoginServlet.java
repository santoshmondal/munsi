package com.example.websocket.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SubmitLoginServlet
 */
@WebServlet("/login.do")
public class SubmitLoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException
	{
		HttpSession lSession = pRequest.getSession();
		lSession.setAttribute("ERROR_MESSAGE", "Invalid form method!");
		pResponse.sendRedirect("login");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException
	{
		HttpSession lSession = pRequest.getSession(); 
		String lstrName = pRequest.getParameter("name");
		if( lstrName== null || lstrName.trim().length() ==0 ){
			lSession.setAttribute("ERROR_MESSAGE", "Please provide your name!");
			pResponse.sendRedirect("login");
			return;
		}
		lSession.invalidate();
		lSession = pRequest.getSession();
		lSession.setAttribute("USER_NAME", lstrName);
		pResponse.sendRedirect("chatroom");
		return;
	}

}
