package com.munsi.websocket;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Servlet implementation class WebSocketServletImpl
 */

@ServerEndpoint(value = "/alert", configurator = CustomConfigurator.class)
public class ServerEndPointImpl {
	private String mstrId;
	private Session mSession;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServerEndPointImpl() {
		super();
	}

	@OnOpen
	public void onOpen(Session pSession, EndpointConfig pEPconfig) {
		mSession = pSession;
		HttpSession lHttpSession = (HttpSession) pEPconfig.getUserProperties().get(HttpSession.class.getName());
		mstrId = lHttpSession.getId();
		NotificationUtil.addWebClient(this);
	}

	@OnClose
	public void onClose(CloseReason reason) {
		NotificationUtil.removeWebClient(this);
	}

	@OnMessage
	public void onMessage(String pMessage) {
	}

	@OnError
	public void onError(Throwable throwable) throws Exception {
		throwable.printStackTrace();
	}

	public void writeResponse(String pMessage) {
		if (!mSession.isOpen()) {
			return;
		}

		RemoteEndpoint.Basic lRemote = mSession.getBasicRemote();
		try {
			lRemote.sendText(pMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// My methods
	public String getId() {
		return mstrId;
	}

	public void setId(String pID) {
		this.mstrId = pID;
	}

	@Override
	public boolean equals(Object pObj) {
		ServerEndPointImpl lEndPointImpl = (ServerEndPointImpl) pObj;
		if (lEndPointImpl == null) {
			return false;
		}
		return mstrId.equals(lEndPointImpl.mstrId);
	}

	@Override
	public int hashCode() {
		return mstrId.hashCode();
	}
}
