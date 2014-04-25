package com.estudio.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants;
import com.async.util.Constants.ServiceEnum;
import com.async.util.Constants.UIOperations;
import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.pojo.Customer;
import com.estudio.pojo.FrameDetails;
import com.estudio.pojo.Invoice;
import com.estudio.pojo.LaminationDetails;
import com.estudio.pojo.PhotoDetails;
import com.estudio.service.InvoiceService;

/**
 * Servlet implementation class InvoiceAction
 */
@WebServlet("/invoiceaction.do")
public class InvoiceAction extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(InvoiceAction.class);
	private static final long serialVersionUID = 1L;
	private InvoiceService invoiceService;

	@Override
	public void init() throws ServletException {
		super.init();
		Object object = ObjectFactory.getInstance(ObjectEnum.INVOICE_SERVICE);
		if (object instanceof InvoiceService) {
			invoiceService = (InvoiceService) object;
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
		} catch (RuntimeException me) {
			LOG.error(me);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

		PrintWriter out = response.getWriter();
		String json = "";
		String operation = request.getParameter(Constants.OPERATION);

		if (operation != null && invoiceService != null) {
			//String id = request.getParameter(Constants.COLLECTION_KEY);

			Invoice invoice = new Invoice();
			//BeanUtils.populate(area, request.getParameterMap() );

			Constants.UIOperations opEnum = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case ADD:
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				invoice.setTotalAmount(Float.parseFloat(request.getParameter("fTotalAmount")));
				Customer customer = new Customer();
				customer.set_id(request.getParameter("fMobile"));
				customer.setName(request.getParameter("fName"));
				customer.setEmailId(request.getParameter("fEmail"));
				customer.setDob(formatter.parse(request.getParameter("fDOB")));
				customer.setMarriageDate(formatter.parse(request.getParameter("fMAnniversary")));
				customer.setAddress(request.getParameter("fAddress"));
				invoice.setCustomer(customer);

				List<PhotoDetails> photoDetailsList = (List<PhotoDetails>) getServiceDetailsList(Constants.ServiceEnum.PHOTO_DETAILS, request.getParameterMap());
				List<FrameDetails> frameDetailsList = (List<FrameDetails>) getServiceDetailsList(Constants.ServiceEnum.FRAME_DETAILS, request.getParameterMap());
				List<LaminationDetails> laminationDetailsList = (List<LaminationDetails>) getServiceDetailsList(Constants.ServiceEnum.LAMINATION_DETAILS, request.getParameterMap());

				invoice.setPhotoDetailsList(photoDetailsList);
				invoice.setFrameDetailsList(frameDetailsList);
				invoice.setLaminationDetailsList(laminationDetailsList);

				//TODO check for the valid insert
				Invoice newInvoice = invoiceService.create(invoice);
				if (newInvoice != null) {
					request.setAttribute("NEW_INVOICE_DETAIL", newInvoice);
					RequestDispatcher rd = request.getRequestDispatcher("/embedpage.action?reqPage=/jsp/studio/invoiceprint.jsp");
					rd.forward(request, response);
				}

				break;
			/*case EDIT :
					if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
						area.set_id(id);
						areaService.update(area);
					} 
				break;	
				
			case DELETE :
				if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					areaService.delete(id);
				}
				break;

			case VIEW :
				
				break;	
				*/
			case VIEW_ALL:

				List<Invoice> invList = invoiceService.getAll();
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

	private List<?> getServiceDetailsList(ServiceEnum photoDetails, Map<String, String[]> parameterMap) {
		List<?> serviceDetailList = null;

		switch (photoDetails) {
		case PHOTO_DETAILS:
			List<PhotoDetails> photoDetailList = new ArrayList<PhotoDetails>();

			PhotoDetails pDetails = new PhotoDetails();
			photoDetailList.add(pDetails);

			serviceDetailList = photoDetailList;
			break;
		case FRAME_DETAILS:
			List<FrameDetails> frameDetailList = new ArrayList<FrameDetails>();

			serviceDetailList = frameDetailList;
			break;

		case LAMINATION_DETAILS:
			List<LaminationDetails> laminationDetailList = new ArrayList<LaminationDetails>();

			serviceDetailList = laminationDetailList;
			break;
		default:
			break;
		}

		return serviceDetailList;
	}
}
