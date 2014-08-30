package websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public class WebClient extends MessageInbound{
	
	@Override
    protected void onOpen(WsOutbound outbound) {
		WebSocketServletImpl.webClientList.add(this);
    }

    @Override
    protected void onClose(int status) {
    	WebSocketServletImpl.webClientList.remove(this);
    }

    @Override
    protected void onBinaryMessage(ByteBuffer message) throws IOException {
        // do something
    }

    @Override
    protected void onTextMessage(CharBuffer message) throws IOException {
    	// do something
    }

    public void writeResponse(String message) {
        try {
            CharBuffer buffer = CharBuffer.wrap(message);
            getWsOutbound().writeTextMessage(buffer);
        }
        catch (IOException ignore) {
            // Ignore
        }
    }

}
