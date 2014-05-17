<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page
	import="java.net.HttpURLConnection,java.net.MalformedURLException,java.io.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>JSP Page</title>
</head>
<%!public void callURL(String strURL) {
		System.out.println(" URL is :" + strURL);
		try {
			java.net.URL obj = new java.net.URL(strURL);
			HttpURLConnection httpReq = (HttpURLConnection) obj
					.openConnection();
			httpReq.setDoOutput(true);
			httpReq.setInstanceFollowRedirects(true);
			httpReq.setRequestMethod("GET");
			String iStatus = httpReq.getResponseMessage();
			System.out.println("iStatus: " + iStatus);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}%>
<body>
	<%
		String strMsg = java.net.URLEncoder.encode("This is a API msg 123",
				"UTF-8");
		String strSenderId = java.net.URLEncoder
				.encode("TEST SMS", "UTF-8");
		//String strMsg=java.net.URLEncoder.encode("This is a test from mVaayoo API", "UTF-8");
		callURL("http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=padiyodi@gmail.com:isdc@1234&senderID="
				+ strSenderId
				+ "&receipientno=9819488395&msgtxt="
				+ strMsg
				+ "&state=4");
	%>
</body>
</html>