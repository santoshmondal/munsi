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

			Invoice invoice = new Invoice();

			Constants.UIOperations opEnum = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case ADD:
				invoice.setTotalAmount((request.getParameter("fTotalAmount") != null && request.getParameter("fTotalAmount").trim().length() > 0) ? Float.parseFloat(request.getParameter("fTotalAmount")) : 00.00f);
				invoice.setAdvanceBal((request.getParameter("fAdvPaid") != null && request.getParameter("fAdvPaid").trim().length() > 0) ? Float.parseFloat(request.getParameter("fAdvPaid")) : 00.00f);
				invoice.setDelivaryDate(CommonUtil.stringToDate(request.getParameter("fEstDeliveryDate")));

				Customer customer = getCustomerDetails(request.getParameterMap());
				invoice.setCustomer(customer);

				List<PhotoDetails> photoDetailsList = (List<PhotoDetails>) getServiceDetailsList(Constants.ServiceEnum.PHOTO_DETAILS, request.getParameterMap());
				List<FrameDetails> frameDetailsList = (List<FrameDetails>) getServiceDetailsList(Constants.ServiceEnum.FRAME_DETAILS, request.getParameterMap());
				List<LaminationDetails> laminationDetailsList = (List<LaminationDetails>) getServiceDetailsList(Constants.ServiceEnum.LAMINATION_DETAILS, request.getParameterMap());

				invoice.setPhotoDetailsList(photoDetailsList);
				invoice.setFrameDetailsList(frameDetailsList);
				invoice.setLaminationDetailsList(laminationDetailsList);

				//TODO check for the valid insert
				Boolean isValid = true;
				if (invoice.getCustomer().get_id() == null || invoice.getCustomer().get_id().trim().isEmpty()) {
					isValid = false;
				}

				if (photoDetailsList.size() == 0 && frameDetailsList.size() == 0 && laminationDetailsList.size() == 0) {
					isValid = false;
				}

				if (!isValid) {
					request.setAttribute("SERVER_MESSAGE", "Fail to create invoice. Please Try Again."); 
					RequestDispatcher rd = request.getRequestDispatcher("/embedpage.action?reqPage=/jsp/studio/newinvoice.jsp");
					rd.forward(request, response);
					return;
				}

				Invoice newInvoice = invoiceService.create(invoice);

				if (newInvoice != null) {
					request.setAttribute("NEW_INVOICE_DETAIL", invoice); //newInvoice);
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
			*/
			case VIEW:

				//TODO check for the valid insert
				newInvoice = invoiceService.get(request.getParameter("invoiceno"));

				if (newInvoice != null) {
					request.setAttribute("INVOICE_DETAIL", newInvoice);
					RequestDispatcher rd = request.getRequestDispatcher("/embedpage.action?reqPage=/jsp/studio/showinvoice.jsp");
					rd.forward(request, response);
				}
				break;

			case VIEW_ALL:

				List<Invoice> invList = invoiceService.getAll();
				json = CommonUtil.objectToJson(invList);
				json = json.replaceAll("_id", "id");
				break;

			case SAVE:

				invoiceService.getAll();
				break;

			default:
				break;
			}
		}

		out.write(json);
		out.close();
	}

	private Customer getCustomerDetails(Map<String, String[]> parameterMap) throws ParseException {
		Customer customer = new Customer();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if (parameterMap.containsKey("fMobile"))
			customer.set_id(parameterMap.get("fMobile")[0]);
		if (parameterMap.containsKey("fName"))
			customer.setName(parameterMap.get("fName")[0]);
		if (parameterMap.containsKey("fEmail"))
			customer.setEmailId(parameterMap.get("fEmail")[0]);
		if (parameterMap.containsKey("fDOB") && !parameterMap.get("fDOB")[0].equals(""))
			customer.setDob(CommonUtil.stringToDate(parameterMap.get("fDOB")[0]));
		if (parameterMap.containsKey("fMAnniversary") && !parameterMap.get("fMAnniversary")[0].equals(""))
			customer.setMarriageDate(CommonUtil.stringToDate(parameterMap.get("fMAnniversary")[0]));
		if (parameterMap.containsKey("fAddress"))
			customer.setAddress(parameterMap.get("fAddress")[0]);
		return customer;
	}

	private List<?> getServiceDetailsList(ServiceEnum photoDetails, Map<String, String[]> parameterMap) {
		List<?> serviceDetailList = null;

		switch (photoDetails) {
		case PHOTO_DETAILS:
			List<PhotoDetails> photoDetailList = new ArrayList<PhotoDetails>();
			int pCouter = 0;
			try {
				pCouter = Integer.parseInt(parameterMap.get("fPhotoCounter")[0]);
			} catch (Exception e) {

			}
			PhotoDetails pDetails = null;
			if (parameterMap.containsKey("fPhotoNumber") && parameterMap.get("fPhotoNumber")[0] != null && !parameterMap.get("fPhotoNumber")[0].trim().isEmpty()) {
				pDetails = new PhotoDetails();

				if (parameterMap.containsKey("fPhotoNumber"))
					pDetails.setPhotoNumber(parameterMap.get("fPhotoNumber")[0]);
				if (parameterMap.containsKey("fPhotoSource"))
					pDetails.setPhotoSource(parameterMap.get("fPhotoSource")[0]);
				if (parameterMap.containsKey("fNoPhoto") && parameterMap.get("fNoPhoto")[0] != null && !parameterMap.get("fNoPhoto")[0].trim().isEmpty())
					pDetails.setQuantity(Integer.parseInt(parameterMap.get("fNoPhoto")[0]));
				if (parameterMap.containsKey("fQuality"))
					pDetails.setQuality(parameterMap.get("fQuality")[0]);
				if (parameterMap.containsKey("fSize"))
					pDetails.setSize(parameterMap.get("fSize")[0]);
				if (parameterMap.containsKey("fRemark"))
					pDetails.setRemark(parameterMap.get("fRemark")[0]);
				if (parameterMap.containsKey("fPhotoCost"))
					pDetails.setPrice(parameterMap.get("fPhotoCost")[0]);
				photoDetailList.add(pDetails);
			}

			for (int i = 2; i <= pCouter; i++) {
				if (parameterMap.containsKey("fPhotoNumber" + i)) {
					pDetails = new PhotoDetails();
					pDetails.setPhotoNumber(parameterMap.get("fPhotoNumber" + i)[0]);
					if (parameterMap.containsKey("fPhotoSource" + i))
						pDetails.setPhotoSource(parameterMap.get("fPhotoSource" + i)[0]);
					if (parameterMap.containsKey("fNoPhoto" + i) && parameterMap.get("fNoPhoto" + i)[0] != null && !parameterMap.get("fNoPhoto" + i)[0].trim().isEmpty())
						pDetails.setQuantity(Integer.parseInt(parameterMap.get("fNoPhoto" + i)[0]));
					if (parameterMap.containsKey("fQuality" + i))
						pDetails.setQuality(parameterMap.get("fQuality" + i)[0]);
					if (parameterMap.containsKey("fSize" + i))
						pDetails.setSize(parameterMap.get("fSize" + i)[0]);
					if (parameterMap.containsKey("fRemark" + i))
						pDetails.setRemark(parameterMap.get("fRemark" + i)[0]);
					if (parameterMap.containsKey("fPhotoCost" + i))
						pDetails.setPrice(parameterMap.get("fPhotoCost" + i)[0]);
					photoDetailList.add(pDetails);
				}
			}

			serviceDetailList = photoDetailList;
			break;
		case FRAME_DETAILS:
			List<FrameDetails> frameDetailList = new ArrayList<FrameDetails>();
			int fCouter = 0;
			try {
				fCouter = Integer.parseInt(parameterMap.get("fFrameCounter")[0]);
			} catch (Exception e) {

			}
			FrameDetails fDetails = null;
			if (parameterMap.containsKey("fFrameNumber") && parameterMap.get("fFrameNumber")[0] != null && !parameterMap.get("fFrameNumber")[0].trim().isEmpty()) {
				fDetails = new FrameDetails();
				if (parameterMap.containsKey("fFrameNumber"))
					fDetails.setFrameNumber(parameterMap.get("fFrameNumber")[0]);
				if (parameterMap.containsKey("fFrameSize"))
					fDetails.setSize(parameterMap.get("fFrameSize")[0]);
				if (parameterMap.containsKey("fFrameRemark"))
					fDetails.setRemark(parameterMap.get("fFrameRemark")[0]);
				if (parameterMap.containsKey("fFrameCost"))
					fDetails.setPrice(parameterMap.get("fFrameCost")[0]);
				frameDetailList.add(fDetails);
			}

			for (int i = 2; i <= fCouter; i++) {
				if (parameterMap.containsKey("fFrameNumber" + i)) {
					fDetails = new FrameDetails();
					if (parameterMap.containsKey("fFrameNumber" + i))
						fDetails.setFrameNumber(parameterMap.get("fFrameNumber" + i)[0]);
					if (parameterMap.containsKey("fFrameSize" + i))
						fDetails.setSize(parameterMap.get("fFrameSize" + i)[0]);
					if (parameterMap.containsKey("fFrameRemark" + i))
						fDetails.setRemark(parameterMap.get("fFrameRemark" + i)[0]);
					if (parameterMap.containsKey("fFrameCost" + i))
						fDetails.setPrice(parameterMap.get("fFrameCost" + i)[0]);
					frameDetailList.add(fDetails);
				}
			}

			serviceDetailList = frameDetailList;
			break;

		case LAMINATION_DETAILS:
			List<LaminationDetails> laminationDetailList = new ArrayList<LaminationDetails>();

			int lCouter = 0;
			try {
				lCouter = Integer.parseInt(parameterMap.get("fLamCounter")[0]);
			} catch (Exception e) {

			}

			LaminationDetails lDetails = null;
			if (parameterMap.containsKey("fLamQuality") && parameterMap.get("fLamQuality")[0] != null && !parameterMap.get("fLamQuality")[0].trim().isEmpty()) {
				lDetails = new LaminationDetails();
				if (parameterMap.containsKey("fLamQuality"))
					lDetails.setQuality(parameterMap.get("fLamQuality")[0]);
				if (parameterMap.containsKey("fLamSize"))
					lDetails.setSize(parameterMap.get("fLamSize")[0]);
				if (parameterMap.containsKey("fLamRemark"))
					lDetails.setRemark(parameterMap.get("fLamRemark")[0]);
				if (parameterMap.containsKey("fLamCost"))
					lDetails.setPrice(parameterMap.get("fLamCost")[0]);
				laminationDetailList.add(lDetails);
			}

			for (int i = 2; i <= lCouter; i++) {
				if (parameterMap.containsKey("fLamQuality" + i)) {
					lDetails = new LaminationDetails();
					if (parameterMap.containsKey("fLamQuality" + i))
						lDetails.setQuality(parameterMap.get("fLamQuality" + i)[0]);
					if (parameterMap.containsKey("fLamSize" + i))
						lDetails.setSize(parameterMap.get("fLamSize" + i)[0]);
					if (parameterMap.containsKey("fLamRemark" + i))
						lDetails.setRemark(parameterMap.get("fLamRemark" + i)[0]);
					if (parameterMap.containsKey("fLamCost" + i))
						lDetails.setPrice(parameterMap.get("fLamCost" + i)[0]);
					laminationDetailList.add(lDetails);
				}
			}
			serviceDetailList = laminationDetailList;
			break;
		default:
			break;
		}

		return serviceDetailList;
	}
}
