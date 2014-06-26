package com.estudio.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Config;
import com.async.util.Constants;
import com.async.util.Constants.OrderStatuEnum;
import com.async.util.Constants.ServiceEnum;
import com.async.util.Constants.UIOperations;
import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.dao.impl.MongoInvoiceDao;
import com.estudio.pojo.Customer;
import com.estudio.pojo.FlatInvoice;
import com.estudio.pojo.FrameDetails;
import com.estudio.pojo.Invoice;
import com.estudio.pojo.LaminationDetails;
import com.estudio.pojo.PhotoDetails;
import com.estudio.service.InvoiceService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
					String pattern = "dd/MM/yy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String msgNewInvoice= "Your invoice no. "+ newInvoice.getInvoiceNumber() +" dated " + simpleDateFormat.format(newInvoice.getInvoiceDate()) 
							+ " , Total Amt Rs "+ newInvoice.getTotalAmount() +" , Adv paid Rs "+ newInvoice.getAdvanceBal()+" , Bal Amt Rs "+ (newInvoice.getTotalAmount() - newInvoice.getAdvanceBal() )
							+ " , approx. delivery dt " + simpleDateFormat.format(newInvoice.getDelivaryDate()) +" . Thank you, "+Config.getProperty("studio.name")+" Team";
					try{
						CommonUtil.smsMsg(newInvoice.getCustomer().get_id(), msgNewInvoice);
					}catch(Exception ex){
						HttpSession session = request.getSession(false);
						request.setAttribute("SERVER_SMS_FAILED", "SMS sending failed on "+newInvoice.getCustomer().get_id());
						session.setAttribute("SMS_RETRY_MSG", msgNewInvoice);
						session.setAttribute("NEW_INVOICE_DETAIL", invoice);
					}
					request.setAttribute("NEW_INVOICE_DETAIL", invoice);
					request.setAttribute("SERVER_MESSAGE", "SMS sent to "+ newInvoice.getInvoiceNumber());
					request.setAttribute("SERVER_MESSAGE_DETAIL", msgNewInvoice);
					
					RequestDispatcher rd = request.getRequestDispatcher("/embedpage.action?reqPage=/jsp/studio/invoiceprint.jsp");
					rd.forward(request, response);
				}

				break;
				case EDIT :
					String status = request.getParameter("status");
					String invId = request.getParameter("id");
					String advanceBal = request.getParameter("advanceBal");
					
					if((status != null || advanceBal != null) && invId != null) {
						Invoice inv = new Invoice();
						inv.set_id(invId);
						Invoice oInv= invoiceService.get(invId);
						float fAdvanceBal = oInv.getTotalAmount() - Float.parseFloat(advanceBal);
						inv.setAdvanceBal(fAdvanceBal);
						inv.setStatus(status);
						
						String oldStatus = oInv.getStatus();
						String oldDirPath = Config.getProperty("dir."+ OrderStatuEnum.getEnumName(oldStatus));
						final JsonNodeFactory jsonfactory = JsonNodeFactory.instance;
						ObjectNode objNode = jsonfactory.objectNode();
						if(invoiceService.update(inv))
						{
							String newDirPath = Config.getProperty("dir."+OrderStatuEnum.getEnumName(status));
							List<PhotoDetails> phDetailsList = oInv.getPhotoDetailsList();
							Iterator<PhotoDetails> itr = phDetailsList.iterator();
							while(itr.hasNext()){
								PhotoDetails phdt = itr.next();
								try{
								moveFile(phdt.getPhotoNumber(),oldDirPath,newDirPath);
								}catch(Exception ex){
									objNode.set("MOVE_FILE_ERROR", jsonfactory.textNode("Can not find photo "+phdt.getPhotoNumber()+" at "+oldDirPath));
								}
							}
						}
						
						if(status.equals(OrderStatuEnum.RECEIVED_FROM_PRINT.toString())){
							String msgReadyDelivery = "Your invoice no. "+ oInv.getInvoiceNumber() +" is ready to be delivered. Please collect it from our outlet. Thank you for choosing us, "+Config.getProperty("studio.name")+" Team";
							
							try{
							CommonUtil.smsMsg(oInv.getCustomer().get_id(), msgReadyDelivery);
							}catch(Exception ex){
								objNode.set("SMS_ERROR", jsonfactory.textNode("Problem occured while sending SMS to "+oInv.getCustomer().get_id()));
							}
							request.setAttribute("SERVER_MESSAGE", "SMS sent to "+ oInv.getInvoiceNumber());
							request.setAttribute("SERVER_MESSAGE_DETAIL", msgReadyDelivery);
						}
						response.setContentType("application/json");
						response.getWriter().write(objNode.toString());
						response.getWriter().flush();
					} 
				break;	
				/*
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

				String fieldname = request.getParameter("column")!=null?request.getParameter("column"):"";
				String value =  request.getParameter("value")!=null?request.getParameter("value"):"";
				Map<String, String> mapC = new HashMap<String, String>();
				if(!fieldname.equalsIgnoreCase("") || !value.equalsIgnoreCase("") )
					mapC.put(fieldname, value);

				List<Invoice> invList = invoiceService.getAllByField(mapC);
				for (Invoice invRef : invList) {
					invRef.setsCtime(CommonUtil.longToStringDate(invRef.getCtime().getTime()));
					invRef.setsUtime(CommonUtil.longToStringDate(invRef.getUtime().getTime()));

					if (invRef.getInvoiceDate() != null) {
						invRef.setsInvoiceDate(CommonUtil.longToStringDate(invRef.getInvoiceDate().getTime()));
					}
					if (invRef.getDelivaryDate() != null) {
						invRef.setsDelivaryDate(CommonUtil.longToStringDate(invRef.getDelivaryDate().getTime()));
					}

					Customer sCustomer = invRef.getCustomer();
					if (sCustomer != null) {
						if (sCustomer.getDob() != null) {
							sCustomer.setsDob(CommonUtil.longToStringDate(sCustomer.getDob().getTime()));
						}
						if (sCustomer.getMarriageDate() != null) {
							sCustomer.setsMarriageDate(CommonUtil.longToStringDate(sCustomer.getMarriageDate().getTime()));
						}
					}
				}

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

	private void moveFile(String photoNumberFileName, String oldDirPath, String newDirPath) throws IOException {
	
		File oldDir = new File(oldDirPath);
		boolean isPresent = false;
		for(String str : oldDir.list())
	    {
			String[] splitStr = str.split("\\."); 
		 	if(splitStr[0].equals(photoNumberFileName)){
		 		Path oldPath = FileSystems.getDefault().getPath(oldDirPath, str);
		 		Path newPath = FileSystems.getDefault().getPath(newDirPath, str);
                Files.move(oldPath, newPath , StandardCopyOption.REPLACE_EXISTING);
                isPresent = true;
                break;
		 	}
	    }
		if(!isPresent)
			throw new IOException("File not present");
	}

	private Customer getCustomerDetails(Map<String, String[]> parameterMap) throws ParseException {
		Customer customer = new Customer();
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

				if (parameterMap.containsKey("fUrgent")){
					String strUrgent = parameterMap.get("fUrgent")[0];
					pDetails.setUrgent(strUrgent.equalsIgnoreCase("ON")?true:false);
				}
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
					if (parameterMap.containsKey("fUrgent"+ i)){
						String strUrgent = parameterMap.get("fUrgent"+ i)[0];
						pDetails.setUrgent(strUrgent.equalsIgnoreCase("ON")?true:false);
					}
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
	
	
	
	public static Collection<FlatInvoice> getAllByFieldByDate(String startDate,String endDate){
		
		MongoInvoiceDao mongoInvoiceDao = new MongoInvoiceDao();
		List<Invoice> invList = mongoInvoiceDao.getAllByFieldByDate(startDate,endDate,"status");
		List<FlatInvoice> finvList = new ArrayList<FlatInvoice>();
		for (Invoice invRef : invList) {
			FlatInvoice fInv = new FlatInvoice();
			
			invRef.setsCtime(CommonUtil.longToStringDate(invRef.getCtime().getTime()));
			invRef.setsUtime(CommonUtil.longToStringDate(invRef.getUtime().getTime()));
			
			if (invRef.getInvoiceDate() != null) {
				invRef.setsInvoiceDate(CommonUtil.longToStringDate(invRef.getInvoiceDate().getTime()));
			}
			if (invRef.getDelivaryDate() != null) {
				invRef.setsDelivaryDate(CommonUtil.longToStringDate(invRef.getDelivaryDate().getTime()));
			}

			Customer sCustomer = invRef.getCustomer();
			if (sCustomer != null) {
				if (sCustomer.getDob() != null) {
					sCustomer.setsDob(CommonUtil.longToStringDate(sCustomer.getDob().getTime()));
				}
				if (sCustomer.getMarriageDate() != null) {
					sCustomer.setsMarriageDate(CommonUtil.longToStringDate(sCustomer.getMarriageDate().getTime()));
				}
			}
			//----------- Filling flat invoice
			fInv.setsCtime(invRef.getsCtime());
			fInv.setsUtime(invRef.getsUtime());
			fInv.setsInvoiceDate(invRef.getsInvoiceDate());
			fInv.setsDelivaryDate(invRef.getsDelivaryDate());
			fInv.setStatus(invRef.getStatus());
			fInv.set_id(invRef.get_id());
			fInv.setAdvanceBal(invRef.getAdvanceBal());
			fInv.setInvoiceNumber(invRef.getInvoiceNumber());
			fInv.setRemainingBal(invRef.getRemainingBal());
			fInv.setTotalAmount(invRef.getTotalAmount());
			fInv.setCustomerName(invRef.getCustomer().getName());
			fInv.setCustomerPhone(invRef.getCustomer().get_id());
			//fInv.setPhotoNo(invRef.getPhotoDetailsList().);
			String strPhotoNo="";
			if(invRef.getPhotoDetailsList() != null)
			for(PhotoDetails phDt : invRef.getPhotoDetailsList()){
				strPhotoNo = strPhotoNo + phDt.getPhotoNumber()+",";
			}
			if(strPhotoNo.length()>0)
				strPhotoNo=strPhotoNo.substring(1, strPhotoNo.length()-1);
			fInv.setPhotoNo(strPhotoNo);
			finvList.add(fInv);
		}
		
		return finvList;
	}
}
