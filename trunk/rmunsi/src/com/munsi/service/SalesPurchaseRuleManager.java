package com.munsi.service;

import org.apache.log4j.Logger;

import com.munsi.pojo.invoice.purchase.PurchaseInvoice;
import com.munsi.pojo.invoice.sales.SalesInvoice;
import com.munsi.pojo.invoice.sales.SalesProduct;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class SalesPurchaseRuleManager {

	private static final Logger LOG = Logger.getLogger(SalesPurchaseRuleManager.class);

	public void applySalesInvoiceRule(SalesInvoice sInvoice) {

		try {
			Double sumOfNetPaybleProductPrice = 0.0;

			for (SalesProduct sProduct : sInvoice.getSalesProductList()) {

				Integer totalQuantity = null;
				Double derPrice = null;
				Double derTaxPrice = null;
				Double derDiscountPrice = null;
				Double netPaybleProductPrice = null;

				totalQuantity = sProduct.getFreeQuantity() + sProduct.getQuantity();
				derPrice = (double) (sProduct.getSalesRate() * sProduct.getQuantity());
				derTaxPrice = (derPrice * sProduct.getDerSumOfProudctTax()) / 100.0;
				derDiscountPrice = ((derTaxPrice + derPrice) * sProduct.getRawDiscountPercent()) / 100.0;
				netPaybleProductPrice = derPrice + derTaxPrice - derDiscountPrice;

				sProduct.setTotalQuantity(totalQuantity);
				sProduct.setDerPrice(derPrice);
				sProduct.setDerTaxPrice(derTaxPrice);
				sProduct.setDerDiscountPrice(derDiscountPrice);
				sProduct.setNetPaybleProductPrice(netPaybleProductPrice);

				sumOfNetPaybleProductPrice += netPaybleProductPrice;
			}

			Double invoiceTaxPrice = (sumOfNetPaybleProductPrice * sInvoice.getInvoiceTaxPercent()) / 100.0;
			Double invoiceDiscountPrice = sInvoice.getInvoiceDiscountPrice();
			Integer numberOfItem = sInvoice.getSalesProductList().size();
			Double netPayblePrice = sumOfNetPaybleProductPrice + invoiceTaxPrice - invoiceDiscountPrice;

			sInvoice.setSumOfNetPaybleProductPrice(sumOfNetPaybleProductPrice);
			sInvoice.setNumberOfItem(numberOfItem);
			sInvoice.setNetPayblePrice(netPayblePrice);

		} catch (Exception e) {
			LOG.error(e);
		}

	}

	public void applyPurchaseInvoiceRule(PurchaseInvoice pInvoice) {
		try {

		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void main(String[] args) {
		SalesPurchaseRuleManager rManager = (SalesPurchaseRuleManager) ObjectFactory.getInstance(ObjectEnum.SALES_PURCHASE_RULE);
		rManager.applySalesInvoiceRule(null);
	}
}
