<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="portal/css/style.css">
<link rel="stylesheet" href="portal/bootstrap-3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="portal/bootstrap-3.1.1/css/bootstrap-theme.min.css">
<script src="portal/js/jquery-1.9.1.js"></script>
<script src="portal/bootstrap-3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top:50px;"> <!-- this is CLOSED into footer -->

<div id="message">
	<core:if test="${ERROR_MESSAGE ne null and fn:length( fn:trim(ERROR_MESSAGE) ) > 0}">
		<div class="alert alert-danger" role="alert">
			<core:out value="${ERROR_MESSAGE}"/>
		</div>
		<core:remove var="ERROR_MESSAGE" scope="session"/>
	</core:if>
	<core:if test="${INFO_MESSAGE ne null and fn:length( fn:trim(INFO_MESSAGE) ) > 0}">
		<div class="alert alert-danger" role="alert">
			<core:out value="${INFO_MESSAGE}"/>
		</div>
		<core:remove var="INFO_MESSAGE" scope="session"/>
	</core:if>
</div>
