package websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet("/alert")
public class WebSocketServletImpl extends WebSocketServlet {

    private static final long serialVersionUID = 1L;
    public final static Set<WebClient> webClientList = new CopyOnWriteArraySet<WebClient>();

    @Override
    public void init() throws ServletException {
    	super.init();
    	updateStock();
    }
    
    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,
            HttpServletRequest request)
    {
    	return new WebClient();
    }
    
    public static void updateStock()
    {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				int t = 0;
				String str = "<b>Async: </b>J M D";
				while (true) {
					
					for(WebClient webClient : webClientList)
					{
						webClient.writeResponse( str );
					}
					
					try {
						// h * mm * sec * mili sec
						Thread.sleep( 5 * 1000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if( t == 10 ){
						//break;
					}
					t++;
				}
			}
		});
		t.start();
	}

}