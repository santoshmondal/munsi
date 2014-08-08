package com.munsi.action.master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.munsi.pojo.master.ProductGroup;
import com.munsi.service.ProductServeice;
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

			ProductGroup pg = new ProductGroup();
			BeanUtils.populate(pg, request.getParameterMap());

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
		}

		out.write(json);
		out.close();
	}
}
