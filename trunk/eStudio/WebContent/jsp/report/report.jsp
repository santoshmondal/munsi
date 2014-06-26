<%@page import="com.async.util.Config"%>
<%@page import="java.util.List"%>
<%@page import="com.estudio.pojo.LaminationDetails"%>
<%@page import="com.estudio.pojo.FrameDetails"%>
<%@page import="com.estudio.pojo.PhotoDetails"%>
<%@page import="com.estudio.pojo.Invoice"%>
<%@page import="com.async.util.Constants.DBCollectionEnum"%>
<%@page import="com.async.util.CommonUtil"%>
<%@page import="com.async.util.Constants"%>
	
	<div class="container ">

		<div class="row well">
			Enter the <b>Strat Date</b> and <b>End Date</b> Click on <b>Download</b> to download the report in PDF file.
		</div>
		<div class="widget-box col-sm-6 col-lg-4">
			<div class="widget-header">
				<h4>Generate Report</h4>
			</div>
			
			<div class="widget-body">
				<div class="widget-body-inner" style="display: block;">
				<form action="commonaction.do?op=REPORT_DATE" method="post">
					<div class="widget-main">
						<label for="id-date-picker-1">Start Date</label>
						<div class="row">
							<div class="col-xs-8 col-sm-11">
								<div class="input-group">
									<input class="form-control date-picker"  id="idStartDateRep" name="fStartDateRep" type="text" data-date-format="dd-mm-yyyy">
									<span class="input-group-addon">
										<i class="icon-calendar bigger-110"></i>
									</span>
								</div>
							</div>
						</div>

						<hr>
						<label for="id-date-range-picker-1">End Date</label>
						
						<div class="row">
							<div class="col-xs-8 col-sm-11">
								<div class="input-group">
									<input class="form-control date-picker" id="idEndDateRep" name="fEndDateRep" type="text" data-date-format="dd-mm-yyyy">
									<span class="input-group-addon">
										<i class="icon-calendar bigger-110"></i>
									</span>
								</div>
							</div>
						</div>
						
						<hr>
						
						<div class="row">
							<div class="col-xs-8 col-sm-11">
								<div class="input-group">
									<input class="btn btn-sm btn-success" type="submit" value="Download">
								</div>
							</div>
						</div>
						
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	jQuery(function($) {
			$('[data-rel=tooltip]').tooltip();
			
			$('.date-picker').datepicker({autoclose:true,orientation: 'left'}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
			
			
			$("#idSaveInvoice").click(function(e) {
                
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
            });
			
			//$("#idAdvPaid").on('change keyup paste', function() {
				//var toAm = $("#idTotalAmount").val();
			 	//$("#idTotalAmount").val(Number($("#idPhotoCost").val()) + Number($("#idLamCost").val()) + Number($("#idFrameCost").val()));
			 	$("#idBalText").html("<span class='icon-inr'></span> "+(Number($("#idTotAmt").html()) - Number($("#idAdvPaid").html())));
			//});
				<% String fail_msg = (String)request.getAttribute("SERVER_SMS_FAILED");
				if(fail_msg != null) { 
				%>
			 	bootbox.dialog({
					message: "<%=fail_msg %>", 
					title: "<span class='red'>Retry Prompt</span>",
					buttons: {
						success : {
							label : "Try Again",
							className: "btn-sm btn-primary",
							 callback: function() {
								 		var URL = "commonaction.do?op=RESEND_SMS";
										var DATA = {};
										var embedInElement = "id_EmbedPage";
										async.munsi.ajaxCall(URL,DATA,embedInElement);
							 }
						},
					    danger: {
					      label: "Cancel",
					      className: "btn-sm btn-danger"
					    }
					}
				});
			 	<%
				}
			 	%>
	});
	</script>