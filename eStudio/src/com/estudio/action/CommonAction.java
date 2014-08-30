package com.estudio.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.async.simulations.SantoshUtil;
import com.async.util.CommonUtil;
import com.async.util.Constants;
import com.async.util.Constants.UIOperations;
import com.estudio.pojo.Invoice;
import com.estudio.report.MonthlyReport;

/**
 * Servlet implementation class CommonAction
 */
@WebServlet("/commonaction.do")
public class CommonAction extends HttpServlet {
	private static final Logger LOG = Logger.getLogger("DAILY_FILE_STORE");

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommonAction() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	private void doProcessWithException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LOG.info("SMS LOG :: " + " message");
			doProcess(request, response);
		} catch (RuntimeException me) {
			LOG.error(me);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

		String json = "";
		String operation = request.getParameter(Constants.OPERATION);
		PrintWriter out = null;

		if (operation != null) {

			Constants.UIOperations opEnum = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case FETCH:

				out = response.getWriter();
				if (!request.getParameter("service").equalsIgnoreCase("")) {

					if (request.getParameter("key") != null && !request.getParameter("key").equalsIgnoreCase("")) {
						json = SantoshUtil.getIDTextFormat(request.getParameter("service"), request.getParameter("key"));
					} else {
						if (request.getParameter("get") != null && request.getParameter("get").equalsIgnoreCase("all")) {
							Map<String, Object> whereFields = new HashMap<String, Object>();
							whereFields.put("_id", request.getParameter("cust_mobile"));
							json = SantoshUtil.getAllValue(whereFields);
						} else if (request.getParameter("get") != null) {
							Map<String, Object> whereFields = new HashMap<String, Object>();
							whereFields.put("type", request.getParameter("service"));
							whereFields.put("size", request.getParameter("size"));
							if (request.getParameterMap().containsKey("quality")) {
								whereFields.put("quality", request.getParameter("quality"));
							}
							if (request.getParameterMap().containsKey("frameNumber")) {
								whereFields.put("frameNumber", request.getParameter("frameNumber"));
							}

							json = SantoshUtil.getValue(whereFields, request.getParameter("get").toString());
						}
					}
				}
				break;
			case RESEND_SMS:
				out = response.getWriter();
				HttpSession session = request.getSession(false);
				Invoice invoiceObj = (Invoice) session.getAttribute("NEW_INVOICE_DETAIL");
				String msgNewInvoice = (String) session.getAttribute("SMS_RETRY_MSG");
				try {
					CommonUtil.smsMsg(invoiceObj.getCustomer().get_id(), msgNewInvoice);
				} catch (Exception ex) {
					request.setAttribute("SERVER_SMS_FAILED", "SMS sending failed on " + invoiceObj.getCustomer().get_id());
					session.setAttribute("SMS_RETRY_MSG", msgNewInvoice);
					session.setAttribute("NEW_INVOICE_DETAIL", invoiceObj);
				}
				request.setAttribute("NEW_INVOICE_DETAIL", invoiceObj);
				request.setAttribute("SERVER_MESSAGE", "SMS sent to " + invoiceObj.getInvoiceNumber());
				request.setAttribute("SERVER_MESSAGE_DETAIL", msgNewInvoice);

				RequestDispatcher rd = request.getRequestDispatcher("/embedpage.action?reqPage=/jsp/studio/invoiceprint.jsp");
				rd.forward(request, response);

				break;

			case REPORT_DATE:
				/*Invoice invoiceObj = (Invoice)session.getAttribute("NEW_INVOICE_DETAIL");
				String msgNewInvoice = (String)session.getAttribute("SMS_RETRY_MSG");*/
				/*try{
					CommonUtil.smsMsg(invoiceObj.getCustomer().get_id(), msgNewInvoice);
				}catch(Exception ex){
					request.setAttribute("SERVER_SMS_FAILED", "SMS sending failed on "+invoiceObj.getCustomer().get_id());
					session.setAttribute("SMS_RETRY_MSG", msgNewInvoice);
					session.setAttribute("NEW_INVOICE_DETAIL", invoiceObj);
				}
				request.setAttribute("NEW_INVOICE_DETAIL", invoiceObj);
				request.setAttribute("SERVER_MESSAGE", "SMS sent to "+ invoiceObj.getInvoiceNumber());
				request.setAttribute("SERVER_MESSAGE_DETAIL", msgNewInvoice);
				
				RequestDispatcher rd = request.getRequestDispatcher("/embedpage.action?reqPage=/jsp/studio/invoiceprint.jsp");
				rd.forward(request, response);
				*/

				String startDate = request.getParameter("fStartDateRep");
				String endDate = request.getParameter("fEndDateRep");

				if (startDate == null) {
					String pattern = "dd-MM-yy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					startDate = simpleDateFormat.format(new Date());
				}

				if (endDate == null) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.DAY_OF_YEAR, 1);
					Date tomorrow = cal.getTime();

					String pattern = "dd-MM-yy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					endDate = simpleDateFormat.format(tomorrow);
				}

				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"Report_" + startDate + endDate + ".pdf\"");

				InputStream inputStream = MonthlyReport.exportToPdf(startDate, endDate);
				// ServletOutputStream output = response.getOutputStream();

				BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
				// OutputStream output = new FileOutputStream(filename);
				int read = 0;
				byte[] bufferData = new byte[1024];
				while ((read = inputStream.read(bufferData)) != -1) {
					output.write(bufferData, 0, read);
				}

				output.flush();
				output.close();
				inputStream.close();

				break;
			default:

			}

			out.write(json);
			out.close();
		}
	}
}