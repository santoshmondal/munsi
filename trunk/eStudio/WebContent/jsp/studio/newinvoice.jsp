
<%@page import="com.async.simulations.SantoshUtil"%>
<%@page import="com.async.util.Constants.DBCollectionEnum"%>
<%@page import="com.async.util.CommonUtil"%>
<%@page import="com.async.util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<!-- <div class="page-header">
							
							<h1>New Invoice
								
							</h1>
						</div>/.page-header -->

						<div class="row-fluid">
						<% String srvmsg = (String)(request.getAttribute("SERVER_MESSAGE")!=null?request.getAttribute("SERVER_MESSAGE"):"");
						if(srvmsg != null && !srvmsg.trim().equalsIgnoreCase("")){ %>
							<div class="alert alert-block alert-danger">
								<button class="close" data-dismiss="alert" type="button">
									<i class="icon-remove"></i>
								</button>
								<i class="icon-warning red"></i><%= srvmsg%> 
							</div>
							<%} %>
								<div class="span12">
										<div class="widget-box">
											<div class="widget-header widget-header-blue widget-header-flat">
												<h4 class="lighter">New Invoice</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main">
													<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
														<ul class="wizard-steps">
															<li data-target="#step1" class="active">
																<span class="step">1</span>
																<span class="title">Customer</span>
															</li>
															<li data-target="#step2">
																<span class="step">2</span>
																<span class="title">Photo</span>
															</li>

															<li data-target="#step3">
																<span class="step">3</span>
																<span class="title">Frame</span>
															</li>

															<li data-target="#step4">
																<span class="step">4</span>
																<span class="title">Lamination</span>
															</li>

															<li data-target="#step5">
																<span class="step">5</span>
																<span class="title">Print</span>
															</li>
															
														</ul>
													</div>

													<hr />
													<div class="step-content row-fluid position-relative" id="step-container">
														<div class="step-pane active" id="step1">
															<h3 class="lighter block green">Enter Customer information</h3>
															<div class="row-fluid col-lg-7 col-md-8 col-sm-8 col-xs-12">
																<form class="form-horizontal" id="customer-form">
																	
																	<div class="form-group">
																		<label for="idMobile" class="col-xs-12 col-sm-3 control-label no-padding-right">Mobile</label>
	
																		<div class="col-xs-12 col-sm-5">
																			<span class="block input-icon input-icon-right">
																				<input type="text" id="idMobile" name="fMobile" class="width-100" />
																				<i class="icon-phone green"></i>
																			</span>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="idName" class="col-xs-12 col-sm-3 control-label no-padding-right">Name</label>
																		<div class="col-xs-12 col-sm-5">
																			<span class="block input-icon input-icon-right">
																				<input type="text" id="idName" name="fName" class="width-100" />
																				<i class="icon-user blue"></i>
																			</span>
																		</div>
																	</div>
																	<div id="accordion" class="accordion-style2">
																		<div>
																			<div class="panel-heading pull-l">
																				<h4 class="panel-title">
																					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
																						<i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>
																						&nbsp;Details...
																					</a>
																				</h4>
																			</div>
																			<div class="panel-collapse collapse" id="collapseOne">
																				<div class="panel-body">
																					
																					<div class="form-group">
																						<label for="idEmail" class="col-xs-12 col-sm-3 control-label no-padding-right">Email</label>
					
																						<div class="col-xs-12 col-sm-5">
																							<span class="block input-icon input-icon-right">
																								<input type="email" id="idEmail" name="fEmail" class="width-100" />
																								<i class="icon-envelope-alt red"></i>
																							</span>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="idDOB" class="col-xs-12 col-sm-3 control-label no-padding-right">D.O.B</label>
					
																						<div class="col-xs-12 col-sm-5">
																							<span class="block input-icon input-icon-right">
																								<input type="text" id="idDOB" name="fDOB" class="width-100 date-picker" data-date-format="dd/mm/yyyy" />
																								<i class="icon-calendar black"></i>
																							</span>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="idMAnniversary" class="control-label col-xs-12 col-sm-3 no-padding-right">Marriage Anniversary</label>
																						
																						<div class="col-xs-12 col-sm-5">
																							<span class="block input-icon input-icon-right">
																								<input type="text" id="idMAnniversary" name="fMAnniversary" class="width-100 date-picker" data-date-format="dd/mm/yyyy"/>
																								<i class="icon-calendar black"></i>
																							</span>
																						</div>
																					</div>
																					<div class="form-group">
																						<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idAddress">Address</label>
																						<div class="col-xs-12 col-sm-5">
																							<div class="clearfix">
																								<textarea class="input-xlarge" cols="50" name="fAddress" id="idAddress"></textarea>
																							</div>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</form>
															</div>
															<div class="row-fluid col-lg-5 col-md-4 col-sm-4 hidden-xs">
																<img src="${pageContext.request.contextPath}/assets/images/Picture4.png" class="img-responsive" alt="Responsive image">
																	
															</div>
														</div>
														
														<div class="step-pane" id="step2">
															<div  class="row-fluid col-lg-7 col-md-8 col-sm-8 col-xs-12">
															<form class="form-horizontal" id="formPhoto">
																<input type="hidden" id="idPhotoCounter" name="fPhotoCounter" value="1"/>
																<div class="tabbable">
																	<ul class="nav nav-tabs" id="myPhotoTab">
																		<li id='myPhotoTabLi' class="active">
																			<a data-toggle="tab" href="#photo1">
																				Photo 1
																			</a>
																		</li>
																		<a id="idAddPhoto" class="btn btn-sm btn-primary"><span class="icon-plus"></span></a>
																	</ul>
																	
																	<div class="tab-content">
																		<div id="photo1" class="tab-pane in active">
																			<div>
																				<div class="form-group">
																					<label for="idUrgent" class="col-xs-12 col-sm-3 control-label no-padding-right">Urgent Photo</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block" >
																							<input class="ace ace-switch ace-switch-5" type="checkbox" name="fUrgent" />
																							<span class="lbl"></span>
																						</span>
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="idPhotoNumber" class="col-xs-12 col-sm-3 control-label no-padding-right">Photo Number</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<input id="idPhotoNumber" class="width-100" type="text" name="fPhotoNumber" />
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="idPhotoSource" class="col-xs-12 col-sm-3 control-label no-padding-right">Photo Source</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<select id="idPhotoSource" name="fPhotoSource" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="studio">Studio</option>
																							<option value="mediaprint">Media Print</option>
																							<option value="scanprint">Scan Print</option>
																							<option value="reprint">Re-Print</option>
																						</select>
																						<!-- <input type="hidden" id="idPhotoSource" name="fPhotoSource" style="width:300px" class="select2" /> -->
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="idNoPhoto" class="col-xs-12 col-sm-3 control-label no-padding-right">Number of copies</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right">
																							<input type="text" id="idNoPhoto" name="fNoPhoto" class="width-100 spinner" />
																						</span>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idSize">Photo Size</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<!-- <select id="idSize" name="fSize" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select> -->
																						<input type="hidden" id="idSize" name="fSize" style="width:300px" class="select2" />
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idQuality">Photo Quality</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<!-- <select id="idQuality" name="fQuality" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select> -->
																						<input type="hidden" id="idQuality" name="fQuality" style="width:300px" class="select2" />
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idRemark">Remark</label>
																					<div class="col-xs-12 col-sm-5">
																						<div class="clearfix">
																							<textarea class="input-xlarge" cols="50" name="fRemark" id="idRemark"></textarea>
																						</div>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idPhotoCost"><h4>Photo cost (<span class="icon-inr"></span>)</h4></label>
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right red">
																							<input type="text" id="idPhotoCost" name="fPhotoCost" class="width-100 spinner red costfield" style="font-size: large; " />
																						</span>
																					</div>
																				</div>
																			  </div>
																		</div>
																	</div>
																</div>
															
															</form>
															</div>
															
															<div class="row-fluid col-lg-5 col-md-4 col-sm-4 hidden-xs">
																<img src="${pageContext.request.contextPath}/assets/images/Picture2.png" class="img-responsive" alt="Responsive image">
															</div>
															
														</div>

														<div class="step-pane" id="step3">
															<h3 class="lighter block green">Enter frame detail</h3>
															<div class="row-fluid col-lg-7 col-md-8 col-sm-8 col-xs-12">
																
																<form class="form-horizontal" id="formFrame">
																	<input type="hidden" id="idFrameCounter" name="fFrameCounter" value="1"/>
																	<div class="tabbable">
																	<ul class="nav nav-tabs" id="myFrameTab">
																		<li id='myFrameTabLi' class="active">
																			<a data-toggle="tab" href="#frame1">
																				Frame 1
																			</a>
																		</li>
																		<a id="idAddFrame" class="btn btn-sm btn-primary"><span class="icon-plus"></span></a>
																	</ul>
																	
																	<div class="tab-content">
																		<div id="frame1" class="tab-pane in active">
																			<div>
																				<div class="form-group">
																					<label for="idFrameNumber" class="col-xs-12 col-sm-3 control-label no-padding-right">Frame Number</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right">
																							<input type="hidden" id="idFrameNumber" name="fFrameNumber" class="select2" />
																						</span>
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="idFrameSize" class="col-xs-12 col-sm-3 control-label no-padding-right">Frame Size</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right">
																							<input type="hidden" id="idFrameSize" name="fFrameSize" class="select2" />
																						</span>
																					</div>
																				</div>
																				
																				<div class="form-group">
																					<label for="idFrameType" class="col-xs-12 col-sm-3 control-label no-padding-right">Frame Quality/Type</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<label class="col-xs-6 col-sm-6"><input type="checkbox" name="fFrameDirect" id="idFrameTDirect" value="direct"/> Direct </label>
																						<label class="col-xs-6 col-sm-6"><input type="checkbox" name="fFrameMount" id="idFrameTMount" value="mount"/> Mount </label>
																						<label class="col-xs-6 col-sm-6"><input type="checkbox" name="fFrameRightMount" id="idFrameTRightMount" value="rightMount"/> Right Mount </label>
																						<label class="col-xs-6 col-sm-6"><input type="checkbox" name="fFrameLeftMount" id="idFrameTLeftMount" value="leftMount"/> Left Mount </label>
																						<label class="col-xs-6 col-sm-6"><input type="checkbox" name="fFrameGoldMount" id="idFrameTGoldMount" value="goldMount"/> Gold Mount </label>
																						<label class="col-xs-6 col-sm-6"><input type="checkbox" name="fFramePastting" id="idFrameTPastting" value="pastting"/> Pastting </label> 
																					</div>
																				</div>
																				
																				<!-- 
																				<div class="form-group">
																					<label for="idFrameType" class="col-xs-12 col-sm-3 control-label no-padding-right">Frame Quality/Type</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right">
																							<input type="hidden" id="idFrameType" name="fFrameType" class="select2" />
																						</span>
																					</div>
																				</div> -->
																				
																				<!-- <div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idFrameQuality">Frame Quality/Type</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<select id="idFrameQuality" name="fFrameQuality" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select>
																					</div>
																				</div> -->
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idFrameRemark">Remark</label>
																					<div class="col-xs-12 col-sm-5">
																						<div class="clearfix">
																							<textarea class="input-xlarge" cols="50" name="fFrameRemark" id="idFrameRemark"></textarea>
																						</div>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idFrameCost"><h4>Frame cost (<span class="icon-inr"></span>)</h4></label>
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right red">
																							<input type="text" id="idFrameCost" name="fFrameCost" class="costfield width-100 spinner red" style="font-size: large; " />
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	</div>
																</form>
															</div>
															
															<div class="row-fluid col-lg-5  col-md-4 col-sm-4 hidden-xs">
																<img src="${pageContext.request.contextPath}/assets/images/Picture1.png" class="img-responsive" alt="Responsive image">
																	
															</div>
															
														</div>

														<div class="step-pane" id="step4">
															<h3 class="lighter block green">Enter lamination detail</h3>
	
															<div class="row-fluid col-lg-7 col-md-8 col-sm-8 col-xs-12">
	
																<form class="form-horizontal" id="formLam">
																	<input type="hidden" id="idLamCounter" name="fLamCounter" value="1"/>
																	<div class="tabbable">
																	<ul class="nav nav-tabs" id="myLamTab">
																		<li id='myLamTabLi' class="active">
																			<a data-toggle="tab" href="#lam1">
																				Lamination 1
																			</a>
																		</li>
																		<a id="idAddLam" class="btn btn-sm btn-primary"><span class="icon-plus"></span></a>
																	</ul>
																	
																	<div class="tab-content">
																		<div id="lam1" class="tab-pane in active">
																			<div>
																				<div class="form-group">
																					<label for="idLamSize" class="col-xs-12 col-sm-3 control-label no-padding-right">Lamination Size</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right">
																							<input type="hidden" id="idLamSize" name="fLamSize" class="select2" />
																							<!-- <select id="idLamSize" name="fLamSize" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select> -->
																						</span>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idLamQuality">Lamination Quality/Type</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<input type="hidden" id="idLamQuality" name="fLamQuality" class="select2" />
																						<!-- <select id="idLamQuality" name="fLamQuality" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select> -->
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idLamRemark">Remark</label>
																					<div class="col-xs-12 col-sm-5">
																						<div class="clearfix">
																							<textarea class="input-xlarge" cols="50" name="fLamRemark" id="idLamRemark"></textarea>
																						</div>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idLamCost"><h4>Lamination cost (<span class="icon-inr"></span>)</h4></label>
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right red">
																							<input type="text" id="idLamCost" name="fLamCost" class="costfield width-100 spinner red" style="font-size: large; " />
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	</div>
																	<input type="hidden" name="fTotalAmount" id="idTotalAmount"/> 
																</form>
															</div>
															<div class="row-fluid col-lg-5 col-md-4 col-sm-4 hidden-xs">
																<img src="${pageContext.request.contextPath}/assets/images/Picture3.png" class="img-responsive" alt="Responsive image">
															</div>
															
														</div>

														<div class="step-pane" id="step5">
															<div class="center">
																<h3 class="green">Congrats!</h3>
																<div class="space-12"></div>
																<form class="form-horizontal" id="formFinal">
																	<div class="form-group">
																		<label for="idEstDeliveryDate" class="col-xs-6 col-sm-6 control-label no-padding-right">Estimated Delivery Date</label>

																		<div class="col-xs-6 col-sm-6">
																			<span class="input-icon input-icon-right pull-left">
																				<input type="text" id="idEstDeliveryDate" name="fEstDeliveryDate" class="width-100 date-picker" data-date-format="dd/mm/yyyy" />
																				<i class="icon-calendar black"></i>
																			</span>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="idAdvPaid" class="col-xs-6 col-sm-6 control-label no-padding-right">Advance Paid</label>

																		<div class="col-xs-6 col-sm-6">
																			<span class="input-icon input-icon-right pull-left">
																				<input type="text" class="blue" id="idAdvPaid" name="fAdvPaid"/>
																				<b class="icon-inr red"></b>
																			</span>
																		</div>
																	</div>
																</form>
																
																Your invoice is ready to print! Click generate to continue!
															</div>
														</div>
													</div>

													<hr />
													<div class="row-fluid wizard-actions">
														<h3 class="pull-left" style="margin:0 10px">Total : <span class="red" id="idTotaltext"><span class="icon-inr red"></span> 00.00 </span></h3>
														<button class="btn btn-sm btn-prev">
															<i class="icon-arrow-left"></i>
															Prev
														</button>

														<button class="btn  btn-sm btn-success btn-next" data-last="Generate Invoice">
															Next
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												</div><!-- /widget-main -->
											</div><!-- /widget-body -->
										</div>
									</div>
								</div>
						

	<script type="text/javascript">
	jQuery(function($) {
			var $validation = true;
			$('#fuelux-wizard').ace_wizard().on('change' , function(e, info){
				if(info.step == 1 && $validation) {
					if(!$('#customer-form').valid()) return false;
				}
			}).on('finished', function(e) {
				if(Number($("#idAdvPaid").val()) > Number($('#idTotalAmount').val()))
				{
					bootbox.dialog({
						message: "Advance payment cannot exceed total amout.", 
						title: "Validation Prompt",
						buttons: {
							"success" : {
								"label" : "OK",
								"className" : "btn-sm btn-primary"
							}
						}
					}); 
				}
				else{
					var frm = $(document.forms);
					//var hrefEle  = "/jsp/studio/invoiceprint.jsp";
					var URL = "invoiceaction.do?op=ADD&"+frm.serialize();
					var DATA = {};
					var embedInElement = "id_EmbedPage";
					async.munsi.ajaxCall(URL,DATA,embedInElement);
				}
			}).on('stepclick', function(e){
				//return false;//prevent clicking on steps
			});
			
			
			function callAllFunctions(){
				$('[data-rel=tooltip]').tooltip();

//----------------- Photo Size, Quality , Source
				
				/* $("input[id^='idPhotoSource'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=photo&key=size",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				}); */

				$("input[id^='idSize'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=photo&key=size",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});

				$("input[id^='idQuality'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=photo&key=quality",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});
				
//----------------- Frame Size, Quality and Numbers
				$("input[id^='idFrameSize'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=frame&key=size",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});

				$("input[id^='idFrameNumber'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=frame&key=frameNumber",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});
				
				$("input[id^='idFrameNumber'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=frame&key=frameNumber",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});

