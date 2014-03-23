package com.munsi.action.master;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.isdc.simulations.VivekSimulation;
import com.munsi.dao.MainAccountDao;
import com.munsi.pojo.master.Product;
import com.munsi.pojo.master.Tax;
import com.munsi.service.MainAccountServeice;
import com.munsi.service.ProductServeice;
import com.munsi.service.TaxServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.ObjectFactory;
import com.munsi.util.Constants.UIOperations;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class MainAccount
 */
@WebServlet("/productmaster.action")
public class ProductMasterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ProductMasterAction.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request,response);
	}

	private void doProcessWithException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			doProcess(request,response);
		}catch(Exception e){
			LOG.error(e);
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		String json = "";
		String operation = request.getParameter(Constants.OPERATION);
		
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.PRODUCT_SERVICE);
		ProductServeice prodService = null;
		if (object instanceof ProductServeice ) {
			prodService = (ProductServeice ) object;
		}
		
		if(operation != null && prodService  != null){
			String id = request.getParameter(Constants.COLLECTION_KEY);
			Product product =  new Product();
			BeanUtils.populate(product, request.getParameterMap() );
			
			Constants.UIOperations opEnum  = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case ADD:
				prodService.create(product);	
				break;
			case EDIT :
					if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
						product.set_id(id);
						prodService.update(product);
					} 
				break;	
				
			case DELETE :
				if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					prodService.delete(id);
				}
				break;

			case VIEW :
				
				break;	
				
			case VIEW_ALL :
				
				List<Product> prodList = prodService.getAll();
				json = CommonUtil.objectToJson(prodList);
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
