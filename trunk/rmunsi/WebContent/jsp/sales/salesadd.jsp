
<%@page import="com.munsi.util.Constants.DBCollectionEnum"%>
<%@page import="com.munsi.util.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.profile-info-value{
		margin-left: 160px;
	}
	.profile-info-name{
		width: 160px;
	}
</style>

<div class="row page-header">
	<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12" style="padding: 0px; line-height: 1.2">
		<div class="center col-xs-12 col-md-3 col-sm-3 col-lg-3">
			<div class="form-group" style="background-color:#eee;  border:1px solid #f59942; padding: 10px 0px 10px 10px">
				<div class="input-group" style="margin-right: 10px">
				  <input type="text" id="idCustomer" class="form-control manual-tooltip tooltip-error" tabindex="1" placeholder="Enter Customer Name" title="Please enter customer name or WALKIN">
				  <input type="hidden" id="idCustomerID" >
				  <span class="input-group-addon" style="padding: 0px 10px;">
				  	<a href="#" onclick='showCustomerInfo()' id='idShowCustomer' tabindex="2" class="btn btn-inverse btn-xs" data-rel="tooltip" title="Show Customer Detail">
				  		<i class="icon-user white"></i>
				  	</a>
				  </span>
				</div>
			</div>
		</div>
		<div class="center col-xs-12 col-sm-7 col-md-7 col-lg-7">
			<div class="grid4">
				Outstanding Amount <br> <span class="bigger-125 blue"> <i class="icon-rupee"></i> <span id="idOutstandingAmt">0</span></span>
				<div class="hr hr16 dotted"></div>
				<div class="input-group">
				  <input type="number" class="form-control invoiceField" id="idAddTax" data-rel="tooltip" title="Additional TAX" tabindex="3" style="text-align: center;" placeholder="Tax" >
				  <span class="input-group-addon">%</span>
				</div>
			</div>
			<div class="grid4">
				DISCOUNT<br> <span class="bigger-125 blue"><i class="icon-rupee"></i> <span id="idTotalDiscount">0</span></span>
				<div class="hr hr16 dotted"></div>
				<!-- Add. DISCOUNT<br> <span class="bigger-125 blue"><i class="icon-rupee"></i> 0</span> -->
				<div class="input-group">
				  <input type="number" class="form-control invoiceField" id="idAddDisc" data-rel="tooltip" title="Additional Discount"  tabindex="4" style="text-align: center;" placeholder="Discount" >
				  <span class="input-group-addon"><i class="icon-rupee"></i></span>
				</div>
			</div>
			
			<div class="grid4">
				SUBTOTAL<br/><span class="bigger-125 blue"><i class="icon-rupee"></i> <span id="idSubTotal">0</span></span>
				<div class="hr hr16 dotted"></div>
				TAX<br/><span class="bigger-125 green"><i class="icon-rupee"></i> <span id="idTotalTax">0</span></span>
			</div>
			<div class="grid4">
				<pre style="background-color:#FFB752"><span class="bigger-200"><strong><span id="idTotalAmt">0</span></strong></span><br/>Bill Amount(<i class="icon-rupee"></i>)</pre>
				Total <span class="bigger-150 red" data-rel="tooltip" title="Outstanding + Bill Amount"><i class="icon-rupee"></i> <span id="idAllTotal">0</span></span>
			</div>
		</div>
		<div class="center col-xs-12 col-md-2 col-sm-2 col-lg-2 pull-right hidden-print">
			<button id="idSaveBtn" class="btn btn-sm btn-inverse pull-right" type="button" tabindex="5" data-rel="tooltip" title="Save (Alt+s)" data-placement="bottom">
				<i class="icon-save"></i> Save
			</button>
			<button id="idPayBtn" class="btn btn-sm btn-light pull-right" onclick="saveAndPrintInvoice()" type="button" tabindex="4" data-rel="tooltip" title="Pay Bill" data-placement="bottom">
				Pay <i class="icon-print"></i> 
			</button>
		</div>
	</div>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12 manual-tooltip tooltip-error" id="gridDiv" title="No PRODUCT added, please add PRODUCT" data-placement="top">
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
                   {id:"1", code:"",name:"",  quantity:"",  salesRate:"",derSumOfProudctTax:"",rawDiscountPercent:"",rawDiscountPrice:"",freeQuantity:"",totalQuantity:'',netPaybleProductPrice:''}
             ];
	 var gridDisc=0,gridTax=0,g_objJsonBatch=[],g_rowData=[];
	 
			jQuery(function($) {
				var grid_selector = jQuery("#grid-table_pinvoice");
				var pager_selector = jQuery("#grid-table_pinvoice_toppager");
				var pager_selector_id = "#grid-table_pinvoice_toppager";
				var colModel, i, cmi, tr = "<tr>", skip = 0, ths,curCellname,curRow;
				
			     
				grid_selector.jqGrid({
					
					datatype: "local",
                	data: mydata,
                	height: '328',
                	rownumbers:true,
					cellsubmit: 'clientArray',
					cellEdit : true,
					colNames:['id', 'Barcode', 'Code', 'Name', 'Batch', 'Quantity', 'Rate', 'Tax','%','Rs','Free Qty.',' Total Qty.','Total Amt.',''],
					colModel:[
						{name:'_id',index:'id', width:60, sorttype:"int", sortable:false, editable: false, hidden:true},
						{name:'barCode',index:'barCode', width:100, sortable:false, editable: true},
						{name:'code',index:'code', width:100, sortable:false, editable: true,unformat: pickCodeAutoComplete},
						{name:'name',index:'name', width:250, sortable:false, editable: true,unformat: pickNameAutoComplete},
						{name:'batchNumber',index:'batchno', sortable:false, editable: true,unformat: pickBatchAutoComplete},
						{name:'quantity',index:'quantity', sortable:false, align:'right', width:90,editable: true, formatter:'integer', sorttype:'int'},
						{name:'salesRate',index:'salesRate', width:90, sortable:false, align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'derSumOfProudctTax',index:'derSumOfProudctTax', width:80, sortable:false, align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'rawDiscountPercent',index:'rawDiscountPercent', sortable:false, width:80,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'rawDiscountPrice',index:'rawDiscountPrice', sortable:false, width:90,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'freeQuantity',index:'freeQuantity', sortable:false, align:'right', width:90,editable: true, formatter:'integer', sorttype:'int',editoptions: {
                            dataEvents: [
                                         {
                                             type: 'keydown',
                                             fn: function (e) {
                                            
                                            	 var key = e.charCode || e.keyCode;
                                                 if (key == 9 || key == 15)//tab
                                                 {
                                                     if (curRow == grid_selector.jqGrid('getGridParam','data').length) {
														
                                                     }else{
                                                    	 
	                                                     //Enter edit row for next row in grid
	                                                     grid_selector.jqGrid("editCell",curRow+1,2,false);
	                                                     //grid_selector.jqGrid("editCell",curRow+1,2,true);
	                                                     //$("input #" + (Number(curRow)) + "_barCode").trigger('focus');
                                                     }
                                                 }
                                             }
                                         }
                                     ]
                                 }},
						{name:'totalQuantity',index:'totalQuantity', sortable:false, align:'right', width:100,editable: false,formatter:'integer'},
						{name:'netPaybleProductPrice',index:'netPaybleProductPrice', sortable:false, align:'right', width:120,editable: false,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name : 'actions', formatter:'actions', width:50,
							formatoptions: {
							    keys: true,
							    editbutton: false,
							    delOptions: {url: "#"}
							               }}
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
	                	console.log("rowid:"+rowid+", name:"+ name+", val:"+val+", iRow:"+iRow+", iCol:"+iCol);
	                	if(iCol!=5){
		                	getRowAndPopulate(rowid,name,val);
		                	calculateTotalAmount();
	                	}else
                		{
                			console.log(g_rowData);
                			console.log(g_objJsonBatch);
                			for(i=0;i<g_objJsonBatch.length;i++){
                				if(g_objJsonBatch[i].label==val){
                					g_rowData.salesRate=g_objJsonBatch[i].salesRate;
                					g_rowData.batchNumber=g_objJsonBatch[i].batchNumber;
                					grid_selector.jqGrid('setRowData', rowid, g_rowData);
                					break;
                				}
                			}
                		}
                		
	                }/* ,
	                beforeSelectRow: function(rowid) {
	                 //   return false;
	                } */,
	                beforeEditCell: function(id,name,val,iRow,iCol){
	                	// Trigger Batch Fetch on edit click
	                	g_rowData = [];
	                	g_objJsonBatch=[];
	                	if(iCol == 5){
	                		var prodData = ajaxProductFetch('code',grid_selector.jqGrid ('getCell', id, 'code'),true);
		                	if(prodData && prodData.batchList){
		                		$.each(prodData.batchList,function(i,obj){
		                			obj._id=obj.batchNumber;
		                			g_objJsonBatch.push(obj);
		                		});
		                	}
		                	var rowid = grid_selector.jqGrid('getGridParam', 'selrow');
							g_rowData = grid_selector.jqGrid('getRowData', rowid);
		                	 
	                	}
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
					afterEditCell: function(rowid, cellname, value, iRow, iCol) {
					   /* 
					   if(iCol == 5){
	                		if(g_rowData._id)
	                			grid_selector.jqGrid('setRowData', rowid, g_rowData);
	                	 console.log("afterEditCell called ....");
						} */
						
						// Get a handle to the actual input field
					    var inputControl = jQuery('#' + (iRow) + '_' + cellname);
					    curCellname=cellname;
					    curRow = iRow;
					    // Listen for enter (and shift-enter)
					    inputControl.bind("keydown",function(e) {

					      if (e.keyCode === 13) {  // Enter key:
					        var increment = e.shiftKey ? -1 : 1;

					        	grid_selector.jqGrid("editCell",iRow,iCol+increment,true);
					        
					      }
					    }); // End keydown binding
					  },
			
					editurl: "${pageContext.request.contextPath}/areamaster.action?op=edit",
					//caption: "List of areas",
					scrollOffset: 20,
					footerrow: true,
					autowidth: true
				});
				
				
				colModel = grid_selector[0].p.colModel;
	            ths = grid_selector[0].grid.headers;
	            for(i=0;i<colModel.length;i++) {
	                cmi = colModel[i];
	                if (cmi.name !== 'rawDiscountPercent') {
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
				
				//autocomplete for Code
				 var availableProductCode = '<%= CommonUtil.getIdLabelJSON(DBCollectionEnum.MAST_PRODUCT, "_id", "code", "") %>';
				 availableProductCode = JSON.parse(availableProductCode);
				 var finProdCode=[],itr=0;
				 for(i=0;i<availableProductCode.length;i++){
					 if(availableProductCode[i].code){
					 	finProdCode[itr++] = availableProductCode[i].code;
					 }
				 }
				 //console.log(availableProductCode+"\n finProdCode::"+finProdCode);
				function pickCodeAutoComplete( cellvalue, options, cell ) {
					setTimeout(function(){
					$(cell) .find('input[type=text]').autocomplete({
						minLength: 0,
						source: finProdCode
					});
					}, 0);
				}
				
				//autocomplete for Name
				 var availableProductName = '<%= CommonUtil.getIdLabelJSON(DBCollectionEnum.MAST_PRODUCT, "_id", "name", "") %>';
				 availableProductName = JSON.parse(availableProductName);
				 var finProdName=[],itr=0;
				 for(i=0;i<availableProductName.length;i++){
					 if(availableProductName[i].name){
					 	finProdName[itr++] = availableProductName[i].name;
					 }
				 }
				 
				function pickNameAutoComplete( cellvalue, options, cell ) {
					setTimeout(function(){
					$(cell) .find('input[type=text]').autocomplete({
						minLength: 0,
						source: finProdName
					});
					}, 0);
				}
				

				//autocomplete for batch

				function pickBatchAutoComplete( cellvalue, options, cella ) {
					setTimeout(function(){
						try{
						    g_objJsonBatch = JSON.parse(JSON.stringify(g_objJsonBatch).replace(/_id/g,"id").replace(/batchNumber/g,"label"));
						     
							$(cella).find('input[type=text]').autocomplete({
						    	 minLength: 0,
						    	 source: g_objJsonBatch,
						    	 open: function() { 
						    		 $(cella).find('input[type=text]').autocomplete("widget").width(300); 
						    	    }
						     }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
							     var temp = $( "<li>" );
							     console.log(item);
							     console.log(item.label +" "+item.id);
							     temp.append( "<a>" + item.label + "<span class='badge badge-primary pull-right'><i class='icon-shopping-cart'></i> "+ item.batchCurrentStock  +"</span> "+
							    		 "<span class='badge badge-warning pull-right'><i class='icon-rupee'></i> "+ item.salesRate  +"</span>" +
							    		 "<span class='label label-inverse arrowed arrowed-in-right pull-right'><i class='icon-calendar'></i> "+ (item.sExpdate?item.sExpdate:' NA') +"</span>" +"</a>" ).appendTo( ul );
						     	 return temp;
						     };
							
							$(cella).find('input[type=text]').on( "autocompleteselect", function( event, ui ) {console.log("works.......");} );
						}catch(e){
							//console.log(e);
							}
					}, 0);
					
				}

				
				
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
					$('[data-rel=tooltip]').tooltip({container:'body'});
					$('.manual-tooltip').tooltip({trigger:'manual'});
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
		             i=getColumnIndexByName(grid_selector,'netPaybleProductPrice'); // nth-child need 1-based index so we use (i+1) below
			         $("tbody > tr.jqgrow > td:nth-child("+(i+1)+")",grid_selector[0]).each(function() {
			        	    
			        	 totalAmount += Number(getTextFromCell(this));
			         });

	                    
			         grid_selector.jqGrid('footerData','set',{name:'TOTAL',netPaybleProductPrice:totalAmount});
			         $("#idSubTotal").html(Number(totalAmount).toFixed(2));
			         var addTaxAmt = (Number($("#idSubTotal").html())-Number($("#idAddDisc").val()))*Number($("#idAddTax").val())/100;
			    	 $("#idTotalAmt").html(Math.round((Number($("#idSubTotal").html())+addTaxAmt-Number($("#idAddDisc").val()))*100)/100);
			    	 $("#idAllTotal").html(Math.round((Number($("#idTotalAmt").html()) + Number($("#idOutstandingAmt").html()))*100)/100);
			    	 $("#idTotalDiscount").html(Math.round((Number(gridDisc) + Number($("#idAddDisc").val()))*100)/100);
			    	 $("#idTotalTax").html(Math.round((Number(gridTax) + Number(addTaxAmt))*100)/100);
			     };
			     
			     function getRowAndPopulate(rowid,name,val)
             	 {
	                	var rowData = grid_selector.jqGrid('getRowData', rowid);

	                	switch(name) {
		                    case "name":
								var prodData =ajaxProductFetch(name,val,true);
						        if(prodData){
						        	rowData._id=prodData._id?prodData._id:1;
			                        rowData.barCode=prodData.barCode?prodData.barCode:"";
			                        rowData.code=prodData.code?prodData.code:"";
			                        rowData.name=prodData.name?prodData.name:"";
			                        rowData.batchNumber=prodData.batchNumber;
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.freeQuantity="0";
			                        rowData.derSumOfProudctTax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
			                        rowData.netPaybleProductPrice=Number(rowData.quantity)*Number(rowData.salesRate);
			                        var taxValpercent = prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:1;
			                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
			                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;
		                        }
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "code":
								var prodData =ajaxProductFetch(name,val,true);
		                        if(prodData){
						        	rowData._id=prodData._id?prodData._id:1;
			                        rowData.barCode=prodData.barCode?prodData.barCode:"";
			                        rowData.code=prodData.code?prodData.code:"";
			                        rowData.name=prodData.name?prodData.name:"";
			                        rowData.batchNumber=prodData.batchNumber;
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.derSumOfProudctTax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.freeQuantity="0";
			                        rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
			                        rowData.netPaybleProductPrice=Number(rowData.quantity)*Number(rowData.salesRate);
			                        var taxValpercent = prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:1;
			                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
			                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;

		                        }
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "barCode":
								var prodData =ajaxProductFetch(name,val,true);
		                        if(prodData){
						        	rowData._id=prodData._id?prodData._id:1;
			                        rowData.barCode=prodData.barCode?prodData.barCode:"";
			                        rowData.code=prodData.code?prodData.code:"";
			                        rowData.name=prodData.name?prodData.name:"";
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.derSumOfProudctTax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.freeQuantity="0";
			                        rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
			                        var taxValpercent = prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:1;
			                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
			                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;
		                        }
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "quantity":
		                    	rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
		                        var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                        var discPrice = rowData.rawDiscountPrice?rowData.rawDiscountPrice:0;
		                        var taxValrupee = ((Number(rowData.quantity)*Number(rowData.salesRate)-discPrice)*Number(taxValpercent))/100;
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;
		                        rowData.rawDiscountPercent = (discPrice*100.0)/(Number(rowData.quantity)*Number(rowData.salesRate));
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "salesRate":
		                    	var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                    	var discPrice = rowData.rawDiscountPrice?rowData.rawDiscountPrice:0;
		                    	taxValrupee = ((Number(rowData.quantity)*Number(rowData.salesRate)-discPrice)*Number(taxValpercent))/100;
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;
		                        rowData.rawDiscountPercent = (discPrice*100.0)/(Number(rowData.quantity)*Number(rowData.salesRate));
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "rawDiscountPercent":
		                    	var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                    	var discPercent = rowData.rawDiscountPercent?rowData.rawDiscountPercent:0;
			                	rowData.rawDiscountPrice = ((Number(rowData.quantity)*Number(rowData.salesRate)*Number(discPercent))/100);
		                        taxValrupee = ((Number(rowData.quantity)*Number(rowData.salesRate)- rowData.rawDiscountPrice)*Number(taxValpercent))/100;
			                	rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee - Number(rowData.rawDiscountPrice);
			                	grid_selector.jqGrid('setRowData', rowid, rowData);
		                		break;
		                    case "rawDiscountPrice":
	 	                    	var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                        var discPrice = rowData.rawDiscountPrice?rowData.rawDiscountPrice:0;
		                        taxValrupee = ((Number(rowData.quantity)*Number(rowData.salesRate)-discPrice)*Number(taxValpercent))/100.0;
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee-Number(rowData.rawDiscountPrice);
			                	rowData.rawDiscountPercent = (discPrice*100.0)/(Number(rowData.quantity)*Number(rowData.salesRate));
		                    	grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "freeQuantity":
		                    	rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
		                    	grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    default:
		                        console.log("Default Switch");
		                    	
	                	}

	                	var gridData = grid_selector.jqGrid('getGridParam','data');
	                	if(gridData[gridData.length-1].name){
	                		var randId = Math.ceil(Math.random()*10000);
	                		grid_selector.addRowData(randId,{id:randId,date:"a"}, "last");
	                	}
	                	
						//------------- Sales Invoice TAX Rs Calculation
	                	var tTemp=0;
	                	for(i=0;i<gridData.length-1;i++){
		                	var taxValpercent = gridData[i].derSumOfProudctTax?gridData[i].derSumOfProudctTax:0;
	                		tTemp = tTemp + ((Number(gridData[i].quantity)*Number(gridData[i].salesRate)-Number(gridData[i].rawDiscountPrice))*Number(taxValpercent))/100;
		                }
	                	gridTax=Math.round(tTemp*100)/100.0;
	                	$("#idTotalTax").html(gridTax);
	                	
	                	//------------- Sales Invoice Discount Rs Calculation
	                	tTemp=0;
	                	for(i=0;i<gridData.length-1;i++){
		                	var discountAmt = gridData[i].rawDiscountPrice?gridData[i].rawDiscountPrice:0;
	                		tTemp = tTemp + Number(gridData[i].rawDiscountPrice);
		                }
	                	gridDisc=Math.round(tTemp*100)/100.0;
		                $("#idTotalDiscount").html(gridDisc);
		                g_isDirty=true;
             	}
			     function commonCalc(myRowData){
			    	 return myRowData;
			     }
			     //------- Sales Invoice addition of tax and additional discount
			     $( "input.invoiceField" ).change(function() {
			    	 var addTaxAmt = (Number($("#idSubTotal").html())-Number($("#idAddDisc").val()))*Number($("#idAddTax").val())/100.0;
			    	 $("#idTotalAmt").html(Math.round((Number($("#idSubTotal").html())+addTaxAmt-Number($("#idAddDisc").val()))*100)/100.0);
			    	 $("#idAllTotal").html(Math.round((Number($("#idTotalAmt").html()) + Number($("#idOutstandingAmt").html()))*100)/100.0);
			    	 $("#idTotalDiscount").html(Math.round((Number(gridDisc) + Number($("#idAddDisc").val()))*100)/100.0);
			    	 $("#idTotalTax").html(Math.round((Number(gridTax) + Number(addTaxAmt))*100)/100.0);
			     });
			     
			   //------------ AutoComplete Customer Name--------------
			     var objJsonCustomer = '<%= CommonUtil.getJSONColumns(DBCollectionEnum.MAST_CUSTOMER, "_id", "name", "outStandingAmount") %>';
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
					     $( "#idCustomerID" ).val( ui.item.id );
					     $( "#idOutstandingAmt" ).html( Math.round(ui.item.outStandingAmount*100)/100 );
						 calculateTotalAmount();
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
			   
					//-----> Tab from Date to move to Grid first row (setting focus on jqgrid)
					$('#idShowCustomer').bind('keydown', 'Tab', function(){
					    console.log("Tab pressed...");
					    grid_selector.jqGrid("editCell",1,2,false);
						
				    });
			});			
			
			// ENTER Event to TAB 
			/* $(document).ready(function () {
			    $('#gridDiv').bind('keydown', 'return', function (e) {
			    	//alert(e);
			        if (e.keyCode == 13) e.keyCode = 9;
			        return false;
			    });
			});
			 */

			//-------------------------------------------
			//------- Product Fetch Invoice -------------
			//-------------------------------------------
			
			function ajaxProductFetch(fetchBy,value,fetchAllField){
				var urlPath = "sales.action?op=get&fetchBy="+fetchBy+"&value="+value+"&withReferences="+fetchAllField;
				var proddata;
				$.ajax({
					type: "POST",
					url: urlPath,
					dataType: 'json',
					async:false
					})
					.complete(function( data ) {
						console.log("Data Response:" + data.responseJSON);
						proddata = data.responseJSON;
						if(proddata && proddata.batchList){
							proddata.batchNumber=proddata.batchList[0].batchNumber;
							proddata.salesRate=proddata.batchList[0].salesRate;
						}
					})
					.fail(function() {
						console.log( "[async MSG]error in fetching data from server....." );
					});
					
				return proddata;
			}
			
			 //------------ Customer Info ------------
			 function showCustomerInfo(){
				 try{
					
				var urlPath = "customermaster.action?op=VIEW&custid="+$("#idCustomerID").val();
				var cusomerInfo;
				$.ajax({
					type: "POST",
					url: urlPath,
					dataType: 'json',
					async:false
					})
					.complete(function( data ) {
						console.log("Data Response:" + data.responseJSON);
						cusomerInfo = data.responseJSON;
					})
					.fail(function() {
						console.error( "[async MSG]error in fetching data from server....." );
					});
				
				var htmlBody =  $("<div class='profile-user-info profile-user-info-striped'> \
									<div class='profile-info-row'> \
										<div class='profile-info-name'> Customer Name </div> \
										<div class='profile-info-value'> \
											<span class='' >"+ cusomerInfo.name +"</span> \
										</div> \
									</div> \
									<div class='profile-info-row'> \
										<div class='profile-info-name'> Phone </div> \
										<div class='profile-info-value'> \
											<i class='icon-phone light-orange bigger-110'></i> \
											<span class='' >"+ cusomerInfo.phone +"</span> \
										</div> \
									</div> \
									<div class='profile-info-row'> \
										<div class='profile-info-name'> Address </div> \
										<div class='profile-info-value'> \
											<i class='icon-map-marker light-orange bigger-110'></i> \
											<span >"+ cusomerInfo.address +"</span> \
											<span >"+ cusomerInfo.city +"</span> \
											<span >"+ cusomerInfo.pin +"</span> \
										</div> \
									</div> \
									<div class='profile-info-row'> \
										<div class='profile-info-name'> Outstanding Amount </div> \
										<div class='profile-info-value'> \
											<span class=''>"+ cusomerInfo.outStandingAmount +"</span> \
										</div> \
									</div> \
								</div>");
				 bootbox.dialog({
						message: htmlBody, 
						onEscape: function() {},
						title: "<span class='blue'>Customer Detail (<span class='red'>"+cusomerInfo.name+"</span>)</span>",
						buttons: {
						    danger: {
						      label: "Cancel",
						      className: "btn-sm btn-danger"
						    }
						}
					});
				 
				 }catch(e){
					console.error(e.message); 
				 }
				 
			 }
			 
			 //-------------- Payment Dialog-----------
			 function popupPayment(){
				var htmlForm = $('<div class="center blue bigger-150">Payment Detail</div><div class="hr"></div><form id="idPayForm" role="form"> \
						<div class="form-group row"><label class="col-sm-5" ><b>Outstanding Amount :</b></label> <label >' + $("#idOutstandingAmt").html() + '</label></div>\
						<div class="form-group row"><label class="col-sm-5" ><b>Bill Amount :</b></label> <label >' + $("#idTotalAmt").html() + '</label></div>\
						<div class="form-group row"><label class="col-sm-5" ><b>Total Amount :</b></label> <label class="normal-tooltip tooltip-info" data-placement="right" data-rel="tooltip" title="Outstanding + Bill Amount">' + $("#idAllTotal").html() + '</label></div>\
						<div class="form-group row"><label class="col-sm-5" ><b>Pay Amount :</b></label> <input id="idPaidAmount" class="col-sm-3 middle tooltip-error" data-placement="right" data-rel="tooltip" title="Please enter amount to pay" autocomplete=off type=number /></div> \
						<div class="form-group row "><label class="col-sm-5" for="remType"><b>Payment Mode :</b></label> \
						<div class="col-sm-3 tooltip-error" id="pmID" data-placement="right" data-rel="tooltip" title="Please select payment mode"><label class="" ><input name="pMode" id="rd1" type="radio" value="1" class="ace"><span class="lbl"> Cash</span></label> \
						<label class="" ><input name="pMode" id="rd2" type="radio" value="2" class="ace"><span class="lbl"> Card</span></label></div> </div>\
						<fieldset id="fSh-1" style="display:none" class="toHide"></fieldset>\
						<fieldset id="fSh-2" style="display:none" class="toHide">\
						<div class="form-group"><label class="col-sm-5" for="idCardNumber"><b>Card Number :</b></label><input class="form-control" id="idCardNumber" placeholder="Enter Card Number"/></div> </fieldset> \
						</form>');
				 var payModal = bootbox.dialog({
						message: htmlForm, 
						title: "<span class='blue'></span>",
						onEscape: function() {},
						buttons: {
						    danger: {
						      label: "Cancel",
						      className: "btn-sm btn-danger"
						    },
						    success: {
							      label: "Done",
							      className: "btn-sm btn-primary",
							      callback:function() {
							    	 var paidAmount = $("#idPaidAmount").val();
							    	 var checkedPayMode = $('input[name=pMode]:checked').val();
									 if(paidAmount && checkedPayMode){
										 var gridData = $("#grid-table_pinvoice").jqGrid('getGridParam','data');
										 if(!gridData[gridData.length-1].name)
										 	gridData.splice(gridData.length-1,1);
										var urlPath = "sales.action?op=SAVE&custid="+$("#idCustomerID").val()+"&invoiceAddTaxPrice="+$("#idAddTax").val()+"&invoiceAddDiscountPrice="+$("#idAddDisc").val()+"&paymentMode="+$('input[name=pMode]:radio:checked').val()+"&cardNo="+$("#idCardNumber").val();
										var sSalesProducts = JSON.stringify(gridData);
										$.ajax({
											type: "POST",
											url: urlPath,
											dataType: 'json',
											data:{'paidAmount':paidAmount,'salesProductJSON':sSalesProducts},
											async:false
											})
											.complete(function( data ) {
												console.log("Data Response:" + data.responseJSON);
												alert("saved successfully");
											})
											.fail(function() {
												console.error( "[async MSG]:saveAndPrintInvoice > error in fetching data from server....." );
											});
											
										g_isDirty=false;
										$("#idPayBtn").prop("disabled",true);
									 }else{
										 if(!paidAmount)
										 	$("#idPaidAmount").tooltip('show');
										 if(!checkedPayMode)
											$("#pmID").tooltip('show');
										 return false;
									 }
							      }
							    }
						}
					});
				 $("[name=pMode]").click(function(){
					 $('.toHide').hide();
			        $("#fSh-"+$(this).val()).show('slow');
				});
				 $('.normal-tooltip').tooltip();
				 $('#idPaidAmount,#pmID').tooltip({trigger:'manual'});
			 }
	
			 //---------- Save and Print Invoice--------
			 function saveAndPrintInvoice(){
				var cID =$("#idCustomerID").val();
				var valida=true;
				if(!cID){
					$("#idCustomer").tooltip('show');
					valida =false;
				}
				var gridData = $("#grid-table_pinvoice").jqGrid('getGridParam','data');
				if(gridData[0].name==""){
					$("#gridDiv").tooltip('show');
					valida = false;
				}
				if(valida)
					popupPayment();
				else{
					setTimeout(function() {
					 $("#idCustomer").tooltip('hide');
					 $("#gridDiv").tooltip('hide');
					}, 5000);
					return;
				}
			 }
</script>
