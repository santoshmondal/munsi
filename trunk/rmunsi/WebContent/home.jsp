<!-- Including Header -->
<jsp:include page='/jsp/template/header.jsp'></jsp:include>
<% 
String appPath = request.getContextPath();
%>
<script type="text/javascript">
var webSocket = null;
var url = '';
var jsAppName = '<%=appPath%>';
if (window.location.protocol == 'http:') {	
	url  = 'ws://' + window.location.host+jsAppName+'/alert';
}
else{
	url = 'wss://' + window.location.host+jsAppName+'/alert';
}
console.log("WS url:"+url);

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




<div class="main-container" id="main-container">
	<script type="text/javascript">
		try {
			ace.settings.check('main-container', 'fixed');
		} catch (e) {
		}
	</script>

	<!-- Main Container -->
	<div class="main-container-inner">
		<a class="menu-toggler" id="menu-toggler" href="#"> <span
			class="menu-text"></span>
		</a>

		<!-- Including Navigation -->
		<jsp:include page="/jsp/template/leftnav.jsp"></jsp:include>

		<div class="main-content">
			<!-- <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
				

				<ul class="breadcrumb">
					<li><i class="icon-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Dashboard</li>
				</ul>
				.breadcrumb
				
				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon"> <input type="text"
							placeholder="Search ..." class="nav-search-input"
							id="nav-search-input" autocomplete="off" /> <i
							class="icon-search nav-search-icon"></i>
						</span>
					</form>
				</div>
				#nav-search
			</div> -->
			
			<!-- Dynamic Content Goes here-->
			<div class="page-content">
				<!-- <div class="space"></div><div class="space"></div>
				 -->
				<div id="id_EmbedPage">
				
					
				</div>
			</div>
			<!-- /.page-content -->





		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container-inner -->
	</div><!-- /.main-container -->
	<!-- Including Footer -->
	<jsp:include page="/jsp/template/footer.jsp"></jsp:include>