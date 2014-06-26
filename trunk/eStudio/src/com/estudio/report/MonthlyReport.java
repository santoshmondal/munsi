package com.estudio.report;


import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.async.util.Config;
import com.estudio.action.InvoiceAction;

public class MonthlyReport {
	public static void main(String[] args) {
		//exportToPdf();
	}

	@SuppressWarnings("deprecation")
	public static FileInputStream exportToPdf(String startDate,String endDate) {
		System.out.println("Start with Report Design ...");
		try {
			// Compile it generates .jasper
			String sourceFileName = URLDecoder.decode(MonthlyReport.class.getClassLoader().getResource("MonthlyReport.jrxml").getFile());
			JasperCompileManager.compileReportToFile(sourceFileName);

			String sourceJasperFileName = URLDecoder.decode(MonthlyReport.class.getClassLoader().getResource("MonthlyReport.jasper").getFile());
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("STARTDATE", startDate);
			parameters.put("ENDDATE", endDate);
			parameters.put("STUDIONAME", Config.getProperty("studio.name"));

			// fillreport it generates .jrprint
			JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource( InvoiceAction.getAllByFieldByDate(startDate,endDate));
			JasperFillManager.fillReportToFile(sourceJasperFileName, parameters, collectionDS);

			// export
			String jrprintName = URLDecoder.decode(MonthlyReport.class.getClassLoader().getResource("MonthlyReport.jrprint").getFile());
			JasperExportManager.exportReportToPdfFile(jrprintName, "/MonthlyReport.pdf");
			return new FileInputStream( "/MonthlyReport.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done reporting!!!");
		return null;
	}
}
