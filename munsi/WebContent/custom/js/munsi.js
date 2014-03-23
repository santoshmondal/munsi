

$(function(){

	$("#sidebar").click(function(event) {
		
		
		if ($(event.target).is("li")){
			var hrefEle  = $(event.target).find("[data-href]");
			alert("clicked: " + hrefEle.data("href"));
		}
		else{
			var hrefEle  = $(event.target).closest("li").find("[data-href]");

			
			if (hrefEle.data("href")){
				$("#sidebar li").removeClass("active");
				console.log("clicked: " + hrefEle.data("href"));
				var parentLi = hrefEle.closest("li");
				 parentLi.addClass("active");
				var URL = "hello.action";
				var DATA = {reqPage:hrefEle.data("href")};
				var embedInElement = "id_EmbedPage";
	
				async.munsi.ajaxCall(URL,DATA,embedInElement);
			}
		}
	});
	//-------------------------------------------
	//----------------- HotKeys -----------------
	//-------------------------------------------
	
	//-----> press g for setting focus on jqgrid
	$(document).bind('keydown', 'm', function(){
	    $("#sidebar-collapse").click();
    });
});



