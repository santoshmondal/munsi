
<%@page import="com.async.util.Constants.DBCollectionEnum"%>
<%@page import="com.async.util.CommonUtil"%>
<%@page import="com.async.util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<!-- <div class="page-header">
							
							<h1>New Invoice
								
							</h1>
						</div>/.page-header -->

						<div class="row-fluid">
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
																<form class="form-horizontal" id="sample-form">
																	
																	<div class="form-group">
																		<label for="idName" class="col-xs-12 col-sm-3 control-label no-padding-right">Name</label>
	
																		<div class="col-xs-12 col-sm-5">
																			<span class="block input-icon input-icon-right">
																				<input type="text" id="idName" name="fName" class="width-100" />
																				<i class="icon-user blue"></i>
																			</span>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="idMobile" class="col-xs-12 col-sm-3 control-label no-padding-right">Mobile</label>
	
																		<div class="col-xs-12 col-sm-5">
																			<span class="block input-icon input-icon-right">
																				<input type="text" id="idMobile" name="fMobile" class="width-100" />
																				<i class="icon-phone green"></i>
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
																						<select id="idSize" name="fSize" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idQuality">Photo Quality</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<select id="idQuality" name="fQuality" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select>
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
																					<label for="idFrameNumber" class="col-xs-12 col-sm-3 control-label no-padding-right">Frame Quality/Type</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right">
																							<select id="idFrameNumber" name="fFrameNumber" class="select2 width-100" data-placeholder="Click to Choose...">
																								<option value="">&nbsp;</option>
																								<option value="4x6">4x6</option>
																								<option value="8x12">8x12</option>
																							</select>
																						</span>
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="idFrameSize" class="col-xs-12 col-sm-3 control-label no-padding-right">Frame Size</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<span class="block input-icon input-icon-right">
																							<select id="idFrameSize" name="fFrameSize" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select>
																						</span>
																					</div>
																				</div>
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
																							<select id="idLamSize" name="fLamSize" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select>
																						</span>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idLamQuality">Lamination Quality/Type</label>
				
																					<div class="col-xs-12 col-sm-5">
																						<select id="idLamQuality" name="fLamQuality" class="select2 width-100" data-placeholder="Click to Choose...">
																							<option value="">&nbsp;</option>
																							<option value="4x6">4x6</option>
																							<option value="8x12">8x12</option>
																							<option value="Landsacpe">Landscape</option>
																						</select>
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
			var $validation = false;
			$('#fuelux-wizard').ace_wizard().on('change' , function(e, info){
				if(info.step == 1 && $validation) {
					if(!$('#validation-form').valid()) return false;
				}
			}).on('finished', function(e) {
				/* bootbox.dialog({
					message: "Thank you! Your information was successfully saved!", 
					buttons: {
						"success" : {
							"label" : "OK",
							"className" : "btn-sm btn-primary"
						}
					}
				}); */
				var frm = $(document.forms);
				//console.log(JSON.stringify(frm.serialize()));
				var hrefEle  = "/jsp/studio/invoiceprint.jsp";
				var URL = "invoiceaction.do?op=ADD&"+frm.serialize();
				var DATA = {};
				var embedInElement = "id_EmbedPage";
				async.munsi.ajaxCall(URL,DATA,embedInElement);
	
			}).on('stepclick', function(e){
				//return false;//prevent clicking on steps
			});
			
			function callAllFunctions(){
				$('[data-rel=tooltip]').tooltip();
				
				$("select.select2").css('width','200px').select2({allowClear:true})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				}); 
			
				
				//documentation : http://docs.jquery.com/Plugins/Validation/validate
				
				
				$.mask.definitions['~']='[+-]';
				$('#phone').mask('(999) 999-9999');
			
				jQuery.validator.addMethod("phone", function (value, element) {
					return this.optional(element) || /^\(\d{3}\) \d{3}\-\d{4}( x\d{1,6})?$/.test(value);
				}, "Enter a valid phone number.");
			
				$('#validation-form').validate({
					errorElement: 'div',
					errorClass: 'help-block',
					focusInvalid: false,
					rules: {
						email: {
							required: true,
							email:true
						},
						password: {
							required: true,
							minlength: 5
						},
						password2: {
							required: true,
							minlength: 5,
							equalTo: "#password"
						},
						name: {
							required: true
						},
						phone: {
							required: true,
							phone: 'required'
						},
						url: {
							required: true,
							url: true
						},
						comment: {
							required: true
						},
						state: {
							required: true
						},
						platform: {
							required: true
						},
						subscription: {
							required: true
						},
						gender: 'required',
						agree: 'required'
					},
			
					messages: {
						email: {
							required: "Please provide a valid email.",
							email: "Please provide a valid email."
						},
						password: {
							required: "Please specify a password.",
							minlength: "Please specify a secure password."
						},
						subscription: "Please choose at least one option",
						gender: "Please choose gender",
						agree: "Please accept our policy"
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
						.next().addClass('btn btn-danger').html('<i class="icon-minus"></i>')
						
						//larger buttons on touch devices
						if(ace.click_event == "tap") $(this).closest('.ui-spinner').addClass('ui-spinner-touch');
					}
				});
				$('.date-picker').datepicker({autoclose:true,orientation: 'left'}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				$(".costfield").on('change keyup paste', function() {
					var toAm = 0;
					$(".costfield").each(function(){
						toAm = toAm + Number($(this).val());
					});
					//var toAm = $("#idTotalAmount").val();
				 	$("#idTotalAmount").val(toAm);
				 	$("#idTotaltext").html("<span class='icon-inr red'></span> "+$("#idTotalAmount").val());
				});
				$(".closeLI").on('click',function(){
					   var closeLi = $(this).closest('li');
					   var idLi = closeLi.attr('id');
					   var numb = idLi.replace("idli","");
					   var closeContent = $("#photo"+numb);
					   closeLi.remove();
					   closeContent.remove();
					   $("#myPhotoTab li:last-child a").click();
				});
				$(".closeLIFrame").on('click',function(){
					   var closeLi = $(this).closest('li');
					   var idLi = closeLi.attr('id');
					   var numb = idLi.replace("idliFrame","");
					   var closeContent = $("#frame"+numb);
					   closeLi.remove();
					   closeContent.remove();
					   $("#myFrameTab li:last-child a").click();
				});

				$(".closeLILam").on('click',function(){
					   var closeLi = $(this).closest('li');
					   var idLi = closeLi.attr('id');
					   var numb = idLi.replace("idliLam","");
					   var closeContent = $("#lam"+numb);
					   closeLi.remove();
					   closeContent.remove();
					   $("#myLamTab li:last-child a").click();
				});
			}
			
			// JS for Photo Tabs
			var c = 1;
			var cloned;
			var clonedHeader;
			
			$("#idAddPhoto").on('click',function(){
			   cloned = $('#photo1');
			   clonedHeader = $("#myPhotoTabLi");
			   $( ".spinner" ).spinner( "destroy" );
			   $("#myPhotoTabLi").clone().removeClass('active').attr('id','idli'+(c+1)).insertBefore("#idAddPhoto").find('a').attr({'href':'#photo'+(c+1)}).html('Photo '+ (c+1) + ' <a class="badge badge-grey closeLI" id="idClose'+(c+1)+'" style="padding: 0px 4px 1px 3px; line-height: 14px;"><b>x</b></a>');
			   $("#photo1").clone().attr('id', 'photo'+(++c) ).insertAfter( cloned );

			   $("#idli"+(c)+ " a").click();
			   $("#photo"+c).find("[id*=s2id_]").remove();
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
			   cloned = $('#frame1');
			   clonedHeader = $("#myFrameTabLi");
			   $( ".spinner" ).spinner( "destroy" );
			   $("#myFrameTabLi").clone().removeClass('active').attr('id','idliFrame'+(f+1)).insertBefore("#idAddFrame").find('a').attr({'href':'#frame'+(f+1)}).html('Frame '+ (f+1) + ' <a class="badge badge-grey closeLIFrame" id="idCloseFrame'+(f+1)+'" style="padding: 0px 4px 1px 3px; line-height: 14px;"><b>x</b></a>');
			   $("#frame1").clone().attr('id', 'frame'+(++f) ).insertAfter( cloned );

			   $("#idliFrame"+(f)+ " a").click();
			   $("#frame"+f).find("[id*=s2id_]").remove();
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
			   cloned = $('#lam1');
			   clonedHeader = $("#myLamTabLi");
			   $( ".spinner" ).spinner( "destroy" );
			   $("#myLamTabLi").clone().removeClass('active').attr('id','idliLam'+(l+1)).insertBefore("#idAddLam").find('a').attr({'href':'#lam'+(l+1)}).html('Lamination '+ (l+1) + ' <a class="badge badge-grey closeLILam" id="idCloseLam'+(l+1)+'" style="padding: 0px 4px 1px 3px; line-height: 14px;"><b>x</b></a>');
			   $("#lam1").clone().attr('id', 'lam'+(++l) ).insertAfter( cloned );

			   $("#idliLam"+(l)+ " a").click();
			   $("#lam"+l).find("[id*=s2id_]").remove();
			   $("#lam"+l).find("[id]").each(function() {
				   var eleID = $( this ).attr('id');
				   var eleName = $( this ).attr('name');
				   $( this ).attr({'id':eleID+l,'name':eleName+l});
			   });
			   $("#idLamCounter").val(l);
			   clearForm($("#lam"+l));
			   callAllFunctions();
			});

			function clearForm(pElement)
			{
				pElement.find(':input').not(':button, :submit, :reset, :hidden, :checkbox, :radio').val('');
				pElement.find(':checkbox, :radio').prop('checked', false);
			}

			callAllFunctions();
	});
	</script>