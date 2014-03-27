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
	async.loadingDialog.showPleaseWait();
	$.ajax({
		type: "POST",
		url: urlPath,
		data: dataJSON,
		dataType: 'html'
		})
		.done(function( data ) {
			$("#"+embedInElement).html(data);
			async.loadingDialog.hidePleaseWait();
		})
		.fail(function() {
			async.loadingDialog.hidePleaseWait();
			console.error( "error in fetching data from server....." );
		});

};

async.loadingDialog = (function () {
    var pleaseWaitDiv = $('<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"><div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div></div></div>');
    return {
        showPleaseWait: function() {
            pleaseWaitDiv.modal();
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();