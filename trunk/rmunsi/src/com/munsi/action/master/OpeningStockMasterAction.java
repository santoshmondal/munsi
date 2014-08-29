package com.munsi.action.master;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.munsi.pojo.master.OpeningProductStock;
import com.munsi.pojo.master.Product;
import com.munsi.pojo.master.ProductBatch;
import com.munsi.service.OpeningProductStockService;
import com.munsi.service.ProductServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.Constants.UIOperations;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class MainAccount
 */
@WebServlet("/openingstockmaster.action")
public class OpeningStockMasterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(OpeningStockMasterAction.class);

	private OpeningProductStockService openStockService;
	private ProductServeice productServeice;

	/**
	 * Initialise all services here, Every request should not initialise them.
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		Object object = ObjectFactory.getInstance(ObjectEnum.OPENING_PRODUCT_STOCK_SERVICE);
		if (object instanceof OpeningProductStockService) {
			openStockService = (OpeningProductStockService) object;
		} else {
			throw new ServletException("ProductSchemeService not initialized !");
		}

		object = ObjectFactory.getInstance(ObjectEnum.PRODUCT_SERVICE);
		if (object instanceof ProductServeice) {
			productServeice = (ProductServeice) object;
		} else {
			throw new ServletException("ProductServeice not initialized !");
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	private void doProcessWithException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		String json = "";
		String operation = request.getParameter(Constants.OPERATION);

		if (operation != null) {
			String id = request.getParameter(Constants.COLLECTION_KEY);
			String product_id = request.getParameter("productId");
			String expDate = request.getParameter("1expDate");
			String mfgDate = request.getParameter("1mfgDate");

			OpeningProductStock ps = new OpeningProductStock();
			BeanUtils.populate(ps, request.getParameterMap());

			if (expDate != null) {
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				ps.setExpDate(formatter.parse(expDate));
			}
			if (mfgDate != null) {
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				ps.setMfgDate(formatter.parse(mfgDate));
			}

			Constants.UIOperations opEnum = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case ADD:
				Product product = productServeice.get(product_id);
				ps.setProduct(product);
				openStockService.create(ps);

				int batchQuantity = ps.getQuantity();
				product.setCurrentStock(batchQuantity + product.getCurrentStock());
				ProductBatch productBatch = new ProductBatch();
				productBatch.setBatchCurrentStock(Long.valueOf(batchQuantity));
				productBatch.setBatchNumber(ps.getBatchNumber());
				productBatch.setExpiryDate(ps.getExpDate());
				productBatch.setManufactureDate(ps.getMfgDate());
				productBatch.setMrp(product.getMrp());
				productBatch.setSaleRate(product.getSalesRate());
				productBatch.setPurchaseRate(product.getPurchaseRate());
				Set<ProductBatch> batchList = product.getBatchList();
				if (batchList == null) {
					batchList = new HashSet<>();
				}
				batchList.add(productBatch);
				product.setBatchList(batchList);
				productServeice.update(product);

				break;
			case EDIT:
				if (id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					if (product_id != null) {
						product = productServeice.get(product_id);
						ps.setProduct(product);
					}
					ps.set_id(id);
					openStockService.update(ps);
				}
				break;

			case DELETE:
				if (id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					openStockService.delete(id);
				}
				break;

			case VIEW:
				List<OpeningProductStock> pgList = openStockService.getOpeningStockByProduct(product_id);
				json = CommonUtil.objectToJson(pgList);
				json = json.replaceAll("sExpDate", "1expDate");
				json = json.replaceAll("sMfgDate", "1mfgDate");
				json = json.replaceAll("_id", "id");
				break;

			case VIEW_ALL:

				List<OpeningProductStock> pgList1 = openStockService.getAll();
				json = CommonUtil.objectToJson(pgList1);
				json = json.replaceAll("sExpDate", "1expDate");
				json = json.replaceAll("sMfgDate", "1mfgDate");
				json = json.replaceAll("_id", "id");
				break;

			default:
				break;
			}
		}

		out.write(json);
		out.close();
	}
}
