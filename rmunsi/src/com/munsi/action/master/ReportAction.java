package com.munsi.action.master;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.munsi.report.ReportUtil;
import com.munsi.util.Constants;
import com.munsi.util.Constants.UIOperations;

/**
 * Servlet implementation class ReportAction
 */
@WebServlet("/report.action")
public class ReportAction extends HttpServlet {
	private static final Logger LOG = Logger.getLogger("DAILY_FILE_STORE");

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

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
			case REPORT_DATE:

				String startDate = request.getParameter("fStartDateRep");
				String endDate = request.getParameter("fEndDateRep");

				String reportName = request.getParameter("reportName");
				String reportFileType = request.getParameter("fileType");

				if (startDate == null && endDate == null) {
					String pattern = "dd-MM-yy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					startDate = simpleDateFormat.format(new Date());
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.DAY_OF_YEAR, 1);
					Date tomorrow = cal.getTime();
					System.out.println(tomorrow);
					endDate = simpleDateFormat.format(tomorrow);
				}
				reportName = reportName == null ? "SALES" : reportName;
				reportFileType = reportFileType == null ? "pdf" : reportFileType;

				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + "Report_" + startDate + endDate + "." + reportFileType + "\"");

				InputStream inputStream = null;
				if (reportName.equalsIgnoreCase("SALES"))
					inputStream = reportFileType.equalsIgnoreCase("PDF") ? ReportUtil.exportToPdf(startDate, endDate) : ReportUtil.exportToExcel(startDate, endDate);
				else if (reportName.equalsIgnoreCase("PURCHASE"))
					inputStream = reportFileType.equalsIgnoreCase("PDF") ? ReportUtil.exportToPdf(startDate, endDate) : ReportUtil.exportToExcel(startDate, endDate);

				BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());

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

		}

		out.write(json);
		out.close();
	}
}