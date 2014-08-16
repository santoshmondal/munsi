
<%@page import="com.munsi.pojo.invoice.purchase.PurchaseInvoice"%>
<%@page import="com.munsi.util.Constants.DBCollectionEnum"%>
<%@page import="com.munsi.util.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% PurchaseInvoice pInvoice = (PurchaseInvoice)request.getAttribute("INVOICE_DETAIL"); %>

<style>
	.fixedHeightDiv{
		height: 120px;
		overflow: auto;
	}
	.hr{
		margin: 2px 0 !important;
	}
</style>
<div class="row page-header">
	<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12" style="padding: 0px; line-height: 1.2">
		<div class="center col-xs-12 col-md-3 col-sm-3 col-lg-3">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name"> Supplier </div>
					<div class="profile-info-value">
						<span class="" ><%=pInvoice.getSupplier().getName() %></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> Phone </div>
					<div class="profile-info-value">
						<i class="icon-phone light-orange bigger-110"></i>
						<span class="" ><%=pInvoice.getSupplier().getPhone() %></span>
					</div>
				</div>
			</div>
		</div>
		<div class="center col-xs-12 col-sm-8 col-md-8 col-lg-8">
			<div class="grid4 fixedHeightDiv">
				<div class="input-group input-icon-right">
				  <input type="text" class="form-control invoiceField" id="idSupplierInv" readonly="readonly" tabindex="3" style="text-align: center;" placeholder="Invoice No." value="<%=pInvoice.getInvoiceNumber() %>" >
				  <span class="input-group-addon"><i class="icon-edit blue"></i></span>
				</div>
				<div class="hr dotted"></div>
				<div class="input-group input-icon-right">
				  <input type="text" class="form-control invoiceField datepicker" id="idSupplierInvDate" readonly="readonly" tabindex="3" style="text-align: center;" placeholder="Invoice Date"  value="<%=pInvoice.getSctime() %>" >
				  <span class="input-group-addon"><i class="icon-calendar blue"></i></span>
				</div>
			</div>
			<div class="clearfix">
				<div class="grid2 fixedHeightDiv" style="padding-right: 0px; margin-right: 5px; ">
					<div class="input-group">
					  <input type="number" class="form-control invoiceField" id="idAddFreight" readonly="readonly" data-rel="tooltip" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Freight" tabindex="4" style="text-align: center;" placeholder="Freight"  value="<%=pInvoice.getFreight() %>" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
					<div class="hr dotted"></div>
	
					<div class="input-group col-xs-6" style='padding: 0px;'>
					  <span class="input-group-addon">%</span>
					  <input type="number" class="form-control invoiceField tooltip-info" id="idAddDiscPer" readonly="readonly" data-rel="tooltip" data-toggle="tooltip" title="Discount %"  data-placement="bottom" tabindex="4" style="text-align: center;" placeholder="Discount %" >
					</div>
					
					<div class="input-group col-xs-6" style='padding: 0px;'>
					  <input type="number" class="form-control invoiceField tooltip-info" id="idAddDiscPrice" readonly="readonly" data-rel="tooltip" title="Discount Rs" data-placement="bottom" tabindex="4" style="text-align: center;" placeholder="Discount Rs" value="<%=pInvoice.getInvoiceDiscountPrice() %>" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
					<div class="hr dotted"></div>
					<div class="input-group">
					  <input type="number" readonly="readonly" class="form-control invoiceField" id="idAddTax" data-rel="tooltip" title="Total TAX" data-placement="top" tabindex="4" style="text-align: center;" placeholder="Tax" value="<%=pInvoice.getInvoiceTaxPercent() %>" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
					<div class="hr dotted"></div>
					<div class="input-group">
					  <input type="number" class="form-control invoiceField" id="idRounding" data-rel="tooltip" title="Enter a positive or a negative value to round the Total" readonly="readonly" tabindex="4" data-placement="top" style="text-align: center;" placeholder="Round off Amount" value="<%=pInvoice.getRoundOfAmount() %>" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
				</div>
				<div class="grid5 fixedHeightDiv">
					<pre style="background-color:#87B87F"><span class="bigger-200"><strong><span id="idTotalAmt">0</span></strong></span><br/>Bill Amount(<i class="icon-rupee"></i>)</pre>
					<span id="idSubTotal" ></span>
				</div>
			</div>
		</div>
		<!-- <div class="center col-xs-12 col-md-1 col-sm-1 col-lg-1 pull-right hidden-print">
			<button class="btn btn-sm btn-inverse pull-right" type="button" onclick="" tabindex="5" data-rel="tooltip" title="Save (Alt+s)" data-placement="bottom">
				<i class="icon-save"></i> Save
			</button>
		</div> -->
	</div>

