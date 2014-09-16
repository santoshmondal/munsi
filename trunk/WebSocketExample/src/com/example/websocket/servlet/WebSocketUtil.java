package com.example.websocket.servlet;

import java.util.HashSet;
import java.util.Set;

public class WebSocketUtil
{
	
	private static Set<WebClient> msetWebClients = new HashSet<WebClient>();
	
	synchronized public static void addWebClient(WebClient pWebClient)
	{
		// Send already joined clients name to current
		for(WebClient lWebClient : msetWebClients)
		{
			pWebClient.writeMessage("{\"sender\":\""+lWebClient.getName()+"\", \"message\":\"Joined!\"}");
		}
		
		msetWebClients.add(pWebClient);
		
		// Notify other that current is Joined
		sendToAllClients("Joined!", pWebClient);
	}
	
	synchronized public static void removeWebClient(WebClient pWebClient)
	{
		msetWebClients.remove(pWebClient);
		
		// Notify other that current is Disconnected
		sendToAllClients("Disconnected!", pWebClient);
	}

	public static void sendToAllClients(String pMessage, WebClient pWebClient)
	{
		for(WebClient lWebClient : msetWebClients)
		{
			// Ignore write to sender
			if( lWebClient.getId() == pWebClient.getId() )
			{
				continue;
			}
			lWebClient.writeMessage("{\"sender\":\""+pWebClient.getName()+"\", \"message\":\""+pMessage+"\"}");
		}
	}
	
}
