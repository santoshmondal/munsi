
<%@page import="com.munsi.util.Constants.DBCollectionEnum"%>
<%@page import="com.munsi.util.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row page-header">
	<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12" style="padding: 0px; line-height: 1.2">
		<div class="center col-xs-12 col-md-3 col-sm-3 col-lg-3">
			<div class="form-group" style="background-color:#eee;  border:1px solid #f59942; padding: 10px 0px 10px 10px">
				<div class="input-group" style="margin-right: 10px">
				  <input type="text" id="idCustomer" class="form-control" tabindex="1" placeholder="Enter Customer Name" >
				  <span class="input-group-addon" style="padding: 0px 10px;">
				  	<a href="#" tabindex="2" class="btn btn-inverse btn-xs" data-rel="tooltip" title="Show Customer Detail">
				  		<i class="icon-user white"></i>
				  	</a>
				  </span>
				</div>
			</div>
		</div>
		<div class="center col-xs-12 col-sm-7 col-md-7 col-lg-7">
			<div class="grid4">
				Outstanding Amount <br> <span class="bigger-125 blue"> <i class="icon-rupee"></i> 25</span>
				<div class="hr hr16 dotted"></div>
				<!-- <span class="input-icon input-icon-right"> 
					<input type="number" id="idAddDisc" placeholder="Discount" style="width:120px; text-align: center;"/> <i
					class="icon-rupee green"></i>
				</span> -->
				<div class="input-group">
				  <input type="number" class="form-control" id="idAddDisc" tabindex="3" style="text-align: center;" placeholder="Discount" >
				  <span class="input-group-addon"><i class="icon-rupee"></i></span>
				</div>
			</div>
			<div class="grid4">
				DISCOUNT<br> <span class="bigger-125 blue"><i class="icon-rupee"></i> 25</span>
				<div class="hr hr16 dotted"></div>
				Add. DISCOUNT<br> <span class="bigger-125 blue"><i class="icon-rupee"></i> 0</span>
			</div>
			
			<div class="grid4">
				SUBTOTAL<br/><span class="bigger-125 blue"><i class="icon-rupee"></i> 120000</span>
				<div class="hr hr16 dotted"></div>
				TAX<br/><span class="bigger-125 green"><i class="icon-rupee"></i> 12</span>
			</div>
			<div class="grid4">
				<pre style="background-color:#FFB752"><span class="bigger-200"><strong>120000</strong></span><br/>Bill Amount(<i class="icon-rupee"></i>)</pre>
				Total <span class="bigger-150 red" data-rel="tooltip" title="Outstanding + Bill Amount"><i class="icon-rupee"></i> 120025</span>
			</div>
		</div>
		<div class="center col-xs-12 col-md-2 col-sm-2 col-lg-2 pull-right hidden-print">
			<button class="btn btn-sm btn-inverse pull-right" type="button" tabindex="5" data-rel="tooltip" title="Save (Alt+s)" data-placement="bottom">
				<i class="icon-save"></i> Save
			</button>
			<button class="btn btn-sm btn-light pull-right" type="button" tabindex="4" data-rel="tooltip" title="Pay Bill" data-placement="bottom">
				Pay <i class="icon-print"></i> 
			</button>
		</div>
	</div>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12" id="gridDiv">
		<!-- PAGE CONTENT BEGINS -->
		<table id="grid-table_pinvoice"></table>
		<div id="grid-pager_pinvoice"></div>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<script type="text/javascript">
	 var mydata = [
                   {id:"1", code:"",name:"",  quantity:"",  rate:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:''}
             ];

			
			jQuery(function($) {
				var grid_selector = jQuery("#grid-table_pinvoice");
				var pager_selector = jQuery("#grid-table_pinvoice_toppager");
				var pager_selector_id = "#grid-table_pinvoice_toppager";
				var colModel, i, cmi, tr = "<tr>", skip = 0, ths,lastSel=-1;
				
			     
				grid_selector.jqGrid({
					
					datatype: "local",
                	data: mydata,
                	height: '328',
                	rownumbers:true,
					cellsubmit: 'clientArray',
					cellEdit : true,
					colNames:['id','Code','Name', 'Quantity', 'Rate', 'Tax','%','Rs','Free Qty.',' Total Qty.','Total Amt.'],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", sortable:false, editable: false, hidden:true},
						{name:'code',index:'code', width:100, sortable:false, editable: true,unformat: pickAutoComplete},
						{name:'name',index:'name', width:250, sortable:false, editable: true,unformat: pickAutoComplete},
						{name:'quantity',index:'quantity', sortable:false, align:'right', width:90,editable: true, formatter:'integer', sorttype:'int'},
						{name:'rate',index:'rate', width:90, sortable:false, align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'tax',index:'tax', width:80, sortable:false, align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'discountpercent',index:'discountpercent', sortable:false, width:80,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'discountamount',index:'discountamount', sortable:false, width:90,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'freequantity',index:'freequantity', sortable:false, align:'right', width:90,editable: true, formatter:'integer', sorttype:'int'},
						{name:'totalquantity',index:'totalquantity', sortable:false, align:'right', width:100,editable: true,formatter:'integer'},
						{name:'totalamount',index:'totalamount', sortable:false, align:'right', width:120,editable: false,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}}
					], 
			
					viewrecords : true,
					rowNum:1000,
					recordtext: "Total Entries {2}",
					pgbuttons: false,
					pgtext: null,
					altRows: true,

					multiselect: false,
			        gridComplete: function () {
			        	calculateTotalAmount();
	                },
	                afterSaveCell: function (rowid, name, val, iRow, iCol) {
	                	calculateTotalAmount();
	                },
	                beforeSelectRow: function(rowid) {
	                    /* if (rowid !== lastSel) {
	                    	grid_selector.jqGrid('restoreRow',lastSel);
	                        lastSel = rowid;
	                    }
	                    return true;
	                         */return false;
	                },
	                loadComplete : function() {
						var table = this;
						setTimeout(function(){
							enableTooltips(table);
						}, 0);
						
						$(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
						$('#grid-table_pinvoice input').css({'background': 'transaprent !important',
						    'border': '0 none'});
							
						//---- Setting Auto Total in Footer --
						calculateTotalAmount();
                    	//----------- Discount Cell Hyperlink----------
						var ids = grid_selector.getDataIDs();
	                    var myGridNode = grid_selector[0];
	                    for (var i = 0, idCount = ids.length; i < idCount; i++) {
	                        var a = $("#"+ids[i]+" a",myGridNode);
	                        a.html("Manage");
	                        a.each(function() {
	                        	var sHref = $( this ).attr('href');
	                        	$( this ).attr('href',"#?id="+ids[i]+"&"+sHref);
	                        });
	                        a.click(function(e) {
	                            var hash=e.currentTarget.hash;// string like "#?id=0"
	                            if (hash.substring(0,5) === '#?id=') {
	                                var id = hash.substring(5,hash.length);
	                                if(hash.indexOf('action=manage_beat') > -1){
	                                	showBeatDialog(id);
	                                }
	                            }
	                            e.preventDefault();
	                        });
	                    }
	                    
					},
			
					editurl: "${pageContext.request.contextPath}/areamaster.action?op=edit",//nothing is saved
					//caption: "List of areas",
					scrollOffset: 20,
					footerrow: true,
					autowidth: true
				});
				
				
				colModel = grid_selector[0].p.colModel;
	            ths = grid_selector[0].grid.headers;
	            for(i=0;i<colModel.length;i++) {
	                cmi = colModel[i];
	                if (cmi.name !== 'discountpercent') {
	                    if (skip === 0) {
	                        $(ths[i].el).attr("rowspan", "2");
	                    } else {
	                        skip--;
	                    }
	                } else {
	                    tr += '<th class="ui-state-default ui-th-column ui-th-ltr" colspan="3" role="columnheader">Discount</th>';
	                    skip = 2; // because we make colspan="3" the next 2 columns should not receive the rowspan="2" attribute
	                }
	            }
	            tr += "</tr>";
	            grid_selector.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
				
				grid_selector.jqGrid('bindKeys', {"onEnter":function( rowid ) {  
					//alert("You enter a row with id: " + rowid);
                    editingRowId = rowid;
                    // we use aftersavefunc to restore focus
                    grid_selector.jqGrid('editRow',rowid,true,null, null, null, {},function(){
                        setTimeout(function(){
                        	grid_selector.focus();
                        },100);
                    },
                    null,
                    function(){
                        setTimeout(function(){
                        	grid_selector.focus();
                        },100);
                    });
				} } );
				
				//switch element when editing inline
				function aceSwitch( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=checkbox]')
								.wrap('<label class="inline" />')
							.addClass('ace ace-switch ace-switch-5')
							.after('<span class="lbl"></span>');
					}, 0);
				}
				//enable datepicker
				function pickDate( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=text]')
								.datepicker({format:'dd-mm-yyyy' , autoclose:true}); 
					}, 0);
				}
				
				//autocomplete
				 var availableProductCode = '<%= CommonUtil.getIdLabelJSON(DBCollectionEnum.MAST_PRODUCT, "_id", "name", "") %>';
				 availableProductCode = JSON.parse(availableProductCode);
				 debugger;
				 var finProdCode=[];
				 for(i=0;i<availableProductCode.length;i++){
					 finProdCode[i] = availableProductCode[i].name;
					 
				 }
			     /* [
					"ActionScript",
					"AppleScript",
					"Asp",
					"BASIC",
					"C",
					"C++",
					"Clojure",
					"COBOL",
					"ColdFusion",
					"Erlang",
					"Fortran",
					"Groovy",
					"Haskell",
					"Java",
					"JavaScript",
					"Lisp",
					"Perl",
					"PHP",
					"Python",
					"Ruby",
					"Scala",
					"Scheme"
				] */
				
				function pickAutoComplete( cellvalue, options, cell ) {
					setTimeout(function(){
					$(cell) .find('input[type=text]').autocomplete({
						source: finProdCode
					});
					}, 0);
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
					$('[data-rel=tooltip]').tooltip({container:'body'});
				}
			

				//-----> press g for setting focus on jqgrid
				$(document).bind('keydown', 'Alt+g', function(){
				    var	ids = grid_selector.jqGrid("getDataIDs");
					if(ids && ids.length > 0){
						grid_selector.focus();
						grid_selector.jqGrid("setSelection", ids[0]);
					}
			    });
				
				function  getTextFromCell(cellNode) {
					var text = cellNode.childNodes[0].nodeName === "INPUT"?
                            cellNode.childNodes[0].value:
	                            cellNode.textContent || cellNode.innerText;
                    return text.replace("Rs","").replace("%","").replace(",","");
				};
				
				function getColumnIndexByName(grid,columnName) {
			         var cm = grid.jqGrid('getGridParam','colModel');
			         
			         for (var i=0,l=cm.length; i<l; i++) {
			             if (cm[i].name===columnName) {
			                 return i; // return the index
			             }
			         }
			         return -1;
			     };

				function calculateTotalAmount(){

			         var totalAmount = 0, totalTax = 0,
		             i=getColumnIndexByName(grid_selector,'totalamount'); // nth-child need 1-based index so we use (i+1) below
			         $("tbody > tr.jqgrow > td:nth-child("+(i+1)+")",grid_selector[0]).each(function() {
			        	    
			        	 totalAmount += Number(getTextFromCell(this));
			         });

                    i=getColumnIndexByName(grid_selector,'tax');
                    $("tbody > tr.jqgrow > td:nth-child("+(i+1)+")",grid_selector[0]).each(function() {
                        totalTax += Number(getTextFromCell(this));
                    });
	                    
			         grid_selector.jqGrid('footerData','set',{name:'TOTAL',totalamount:totalAmount,tax:totalTax});
			     };
			     
			     
			   //------------ AutoComplete Customer Name--------------
			     var objJsonCustomer = '<%= CommonUtil.getIdLabelJSON(DBCollectionEnum.MAST_CUSTOMER, "_id", "name", "") %>';
			     objJsonCustomer = JSON.parse(objJsonCustomer.replace(/_id/g,"id").replace(/name/g,"label"));
			     $("#idCustomer").autocomplete({
			    	 minLength: 0,
				     source: objJsonCustomer,
				     focus: function( event, ui ) {
				     $( "#idCustomer" ).val( ui.item.label );
				     return false;
				     },
				     select: function( event, ui ) {
				     $( "#idCustomer" ).val( ui.item.label );
				     $( "#idCustomer-id" ).val( ui.item.id );
				     
				     return false;
				     }
			     })
			     .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
			     var temp = $( "<li>" );
			     console.log(item);
			     console.log(item.label +" "+item.id);
			     	temp.append( "<a>" + item.label + "<span class='badge badge-primary pull-right'>"+ item.id  +"</span>"+ "</a>" ).appendTo( ul );
			     return temp;
			     };
					
			   //-----> press alt + g for setting focus on jqgrid
					$(document).bind('keydown', 'Alt+g', function(){
					    var	ids = grid_selector.jqGrid("getDataIDs");
						if(ids && ids.length > 0){
							grid_selector.editCell(1,2,true);
							$("#" + $.jgrid.jqID(ids[0]) + "_code").focus();
						}
				    });
			   
			});

			function addLastRow(){
				jQuery("#grid-table_pinvoice").jqGrid('addRowData','myrowid'+ (Math.floor(Math.random() * (100 - 1)) + 1), { code:"",name:"",  quantity:"",  rate:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:''}, 'last');
			}
			
			$(document).ready(function () {
			    $('#gridDiv').bind('keydown', 'return', function () {
			    	alert(e);
			        //if (e.keyCode == 13) e.keyCode = 9;
			        return false;
			    });
			});
</script>
