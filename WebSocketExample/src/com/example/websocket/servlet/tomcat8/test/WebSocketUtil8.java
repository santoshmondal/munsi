package com.example.websocket.servlet.tomcat8.test;

import java.util.HashSet;
import java.util.Set;

public class WebSocketUtil8
{
	
	private static Set<EndPointImpl> msetWebClients = new HashSet<EndPointImpl>();
	
	synchronized public static void addWebClient(EndPointImpl pEndPointImpl)
	{
		// Send already joined clients name to current
		for(EndPointImpl lEndPointImpl : msetWebClients)
		{
			pEndPointImpl.writeMessage("{\"sender\":\""+lEndPointImpl.getName()+"\", \"message\":\"Joined!\"}");
		}
		
		msetWebClients.add(pEndPointImpl);
		
		// Notify other that current is Joined
		sendToAllClients("Joined!", pEndPointImpl);
	}
	
	synchronized public static void removeWebClient(EndPointImpl pEndPointImpl)
	{
		msetWebClients.remove(pEndPointImpl);
		
		// Notify other that current is Disconnected
		sendToAllClients("Disconnected!", pEndPointImpl);
	}

	public static void sendToAllClients(String pMessage, EndPointImpl pEndPointImpl)
	{
		for(EndPointImpl lEndPointImpl : msetWebClients)
		{
			// Ignore write to sender
			if( lEndPointImpl.getId() == pEndPointImpl.getId() )
			{
				continue;
			}
			lEndPointImpl.writeMessage("{\"sender\":\""+pEndPointImpl.getName()+"\", \"message\":\""+pMessage+"\"}");
		}
	}
	
}
