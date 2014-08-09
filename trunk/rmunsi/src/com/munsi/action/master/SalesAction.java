package com.munsi.action.master;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.munsi.pojo.invoice.sales.SalesInvoice;
import com.munsi.pojo.invoice.sales.SalesProduct;
import com.munsi.pojo.master.Customer;
import com.munsi.service.CustomerServeice;
import com.munsi.service.ProductServeice;
import com.munsi.service.SalesInvoiceServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class SalesAction
 */
@WebServlet("/sales.action")
public class SalesAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SalesAction.class);
	private ProductServeice productService = (ProductServeice) ObjectFactory.getInstance(ObjectEnum.PRODUCT_SERVICE);
	private SalesInvoiceServeice salesInvoiceService = (SalesInvoiceServeice) ObjectFactory.getInstance(ObjectEnum.SALES_INVOICE_SERVICE);
	private CustomerServeice customerService = (CustomerServeice) ObjectFactory.getInstance(ObjectEnum.CUSTOMER_SERVICE);

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
					String custId = request.getParameter("custid");
					String invoiceAddTaxPrice = request.getParameter("invoiceAddTaxPrice");
					String invoiceAddDiscountPrice = request.getParameter("invoiceAddDiscountPrice");
					String salesProductJSON = request.getParameter("salesProductJSON");
					Customer customer = customerService.get(custId);
					SalesInvoice sInvoice = new SalesInvoice();
					sInvoice.setCustomer(customer);
					try {
						List<SalesProduct> salesProductList = CommonUtil.mapper.readValue(salesProductJSON, CommonUtil.mapper.getTypeFactory().constructCollectionType(List.class, SalesProduct.class));
						Set<SalesProduct> salesProductSet = new HashSet<SalesProduct>(salesProductList);
						sInvoice.setSalesProductList(salesProductSet);
					} catch (Exception e) {
						e.printStackTrace();
					}
					salesInvoiceService.create(sInvoice);
					break;

				default:
					break;
				}
		}

		out.write(json);
		out.close();
	}
}
