package com.munsi.ws;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet("/alert")
public class WebSocketServletImpl extends WebSocketServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest arg1) {
		return new WebClient();
	}
}
