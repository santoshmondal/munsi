package com.smartcall.action.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants;
import com.async.util.Constants.UIOperations;
import com.async.util.ObjectFactory;
import com.smartcall.action.BaseServlet;
import com.smartcall.pojo.CustomerDetails;
import com.smartcall.service.CustomerDetailsService;

/**
 * Servlet implementation class CustomerAction
 */
@WebServlet("/customer.action")
public class CustomerAction extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger LOG = Logger.getLogger(CustomerAction.class);
	private CustomerDetailsService customerDetailsService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		Object object = ObjectFactory.getInstance(ObjectFactory.ObjectEnum.CUSTOMER_DETAILS_SERVICE);
		if (object instanceof CustomerDetailsService ) {
			customerDetailsService = (CustomerDetailsService ) object;
		}
		
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doProcessWithException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			doProcess(request,response);
		}catch(Exception e){
			LOG.error(e);
		}
	}

	@Override
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		String json = "";
		String operation = request.getParameter(Constants.OPERATION);
		
		if(operation != null){
			String id = request.getParameter(Constants.COLLECTION_KEY);
			
			Constants.UIOperations opEnum  = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case EDIT :
				break;	
				
			case DELETE :
				break;

			case VIEW :
				break;	
				
			case VIEW_ALL :
				
				List<CustomerDetails> customerList = customerDetailsService.getCusDetailsList();
				//json = CommonUtil.objectToJson(customerList);
				//json = json.replaceAll("_id", "id");
				json = "{["+
							"{id:\"11\",customerName:\"Cust1\",mobileNo:\"547896525\",model:\"Audi\",variant:\"x1\",vin:\"asadfd224411545\",engineNo:\"AASDFX545\",regNovehNo:\"XXAX12154\",invoiceDateSale:\"1-12-2012\",vehicleDeliveryDate:\"25-12-2012\",dealerName:\"\",dealerState:\"\",dealerCity:\"\",odometerReading:\"200\",status:\"Ringing\"},"+
								"{id:\"2\",customerName:\"Cust12\",mobileNo:\"547896525\",model:\"VW\",variant:\"x1\",vin:\"asadfd224411545\",engineNo:\"AASDFX545\",regNovehNo:\"XXAX12154\",invoiceDateSale:\"1-12-2012\",vehicleDeliveryDate:\"25-12-2012\",dealerName:\"\",dealerState:\"\",dealerCity:\"\",odometerReading:\"200\",status:\"Ringing\"},"+
								"{id:\"3\",customerName:\"Cust13\",mobileNo:\"547896525\",model:\"VW\",variant:\"x1\",vin:\"asadfd224411545\",engineNo:\"AASDFX545\",regNovehNo:\"XXAX12154\",invoiceDateSale:\"1-12-2012\",vehicleDeliveryDate:\"25-12-2012\",dealerName:\"\",dealerState:\"\",dealerCity:\"\",odometerReading:\"200\",status:\"Ringing\"},"+
								"{id:\"4\",customerName:\"Cust21\",mobileNo:\"547896525\",model:\"Audi\",variant:\"x1\",vin:\"asadfd224411545\",engineNo:\"AASDFX545\",regNovehNo:\"XXAX12154\",invoiceDateSale:\"1-12-2012\",vehicleDeliveryDate:\"25-12-2012\",dealerName:\"\",dealerState:\"\",dealerCity:\"\",odometerReading:\"200\",status:\"Ringing\"},"+
								"{id:\"5\",customerName:\"Cust221\",mobileNo:\"547896525\",model:\"Hundai\",variant:\"x1\",vin:\"asadfd224411545\",engineNo:\"AASDFX545\",regNovehNo:\"XXAX12154\",invoiceDateSale:\"1-12-2012\",vehicleDeliveryDate:\"25-12-2012\",dealerName:\"\",dealerState:\"\",dealerCity:\"\",odometerReading:\"200\",status:\"Ringing\"},"+
								"{id:\"6\",customerName:\"Cust12\",mobileNo:\"547896525\",model:\"Hundai\",variant:\"x1\",vin:\"asadfd224411545\",engineNo:\"AASDFX545\",regNovehNo:\"XXAX12154\",invoiceDateSale:\"1-12-2012\",vehicleDeliveryDate:\"25-12-2012\",dealerName:\"\",dealerState:\"\",dealerCity:\"\",odometerReading:\"200\",status:\"Ringing\"},"+
								"{id:\"7\",customerName:\"Cust121\",mobileNo:\"547896525\",model:\"Hundai\",variant:\"x1\",vin:\"asadfd224411545\",engineNo:\"AASDFX545\",regNovehNo:\"XXAX12154\",invoiceDateSale:\"1-12-2012\",vehicleDeliveryDate:\"25-12-2012\",dealerName:\"\",dealerState:\"\",dealerCity:\"\",odometerReading:\"200\",status:\"Ringing\"},"+
								"]}";
				System.out.println("hk");
				
				break;	
				
			default:
				break;
			}
		}
		out.write(json);
		out.close();
	}
}
