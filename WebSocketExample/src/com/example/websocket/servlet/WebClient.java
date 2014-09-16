package com.example.websocket.servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public class WebClient extends MessageInbound
{
	private String mstrId;
	private String mstrName;
	
	@Override
	protected void onOpen(WsOutbound outbound)
	{
		super.onOpen(outbound);
		System.out.println("WebSocket connection opened for "+mstrName);
		WebSocketUtil.addWebClient(this);
	}
	
	@Override
	protected void onClose(int status) 
	{
		super.onClose(status);
		System.out.println("WebSocket connection closed for "+mstrName);
		WebSocketUtil.removeWebClient(this);
	}
	
	@Override
	protected void onBinaryMessage(ByteBuffer pByteBuffer) throws IOException
	{
		
	}

	@Override
	protected void onTextMessage(CharBuffer pCharBuffer) throws IOException 
	{
		System.out.println("Message received from "+mstrName);
		WebSocketUtil.sendToAllClients(pCharBuffer.toString(), this);
	}

	public void writeMessage(String pMessage)
	{
		try
		{
            CharBuffer buffer = CharBuffer.wrap(pMessage);
            getWsOutbound().writeTextMessage(buffer);
        }
		catch (IOException ignore)
		{
            //
        }
	}
	
	
	public String getId()
	{
		return mstrId;
	}

	public void setId(String pID)
	{
		this.mstrId = pID;
	}
	
	public String getName()
	{
		return mstrName;
	}

	public void setName(String pName)
	{
		this.mstrName = pName;
	}
	
	
	
	@Override
	public boolean equals(Object pObj) {
		WebClient lWebClient = (WebClient) pObj;
		if( lWebClient == null)
		{
			return false;
		}
		return mstrId.equals(lWebClient.mstrId);
	}
	
	@Override
	public int hashCode()
	{
		return mstrId.hashCode();
	}

	
}
