<style>
<!--
@media print
{
body * { visibility: hidden; }
#idInvoicePrint *{ visibility: visible;}
#idInvoicePrint{width:100% !important; position: absolute; top: -20px; left: 30px; }
}
-->
</style>
<%@page import="com.async.util.Constants.DBCollectionEnum"%>
<%@page import="com.async.util.CommonUtil"%>
<%@page import="com.async.util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

					<div id="idInvoicePrint" class="row-fluid">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="space-6">
								</div>

								<div class="row">
									<div class="col-sm-10 col-sm-offset-1">
										<div class="widget-box transparent invoice-box">
											<div class="widget-header widget-header-large">
												<h3 class="grey lighter pull-left position-relative">
													<i class="icon-leaf green"></i>
													Order Invoice
												</h3>

												<div class="widget-toolbar no-border invoice-info">
													<span class="invoice-info-label">Invoice:</span>
													<span class="red">#121212</span>

													<br />
													<span class="invoice-info-label">Date:</span>
													<span class="blue">03/03/2013</span>
												</div>

												<div class="widget-toolbar hidden-480">
													
													<a href="#">
														<i class="icon-save red"></i>
													</a>
													<a href="#">
														<i class="icon-print"></i>
													</a>
												</div>
											</div>

											<div class="widget-body">
												<div class="widget-main padding-24">
													<div class="row">
														<div class="col-sm-6 hidden-xs">
															<div class="row">
																<div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
																	<b>Company Info</b>
																</div>
															</div>

															<div class="row">
																<ul class="list-unstyled spaced">
																	<li>
																		<i class="icon-caret-right blue"></i>
																		Street, City
																	</li>

																	<li>
																		<i class="icon-caret-right blue"></i>
																		Zip Code
																	</li>

																	<li>
																		<i class="icon-caret-right blue"></i>
																		State, Country
																	</li>

																	<li>
																		<i class="icon-caret-right blue"></i>
																		Phone:
																		<b class="red">111-111-111</b>
																	</li>
																</ul>
															</div>
														</div><!-- /span -->

														<div class="col-sm-6">
															<div class="row">
																<div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
																	<b>Customer Info</b>
																</div>
															</div>

															<div>
																<ul class="list-unstyled  spaced">
																	<li>
																		<i class="icon-caret-right green"></i>
																		Name
																	</li>

																	<li>
																		<i class="icon-caret-right green"></i>
																		Mobile
																	</li>
																</ul>
															</div>
														</div><!-- /span -->
													</div><!-- row -->
													<div class="row">
														<div class="pull-right">
															<h5 class="pull-right">
																Estimated Delivery Date :
																<span class="input-icon input-icon-right">
																	<input type="text" id="idEstDeliveryDate" name="fEstDeliveryDate" class="width-100 date-picker" data-date-format="dd/mm/yyyy" />
																	<i class="icon-calendar black"></i>
																</span>
															</h5>
														</div>
													</div>
													<div class="space"></div>

													<div>
														<table class="table table-striped table-bordered">
															<thead>
																<tr>
																	<th class="center">#</th>
																	<th>Particulars</th>
																	<th class="hidden-xs">Description</th>
																	<th class="hidden-480">Units</th>
																	<th>Total</th>
																</tr>
															</thead>

															<tbody>
																<tr>
																	<td class="center">1</td>

																	<td>
																		CloseUp Photo
																	</td>
																	<td class="hidden-xs">
																		5 year domain registration
																	</td>
																	<td class="hidden-480"> 50 </td>
																	<td>150.00</td>
																</tr>

																<tr>
																	<td class="center">2</td>

																	<td>
																		Photo Frame
																	</td>
																	<td class="hidden-xs">
																		5 year domain registration
																	</td>
																	<td class="hidden-480"> 2</td>
																	<td>200.00</td>
																</tr>

																<tr>
																	<td class="center">3</td>
																	<td>Lamination</td>
																	<td class="hidden-xs">
																		1 year basic hosting
																	</td>
																	<td class="hidden-480"> 10 </td>
																	<td>100.00</td>
																</tr>
															</tbody>
														</table>
													</div>

													<div class="hr hr8 hr-double hr-dotted"></div>
													
													<div class="row">
														<div class="col-sm-7 pull-left">
															
																Extra information :
																<span>Urgent photo copy</span>
															
														</div>
														
														<div class="col-sm-5 pull-right">
															<h4 class="pull-right">
																Total amount :
																<span class="green">Rs 450.00</span>
															</h4>
														</div>
														
													</div>
													<div class="row">
														<div class="col-sm-5 pull-right">
															<h4 class="pull-right">
																Advance paid :
																<input type="text" size="8" class="blue" id="idAdvPaid" name="fAdvPaid"/>
															</h4>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-5 pull-right">
															<h4 class="pull-right">
																Balance amount :
																<span class="red">Rs 450.00</span>
															</h4>
														</div>
													</div>
													<div class="space-6"></div>
													<div class="well">
														Thank you for choosing us. We believe you will be satisfied by our services.
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div>
						
	<script type="text/javascript">
	jQuery(function($) {
			$('[data-rel=tooltip]').tooltip();
			
			$('.date-picker').datepicker({autoclose:true,orientation: 'left'}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
	});
	</script>