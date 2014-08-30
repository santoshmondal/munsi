package com.munsi.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.munsi.dao.SalesInvoiceDao;
import com.munsi.pojo.invoice.sales.SalesInvoice;
import com.munsi.pojo.master.Customer;
import com.munsi.util.CommonUtil;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class ReportFactory {

	public static Collection<ReportSalesInvoice> getAllByFieldByDate(String startDate, String endDate) {

		SalesInvoiceDao sInvoiceDao = (SalesInvoiceDao) ObjectFactory.getInstance(ObjectEnum.SALES_INVOICE_DAO);

		List<SalesInvoice> salesList = sInvoiceDao.getAllByDate(startDate, endDate);

		List<ReportSalesInvoice> reportSalesInvoiceList = new ArrayList<ReportSalesInvoice>();
		for (SalesInvoice salesInvoice : salesList) {
			ReportSalesInvoice reportSalesInvoice = new ReportSalesInvoice();

			String strCtime = CommonUtil.longToStringDate(salesInvoice.getCtime().getTime());
			reportSalesInvoice.setSctime(strCtime);
			reportSalesInvoice.setCtime(salesInvoice.getUtime());

			String strUtime = CommonUtil.longToStringDate(salesInvoice.getUtime().getTime());
			reportSalesInvoice.setSutime(strUtime);
			reportSalesInvoice.setUtime(salesInvoice.getUtime());
			reportSalesInvoice.setDeleted(salesInvoice.getDeleted());

			reportSalesInvoice.set_id(salesInvoice.get_id());
			reportSalesInvoice.setInvoiceNumber(salesInvoice.getInvoiceNumber());
			Customer customer = salesInvoice.getCustomer();
			if (customer != null) {
				reportSalesInvoice.setCustomerName(customer.getName());
				reportSalesInvoice.setCustomerMobile(customer.getMobile());
				reportSalesInvoice.setCustomerPhone(customer.getPhone());
				reportSalesInvoice.setCustomerOutStandingAmount(customer.getOutStandingAmount());
			}

			String strInvoiceDate = CommonUtil.longToStringDate(salesInvoice.getCtime().getTime());
			reportSalesInvoice.setSinvoiceDate(strInvoiceDate);
			reportSalesInvoice.setInvoiceDate(salesInvoice.getCtime());
			reportSalesInvoice.setInvoiceDiscountPrice(salesInvoice.getInvoiceDiscountPrice());
			reportSalesInvoice.setInvoiceDiscountPrice(salesInvoice.getInvoiceDiscountPercent());
			reportSalesInvoice.setInvoiceTaxPercent(salesInvoice.getInvoiceTaxPercent());
			reportSalesInvoice.setInvoiceTaxPrice(salesInvoice.getInvoiceTaxPrice());
			reportSalesInvoice.setSumOfNetPaybleProductPrice(salesInvoice.getSumOfNetPaybleProductPrice());

			reportSalesInvoice.setNetPayblePrice(salesInvoice.getNetPayblePrice());
			reportSalesInvoice.setNumberOfItem(salesInvoice.getNumberOfItem());
			reportSalesInvoice.setPaidAmount(salesInvoice.getPaidAmount());
			reportSalesInvoice.setFreight(salesInvoice.getFreight());
			reportSalesInvoice.setRoundOfAmount(salesInvoice.getRoundOfAmount());
			reportSalesInvoice.setBalanceAmount(salesInvoice.getNetPayblePrice() - salesInvoice.getPaidAmount());
			reportSalesInvoiceList.add(reportSalesInvoice);

		}

		return reportSalesInvoiceList;
	}

	public static Collection<ReportSalesInvoice> getAllByFieldByDate() {
		List<ReportSalesInvoice> reportSalesInvoiceList = new ArrayList<ReportSalesInvoice>();
		return reportSalesInvoiceList;
	}

}