//----------------- Lamination Size, Quality and Numbers
				$("input[id^='idLamQuality'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=lamination&key=quality",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});
				
				$("input[id^='idLamSize'].select2").css('width','200px').select2({allowClear:true,ajax: {
			        dataType: "json",
			        url: "\commonaction.do?op=fetch&service=lamination&key=size",
			        results: function (data) {
			            return {results: data};
			        }
			    }})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});

//----------- SELECT2 -----------
				$("select.select2").css('width','200px').select2({allowClear:true})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				});

				//documentation : http://docs.jquery.com/Plugins/Validation/validate
				
				$.mask.definitions['~']='[+-]';
				//$('#idMobile').mask('(999) 999-9999');
			
				jQuery.validator.addMethod("phone", function (value, element) {
					return this.optional(element) || /^\(\d{3}\) \d{3}\-\d{4}( x\d{1,6})?$/.test(value);
				}, "Enter a valid phone number.");

				$('#customer-form').validate({
					errorElement: 'div',
					errorClass: 'help-block',
					focusInvalid: false,
					rules: {
						fEmail: {
							required: false,
							email:true
						},
						fMobile: {
							required: true,
							digits:true,
							minlength:10,
							maxlength:10
						}
					},
			
					messages: {
						fEmail: {
							required: "Please provide a valid email.",
							email: "Please provide a valid email."
						},
						fMobile: {
							required: "Please provide a mobile number.",
							minlength: "Please provide a valid mobile number.",
							maxlength: "Please provide a valid mobile number."
						}
					},
			
					invalidHandler: function (event, validator) { //display error alert on form submit   
						$('.alert-danger', $('.login-form')).show();
					},
			
					highlight: function (e) {
						$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
					},
			
					success: function (e) {
						$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
						$(e).remove();
					},
			
					errorPlacement: function (error, element) {
						if(element.is(':checkbox') || element.is(':radio')) {
							var controls = element.closest('div[class*="col-"]');
							if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
							else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
						}
						else if(element.is('.select2')) {
							error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
						}
						else if(element.is('.chosen-select')) {
							error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
						}
						else error.insertAfter(element.parent());
					},
			
					submitHandler: function (form) {
					},
					invalidHandler: function (form) {
					}
				});
				
				//spinner
				var spinner = $( ".spinner" ).spinner({
					create: function( event, ui ) {
						//add custom classes and icons
						$(this)
						.next().addClass('btn btn-success').html('<i class="icon-plus"></i>')
						.next().addClass('btn btn-danger').html('<i class="icon-minus"></i>');
						
						//larger buttons on touch devices
						if(ace.click_event == "tap") $(this).closest('.ui-spinner').addClass('ui-spinner-touch');
					},
					change: function( event, ui ) {
						$(".costfield").trigger("change");
					}
				});
				$( ".spinner" ).spinner("option", "min", 0);
				$( ".spinner,#idAdvPaid" ).keypress(function(evt) {
					evt = (evt) ? evt : window.event;
			   	    var charCode = (evt.which) ? evt.which : evt.keyCode;
			   	    if (charCode > 31 && (charCode < 46 || charCode > 57)) {
			   	        return false;
			   	    }
			   	    return true;
				});
				$('.date-picker').datepicker({autoclose:true,orientation: 'left'}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});

				$(".costfield").on('change keyup paste', function() {
					var toAm = 0;
					$(".costfield").each(function(){
						toAm = toAm + Number($(this).val());
					});
				 	$("#idTotalAmount").val(toAm);
				 	$("#idTotaltext").html("<span class='icon-inr red'></span> "+$("#idTotalAmount").val());
				});
				$(".closeLI").on('click',function(){
					   var closeLi = $(this).closest('li');
					   var idLi = closeLi.attr('id');
					   var numb = idLi.replace("idli","");
					   var closeContent = $("#photo"+numb);
					   clearForm($("#photo"+numb));
					   $("#idPhotoCost"+numb).trigger("change");

					   closeLi.remove();
					   closeContent.remove();
					   $("#myPhotoTab li:last-child a").click();
				});
				$(".closeLIFrame").on('click',function(){
					   var closeLi = $(this).closest('li');
					   var idLi = closeLi.attr('id');
					   var numb = idLi.replace("idliFrame","");
					   var closeContent = $("#frame"+numb);
					   clearForm($("#frame"+numb));
					   $("#idFrameCost"+numb).trigger("change");
					   
					   closeLi.remove();
					   closeContent.remove();
					   $("#myFrameTab li:last-child a").click();
				});

				$(".closeLILam").on('click',function(){
					   var closeLi = $(this).closest('li');
					   var idLi = closeLi.attr('id');
					   var numb = idLi.replace("idliLam","");
					   var closeContent = $("#lam"+numb);
					   clearForm($("#lam"+numb));
					   $("#idLamCost"+numb).trigger("change");

					   closeLi.remove();
					   closeContent.remove();
					   $("#myLamTab li:last-child a").click();
				});

				$("input[id^='idQuality'],input[id^='idSize']").on('change keyup paste', function() {
					var urlPath = "commonaction.do?op=fetch&service=photo&get=price";
					var tabNo = $(this).closest("div[id^='photo']").data('tabNo');
					if(tabNo){
						
					}else
						tabNo = '';
					
					$.ajax({
						type: "POST",
						url: urlPath,
						data:{quality:$("#idQuality"+tabNo).val(),size:$("#idSize"+tabNo).val(),type:'photo'},
						dataType: 'json'
						})
						.done(function( data ) {
							if(data.length > 0)
								$("#idPhotoCost"+tabNo).val(data[0].id);
							else
								$("#idPhotoCost"+tabNo).val("");
							$("#idPhotoCost"+tabNo).trigger("change");
						})
						.fail(function() {
							console.error( "error in fetching data from server....." );
						});
					
				});

				$("input[id^='idFrameNumber'],input[id^='idFrameSize']").on('change keyup paste', function() {
					var urlPath = "commonaction.do?op=fetch&service=frame&get=price";
					var tabNo = $(this).closest("div[id^='frame']").data('tabNo');

					$(this).closest("div[id^='frame']").find('input:checkbox').removeAttr('checked');
					
					if(tabNo){
						
					}else
						tabNo = '';
					$.ajax({
						type: "POST",
						url: urlPath,
						data:{frameNumber:$("#idFrameNumber"+tabNo).val(),size:$("#idFrameSize"+tabNo).val(),type:'frame'},
						dataType: 'json'
						})
						.done(function( data ) {
							if(data.length > 0)
								$("#idFrameCost"+tabNo).val(data[0].id);
							else
								$("#idFrameCost"+tabNo).val("");
							$("#idFrameCost"+tabNo).trigger("change");
						})
						.fail(function() {
							console.error( "error in fetching data from server....." );
						});	
				});
				

				$("input[id^='idFrameT']").click(function() {
					
					var $this = $(this);
				 
					var urlPath = "commonaction.do?op=fetch&service=frame&get="+ $this.val();
					var tabNo = $(this).closest("div[id^='frame']").data('tabNo');
					
					if(tabNo){
						
					}else
						tabNo = '';
					
					$.ajax({
						type: "POST",
						url: urlPath,
						data:{frameNumber:$("#idFrameNumber"+tabNo).val(),size:$("#idFrameSize"+tabNo).val(),type:'frame'},
						dataType: 'json'
						})
						.done(function( data ) {
							if(data.length > 0){
								if ($this.is(':checked')) {
									$("#idFrameCost"+tabNo).val(Number($("#idFrameCost"+tabNo).val()) + Number(data[0].id));
							    } else {
							    	$("#idFrameCost"+tabNo).val(Number($("#idFrameCost"+tabNo).val()) - Number(data[0].id));
							    }
							}
							else
								$("#idFrameCost"+tabNo).val("");
							
							$("#idFrameCost"+tabNo).trigger("change");
						})
						.fail(function() {
							console.error( "error in fetching data from server....." );
						});	
				});
				
				$("input[id^='idLamQuality'],input[id^='idLamSize']").on('change keyup paste', function() {
					var urlPath = "commonaction.do?op=fetch&service=lamination&get=price";
					
					var tabNo = $(this).closest("div[id^='lam']").data('tabNo');
					
					if(tabNo){
						
					}else
						tabNo = '';
					$.ajax({
						type: "POST",
						url: urlPath,
						data:{quality:$("#idLamQuality"+tabNo).val(),size:$("#idLamSize"+tabNo).val(),type:'lamination'},
						dataType: 'json'
						})
						.done(function( data ) {
							if(data.length > 0)
								$("#idLamCost"+tabNo).val(data[0].id);
							else
								$("#idLamCost"+tabNo).val("");
							$("#idLamCost"+tabNo).trigger("change");
						})
						.fail(function() {
							console.error( "error in fetching data from server....." );
						});
					
				});
			}
			
			// JS for Photo Tabs
			var c = 1;
			var cloned;
			var clonedHeader;
			
			$("#idAddPhoto").on('click',function(){

			   $( ".spinner" ).spinner( "destroy" );
			   $( "#idPhotoSource.select2").select2("destroy");
			   $( "#idSize.select2").select2("destroy");
			   $( "#idQuality.select2").select2("destroy");
			   cloned = $('#photo1');
			   clonedHeader = $("#myPhotoTabLi");
			   $("#myPhotoTabLi").clone().removeClass('active').attr('id','idli'+(c+1)).insertBefore("#idAddPhoto").find('a').attr({'href':'#photo'+(c+1)}).html('Photo '+ (c+1) + ' <a class="badge badge-grey closeLI" id="idClose'+(c+1)+'" style="padding: 0px 4px 1px 3px; line-height: 14px;"><b>x</b></a>');
			   $("#photo1").clone().attr('id', 'photo'+(++c) ).insertAfter( cloned );
			   
			   $("#photo"+c).data("tabNo",c);
			   
			   $("#idli"+(c)+ " a").click();
			   $("#photo"+c).find("[id]").each(function() {
				   var eleID = $( this ).attr('id');
				   var eleName = $( this ).attr('name');
				   $( this ).attr({'id':eleID+c,'name':eleName+c});
			   });
			   
			   $("#idPhotoCounter").val(c);
			   clearForm($("#photo"+c));
			   callAllFunctions();
			});	

			// JS for Frame Tabs
			var f = 1;

			$("#idAddFrame").on('click',function(){
				$( ".spinner" ).spinner( "destroy" );
				$( "#idFrameNumber.select2").select2("destroy");
				$( "#idFrameType.select2").select2("destroy");
				$( "#idFrameSize.select2").select2("destroy");

			   cloned = $('#frame1');
			   clonedHeader = $("#myFrameTabLi");

			   $("#myFrameTabLi").clone().removeClass('active').attr('id','idliFrame'+(f+1)).insertBefore("#idAddFrame").find('a').attr({'href':'#frame'+(f+1)}).html('Frame '+ (f+1) + ' <a class="badge badge-grey closeLIFrame" id="idCloseFrame'+(f+1)+'" style="padding: 0px 4px 1px 3px; line-height: 14px;"><b>x</b></a>');
			   $("#frame1").clone().attr('id', 'frame'+(++f) ).insertAfter( cloned );

			   $("#frame"+f).data("tabNo",f);

			   $("#idliFrame"+(f)+ " a").click();
			   $("#frame"+f).find("[id]").each(function() {
				   var eleID = $( this ).attr('id');
				   var eleName = $( this ).attr('name');
				   $( this ).attr({'id':eleID+f,'name':eleName+f});
			   });
			   $("#idFrameCounter").val(f);
			   clearForm($("#frame"+f));
			   callAllFunctions();
			});

			// JS for Lamination Tabs
			var l = 1;

			$("#idAddLam").on('click',function(){
			   
				var bkpLamSize = $( "#idLamSize").select2("val");
				var bkpLamQuality = $( "#idLamQuality").select2("val");
				
				$( ".spinner" ).spinner( "destroy" );
				$( "#idLamSize.select2").select2("destroy");
				$( "#idLamQuality.select2").select2("destroy");
				   
				cloned = $('#lam1');
			   clonedHeader = $("#myLamTabLi");
			   $("#myLamTabLi").clone().removeClass('active').attr('id','idliLam'+(l+1)).insertBefore("#idAddLam").find('a').attr({'href':'#lam'+(l+1)}).html('Lamination '+ (l+1) + ' <a class="badge badge-grey closeLILam" id="idCloseLam'+(l+1)+'" style="padding: 0px 4px 1px 3px; line-height: 14px;"><b>x</b></a>');
			   $("#lam1").clone().attr('id', 'lam'+(++l) ).insertAfter( cloned );

			   $("#lam"+l).data("tabNo",l);

			   $("#idliLam"+(l)+ " a").click();
			   $("#lam"+l).find("[id]").each(function() {
				   var eleID = $( this ).attr('id');
				   var eleName = $( this ).attr('name');
				   $( this ).attr({'id':eleID+l,'name':eleName+l});
			   });
			   $("#idLamCounter").val(l);
			   clearForm($("#lam"+l));
			   callAllFunctions();
			  /*  $( "#idLamSize.select2").select2("open");
			   $( "#idLamSize.select2").select2("close");
			   $( "#idLamSize.select2").select2("val", bkpLamSize);
			   $( "#idLamQuality.select2").select2("open");
			   $( "#idLamQuality.select2").select2("close");
			   $( "#idLamQuality.select2").select2("val", bkpLamQuality); */
			});

			function clearForm(pElement)
			{
				pElement.find(':input').not(':button, :submit, :reset, :hidden, :checkbox, :radio').val('');
				pElement.find(':checkbox, :radio').prop('checked', false);
			}

			callAllFunctions();
			
			
		     //------------ AutoComplete Customer Form--------------
		     
		     var objJsonCustomer= '<%=SantoshUtil.getIdLabelJSON(DBCollectionEnum.CUSTOMER, "_id", "name", "") %>';
		     
		     objJsonCustomer = JSON.parse(objJsonCustomer.replace(/"_id"/g,"\"id\"").replace(/"name"/g,"\"label\""));
		     
		     $("#idMobile").autocomplete({
		    	 minLength: 0,
			     source: function( request, response ) {
			          var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( request.term ), "i" );
			          response( $.grep( objJsonCustomer, function( item ){
			              return matcher.test( item.id );
			          }) );
			      },
			     focus: function( event, ui ) {
			     $( "#idMobile" ).val( ui.item.id );
			     return false;
			     },
			     select: function( event, ui ) {
			     $( "#idMobile" ).val( ui.item.id );
			     $( "#idName" ).val( ui.item.label );
			     
			     $.ajax({
						type: "POST",
						url: "commonaction.do?op=fetch&service=customer&get=all",
						data:{cust_mobile:$("#idMobile").val()},
						dataType: 'json'
						})
						.done(function( data ) {
							$("#idEmail").val('');
							$("#idAddress").val('');
							$("#idDOB").val('');
							$("#idMAnniversary").val('');
							$("#idEmail").val(data[0].emailId);
							$("#idAddress").val(data[0].address);
							$("#idDOB").val(data[0].dob);
							$("#idMAnniversary").val(data[0].marriageDate);
						})
						.fail(function() {
							console.error( "error in fetching data from server....." );
						});
			     
			     return false;
			     }
		     })
		     .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		     return $( "<li>" )
		     .append( "<a>" + item.id + "<span class='badge badge-primary pull-right'>"+ item.label +"</span>"+ "</a>" )
		     .appendTo( ul );
		     };
		     
	});

	</script>