
<div class="page-header row">
	<div class="col-xs-12">
			<h1>
				Appointment<small> <i class="icon-double-angle-right"></i>
					schedule the appointment
				</small>
			</h1>
	</div>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
			<div class="container col-lg-12 col-xs-12 col-md-12 col-sm-12">
				<div class="space-10"></div>
				<form class="form-inline" role="form">
					<div class="form-group col-lg-10 col-md-12 col-sm-12">
						<label class="col-xs-5 col-sm-5 col-md-2 col-lg-2 control-label no-padding-right" for="id_date"> Assign Date & Time </label>

						<div class="col-sm-6 col-xs-6 col-lg-6">
							<span class="input-group input-icon">
								<input class="form-control date-picker" id="id_date" type="text" data-date-format="dd-mm-yyyy" />
								<i class="icon-calendar bigger-110"></i>
							</span>
							<span class="input-icon input-icon-right bootstrap-timepicker">
								<input id="timepicker1" type="text" class="form-control" />
								<i class="icon-time bigger-110"></i>
							</span>
						</div>
						<button class="btn btn-sm btn-success" type="button">
							<i class="icon-ok bigger-110"></i>
							Done
						</button>

						<button class="btn btn-sm" type="reset">
							<i class="icon-undo bigger-110"></i>
							Reset
						</button>
					</div>
					
				</form><br/>
				<div class="space-6"></div>
				<div class="hr hr-24"></div>
				<div class="space-2"></div>
			</div>
			<div class="clearfix col-lg-9 col-xs-12 col-md-12 col-sm-12">
						<div class="grid3">
							<div><span class="icon-user bigger-300"></span> <span class="bigger-275 blue pull-right">Tom Smith</span></div><br/>
							<div> <i class="icon-phone bigger-200"></i> <span class="bigger-175 green pull-right"> 8655845191</span></div><br/>
							<div> <i class="icon-envelope bigger-200"></i>  <span class="bigger-175 orange pull-right"> tom@abc.com</span></div><br/>
							<div class="bigger-175 blue pull-right"><button type="button" class="btn btn-link" data-rel="tooltip" title="Click to view detail">More details</button></div>
						</div>

						<div class="grid2">
							<div><span class="bigger-150 gray">Nissan</span> Model</div><br/>
							<div><span class="bigger-150 gray">X6</span> Variant</div><br/>
							<div><span class="bigger-150 gray">XCS54455566S</span> VIN</div><br/>
							<div><span class="bigger-150 gray">MH 02 AS 2124</span> Reg No.</div> <br/>
							
						</div>
			</div>
			
			
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<script type="text/javascript">
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	
	$('#timepicker1').timepicker({
		minuteStep: 1,
		showSeconds: true,
		showMeridian: true
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('[data-rel=tooltip]').tooltip({container:'body'});
</script>