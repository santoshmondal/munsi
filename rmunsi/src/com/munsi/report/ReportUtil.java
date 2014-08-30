package com.munsi.report;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportUtil {

	public static void main(String[] args) {
		exportToPdf("01-01-2000", "01-01-2010");
	}

	@SuppressWarnings("deprecation")
	public static FileInputStream exportToPdf(String startDate, String endDate) {
		System.out.println("Start with Report Design ...");
		try {
			// Compile it generates .jasper
			String sourceFileName = URLDecoder.decode(ReportUtil.class.getClassLoader().getResource("SalesReport.jrxml").getFile());
			JasperCompileManager.compileReportToFile(sourceFileName);

			String sourceJasperFileName = URLDecoder.decode(ReportUtil.class.getClassLoader().getResource("SalesReport.jasper").getFile());
			Map<String, Object> parameters = new HashMap<String, Object>();
			/*parameters.put("STARTDATE", startDate);
			parameters.put("ENDDATE", endDate);
			parameters.put("STUDIONAME", Config.getProperty("studio.name"));*/

			// fillreport it generates .jrprint
			/*			List<ReportSalesInvoice> reportSalesInvoiceList = new ArrayList<ReportSalesInvoice>();

						ReportSalesInvoice repSalInv = new ReportSalesInvoice();
						repSalInv.set_id("1");
						repSalInv.setCustomerName("customerName");
						repSalInv.setBalanceAmount(100.00);
						repSalInv.setInvoiceDate(new Date());
						//repSalInv.setSinvoiceDate("01-02-2014");
						//repSalInv.setInvoiceNumber("asa1212");
						repSalInv.setNetPayblePrice(500.22);
						reportSalesInvoiceList.add(repSalInv);
			//JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource(reportSalesInvoiceList);
			*/
			JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource(ReportFactory.getAllByFieldByDate(startDate, endDate));
			JasperFillManager.fillReportToFile(sourceJasperFileName, parameters, collectionDS);

			// export
			String jrprintName = URLDecoder.decode(ReportUtil.class.getClassLoader().getResource("SalesReport.jrprint").getFile());
			JasperExportManager.exportReportToPdfFile(jrprintName, "/SalesReport.pdf");
			return new FileInputStream("/SalesReport.pdf");

			/**
			 * export to Excel sheet
			 */
			/*
			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
				jrprintName);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
			  "C://sample_report.xls");

			exporter.exportReport();*/
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done reporting!!!");
		return null;
	}
}