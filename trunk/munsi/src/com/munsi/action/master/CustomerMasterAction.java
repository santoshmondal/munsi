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

import com.munsi.pojo.master.Customer;
import com.munsi.pojo.master.Manufacturer;
import com.munsi.service.AreaServeice;
import com.munsi.service.BeatServeice;
import com.munsi.service.CustomerServeice;
import com.munsi.service.MainAccountServeice;
import com.munsi.service.ManufacturerServeice;
import com.munsi.service.ProductGroupServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.Constants.UIOperations;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class MainAccount
 */
@WebServlet("/customermaster.action")
public class CustomerMasterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CustomerMasterAction.class);
	private CustomerServeice customerService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		Object object = ObjectFactory.getInstance(ObjectEnum.CUSTOMER_SERVICE);
		if (object instanceof CustomerServeice) {
			customerService = (CustomerServeice) object;
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
		
		if(operation != null && customerService  != null){
			String id = request.getParameter(Constants.COLLECTION_KEY);
			
			String area = request.getParameter("1area");
			String beat = request.getParameter("1beat");
			String mainAccount = request.getParameter("1mainAccount");

			Customer ma =  new Customer();
			BeanUtils.populate(ma, request.getParameterMap() );
			if (area != null){
				AreaServeice areaServ = (AreaServeice)ObjectFactory.getInstance(ObjectEnum.AREA_SERVICE);
				ma.setArea(areaServ.get(area));
			}
			if (beat != null){
				BeatServeice beatServ = (BeatServeice)ObjectFactory.getInstance(ObjectEnum.BEAT_SERVICE);
				ma.setBeat(beatServ.get(beat));
			}
			if (mainAccount != null){
				MainAccountServeice maServ = (MainAccountServeice)ObjectFactory.getInstance(ObjectEnum.MAIN_ACCOUNT_SERVICE);
				ma.setMainAccount(maServ.get(mainAccount));
			}

			Constants.UIOperations opEnum  = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case ADD:
				customerService.create(ma);	
				break;
			case EDIT :
					if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
						ma.set_id(id);
						customerService.update(ma);
					} 
				break;	
				
			case DELETE :
				if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					customerService.delete(id);
				}
				break;

			case VIEW :
				
				break;	
				
			case VIEW_ALL :
				
				List<Customer> customerList = customerService.getAll();
				json = CommonUtil.objectToJson(customerList);
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
