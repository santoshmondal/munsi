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

import com.munsi.pojo.master.Tax;
import com.munsi.service.TaxServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.Constants.UIOperations;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class MainAccount
 */
@WebServlet("/tax.action")
public class TaxAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TaxAction.class);
	private TaxServeice taxService;
	@Override
	public void init() throws ServletException {
		super.init();
		Object object = ObjectFactory.getInstance(ObjectEnum.TAX_SERVICE);
		
		if (object instanceof TaxServeice) {
			taxService = (TaxServeice) object;
		}
		
	}
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
		
		if(operation != null && taxService  != null){
			String id = request.getParameter(Constants.COLLECTION_KEY);
			Tax tax =  new Tax();
			BeanUtils.populate(tax, request.getParameterMap() );
			
			Constants.UIOperations opEnum  = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case ADD:
				taxService.create(tax);	
				break;
			case EDIT :
					if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
						tax.set_id(id);
						taxService.update(tax);
					} 
				break;	
				
			case DELETE :
				if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					taxService.delete(id);
				}
				break;

			case VIEW :
				
				break;	
				
			case VIEW_ALL :
				
				List<Tax> taxList = taxService.getAll();
				json = CommonUtil.objectToJson(taxList);
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
