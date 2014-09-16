package com.example.websocket.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet("/chat")
public class WebSocketServletImpl extends WebSocketServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected StreamInbound createWebSocketInbound(String pSubProtocol,
			HttpServletRequest pRequest)
	{
		HttpSession lSession = pRequest.getSession();
		String lstrName = (String) lSession.getAttribute("USER_NAME");
		if( lstrName == null || lstrName.trim().length() == 0 )
		{
			return null;
		}
		
		WebClient lWebClient = new WebClient();
		lWebClient.setId( pRequest.getSession().getId() );
		lWebClient.setName(lstrName);
		
		return lWebClient;
	}

}
