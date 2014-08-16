package com.munsi.action.master;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.munsi.pojo.invoice.purchase.PurchaseInvoice;
import com.munsi.pojo.invoice.purchase.PurchaseProduct;
import com.munsi.pojo.master.Supplier;
import com.munsi.service.ProductServeice;
import com.munsi.service.PurchaseInvoiceServeice;
import com.munsi.service.SupplierServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class SalesAction
 */
@WebServlet("/purchase.action")
public class PurchaseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PurchaseAction.class);
	private ProductServeice productService = (ProductServeice) ObjectFactory.getInstance(ObjectEnum.PRODUCT_SERVICE);
	private PurchaseInvoiceServeice purchaseInvoiceServeice = (PurchaseInvoiceServeice) ObjectFactory.getInstance(ObjectEnum.PURCHASE_INVOICE_SERVICE);
	private SupplierServeice supplierService = (SupplierServeice) ObjectFactory.getInstance(ObjectEnum.SUPPLIER_SERVICE);

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
			String fetchBy = request.getParameter("fetchBy");
			String value = request.getParameter("value");
			String withReferences = request.getParameter("withReferences");

			if (fetchBy != null)
				switch (fetchBy.toUpperCase()) {
				case "CODE":
					json = productService.getProductByCode(value, Boolean.valueOf(withReferences));
					break;
				case "BARCODE":
					json = productService.getProductByBarCode(value, Boolean.valueOf(withReferences));
					break;
				case "NAME":
					json = productService.getProductByName(value, Boolean.valueOf(withReferences));
					break;
				default:
					break;
				}
			else
				switch (operation.toUpperCase()) {
				case "SAVE":
					String supplierId = request.getParameter("supplierid");
					String pInvDate = request.getParameter("invDate");
					//invoiceNumber
					String pinvoiceTaxPrice = request.getParameter("invoiceTaxPrice");

					String purchaseProductJSON = request.getParameter("purchaseProductJSON");

					Supplier supplier = supplierService.get(supplierId);

					PurchaseInvoice pInvoice = new PurchaseInvoice();
					BeanUtils.populate(pInvoice, request.getParameterMap());
					pInvoice.setInvoiceDate(CommonUtil.stringToDate(pInvDate, CommonUtil.DATE_FORMAT_ddMMyyyy_HYPHEN));
					try {
						List<PurchaseProduct> purchaseProductList = CommonUtil.mapper.readValue(purchaseProductJSON, CommonUtil.mapper.getTypeFactory().constructCollectionType(List.class, PurchaseProduct.class));
						Set<PurchaseProduct> purchaseProductSet = new HashSet<PurchaseProduct>(purchaseProductList);
						pInvoice.setPurchaseProductList(purchaseProductSet);
					} catch (Exception e) {
						e.printStackTrace();
					}
					pInvoice.setSupplier(supplier);
					purchaseInvoiceServeice.create(pInvoice);
					break;
				case "VIEW":
					PurchaseInvoice newInvoice = purchaseInvoiceServeice.get(request.getParameter("invoiceid"), true);

					if (newInvoice != null) {
						request.setAttribute("INVOICE_DETAIL", newInvoice);
						RequestDispatcher rd = request.getRequestDispatcher("/hello.action?reqPage=/jsp/purchase/purchasedetailview.jsp");
						rd.forward(request, response);
					}
					break;

				case "VIEW_ALL":

					List<PurchaseInvoice> invList = purchaseInvoiceServeice.getAll(true);

					json = CommonUtil.objectToJson(invList);
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
