<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
var webSocket = null;
var url = '';

if (window.location.protocol == 'http:') {
	url  = 'ws://' + window.location.host + '/websocket/alert';
}
else{
	url = 'wss://' + window.location.host + '/websocket/alert';
}
      
if ('WebSocket' in window) {
	webSocket = new WebSocket(url);
} else if ('MozWebSocket' in window) {
	webSocket = new MozWebSocket(url);
} 
else {
	console.log("Info: WebSocket WebSocket is not supported by this browser");
}

webSocket.onopen = function () {
	console.log('Info: WebSocket connection opened.');
};

webSocket.onclose = function () {
	console.log('Info: WebSocket closed.');
};

webSocket.onmessage = function (message) {
	printMsg(message.data);
};
      
function sendMessage(event)
{
	/*
	if (event.keyCode != 13) {
      return;   
    }
	
    var message = document.getElementById('inputField').value;
    if (message != '') {
    	webSocket.send(message);
        document.getElementById('inputField').value = '';
    }
    */
};


function printMsg(message)
{
	var console1 = document.getElementById('alertDiv');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.innerHTML = message;
	console1.appendChild(p);
	while (console1.childNodes.length > 25) {
	    console1.removeChild(console1.firstChild);
	}
	console1.scrollTop = console1.scrollHeight;
};
</script>

<div id="alertDiv"></div>

</body>
</html>