</div>
<!-- /.page-header -->

<div class="row">
	<!-- PAGE CONTENT BEGINS -->

	<div class="col-xs-12">
		<table id="grid-table_pinvoice"></table>
		<div id="grid-pager_pinvoice"></div>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<script type="text/javascript">
	 var mydata =  <%=CommonUtil.objectToJson(pInvoice.getPurchaseProductList()).replaceAll("_id", "id") %>;
	 var gridDisc,gridTax;
			jQuery(function($) {
				var grid_selector = jQuery("#grid-table_pinvoice");
				var pager_selector_id = "#grid-table_pinvoice_toppager";
				var colModel, i, cmi, tr = "<tr>", skip = 0, ths,curCellname,curRow ;
				
			     
				grid_selector.jqGrid({
					
					datatype: "local",
                	data: mydata,
                	height: '328',
                	rownumbers:true,
					cellsubmit: 'clientArray',
					cellEdit : true,
					colNames:['id','Barcode','Code','Name', 'Qty', 'Pur. Rate', 'Sale Rate', 'MRP','Tax','%','Rs','Free Qty.',' Total Qty.','Total Amt.'],
					colModel:[
						{name:'_id',index:'id', width:60, sorttype:"int", sortable:false, editable: false, hidden:true},
						{name:'barCode',index:'barCode', width:100, sortable:false, editable: false},
						{name:'code',index:'code', width:100, sortable:false, editable: false},
						{name:'name',index:'name', width:250, sortable:false, editable: false},
						{name:'quantity',index:'quantity', sortable:false, align:'right', width:70,editable: false, formatter:'integer', sorttype:'int'},
						{name:'purchaseRate',index:'purchaseRate', width:90, sortable:false, align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'salesRate',index:'salesRate', width:90, sortable:false, align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'mrp',index:'mrp', width:90, sortable:false, align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'derSumOfProudctTax',index:'derSumOfProudctTax', editable: false, width:80, sortable:false, align:'right', formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'rawDiscountPercent',index:'rawDiscountPercent', sortable:false, width:80,align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'rawDiscountPrice',index:'rawDiscountPrice', sortable:false, width:90,align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'freeQuantity',index:'freeQuantity', sortable:false, align:'right', width:90,editable: false, formatter:'integer', sorttype:'int'},
						{name:'totalQuantity',index:'totalQuantity', sortable:false, align:'right', width:100,editable: false,formatter:'integer'},
						{name:'netPaybleProductPrice',index:'netPaybleProductPrice', sortable:false, align:'right', width:120,editable: false,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}}
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
	                	getRowAndPopulate(rowid,name,val);
	                	calculateTotalAmount();
	                }/* ,
	                beforeSelectRow: function(rowid) {
	                 //   return false;
	                } */,
	                loadComplete : function() {
						var table = this;
						lastsel2 = 1;
						setTimeout(function(){
							enableTooltips(table);
						}, 0);
						
						$(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
						$('#grid-table_pinvoice input').css({'background': 'transaprent !important',
						    'border': '0 none'});
							
						//---- Setting Auto Total in Footer --
						calculateTotalAmount();
	                    
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
		             i=getColumnIndexByName(grid_selector,'netPaybleProductPrice'); // nth-child need 1-based index so we use (i+1) below
			         $("tbody > tr.jqgrow > td:nth-child("+(i+1)+")",grid_selector[0]).each(function() {
			        	    
			        	 totalAmount += Number(getTextFromCell(this));
			         });

	                    
			         grid_selector.jqGrid('footerData','set',{name:'TOTAL',netPaybleProductPrice:totalAmount});
			         $("#idSubTotal").html(Math.round((Number(totalAmount))*100)/100);
			    	 $("#idTotalTax").html(Math.round((Number(gridTax))*100)/100);
			    	 var addDisc = $("#idAddDiscPrice").val();
			    	 addDisc = addDisc?addDisc:0;
			    	 $("#idTotalAmt").html(Math.round((Number($("#idSubTotal").html())-Number(addDisc))*100)/100);
			    	 
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
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.purchaseRate=prodData.purchaseRate;
			                        rowData.mrp=prodData.mrp;
			                        rowData.freeQuantity="0";
			                        rowData.derSumOfProudctTax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
			                        rowData.netPaybleProductPrice=Number(rowData.quantity)*Number(rowData.purchaseRate);
									
			                        var taxValpercent = prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:1;
			                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.purchaseRate)*Number(taxValpercent))/100;
			                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.purchaseRate)) + taxValrupee;
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
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.purchaseRate=prodData.purchaseRate;
			                        rowData.mrp=prodData.mrp;
			                        rowData.derSumOfProudctTax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.freeQuantity="0";
			                        rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
			                        rowData.netPaybleProductPrice=Number(rowData.quantity)*Number(rowData.purchaseRate);
			                        var taxValpercent = prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:1;
			                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.purchaseRate)*Number(taxValpercent))/100;
			                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.purchaseRate)) + taxValrupee;

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
			                        rowData.purchaseRate=prodData.purchaseRate;
			                        rowData.mrp=prodData.mrp;
			                        rowData.derSumOfProudctTax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.freeQuantity="0";
			                        rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
			                        var taxValpercent = prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:1;
			                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.purchaseRate)*Number(taxValpercent))/100;
			                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.purchaseRate)) + taxValrupee;
		                        }
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "quantity":
		                    	rowData.totalQuantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
		                        var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.purchaseRate)*Number(taxValpercent))/100;
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.purchaseRate)) + taxValrupee;
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "purchaseRate":
		                    	var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.purchaseRate)*Number(taxValpercent))/100;
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.purchaseRate)) + taxValrupee;
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "rawDiscountPercent":
		                    	var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.purchaseRate)*Number(taxValpercent))/100;
		                    	var discPercent = rowData.rawDiscountPercent?rowData.rawDiscountPercent:0;
			                	rowData.rawDiscountPrice = ((Number(rowData.netPaybleProductPrice)*Number(discPercent))/100);
			                	rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.purchaseRate)) + taxValrupee-Number(rowData.rawDiscountPrice);
			                	grid_selector.jqGrid('setRowData', rowid, rowData);
		                		break;
		                    case "rawDiscountPrice":
	 	                    	var taxValpercent = rowData.derSumOfProudctTax?rowData.derSumOfProudctTax:1;
		                        taxValrupee = (Number(rowData.quantity)*Number(rowData.purchaseRate)*Number(taxValpercent))/100;
		                        var discPrice = rowData.rawDiscountPrice?rowData.rawDiscountPrice:0;
			                	rowData.rawDiscountPercent = (discPrice*100)/Number(rowData.netPaybleProductPrice);
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.purchaseRate)) + taxValrupee-Number(rowData.rawDiscountPrice);
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
	                		tTemp = tTemp + (Number(gridData[i].quantity)*Number(gridData[i].purchaseRate)*Number(taxValpercent))/100;
		                }
	                	
	                	gridTax=Math.round(tTemp*100)/100;
	                	$("#idAddTax").val(gridTax);
	                	
	                	/* //------------- Sales Invoice Discount Rs Calculation
	                	tTemp=0;
	                	for(i=0;i<gridData.length-1;i++){
		                	var discountAmt = gridData[i].rawDiscountPrice?gridData[i].rawDiscountPrice:0;
	                		tTemp = tTemp + Number(gridData[i].rawDiscountPrice);
		                }
	                	gridDisc=Math.round(tTemp*100)/100;
		                $("#idTotalDiscount").html(gridDisc);
		                 */
		                 g_isDirty=true;
             	}
			     
			     //------- Sales Invoice addition of tax and additional discount
			     $( "input.invoiceField" ).change(function() {
			    	 $("#idTotalAmt").html(Math.round((Number($("#idSubTotal").html())-Number($("#idAddDiscPrice").val())+Number($("#idAddFreight").val())+Number($("#idRounding").val()))*100)/100);
			     });
			      
			     
			     $( "input#idAddDiscPer").change(function() {
			    	 $("#idAddDiscPrice").val(Math.round((Number($("#idSubTotal").html())*Number($("#idAddDiscPer").val()))/100)*100/100);
			     });
			     

			     $( "input#idAddDiscPrice").change(function() {
			    	 $("#idAddDiscPer").val(Math.round((Number($("#idAddDiscPrice").val())*100)/Number($("#idSubTotal").html()))*100/100);
			     });

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
					})
					.fail(function() {
						console.error( "[async MSG]error in fetching data from server....." );
					});
				return proddata;
			}
			
			 //------------ Supplier Info ------------
			 function showSupplierInfo(){
				var urlPath = "suppliermaster.action?op=VIEW&supplierid="+$("#idSupplier-id").val();
				var supplierInfo;
				$.ajax({
					type: "POST",
					url: urlPath,
					dataType: 'json',
					async:false
					})
					.complete(function( data ) {
						console.log("Data Response:" + data.responseJSON);
						supplierInfo= data.responseJSON;
					})
					.fail(function() {
						console.error( "[async MSG]error in fetching data from server....." );
					});
				
				var msgStr = "<div class='profile-user-info profile-user-info-striped'><div class='profile-info-row'><div class='profile-info-name'> Username </div><div class='profile-info-value'><span class='editable editable-click' id='username'>alexdoe</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Location </div><div class='profile-info-value'><i class='icon-map-marker light-orange bigger-110'></i><span class='editable editable-click' id='country'>Netherlands</span><span class='editable editable-click' id='city'>Amsterdam</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Age </div><div class='profile-info-value'><span class='editable editable-click' id='age'>38</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Joined </div><div class='profile-info-value'><span class='editable editable-click' id='signup'>20/06/2010</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Last Online </div><div class='profile-info-value'><span class='editable editable-click' id='login'>3 hours ago</span></div></div><div class='profile-info-row'><div class='profile-info-name'> About Me </div><div class='profile-info-value'><span class='editable editable-click' id='about'>Editable as WYSIWYG</span></div></div></div>";
				
				 bootbox.dialog({
						message: msgStr, 
						title: "<span class='blue'>Supplier Detail (<span class='red'>"+supplierInfo.name+"</span>)</span>",
						buttons: {
						    danger: {
						      label: "Cancel",
						      className: "btn-sm btn-danger"
						    }
						}
					});
			 }
			 
			 //---------- Save and Print Invoice--------
			 function saveAndPrintInvoice(){
				var gridData = $("#grid-table_pinvoice").jqGrid('getGridParam','data');
				gridData.splice(gridData.length-1,1);
				var urlPath = "purchase.action?op=SAVE&supplierid="+$("#idSupplier-id").val()+"&invDate="+$("#idSupplierInvDate").val()+"&invoiceTaxPrice="+$("#idAddTax").val()+"&invoiceDiscountPrice="+$("#idAddDiscPrice").val()+"&invoiceNumber="+$("#idSupplierInv").val()+"&freight="+$("#idAddFreight").val()+"&roundOfAmount="+$("#idRounding").val();
					
				$.ajax({
					type: "POST",
					url: urlPath,
					dataType: 'json',
					data:{purchaseProductJSON:JSON.stringify(gridData)},
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
			 }
		});
</script>
