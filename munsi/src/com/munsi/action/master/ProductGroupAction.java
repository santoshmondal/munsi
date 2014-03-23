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
import com.munsi.pojo.master.ProductGroup;
import com.munsi.service.MainAccountServeice;
import com.munsi.service.ProductGroupServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.ObjectFactory;
import com.munsi.util.Constants.UIOperations;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class MainAccount
 */
@WebServlet("/productgroup.action")
public class ProductGroupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ProductGroupAction.class);
	
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
		
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.PRODUCT_GROUP_SERVICE);
		ProductGroupServeice productGroupService = null;
		if (object instanceof ProductGroupServeice) {
			productGroupService = (ProductGroupServeice) object;
		}
		
		if(operation != null && productGroupService  != null){
			String id = request.getParameter(Constants.COLLECTION_KEY);
			String plevel = request.getParameter(Constants.JQGRID_LEVEL);

			ProductGroup pg =  new ProductGroup();
			BeanUtils.populate(pg, request.getParameterMap() );
			
			Constants.UIOperations opEnum  = UIOperations.valueOf(operation.toUpperCase());
			
			
			switch (opEnum) {
			case ADD:
				productGroupService.create(pg);	
				break;
			case EDIT :
					if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
						pg.set_id(id);
						productGroupService.update(pg);
					}
				break;	
				
			case DELETE :
				if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					productGroupService.delete(id);
				}
				break;

			case VIEW :
				
				break;
				
			case VIEW_ALL :
				
				List<ProductGroup> pgList = productGroupService.getAll(plevel);
				json = CommonUtil.objectToJson(pgList);
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
