package com.munsi.ws;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.munsi.dao.ProductDao;
import com.munsi.dao.impl.MongoProductDao;
import com.munsi.pojo.master.Product;
import com.munsi.util.CommonUtil;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class NotificationUtil {

	private final static Set<WebClient> webClientList = new CopyOnWriteArraySet<WebClient>();

	private static Map<String, Product> stockShortageAlertList = new LinkedHashMap<>();
	private static Map<String, Product> stockExpAlertList = new LinkedHashMap<>();

	public static void initNotification() {
		ProductDao productDao = (MongoProductDao) ObjectFactory.getInstance(ObjectEnum.PRODUCT_DAO);
		List<Product> productList = productDao.getAllShortedProdect();
		if (productList != null && productList.size() > 0) {
			for (Product product : productList) {
				stockShortageAlertList.put(product.get_id(), product);
			}
		}

		productList = productDao.getAllExpireProdect();
		if (productList != null && productList.size() > 0) {
			for (Product product : productList) {
				stockExpAlertList.put(product.get_id(), product);
			}
		}
	}

	public static void checkProductForNotification(Product product) {

		if (product.getCurrentStock() <= product.getMinStock()) {
			//String jsonString = CommonUtil.objectToJson(product);
			stockShortageAlertList.put(product.get_id(), product);
			printAlertList();

		} else {
			Product str = stockShortageAlertList.remove(product.get_id());
			if (str != null) {
				printAlertList();
			}
		}
	}

	private static void printAlertList() {
		Map<String, Collection<Product>> tempMap = new HashMap<>();
		tempMap.put("SHORTAGE_ALERT", stockShortageAlertList.values());
		tempMap.put("EXPIRY_ALERT", stockShortageAlertList.values());
		String jsonStringAlert = CommonUtil.objectToJson(tempMap);
		for (WebClient webClient : webClientList) {
			webClient.writeResponse(jsonStringAlert);
		}
	}

	public static void addWebClient(WebClient webClient) {
		webClientList.add(webClient);
		if (stockShortageAlertList.size() > 0 || stockExpAlertList.size() > 0) {
			Map<String, Collection<Product>> tempMap = new HashMap<>();
			tempMap.put("SHORTAGE_ALERT", stockShortageAlertList.values());
			tempMap.put("EXPIRY_ALERT", stockShortageAlertList.values());
			String jsonStringAlert = CommonUtil.objectToJson(tempMap);
			webClient.writeResponse(jsonStringAlert);
		}
	}

	public static void removeWebClient(WebClient webClient) {
		webClientList.remove(webClient);
	}

}
