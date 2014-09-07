package com.munsi.report;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class ReportUtil {

	public static void main(String[] args) {
		exportToPdf("01-06-2014", "31-12-2014");
		exportToExcel("01-01-2000", "01-01-2015");
	}

	@SuppressWarnings("deprecation")
	public static FileInputStream exportToPdf(String startDate, String endDate) {
		System.out.println("Start with Report Design ...");
		try {
			// Compile it generates .jasper
			String sourceFileName = URLDecoder.decode(ReportUtil.class.getClassLoader().getResource("SalesReport.jrxml").getFile(), "UTF-8");

			JasperCompileManager.compileReportToFile(sourceFileName);
			File file = new File(sourceFileName);
			file.getParent();
			String sourceJasperFileName = file.getParent() + File.separator + "SalesReport.jasper";
			Map<String, Object> parameters = new HashMap<String, Object>();

			/*parameters.put("STARTDATE", startDate);
			parameters.put("ENDDATE", endDate);
			parameters.put("CLIENTNAME", Config.getProperty("client.name"));*/

			JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource(ReportFactory.getAllByFieldByDate(startDate, endDate));
			JasperFillManager.fillReportToFile(sourceJasperFileName, parameters, collectionDS);

			// export
			String jrprintName = file.getParent() + File.separator + "SalesReport.jrprint";
			JasperExportManager.exportReportToPdfFile(jrprintName, "/SalesReport.pdf");
			return new FileInputStream("/SalesReport.pdf");

		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done reporting!!!");
		return null;
	}

	@SuppressWarnings("deprecation")
	public static FileInputStream exportToExcel(String startDate, String endDate) {
		System.out.println("Start with Report Excel Design ...");
		try {
			// Compile it generates .jasper
			String sourceFileName = URLDecoder.decode(ReportUtil.class.getClassLoader().getResource("SalesReport.jrxml").getFile(), "UTF-8");
			JasperCompileManager.compileReportToFile(sourceFileName);

			File file = new File(sourceFileName);
			file.getParent();
			String sourceJasperFileName = file.getParent() + File.separator + "SalesReport.jasper";
			Map<String, Object> parameters = new HashMap<String, Object>();

			/*parameters.put("STARTDATE", startDate);
			parameters.put("ENDDATE", endDate);
			parameters.put("CLIENTNAME", Config.getProperty("client.name"));*/

			// fillreport it generates .jrprint
			JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource(ReportFactory.getAllByFieldByDate(startDate, endDate));
			JasperFillManager.fillReportToFile(sourceJasperFileName, parameters, collectionDS);

			// export
			String jrprintName = file.getParent() + File.separator + "SalesReport.jrprint";

			/**
			 * export to Excel sheet
			 */
			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, jrprintName);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "/SalesReport.xls");
			exporter.exportReport();

			return new FileInputStream("/SalesReport.xls");

		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done reporting!!!");
		return null;
	}
}