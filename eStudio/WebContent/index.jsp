<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.async.util.Config"%>
<%@page import="com.async.util.PortalUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	Boolean isValid = PortalUtil.isValidSession(request.getSession());
	if (isValid) {
		response.sendRedirect("hometmp.jsp");
		return;
	}
%>    
<html>
<head>
	<jsp:include page='/jsp/template/cssandjs.jsp' />
	
	<meta name="description" content="User login page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<!-- basic styles -->

	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

	<!--[if IE 7]>
	  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
	<![endif]-->

	<!-- page specific plugin styles -->

	<!-- fonts -->

	<link rel="stylesheet" href="assets/css/ace-fonts.css" />

	<!-- ace styles -->

	<link rel="stylesheet" href="assets/css/ace.min.css" />
	<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- inline styles related to this page -->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">

		.login-container{
			height:400px;
			position: absolute;
			top:0;
			bottom: 0;
			left: 0;
			right: 0;
			margin: auto;
		}
		
		.footer
		{
			position: fixed;
			height: 70px;
			bottom:0px;
			width:100%;
			padding-top:5px;
			color:#000;
		}
	</style>
</head>

<body class="login-layout" style='background-color:lightgrey !important'  onload="async.util.noBack();" onpageshow="if (event.persisted) async.util.noBack();" onunload="">
<div class="center well" Style="padding-top:5px; padding-bottom:5px ">
	<div class="col-sm-3">
		<h1><span class="pull-left" style="font-stretch: wider;">e-studi<b class="icon-camera blue"></b></span></h1>
	</div>
	<div class="col-sm-6">
		<span class="red" style="font-family: cursive; font-size: 25px"><%=Config.getProperty("studio.name") %></span>
	</div>
	<div class="">
	<h1>  </h1>
	</div>
</div>
					<div class="login-container">
					 <% String srvmsg = (String)(session.getAttribute("SERVER_MESSAGE")!=null?session.getAttribute("SERVER_MESSAGE"):"");
						if(srvmsg != null && !srvmsg.trim().equalsIgnoreCase("")){ %>
							<div class="space-6"></div>
							<div class="alert alert-block alert-danger">
								
								<i class="icon-call red"></i><b><%= srvmsg%></b> 
							</div>
							<%} %>
						<div class="position-relative">
							<div id="login-box" class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i>
											Please Enter Your Credentials
										</h4>

										<div class="space-6"></div>

										<form action="login.set" method="post" >
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="text" name="userName" class="form-control" placeholder="Username" />
														<i class="icon-user"></i>
													</span>
												</label>

												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input name='password' type="password" class="form-control" placeholder="Password" />
														<i class="icon-lock"></i>
													</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<!-- <label class="inline">
														<input type="checkbox" class="ace" />
														<span class="lbl"> Remember Me</span>
													</label> -->

													<input type="submit" class="width-35 pull-right btn btn-sm btn-primary" value="Login"/>
														
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

										
									</div><!-- /widget-main -->
								<!-- <div class="toolbar clearfix">
										<div>
											<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
												<i class="icon-arrow-left"></i>
												I forgot my password
											</a>
										</div>

										<div>
											<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
												I want to register
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div> -->
								</div><!-- /widget-body -->
							</div><!-- /login-box -->
						</div><!-- /position-relative -->
					</div>
	<!-- basic scripts -->
<footer class="footer" style="background-color:#c2c2c2"> 
	<div class="col-xs-3 col-sm-1"> 
		
		<img width="110px" src="./custom/images/isdc_deliverable_transbg.png" style="margin-top:8px; margin-left:10px; background-color:green" class="img-responsive"/>
		
	</div>
	<div class="col-xs-6 col-sm-3">
	<font size="+1px"> Innovatory Solutions Designing & Consulting</font>
	
	</div>
	<div class="pull-right">
		<div class="well" style="background-color: #BABABA">
		Support No. :  +91-9969938301
		| Customer Service - <b >customer.support@isdcgroup.in</b> 
		| Sales - <b>sales@isdcgroup.in</b>
		</div>
	</div>
</footer>
	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

	<script type="text/javascript">
		if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		function show_box(id) {
		 jQuery('.widget-box.visible').removeClass('visible');
		 jQuery('#'+id).addClass('visible');
		}
	</script>
</body>