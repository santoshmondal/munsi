<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<core:if test="${ USER_NAME eq null or fn:length(fn:trim(USER_NAME)) eq 0 }">
	<jsp:forward page="/login"/>
</core:if>

<jsp:include page="/header"/>

<div id="message" style="display:none;">
	<div class="alert alert-info" role="alert">
		Dear <b><core:out value="${USER_NAME}"/></b>, Welcome to chat room!
	</div>
</div>

<div id="chat_room" style="width:500px;">
	
	<div class="input-group">
      <input id="input_msg" type="text" class="form-control">
      <span class="input-group-btn">
        <button id="send_btn" class="btn btn-primary" type="button">Send</button>
      </span>
    </div>
    
	<div id="message_box" class="message_box">
    
    </div>
    
    <div class="form-group" style="margin-top:10px;">
		<button id="exit_btn" type="button" class="btn btn-danger">Exit from Chat Room</button>
   	</div>
	
    
</div>
<script type="text/javascript">
// ===== open WebSocket ========= //
var url = 'ws://' + window.location.host + '/WebSocketExample/chat1';
// url = 'wss://' + window.location.host + '/WebSocketExample/chat';

var webSocket = null;
if ('WebSocket' in window)
{
	webSocket = new WebSocket(url);
}
else if ('MozWebSocket' in window)
{
	webSocket = new MozWebSocket(url);
} 
else
{
	$("#message").html('<div class="alert alert-danger" role="alert">WebSocket is not supported by this browser!</div>');
}

webSocket.onopen = function ()
{
    console.log('Info: WebSocket connection opened.');
};

webSocket.onclose = function () {
    console.log('Info: WebSocket closed.');
};

webSocket.onmessage = function (message) {
    printMessage(message.data,'server');
};

function sendMessageFn()
{
	var lMessage = $("#input_msg").val();
	if( lMessage != null && lMessage.trim().length > 0 )
	{
		webSocket.send(lMessage);
		$("#input_msg").val("");
		var lmsg = '{"sender":"me","message":"'+lMessage+'"}';
		printMessage(lmsg,'me');
	}
}

function printMessage(pMessage, pClass)
{
	//console.log(pMessage);
	var lJObj = JSON.parse(pMessage);
	//console.log( lJObj );
	
	var sender = null;
	if ( pClass == 'me')
	{
		sender = $("<span class=\"label label-info\">");
	}
	else
	{
		sender = $("<span class=\"label label-success\">");
	}
	
	var senderIcon = $("<span class=\"glyphicon glyphicon-user\">");
	var senderName = $("<span class=\"senderName\">");
	senderName.text(lJObj.sender);
	
	sender.append(senderIcon).append(senderName);
	
	var messageSpan = $("<span class=\"message\">").text(lJObj.message);
	
	var p = $("<p>");
	p.addClass(pClass);
	p.append(sender).append(messageSpan);
	
	$("#message_box").append(p);
}


$(document).ready(function(){
	// ========= handle send message event ======= // 
	$("#input_msg").keydown( function(event) {
		if( event.keyCode == 13 || event.which == 13 )
		{
			event.preventDefault();
			sendMessageFn();
		}
	});
	
	$("#send_btn").click( function(event){
		sendMessageFn();
	});
	
	$("#exit_btn").click( function(event){
		if(webSocket != null)
		{
			webSocket.close();
			window.location.href = 'login';
		}
	});
	
});
		
</script>
<jsp:include page="/footer"/>