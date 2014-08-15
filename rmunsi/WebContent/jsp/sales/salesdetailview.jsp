
<%@page import="com.munsi.pojo.invoice.sales.SalesInvoice"%>
<%@page import="com.munsi.util.Constants.DBCollectionEnum"%>
<%@page import="com.munsi.util.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% SalesInvoice sInvoice = (SalesInvoice)request.getAttribute("INVOICE_DETAIL"); %>

<div class="row page-header">
	<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12" style="padding: 0px; line-height: 1.2">
		<div class="center col-xs-12 col-md-4 col-sm-4 col-lg-4">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name"> Invoice No </div>
					<div class="profile-info-value">
						<span class="bigger-110" ><b><%=sInvoice.getInvoiceNumber() %></b></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> Invoice Date </div>
					<div class="profile-info-value">
						<span class="" ><%=sInvoice.getSctime() %></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> Customer </div>
					<div class="profile-info-value">
						<span class="" ><%=sInvoice.getCustomer().getName() %></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> Phone </div>
					<div class="profile-info-value">
						<i class="icon-phone light-orange bigger-110"></i>
						<span class="" ><%=sInvoice.getCustomer().getPhone() %></span>
					</div>
				</div>
				
			</div>
		</div>
		<div class="center col-xs-12 col-sm-7 col-md-7 col-lg-7">
			<div class="grid4">
				Outstanding Amount <br> <span class="bigger-125 blue"> <i class="icon-rupee"></i> <span id="idOutstandingAmt"><%=sInvoice.getCustomer().getOutStandingAmount() %></span></span>
				<div class="hr hr16 dotted"></div>
				<div class="input-group">
				  <input type="number" readonly="readonly" class="form-control invoiceField" id="idAddTax" data-rel="tooltip" title="Additional TAX" tabindex="3" style="text-align: center;" placeholder="Tax" value='<%=sInvoice.getInvoiceTaxPercent() %>'>
				  <span class="input-group-addon">%</span>
				</div>
			</div>
			<div class="grid4">
				DISCOUNT<br> <span class="bigger-125 blue"><i class="icon-rupee"></i> <span id="idTotalDiscount">0</span></span>
				<div class="hr hr16 dotted"></div>
				<!-- Add. DISCOUNT<br> <span class="bigger-125 blue"><i class="icon-rupee"></i> 0</span> -->
				<div class="input-group">
				  <input type="number" class="form-control invoiceField" readonly="readonly" id="idAddDisc" data-rel="tooltip" title="Additional Discount"  tabindex="4" style="text-align: center;" placeholder="Discount"  value='<%=sInvoice.getInvoiceDiscountPrice() %>'>
				  <span class="input-group-addon"><i class="icon-rupee"></i></span>
				</div>
			</div>
			
			<div class="grid4">
				SUBTOTAL<br/><span class="bigger-125 blue"><i class="icon-rupee"></i> <span id="idSubTotal"></span></span>
				<div class="hr hr16 dotted"></div>
				TAX<br/><span class="bigger-125 green"><i class="icon-rupee"></i> <span id="idTotalTax">0</span></span>
			</div>
			<div class="grid4">
				<pre style="background-color:#FFB752"><span class="bigger-200"><strong> <span id="idTotalAmt"><%=sInvoice.getNetPayblePrice() %></span></strong></span><br/>Bill Amount(<i class="icon-rupee"></i>)</pre>
			</div>
		</div>
		<div class="center col-xs-12 col-md-1 col-sm-1 col-lg-1 hidden-print">
			
			<button class="btn btn-sm btn-light pull-right" onclick="" type="button" tabindex="4" data-rel="tooltip" title="Print Bill" data-placement="bottom">
				Print <i class="icon-print"></i> 
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
	 var mydata = <%=CommonUtil.objectToJson(sInvoice.getSalesProductList()).replaceAll("_id", "id") %>;
	 var gridDisc,gridTax;
	 
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
					cellEdit : false,
					colNames:['id','Barcode','Code','Name', 'Quantity', 'Rate', 'Tax','%','Rs','Free Qty.',' Total Qty.','Total Amt.'],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", sortable:false, editable: false, hidden:true},
						{name:'barCode',index:'barCode', width:100, sortable:false},
						{name:'code',index:'code', width:100, sortable:false},
						{name:'name',index:'name', width:250, sortable:false},
						{name:'quantity',index:'quantity', sortable:false, align:'right', width:90, formatter:'integer', sorttype:'int'},
						{name:'salesRate',index:'salesRate', width:90, sortable:false, align:'right',formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'derSumOfProudctTax',index:'derSumOfProudctTax', width:80, sortable:false, align:'right', editable: false,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'rawDiscountPercent',index:'rawDiscountPercent', sortable:false, width:80,align:'right',formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'rawDiscountPrice',index:'rawDiscountPrice', sortable:false, width:90,align:'right',formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'freeQuantity',index:'freeQuantity', sortable:false, align:'right', width:90, formatter:'integer', sorttype:'int'},
						{name:'totalQuantity',index:'totalquantity', sortable:false, align:'right', width:100,editable: false,formatter:'integer'},
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

                    i=getColumnIndexByName(grid_selector,'tax');
                    $("tbody > tr.jqgrow > td:nth-child("+(i+1)+")",grid_selector[0]).each(function() {
                        totalTax += Number(getTextFromCell(this));
                    });
	                    
			         grid_selector.jqGrid('footerData','set',{name:'TOTAL',netPaybleProductPrice:totalAmount,tax:totalTax});
			         $("#idSubTotal").html(Number(totalAmount).toFixed(2));
			         var addTaxAmt = Number($("#idSubTotal").html())*Number($("#idAddTax").val())/100;
			    	 $("#idTotalAmt").html((Number($("#idSubTotal").html())+addTaxAmt-Number($("#idAddDisc").val())).toFixed(2));
			    	 $("#idAllTotal").html((Number($("#idTotalAmt").html()) + Number($("#idOutstandingAmt").html())).toFixed(2));
			     };
			     
			     function getRowAndPopulate(rowid,name,val)
             	 {
	                	var rowData = grid_selector.jqGrid('getRowData', rowid);

	                	switch(name) {
		                    case "name":
								var prodData =ajaxProductFetch(name,val,true);
						        if(prodData){
			                        rowData.barCode=prodData.barCode?prodData.barCode:"";
			                        rowData.code=prodData.code?prodData.code:"";
			                        rowData.name=prodData.name?prodData.name:"";
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.freeQuantity="0";
			                        rowData.tax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.totalquantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
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
			                        rowData.barCode=prodData.barCode?prodData.barCode:"";
			                        rowData.code=prodData.code?prodData.code:"";
			                        rowData.name=prodData.name?prodData.name:"";
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.tax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.freeQuantity="0";
			                        rowData.totalquantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
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
			                        rowData.barCode=prodData.barCode?prodData.barCode:"";
			                        rowData.code=prodData.code?prodData.code:"";
			                        rowData.name=prodData.name?prodData.name:"";
			                        rowData.quantity="1";
			                        rowData.salesRate=prodData.salesRate;
			                        rowData.tax=prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:0;
			                        rowData.freeQuantity="0";
			                        rowData.totalquantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
			                        var taxValpercent = prodData.derSumOfProudctTax?prodData.derSumOfProudctTax:1;
			                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
			                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;
		                        }
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "quantity":
		                    	rowData.totalquantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
		                        var taxValpercent = rowData.tax?rowData.tax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "salesRate":
		                    	var taxValpercent = rowData.tax?rowData.tax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee;
		                        grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "rawDiscountPercent":
		                    	var taxValpercent = rowData.tax?rowData.tax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
		                    	var discPercent = rowData.rawDiscountPercent?rowData.rawDiscountPercent:0;
			                	rowData.rawDiscountPrice = ((Number(rowData.netPaybleProductPrice)*Number(discPercent))/100);
			                	//rowData.netPaybleProductPrice=  Number(rowData.netPaybleProductPrice) - Number(rowData.rawDiscountPrice);
			                	rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee-Number(rowData.rawDiscountPrice);
			                	grid_selector.jqGrid('setRowData', rowid, rowData);
		                		break;
		                    case "rawDiscountPrice":
	 	                    	var taxValpercent = rowData.tax?rowData.tax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
		                        var discPrice = rowData.rawDiscountPrice?rowData.rawDiscountPrice:0;
			                	rowData.rawDiscountPercent = (discPrice*100)/Number(rowData.netPaybleProductPrice);
			                	//rowData.netPaybleProductPrice=  Number(rowData.netPaybleProductPrice) - Number(rowData.rawDiscountPrice);
		                        rowData.netPaybleProductPrice=(Number(rowData.quantity)*Number(rowData.salesRate)) + taxValrupee-Number(rowData.rawDiscountPrice);
			                	
		                    	grid_selector.jqGrid('setRowData', rowid, rowData);
		                        break;
		                    case "freeQuantity":
		                    	rowData.totalquantity=Number(rowData.quantity)+Number(rowData.freeQuantity);
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
		                	var taxValpercent = gridData[i].tax?gridData[i].tax:0;
	                		tTemp = tTemp + (Number(gridData[i].quantity)*Number(gridData[i].salesRate)*Number(taxValpercent))/100;
		                }
	                	gridTax=Math.round(tTemp*100)/100;
	                	$("#idTotalTax").html(gridTax);
	                	
	                	//------------- Sales Invoice Discount Rs Calculation
	                	tTemp=0;
	                	for(i=0;i<gridData.length-1;i++){
		                	var discountAmt = gridData[i].rawDiscountPrice?gridData[i].rawDiscountPrice:0;
	                		tTemp = tTemp + Number(gridData[i].rawDiscountPrice);
		                }
	                	gridDisc=Math.round(tTemp*100)/100;
		                $("#idTotalDiscount").html(gridDisc);
		                g_isDirty=true;
             	}
			     
			     //------- Sales Invoice addition of tax and additional discount
			     $( "input.invoiceField" ).change(function() {
			    	 var addTaxAmt = Number($("#idSubTotal").html())*Number($("#idAddTax").val())/100;
			    	 $("#idTotalAmt").html(Math.round((Number($("#idSubTotal").html())+addTaxAmt-Number($("#idAddDisc").val()))*100)/100);
			    	 $("#idAllTotal").html(Math.round((Number($("#idTotalAmt").html()) + Number($("#idOutstandingAmt").html()))*100)/100);
			    	 $("#idTotalDiscount").html(Math.round((Number(gridDisc) + Number($("#idAddDisc").val()))*100)/100);
			    	 $("#idTotalTax").html(Math.round((Number(gridTax) + Number(addTaxAmt))*100)/100);
			     });
			     					
			   //-----> press alt + g for setting focus on jqgrid
					$(document).bind('keydown', 'Alt+g', function(){
					    var	ids = grid_selector.jqGrid("getDataIDs");
						if(ids && ids.length > 0){
							grid_selector.editCell(1,2,true);
							$("#" + $.jgrid.jqID(ids[0]) + "_code").focus();
						}
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
					})
					.fail(function() {
						console.error( "[async MSG]error in fetching data from server....." );
					});
				return proddata;
			}
			
			 //------------ Customer Info ------------
			 function showCustomerInfo(){
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
				
				var msgStr = "<div class='profile-user-info profile-user-info-striped'><div class='profile-info-row'><div class='profile-info-name'> Username </div><div class='profile-info-value'><span class='editable editable-click' id='username'>alexdoe</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Location </div><div class='profile-info-value'><i class='icon-map-marker light-orange bigger-110'></i><span class='editable editable-click' id='country'>Netherlands</span><span class='editable editable-click' id='city'>Amsterdam</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Age </div><div class='profile-info-value'><span class='editable editable-click' id='age'>38</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Joined </div><div class='profile-info-value'><span class='editable editable-click' id='signup'>20/06/2010</span></div></div><div class='profile-info-row'><div class='profile-info-name'> Last Online </div><div class='profile-info-value'><span class='editable editable-click' id='login'>3 hours ago</span></div></div><div class='profile-info-row'><div class='profile-info-name'> About Me </div><div class='profile-info-value'><span class='editable editable-click' id='about'>Editable as WYSIWYG</span></div></div></div>";
				
				 bootbox.dialog({
						message: msgStr, 
						title: "<span class='blue'>Customer Detail (<span class='red'>"+cusomerInfo.name+"</span>)</span>",
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
				 var urlPath = "sales.action?op=SAVE&custid="+$("#idCustomerID").val()+"&invoiceAddTaxPrice="+$("#idAddTax").val()+"&invoiceAddDiscountPrice="+$("#idAddDisc").val()+"&salesProductJSON="+JSON.stringify(gridData);
					
				$.ajax({
					type: "POST",
					url: urlPath,
					dataType: 'json',
					async:false
					})
					.complete(function( data ) {
						console.log("Data Response:" + data.responseJSON);
						alert("saved successfully");
					})
					.fail(function() {
						console.error( "[async MSG]error in fetching data from server....." );
					});
					
					 
			 }
</script>
