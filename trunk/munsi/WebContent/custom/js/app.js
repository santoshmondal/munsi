/**
 * 
 */

var async = {
	munsi : {},
	util : {}
};

async.munsi.navigate = function(gndStr) {
	alert(gndStr);
};


async.munsi.ajaxCall = function(urlPath,dataJSON,embedInElement){

	$.ajax({
		type: "POST",
		url: urlPath,
		data: dataJSON,
		dataType: 'html'
		})
		.done(function( data ) {
			$("#"+embedInElement).html(data);
		})
		.fail(function() {
			 alert( "error" );
		});

};