package com.munsi.ws;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.munsi.pojo.master.Product;
import com.munsi.util.CommonUtil;

@WebServlet("/alert")
public class WebSocketServletImpl extends WebSocketServlet {
	private static final long serialVersionUID = 1L;

	public final static Set<WebClient> webClientList = new CopyOnWriteArraySet<WebClient>();

	public static Map<String, String> outputList = new LinkedHashMap<String, String>();

	public static void addProductForNotification(Product product) {
		String jsonString = CommonUtil.objectToJson(product);
		outputList.put(product.get_id(), jsonString);

		printPutputList();
	}

	private static void printPutputList() {
		Collection<String> list = outputList.values();
		String jsonString = CommonUtil.objectToJson(list);

		for (WebClient webClient : webClientList) {
			webClient.writeResponse(jsonString);
		}
	}

	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest arg1) {
		return new WebClient();
	}

	/*public static void main(String[] args) {
		MongoProductDao ps = new MongoProductDao();
		Product p = ps.getForNotification("4");
		Product p1 = ps.getForNotification("5");
		Product p2 = ps.getForNotification("6");
		Product p3 = ps.getForNotification("7");
		addProductForNotification(p);
		addProductForNotification(p1);
		addProductForNotification(p2);
		addProductForNotification(p3);
	}*/
}
