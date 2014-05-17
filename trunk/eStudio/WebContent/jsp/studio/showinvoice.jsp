<%@page import="java.util.List"%>
<%@page import="com.estudio.pojo.LaminationDetails"%>
<%@page import="com.estudio.pojo.FrameDetails"%>
<%@page import="com.estudio.pojo.PhotoDetails"%>
<%@page import="com.estudio.pojo.Invoice"%>
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
								<%
								 Invoice newInvoice = (Invoice)request.getAttribute("INVOICE_DETAIL");
								%>
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
													<span class="red"><%=newInvoice.get_id()%></span>

													<br />
													<span class="invoice-info-label">Date:</span>
													<span class="blue"><%=newInvoice.getCtime() %></span>
												</div>

												<div class="widget-toolbar hidden-480">
													
													<a href="#" onclick="javascript:window.print()">
														<i class="icon-print"></i>
													</a>
												</div>
											</div>
											<form>
											<%-- <input type="hidden" id="idInvoiceNo" name="fInvoiceNo" value="<%=newInvoice.get_id()%>"/> --%>
											<div class="widget-body">
												<div class="widget-main padding-24">
													<div class="row">
														<div class="col-sm-12">
															<div class="row">
																<div class="col-xs-11 label label-lg label-success">
																	<b>Customer Info</b>
																</div>
															</div>

															<div>
																<ul class="list-unstyled  spaced">
																	<li>
																		<i class="icon-caret-right green"></i>
																		Name : <%=newInvoice.getCustomer().getName() %>
																	</li>

																	<li>
																		<i class="icon-caret-right green"></i>
																		Mobile : <%=newInvoice.getCustomer().get_id() %>
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
																	<label id="idEstDeliveryDate" class="width-100 "><b><%=newInvoice.getDelivaryDate() %></b></label>
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
																	<th>Description</th>
																	<th class="hidden-480">Units</th>
																	<th>Total</th>
																</tr>
															</thead>

															<tbody>
															<%
																List<PhotoDetails> phDt = newInvoice.getPhotoDetailsList();
																int i=0;
																if(phDt != null && phDt.size()>0 ){
																	for(i=0;i<phDt.size();i++){
															%>
																<tr>
																	<td class="center"><%=i+1%></td>

																	<td>
																		Photo
																	</td>
																	<td >
																		<%
																			if(phDt.get(i).isUrgent())
																			{
																				%>
																				<b class="red">Urgent</b>
																				<%
																			}
																		%>
																		Photo #: <b><%=phDt.get(i).getPhotoNumber() %></b>,  Photo Source <b><%=phDt.get(i).getPhotoSource() %></b> and  Quality is <b><%=phDt.get(i).getQuality() %> </b>
																	</td>
																	<td class="hidden-480"> <%=phDt.get(i).getQuantity() %> </td>
																	<td><%=phDt.get(i).getPrice() %> </td>
																</tr>
															<%		}
																}
																List<FrameDetails> frDt = newInvoice.getFrameDetailsList();
																int j =0;
																if(frDt != null && frDt.size()>0 ){
																	for(j=0;j<frDt.size();j++){
															%>
																<tr>
																	<td class="center"><%=j+1+i%></td>

																	<td>
																		Photo Frame
																	</td>
																	<td >
																		Frame number is <%=frDt.get(j).getFrameNumber() %>,
																		Size is <%=frDt.get(j).getSize() %>

																	</td>
																	<td class="hidden-480"> 1 <% //frDt.getQuantity() %> </td>
																	<td><%=frDt.get(j).getPrice() %> </td>
																</tr>
															<%		}
																}
																List<LaminationDetails> lamDt = newInvoice.getLaminationDetailsList();
																int k =0;
																if(lamDt != null && lamDt.size()>0 ){
																	for(k=0;k<lamDt.size();k++){
															%>
																<tr>
																	<td class="center"><%=k+j+i+1%></td>
																	<td>Lamination</td>
																	<td >
																		Quality is <%=lamDt.get(k).getQuality() %>, Size is <%=lamDt.get(k).getSize() %>
																	</td>
																	<td class="hidden-480"> 1 <% //lamDt.getQuantity() %></td>
																	<td><%=lamDt.get(k).getPrice() %></td>
																</tr>
																<%	}
																}%>
															</tbody>
														</table>
													</div>

													<div class="hr hr8 hr-double hr-dotted"></div>
													
													<div class="row">
														<div class="col-sm-7 pull-left">
															
																Extra information :
																<!-- <span>Urgent photo copy</span> -->
															
														</div>
														
														<div class="col-sm-5 pull-right">
															<h4 class="pull-right">
																Total amount :
																<span class="green"><span class="icon-inr"></span> <span id="idTotAmt"><%=newInvoice.getTotalAmount() %></span></span>
															</h4>
														</div>
														
													</div>
													<div class="row">
														<div class="col-sm-5 pull-right">
															<h4 class="pull-right">
																Advance paid :
																<span class="green"><span class="icon-inr"></span> <span id="idAdvPaid"><%=newInvoice.getAdvanceBal() %></span></span>
																<!-- <input type="text" size="8" class="blue" id="idAdvPaid" name="fAdvPaid"/> -->
															</h4>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-5 pull-right">
															<h4 class="pull-right">
																Balance amount :
																<span class="red" id="idBalText"><span class="icon-inr"></span><%=newInvoice.getTotalAmount() %></span>
															</h4>
														</div>
													</div>
													<div class="space-6"></div>
													<div class="well">
														Thank you for choosing us. We believe you will be satisfied by our services.
														<div class="pull-right"><div class="col-sm-8 pull-right">developed by ISDC</div> <div class="col-sm-4 pull-right"><img width="90px" src="${pageContext.request.contextPath}/custom/images/isdc_deliverable_transbg.png" style="margin-left:10px; background-color:green" class="img-responsive"/></div></div>
													</div>
												</div>
											</div>
											</form>
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
			/* 
			$('.date-picker').datepicker({autoclose:true,orientation: 'left'}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
			 */

/* 			$("#idSaveInvoice").click(function(e) {
                
				var frm = $(document.forms);
				//console.log(JSON.stringify(frm.serialize()));
				var URL = "invoiceaction.do?op=save&"+frm.serialize();
				var DATA = {};
				var embedInElement = "";
				async.munsi.ajaxCall(URL,DATA,embedInElement);

				
				var hash=e.currentTarget.hash;// string like "#?id=0"
                if (hash.substring(0,5) === '#?id=') {
                    var id = hash.substring(5,hash.length);
                    if(hash.indexOf('action=manage_beat') > -1){
                    	showBeatDialog(id);
                    }
                }
                e.preventDefault();
            }); */
			
			//$("#idAdvPaid").on('change keyup paste', function() {
				//var toAm = $("#idTotalAmount").val();
			 	//$("#idTotalAmount").val(Number($("#idPhotoCost").val()) + Number($("#idLamCost").val()) + Number($("#idFrameCost").val()));
			 	$("#idBalText").html("<span class='icon-inr'></span> "+(Number($("#idTotAmt").html()) - Number($("#idAdvPaid").html())));
			//});
			
	});
	</script>