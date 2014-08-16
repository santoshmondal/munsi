package com.munsi.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.munsi.pojo.invoice.purchase.PurchaseInvoice;
import com.munsi.pojo.invoice.purchase.PurchaseProduct;
import com.munsi.pojo.invoice.sales.SalesInvoice;
import com.munsi.pojo.invoice.sales.SalesProduct;
import com.munsi.pojo.master.Product;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class SalesPurchaseRuleManager {

	private static final Logger LOG = Logger.getLogger(SalesPurchaseRuleManager.class);
	private ProductServeice productService;

	public SalesPurchaseRuleManager() {
		productService = (ProductServeice) ObjectFactory.getInstance(ObjectEnum.PRODUCT_SERVICE);
	}

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
			Double sumOfNetPaybleProductPrice = 0.0;
			Double sumOfNetTaxPrice = 0.0;
			for (PurchaseProduct pProduct : pInvoice.getPurchaseProductList()) {

				Integer totalQuantity = null;
				Double derPrice = null;
				Double derTaxPrice = null;
				Double derDiscountPrice = null;
				Double netPaybleProductPrice = null;

				totalQuantity = pProduct.getFreeQuantity() + pProduct.getQuantity();
				derPrice = (double) (pProduct.getPurchaseRate() * pProduct.getQuantity());
				derTaxPrice = (derPrice * pProduct.getDerSumOfProudctTax()) / 100.0;
				derDiscountPrice = ((derTaxPrice + derPrice) * pProduct.getRawDiscountPercent()) / 100.0;
				netPaybleProductPrice = derPrice + derTaxPrice - derDiscountPrice;

				pProduct.setTotalQuantity(totalQuantity);
				pProduct.setDerPrice(derPrice);
				pProduct.setDerTaxPrice(derTaxPrice);
				pProduct.setDerDiscountPrice(derDiscountPrice);
				pProduct.setNetPaybleProductPrice(netPaybleProductPrice);

				sumOfNetTaxPrice += derTaxPrice;
				sumOfNetPaybleProductPrice += netPaybleProductPrice;
			}

			Double invoiceTaxPrice = sumOfNetTaxPrice;
			Double invoiceDiscountPrice = pInvoice.getInvoiceDiscountPrice();
			Integer numberOfItem = pInvoice.getPurchaseProductList().size();
			Double freight = pInvoice.getFreight();
			Double roundOfAmount = pInvoice.getRoundOfAmount();
			Double netPayblePrice = sumOfNetPaybleProductPrice + freight - invoiceDiscountPrice + roundOfAmount;

			pInvoice.setInvoiceTaxPrice(invoiceTaxPrice);
			pInvoice.setInvoiceTaxPercent((invoiceTaxPrice / sumOfNetPaybleProductPrice) * 100);
			pInvoice.setInvoiceDiscountPercent((invoiceDiscountPrice / sumOfNetPaybleProductPrice) * 100);
			pInvoice.setSumOfNetPaybleProductPrice(sumOfNetPaybleProductPrice);
			pInvoice.setNumberOfItem(numberOfItem);
			pInvoice.setNetPayblePrice(netPayblePrice);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void applyCustomerUpdates(SalesInvoice sInvoice) {
		try {
			Double netPayblePrice = sInvoice.getNetPayblePrice();
			Double paidAmount = sInvoice.getPaidAmount();

			Float outStandingAmount = sInvoice.getCustomer().getOutStandingAmount();
			outStandingAmount = outStandingAmount != null ? outStandingAmount : 0.0f;

			Double plusMinusAmount = paidAmount - netPayblePrice;

			if (plusMinusAmount > 0) {
				outStandingAmount = (float) (outStandingAmount - plusMinusAmount);
			} else {
				outStandingAmount = (float) (outStandingAmount + Math.abs(plusMinusAmount));
			}

			sInvoice.getCustomer().setOutStandingAmount(outStandingAmount);

		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void applySupplierUpdates(PurchaseInvoice pInvoice) {

	}

	public void applyInventoryUpdates(Object sInvoice, Boolean isSalesOrPurchase) {
		try {
			Set<? extends Product> productList = null;
			// if true imples Sales, false implies Purchase
			if (isSalesOrPurchase) {
				SalesInvoice salesInvoice = (SalesInvoice) sInvoice;
				productList = salesInvoice.getSalesProductList();

			} else {
				PurchaseInvoice purchaseInvoice = (PurchaseInvoice) sInvoice;
				productList = purchaseInvoice.getPurchaseProductList();
			}

			for (Product product : productList) {
				Product productMaster = productService.get(product.get_id());
				Integer currentMasterStock = productMaster.getCurrentStock();
				currentMasterStock = currentMasterStock != null ? currentMasterStock : 0;

				if (isSalesOrPurchase) {
					SalesProduct salesProduct = (SalesProduct) product;
					currentMasterStock -= salesProduct.getTotalQuantity();
				} else {
					PurchaseProduct purchaseProduct = (PurchaseProduct) product;
					currentMasterStock += purchaseProduct.getTotalQuantity();

					productMaster.setMrp(product.getMrp());
					productMaster.setSalesRate(product.getSalesRate());
					productMaster.setPurchaseRate(product.getPurchaseRate());
				}
				productMaster.setCurrentStock(currentMasterStock);
				productService.update(productMaster);
			}

		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void main(String[] args) {
		SalesPurchaseRuleManager rManager = (SalesPurchaseRuleManager) ObjectFactory.getInstance(ObjectEnum.SALES_PURCHASE_RULE);
		rManager.applySalesInvoiceRule(null);
	}

}
