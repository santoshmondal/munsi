
<%@page import="com.munsi.util.Constants.DBCollectionEnum"%>
<%@page import="com.munsi.util.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="form-group" style="background-color:#eee;  border:1px solid #f59942; padding: 10px 0px 10px 10px">
				<div class="input-group" style="margin-right: 10px">
				  <input type="text" id="idSupplier" class="form-control" tabindex="1" placeholder="Enter Supplier Name" >
				  <input type="hidden" id="idSupplier-id" >
				  <span class="input-group-addon" style="padding: 0px 10px;">
				  	<a href="#" onclick='showSupplierInfo()' tabindex="2" class="btn btn-inverse btn-xs" data-rel="tooltip" title="Show Supplier Detail">
				  		<i class="icon-user white"></i>
				  	</a>
				  </span>
				</div>
			</div>
		</div>
		<div class="center col-xs-12 col-sm-8 col-md-8 col-lg-8">
			<div class="grid4 fixedHeightDiv">
				<div class="input-group input-icon-right">
				  <input type="text" class="form-control invoiceField" id="idSupplierInv" tabindex="3" style="text-align: center;" placeholder="Invoice No." >
				  <span class="input-group-addon"><i class="icon-edit blue"></i></span>
				</div>
				<div class="hr dotted"></div>
				<div class="input-group input-icon-right">
				  <input type="text" class="form-control invoiceField datepicker" id="idSupplierInvDate" tabindex="3" style="text-align: center;" placeholder="Invoice Date" >
				  <span class="input-group-addon"><i class="icon-calendar blue"></i></span>
				</div>
			</div>
			<div class="clearfix">
				<div class="grid2 fixedHeightDiv" style="padding-right: 0px; margin-right: 5px; ">
					<div class="input-group">
					  <input type="number" class="form-control invoiceField" id="idAddFreight" data-rel="tooltip" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Freight" tabindex="4" style="text-align: center;" placeholder="Freight" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
					<div class="hr dotted"></div>
	
					<div class="input-group col-xs-6" style='padding: 0px;'>
					  <span class="input-group-addon">%</span>
					  <input type="number" class="form-control invoiceField tooltip-info" id="idAddDiscPer" data-rel="tooltip" data-toggle="tooltip" title="Discount %"  data-placement="bottom" tabindex="4" style="text-align: center;" placeholder="Discount %" >
					</div>
					
					<div class="input-group col-xs-6" style='padding: 0px;'>
					  <input type="number" class="form-control invoiceField tooltip-info" id="idAddDiscPrice" data-rel="tooltip" title="Discount Rs" data-placement="bottom" tabindex="4" style="text-align: center;" placeholder="Discount Rs" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
					<div class="hr dotted"></div>
					<div class="input-group">
					  <input type="number" readonly="readonly" class="form-control invoiceField" id="idAddTax" data-rel="tooltip" title="Total TAX" data-placement="top" tabindex="4" style="text-align: center;" placeholder="Tax" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
					<div class="hr dotted"></div>
					<div class="input-group">
					  <input type="number" class="form-control invoiceField" id="idRounding" data-rel="tooltip" title="Enter a positive or a negative value to round the Total" tabindex="4" data-placement="top" style="text-align: center;" placeholder="Round off Amount" >
					  <span class="input-group-addon"><i class="icon-rupee red"></i></span>
					</div>
				</div>
				<div class="grid5 fixedHeightDiv">
					<pre style="background-color:#87B87F"><span class="bigger-200"><strong><span id="idTotalAmt">0</span></strong></span><br/>Bill Amount(<i class="icon-rupee"></i>)</pre>
				</div>
			</div>
		</div>
		<div class="center col-xs-12 col-md-1 col-sm-1 col-lg-1 pull-right hidden-print">
			<button class="btn btn-sm btn-inverse pull-right" type="button" tabindex="5" data-rel="tooltip" title="Save (Alt+s)" data-placement="bottom">
				<i class="icon-save"></i> Save
			</button><!-- 
			<button class="btn btn-sm btn-light pull-right" onclick="saveAndPrintInvoice()" type="button" tabindex="4" data-rel="tooltip" title="Pay Bill" data-placement="bottom">
				Pay <i class="icon-print"></i> 
			</button> -->
		</div>
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
	 var mydata = [
                   {id:"1", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''}
             ];
			
			jQuery(function($) {
				var grid_selector = jQuery("#grid-table_pinvoice");
				var pager_selector_id = "#grid-table_pinvoice_toppager";
				var colModel, i, cmi, tr = "<tr>", skip = 0, ths,lastSel=-1;
				
			     
				grid_selector.jqGrid({
					
					datatype: "local",
                	data: mydata,
                	height: '328',
					cellsubmit: 'clientArray',
					cellEdit : true,
					colNames:['id','Code','Name', 'Quantity', 'Rate', 'Unit','Tax','%','Rs','Free Qty.',' Total Qty.','Total Amount','MFG Date','EXP Date','Batch Number'],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", sortable:false, editable: false, hidden:true},
						{name:'code',index:'code', width:100, sortable:false, editable: true,unformat: pickCodeAutoComplete},
						{name:'name',index:'name', width:250, sortable:false, editable: true,unformat: pickNameAutoComplete},
						{name:'quantity',index:'quantity', sortable:false, align:'right', width:90,editable: true, formatter:'integer', sorttype:'int'},
						{name:'rate',index:'rate', width:90, sortable:false, align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'unit',index:'unit',width:80, sortable:false, editable: true, edittype:"select",editoptions:{ dataInit: function(elem) {$(elem).width(160);}, value:"Box:Box;Piece:Piece;KG:KG"}},
						{name:'tax',index:'tax', width:80, sortable:false, align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'discountpercent',index:'discountpercent', sortable:false, width:80,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'discountamount',index:'discountamount', sortable:false, width:90,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'freequantity',index:'freequantity', sortable:false, align:'right', width:90,editable: true, formatter:'integer', sorttype:'int'},
						//{name:'discount', width:130, hidden:true, editable: false,align:'center', formatter:function(){ return '<a href="action=manage_discount">Click here</a>';}},
						{name:'totalquantity',index:'totalquantity', sortable:false, align:'right', width:100,editable: true,formatter:'integer'},
						{name:'totalamount',index:'totalamount', sortable:false, align:'right', width:120,editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'mfgdate',index:'mfgdate', sortable:false, width:120, editable: true,sorttype:"date",unformat: pickDate},
						{name:'expdate',index:'expdate', sortable:false, width:120, editable: true,sorttype:"date",unformat: pickDate},
						{name:'batchno',index:'batchno', sortable:false, width:150, editable: true}
						
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
	                	calculateTotalAmount();
	                },
	                beforeSelectRow: function(rowid) {
	                    if (rowid !== lastSel) {
	                    	grid_selector.jqGrid('restoreRow',lastSel);
	                        lastSel = rowid;
	                    }
	                    return true;
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
						source: finProdCode
					});
					}, 0);
				}
			
				//autocomplete for Code
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
						source: finProdName
					});
					}, 0);
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip();
					$(table).find('.ui-pg-div').tooltip();
					$('[data-rel=tooltip]').tooltip();
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
			                	var discPercent = rowData.rawDiscountPercent?rowData.rawDiscountPercent:0;
			                	rowData.netPaybleProductPrice=  rowData.netPaybleProductPrice -((Number(rowData.netPaybleProductPrice)*Number(discPercent))/100);
		                		break;
		                    case "rawDiscountPrice":
		                    	var taxValpercent = rowData.tax?rowData.tax:1;
		                        var taxValrupee = (Number(rowData.quantity)*Number(rowData.salesRate)*Number(taxValpercent))/100;
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
	                	gridTax=Number(tTemp).toFixed(2);
	                	$("#idTotalTax").html(Number(tTemp).toFixed(2));
	                	
	                	//------------- Sales Invoice Discount Rs Calculation
	                	tTemp=0;
	                	for(i=0;i<gridData.length-1;i++){
		                	var discPercent = gridData[i].rawDiscountPercent?gridData[i].rawDiscountPercent:0;
		                	var discountAmt = gridData[i].rawDiscountPrice?gridData[i].rawDiscountPrice:0;
	                		tTemp = tTemp + ((Number(gridData[i].netPaybleProductPrice)*Number(discPercent))/100) + Number(gridData[i].rawDiscountPrice);
		                }
	                	gridDisc=Number(tTemp).toFixed(2);
		                $("#idTotalDiscount").html(Number(tTemp).toFixed(2));
		                g_isDirty=true;
             	}
			     
			     //------- Sales Invoice addition of tax and additional discount
			     $( "input.invoiceField" ).change(function() {
			    	 var addTaxAmt = Number($("#idSubTotal").html())*Number($("#idAddTax").val())/100;
			    	 $("#idTotalAmt").html((Number($("#idSubTotal").html())+addTaxAmt-Number($("#idAddDisc").val())).toFixed(2));
			    	 $("#idAllTotal").html((Number($("#idTotalAmt").html()) + Number($("#idOutstandingAmt").html())).toFixed(2));
			    	 $("#idTotalDiscount").html((Number(gridDisc) + Number($("#idAddDisc").val())).toFixed(2));
			    	 $("#idTotalTax").html((Number(gridTax) + Number(addTaxAmt)).toFixed(2));
			     });
			      
			     //------------ AutoComplete Supplier Name--------------
			     var objJsonSupplier = '<%= CommonUtil.getIdLabelJSON(DBCollectionEnum.MAST_SUPPLIER, "_id", "name", "") %>';
			    
			     objJsonSupplier = JSON.parse(objJsonSupplier.replace(/_id/g,"id").replace(/name/g,"label"));
			     $("#idSupplier").autocomplete({
					     minLength: 0,
					     source: objJsonSupplier,
					     focus: function( event, ui ) {
					     $( "#idSupplier" ).val( ui.item.label );
					     return false;
					     },
					     select: function( event, ui ) {
					     $( "#idSupplier" ).val( ui.item.label );
					     $( "#idSupplier-id" ).val( ui.item.id );
					     
					     return false;
					     }
				     })
				     .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
				     return $( "<li>" )
				     .append( "<a>" + item.label + "<span class='badge badge-primary pull-right'>"+ item.id  +"</span>"+ "</a>" )
				     .appendTo( ul );
				     };
				     
				     $('.datepicker').datepicker({format:'dd-mm-yyyy' , autoclose:true});
				
			});
			
				
		</script>
		