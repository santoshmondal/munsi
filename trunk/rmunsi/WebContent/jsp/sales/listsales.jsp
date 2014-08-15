
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<div class="page-header">
							
							<h1>Sales Invoices
								<small>
									<i class="icon-double-angle-right"></i>
									List of invoices
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div id="alertArea" class="col-xs-12"></div>
							<div class="col-xs-12">
								<table id="grid-table-data"></table>

								<div id="grid-pager-data"></div>
							</div><!-- /.col -->
						</div><!-- /.row -->

	<script type="text/javascript">
	$(document).ready(function () {
        var myGrid = $("#grid-table-data");
        var mydata=[{'id':'01','sInvoiceNumber':'IX1230','sInvoiceDate':'12/2/2014','customerName':'Tom Smith','totalAmount':'12345'}];
        
        myGrid.jqGrid({
			//url: "${pageContext.request.contextPath}/sales.action?op=view_all",
			//mtype: "POST",
			data: mydata,
			loadonce: true,
			gridview: true,
			datatype: "local",//"json",
			viewrecords : true,
			
			colNames:['id','Invoice Number','Invoice Date','Customer Name','Invoice Amount',' '],
			colModel:[
				{name:'id',index:'id', width:60, sorttype:"int", editrules:{required:false, addhidden:true}, editable: false, hidden:true},
				{name:'sInvoiceNumber',index:'sInvoiceNumber', width:170,editable: false},
				{name:'sInvoiceDate',index:'sInvoiceDate',width:100,sorttype:'date', searchoptions: {sopt: ['eq'],
                    dataInit : function (elem) {
                        $(elem).datepicker({ format:'dd-M-yyyy' ,changeYear: true, changeMonth: true, showButtonPanel: true, autoclose: true}) .on('changeDate', function(ev){
                        		if (this.id.substr(0, 3) === "gs_") {
                                    setTimeout(function(){
                                    	myGrid[0].triggerToolbar();
                                    }, 50);
                                } else {
                                    $(this).trigger('change');
                                }
                        });
                    }},formatter:'date', formatoptions: {srcformat: 'd/m/Y', newformat:'d-M-Y'}, datefmt: 'd-M-Y',unformat: pickDate},
                {name:'customerName',index:'customerName', width:170,editable: false},
				{name:'totalAmount',index:'totalAmount', width:100, editable: false},
				{ name: 'act', index: 'act', frozen : true,width:70, search:false, align: 'center', sortable: false, formatter: 'actions',
                    formatoptions: {editbutton:false,delbutton:false,
                        keys: false}
                }
			],
			beforeProcessing: function (data) {

			},
			loadComplete : function() {
				var table = this;
				setTimeout(function(){	
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
				var iCol = getColumnIndexByName(myGrid, 'act');
                $(this).find(">tbody>tr.jqgrow>td:nth-child(" + (iCol + 1) + ")")
                    .each(function() {
                        $("<div>", {
                            title: "Show Details",
                            mouseover: function() {
                                $(this).addClass('ui-state-hover');
                            },
                            mouseout: function() {
                                $(this).removeClass('ui-state-hover');
                            },
                            click: function(e) {
                                console.log("'Detail' button is clicked in the rowis="+ $(e.target).closest("tr.jqgrow").attr("id") +" !");
                                var URL = "sales.action?op=VIEW&invoiceno="+ $(e.target).closest("tr.jqgrow").attr("id");
                				var DATA = {};
                				var embedInElement = "id_EmbedPage";
                				async.munsi.ajaxCall(URL,DATA,embedInElement);
                	
                            }
                        }
                      ).css({"margin-right": "5px", float: "left", cursor: "pointer"})
                       .addClass("ui-pg-div ui-inline-custom")
                       .append('<span class="icon-eye-open bigger-120" style="color:black"></span>')
                       .prependTo($(this).children("div"));
                });
			},
			gridComplete : function() {
				myGrid.find(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
			},
			rowNum:10,
            rowList:[10,20,30],
            pager: '#grid-pager-data',
            ignoreCase:true,
            rownumbers:true,
            sortname: 'invdate',
            scrollOffset: 18,
			altRows: true,
			viewrecords: true,
            autowidth:true,
            sortorder: 'desc',
            height: '380'
        });
         
        myGrid.jqGrid('filterToolbar', {stringResult: true, searchOnEnter: false, defaultSearch : "cn"});
        myGrid.jqGrid('setFrozenColumns');
        
        function getColumnIndexByName(grid, columnName) {
            var cm = grid.jqGrid('getGridParam', 'colModel'), i, l = cm.length;
            for (i = 0; i < l; i++) {
                if (cm[i].name === columnName) {
                    return i; // return the index
                }
            }
            return -1;
        };
        
      //enable datepicker
    	function pickDate( cellvalue, options, cell ) {
    		setTimeout(function(){
    			$(cell) .find('input[type=text]')
    					.datepicker({format:'dd-mm-yyyy' , autoclose:true}); 
    		}, 0);
    	}
    	
      //replace icons with FontAwesome icons like above
		function updatePagerIcons(table) {
			var replacement = 
			{
				'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
				'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
				'ui-icon-seek-next' : 'icon-angle-right bigger-140',
				'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			});
		}
	
		function enableTooltips(table) {
			$('.navtable .ui-pg-button').tooltip({container:'body'});
			$(table).find('.ui-pg-div').tooltip({container:'body'});
		}
	
    });	
				
</script>