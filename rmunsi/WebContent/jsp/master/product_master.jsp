
<%@page import="com.munsi.util.Constants.DBCollectionEnum"%>
<%@page import="com.munsi.util.CommonUtil"%>
<%@page import="com.munsi.util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<div class="page-header">
							
							<h1>Product Master
								<small>
									<i class="icon-double-angle-right"></i>
									
									Add, Edit or Delete product from the master records.
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<table id="grid-table-prodmaster"></table>

								<div id="grid-pager-prodmaster"></div>
							
								
								<!-- Dialog Scheme -->
								<div id="dialog-scheme" class="hide">
									<table id="grid-table-scheme"></table>
									
								</div>	
								<!-- Dialog Opening Stock -->
								<div id="dialog-stock" class="hide">
									<table id="grid-table-stock"></table>
									
								</div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->

	<script type="text/javascript">

			jQuery(function($) {
				var grid_selector = "#grid-table-prodmaster";
				var pager_selector = "#grid-pager-prodmaster";
				
				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					url: "${pageContext.request.contextPath}/productmaster.action?op=view_all",
					mtype: "POST",
					loadonce: true,
					gridview: true,
					datatype: "json",
					height: 366,
					colNames:['id','Name','Alias','Code','Barcode','M.R.P. (Rs)','Sale Rate (Rs)','Sale Unit','Purchase Rate (Rs)','Purchase Unit','Pack','Allow Negative Stock','Min. Stock','Max. Stock','Current Stock','Batch Y/N','Taxes','Manufacturer','Group','Subgroup','Opening Stock',' '],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", editrules:{required:false, addhidden:true}, editable: false, hidden:true},

						{name:'name',index:'name', width:200,editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'alias',index:'alias', width:150,editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'code',index:'code', width:100, editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'barCode',index:'barCode', width:100, editable: true,editoptions:{size:"20",maxlength:"130"}},

						{name:'mrp',index:'mrp',width:120, editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'salesRate',index:'salesRate',width:155,  editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'salesUnit',index:'salesUnit',  editable: true, hidden:true,  editrules:{required:false, edithidden:true},formoptions:{label:'Sale Unit', rowpos:6, colpos:2}, edittype:"select",editoptions:{ value:"Box:Box;Piece:Piece;KG:KG"}},

						{name:'purchaseRate',index:'purchaseRate',width:200,  editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'purchaseUnit',index:'purchaseUnit', editable: true,hidden:true,editrules:{required:false, edithidden:true},formoptions:{label:'Purchase Unit', rowpos:8, colpos:2}, edittype:"select",editoptions:{value:"Box:Box;Piece:Piece;KG:KG"}},
						{name:'pack',index:'pack',  sorttype:"int", sortable:true,hidden:true, editrules:{required:false, edithidden:true}, editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'allowNegativeStock',index:'allowNegativeStock', editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
						{name:'minStock',index:'minStock',hidden:true, editable: true,editrules:{required:false, edithidden:true},editoptions:{size:"20",maxlength:"130"}},
						{name:'maxStock',index:'maxStock',hidden:true, editable: true,editrules:{required:false, edithidden:true},editoptions:{size:"20",maxlength:"130"}},
						{name:'currentStock',index:'currentStock',hidden:false, editable: true,editrules:{required:false, edithidden:true},editoptions:{size:"20",maxlength:"130"}},
						{name:'batchyn',index:'batchyn', editable: true,edittype:"checkbox",hidden:true, editoptions: {value:"Yes:No"},unformat: aceSwitch},
						{name:'1taxList',index:'taxList', editable: true, edittype:"select", hidden:true, editrules:{required:false, edithidden:true},editoptions:{ dataInit: function(elem) {$(elem).width(160);}, multiple: true,  value:"<%=CommonUtil.getIdNameString(DBCollectionEnum.MAST_TAX, "_id", "name") %>", size: 3}},

						{name:'1manufacturer',index:'manufacturer', width:150,editable: true,edittype:"select", hidden:true,editrules:{required:false, edithidden:true},editoptions:{ dataInit: function(elem) {$(elem).width(160);}, value:"<%=CommonUtil.getIdNameString(DBCollectionEnum.MAST_MANUFACTURER, "_id", "name") %>"}},
						{name:'1productGroup',index:'productGroup', width:150,editable: true,edittype:"select", hidden:true,editrules:{required:false, edithidden:true},editoptions:{ dataInit: function(elem) {$(elem).width(160);}, value:"<%=CommonUtil.getIdNameString(DBCollectionEnum.MAST_PRODUCT_GROUP, "_id", "name","{'level':'1'}") %>"}},
						{name:'1productSubGroup',index:'productSubGroup', width:150,editable: true,edittype:"select", hidden:true,editrules:{required:false, edithidden:true},editoptions:{dataInit: function(elem) {$(elem).width(160);}, value:"<%=CommonUtil.getIdNameString(DBCollectionEnum.MAST_PRODUCT_GROUP, "_id", "name","{'level':'2'}") %>"}},
						
						{name:'openingStock',width:165, sortable:false,editable: false,formatter:function(){ return '<a href="action=manage_opening_stock">Click here</a>';}},
						
						{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delOptions:{top:45 , url: "${pageContext.request.contextPath}/productmaster.action?op=delete", left:((($(window).width() - 300) / 2) + $(window).scrollLeft()), recreateForm: true, closeOnEscape:true, beforeShowForm:beforeDeleteCallback},
								editformbutton:true, editOptions:{top:45, left:((($(window).width() - 600) / 2) + $(window).scrollLeft()), width:600, recreateForm: true, closeOnEscape:true, beforeShowForm:beforeEditCallback}
							}
						}
					], 
			
					viewrecords : true,
					rowNum:10,
					rownumbers:true,
					rowList:[10,20,30],
					pager : pager_selector,
					altRows: true,
					//toppager: true,
					
					multiselect: false,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			
					loadComplete : function() {
						var table = this;
						setTimeout(function(){	
							styleCheckbox(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
						var ids = jQuery(grid_selector).getDataIDs();
	                    var myGridNode = jQuery(grid_selector)[0];
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
	                                if(hash.indexOf('action=manage_scheme') > -1){
	                                	showSchemeDialog(id);
	                                }
	                                else{
	                                	showStockDialog(id);
	                                }
	                            }
	                            e.preventDefault();
	                        });
	                    }   
					},
					editurl: "${pageContext.request.contextPath}/productmaster.action?op=edit",
					scrollOffset: 18,
					autowidth: true
			
				});

				jQuery(grid_selector).jqGrid('bindKeys', {"onEnter":function( rowid ) {  
					//alert("You enter a row with id: " + rowid);
                    editingRowId = rowid;
                    // we use aftersavefunc to restore focus
                    jQuery(grid_selector).find('#jEditButton_'+editingRowId).click();
                   
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
			
			
				//navButtons
				jQuery(grid_selector).jqGrid('navGrid',pager_selector,
					{ 	//navbar options
						edit: false,
						editicon : 'icon-pencil blue',
						add: true,
						addtext:"Add",
						addtitle: "Add Product",
						addicon : 'icon-plus-sign purple',
						del: false,
						delicon : 'icon-trash red',
						search: true,
						searchicon : 'icon-search orange',
						refresh: true,
						refreshicon : 'icon-refresh green',
						view: false,
						viewicon : 'icon-zoom-in grey',
					},
					{
						//edit record form
						//closeAfterEdit: true,
						recreateForm: true,
						closeOnEscape:true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
							
							style_edit_form(form);
						}
					},
					{
						//new record form
						closeAfterAdd: true,
						recreateForm: true,
						url: "${pageContext.request.contextPath}/productmaster.action?op=add",
						top:45, left:((($(window).width() - 600) / 2) + $(window).scrollLeft()), width:600,
						closeOnEscape:true,
						viewPagerButtons: false,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" ></div>');
							style_edit_form(form);
						}
					},
					{
						//delete record form
						recreateForm: true,
						closeOnEscape:true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							if(form.data('styled')) return false;
							
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
							style_delete_form(form);
							
							form.data('styled', true);
						},
						onClick : function(e) {
							alert(1);
						}
					},
					{
						//search form
						recreateForm: true,
						afterShowSearch: function(e){
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
							style_search_form(form);
						},
						afterRedraw: function(){
							style_search_filters($(this));
						}
						,
						multipleSearch: true,
						closeOnEscape:true
						/**
						multipleGroup:true,
						showQuery: true
						*/
					},
					{
						//view record form
						recreateForm: true,
						closeOnEscape:true,
						beforeShowForm: function(e){
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
						}
					}
				);
			
				function style_edit_form(form) {
					//enable datepicker on "sdate" field and switches for "stock" field
					form.find('input[name=sdate]').datepicker({format:'dd-mm-yyyy' , autoclose:true})
						.end().find('input[name=stock]')
							  .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
			
					//update buttons classes
					var buttons = form.next().find('.EditButton .fm-button');
					buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
					buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
					buttons.eq(1).prepend('<i class="icon-remove"></i>');
					
					
					buttons = form.next().find('.navButton a');
					buttons.find('.ui-icon').remove();
					buttons.eq(0).append('<i class="icon-chevron-left"></i>');
					buttons.eq(1).append('<i class="icon-chevron-right"></i>');	
					
					var selCmb1 = " - <select id='subgroup2' style='width:100px'></select>";
					var selCmb2 = " - <select id='subgroup3' style='width:100px'></select>";
					
					form.find('select[id=subgroup]').closest('tr').find('td:nth-child(3)').remove();
					form.find('select[id=subgroup]').closest('tr').find('td:nth-child(3)').remove();
					form.find('select[id=subgroup]').closest('td').attr('colspan', 3);
					form.find('select[id=subgroup]').parent().append(selCmb1).append(selCmb2);
				}
			
				function style_delete_form(form) {
					var buttons = form.next().find('.EditButton .fm-button');
					buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
					buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
					buttons.eq(1).prepend('<i class="icon-remove"></i>');
				}
				
				function style_search_filters(form) {
					form.find('.delete-rule').val('X');
					form.find('.add-rule').addClass('btn btn-xs btn-primary');
					form.find('.add-group').addClass('btn btn-xs btn-success');
					form.find('.delete-group').addClass('btn btn-xs btn-danger');
				}
				function style_search_form(form) {
					var dialog = form.closest('.ui-jqdialog');
					var buttons = dialog.find('.EditTable');
					buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
					buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
					buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
				}
				
				function beforeDeleteCallback(e) {
					var form = $(e[0]);
					if(form.data('styled')) return false;
					
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
					style_delete_form(form);
					
					form.data('styled', true);
				}
				
				function beforeEditCallback(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
					style_edit_form(form);
				}
			
			
				//it causes some flicker when reloading or navigating grid
				//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
				//or go back to default browser checkbox styles for the grid
				function styleCheckbox(table) {
				
					$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />');
			
			
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');
				
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
			
			
				//-----> press g for setting focus on jqgrid
				$(document).bind('keydown', 'Alt+g', function(){
				    var	ids = jQuery(grid_selector).jqGrid("getDataIDs");
				    //console.log(ids);
					if(ids && ids.length > 0){
						jQuery(grid_selector).jqGrid("setSelection", ids[0]);
						jQuery(grid_selector).focus();
					}
			    });
		
			//--------- Scheme JQGrid------
			
				var grid_selector_scheme = "#grid-table-scheme";
				
				jQuery(grid_selector_scheme).jqGrid({
					//direction: "rtl",
					
					mtype: "POST",
					loadonce: true,
					gridview: true,
					datatype: "json",
					colNames:['id','Name','Scheme type','Quantity','Scheme On','Min. Purchase Qty.',' '],
					colModel:[
						{name:'id',index:'id', width:30, sorttype:"int", hidden:true, editrules:{required:false, addhidden:true}, editable: false},
						{name:'name',index:'name', width:100,sortable:false,editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'schemeType',index:'schemeType',formatter:'select', width:100, sortable:false, editable: true, editrules:{required:false, edithidden:true},formoptions:{label:'Unit', rowpos:5, colpos:2}, edittype:"select",editoptions:{ value:"<%= CommonUtil.getSchemeTypeJSON() %>"}},
						
						{name:'schemeValue',index:'schemeValue',width:80, sortable:false, editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'schemeOn',index:'schemeOn',formatter:'select',width:130 , sortable:false, editable: true,edittype:"select",editoptions:{value:"<%= CommonUtil.getSchemeONJSON() %>"}},
						{name:'minEligibleValue',index:'minEligibleValue',width:140, sortable:false, editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'myac',index:'', width:70, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delOptions:{top:45 ,url: "${pageContext.request.contextPath}/schememaster.action?op=delete", left:((($(window).width() - 300) / 2) + $(window).scrollLeft()), recreateForm: true, closeOnEscape:true, beforeShowForm:beforeDeleteCallback},
								//editformbutton:true, editOptions:{top:45, left:((($(window).width() - 600) / 2) + $(window).scrollLeft()), width:600, recreateForm: true, closeOnEscape:true, beforeShowForm:beforeEditCallback}
							}
						}
					], 
			
					viewrecords : true,
					
					pager : "",
					altRows: false,
					
					multiselect: false,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			        height: 'auto',
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
						
					},
					//nothing is saved
					caption: "Scheme",
					scrollOffset: 18,
					autowidth: true,
					autoheight:true
				});

				jQuery(grid_selector_scheme).jqGrid('bindKeys', {"onEnter":function( rowid ) {  
					editingRowId = rowid;
                    jQuery(grid_selector_scheme).find('#jEditButton_'+editingRowId).click();
				} } );
				
				//----------------------------------------
				//----------Scheme Related JS ------------
				//----------------------------------------
						function showSchemeDialog(row) {
							console.log("showSchemeDialog[param]> "+row);
							
							$( "#dialog-scheme" ).removeClass('hide').dialog({
								resizable: false,
								modal: true,
								autoOpen: false,
								title: "",
								height: 400,
								width: 660,
								title_html: true,
								open: function() {
									$(".ui-dialog-title").empty().append("<div class='widget-header'><span class='ui-jqdialog-title' style='float: left;'>Manage Scheme</span> </div>");
								    $(".ui-dialog-buttonset").addClass('col-lg-12');
								    $(this).find(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
								 
								    var productMasterRowData = jQuery(grid_selector).jqGrid('getRowData',row.split('&')[0]);
								    prodMasterSelID = productMasterRowData.id;
								    jQuery(grid_selector_scheme).setGridParam( {editurl: "${pageContext.request.contextPath}/schememaster.action?op=edit&productId="+prodMasterSelID, datatype:"json", url:"${pageContext.request.contextPath}/schememaster.action?op=view&productId="+productMasterRowData.id} );
								    jQuery(grid_selector_scheme).jqGrid('setCaption', "Scheme for "+productMasterRowData.name);

								    jQuery(grid_selector_scheme).jqGrid("clearGridData");
								    jQuery(grid_selector_scheme).trigger("reloadGrid");
								},
								buttons: [
									{
										html: "<i class='icon-plus bigger-110'></i>&nbsp; Add",
										"class" : "btn btn-primary btn-xs pull-left",
										click: function() {
											var datarow = {id:"",name:"",schemetype:"",quantity:"",eligiblitycriteria:"",minreq:""};
								            var newId = $.jgrid.randId();
											jQuery(grid_selector_scheme).jqGrid('addRowData', newId , datarow, "last");
											var editparameters = {
													"keys" : true,
													"oneditfunc" : null,
													"successfunc" : null,
													"url" : "${pageContext.request.contextPath}/schememaster.action?op=add&productId="+prodMasterSelID,
												    "extraparam" : {},
													"aftersavefunc" : function(){
														jQuery(grid_selector_scheme).setGridParam( {url:"${pageContext.request.contextPath}/schememaster.action?op=edit&productId="+prodMasterSelID} );
													},
													"errorfunc": null,
													"afterrestorefunc" : null,
													"restoreAfterError" : true,
													"mtype" : "POST"
												};
											jQuery(grid_selector_scheme).jqGrid('editRow',newId , editparameters );
										}
									},
									{
										html: "<i class='icon-remove bigger-110'></i>&nbsp; Cancel",
										"class" : "btn btn-xs pull-right",
										click: function() {
											$( this).dialog( "close" );
										}
									}
								]
							});
							
							$( "#dialog-scheme" ).dialog( "open" );
						}
				
						//--------- Opening Stock JQGrid------
						
						var grid_selector_stock = "#grid-table-stock";
						
						jQuery(grid_selector_stock).jqGrid({
							//direction: "rtl",
							
							mtype: "POST",
							loadonce: true,
							gridview: true,
							datatype: "json",
							colNames:['id','Quantity','Batch Number','Exp. Date','MFG Date'],
							colModel:[
								{name:'id',index:'id', width:30, sorttype:"int", hidden:true, editrules:{required:false, addhidden:true}, editable: false},
								{name:'quantity',index:'quantity', width:100, sortable:false,editable: true,editoptions:{size:"20",maxlength:"130"}},
								
								{name:'batchNumber',index:'batchNumber', width:100, sortable:false, editable: true,editoptions:{size:"20",maxlength:"130"}},
								{name:'1expDate',index:'expDate', width:180,editable:true, sorttype:"date",unformat: pickDate},
								{name:'1mfgDate',index:'mfgDate', width:180, editable:true, sorttype:"date",unformat: pickDate}
							],
					
							viewrecords : true,
							
							pager : "",
							altRows: false,
							
							multiselect: false,
							//multikey: "ctrlKey",
					        multiboxonly: true,
					        height: 'auto',
					        
							loadComplete : function() {
								var table = this;
								setTimeout(function(){
									styleCheckbox(table);
									updatePagerIcons(table);
									enableTooltips(table);
								}, 0);
								
							},
							//nothing is saved
							caption: "Opening Stock",
							scrollOffset: 18,
							autowidth: true,
							autoheight:true
						});

						jQuery(grid_selector_stock).jqGrid('bindKeys', {"onEnter":function( rowid ) {  
							editingRowId = rowid;
		                    jQuery(grid_selector_stock).find('#jEditButton_'+editingRowId).click();
						} } );

						//----------------------------------------
						//----------Stock Related JS ------------
						//----------------------------------------
								function showStockDialog(row) {
									console.log("showStockDialog[param]> "+row);
									
									$( "#dialog-stock" ).removeClass('hide').dialog({
										resizable: false,
										modal: true,
										title: "",
										autoOpen: false,
										height: 400,
										width: 590,
										title_html: true,
										open: function() {
											$(".ui-dialog-title").empty().append("<div class='widget-header'><span class='ui-jqdialog-title' style='float: left;'>Manage Opening Stock</span> </div>");
										    $(".ui-dialog-buttonset").addClass('col-lg-12');
										    $(this).find(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
										 
										    var productMasterRowData = jQuery(grid_selector).jqGrid('getRowData',row.split('&')[0]);
										    prodMasterSelID = productMasterRowData.id;
										    jQuery(grid_selector_stock).setGridParam( {editurl: "${pageContext.request.contextPath}/openingstockmaster.action?op=edit&productId="+prodMasterSelID, datatype:"json", url:"${pageContext.request.contextPath}/openingstockmaster.action?op=view&productId="+productMasterRowData.id} );
										    jQuery(grid_selector_stock).jqGrid('setCaption', "Opening Stock for "+productMasterRowData.name);

										    jQuery(grid_selector_stock).jqGrid("clearGridData");
										    jQuery(grid_selector_stock).trigger("reloadGrid");
										},
										buttons: [
											{
												html: "<i class='icon-plus bigger-110'></i>&nbsp; Add",
												"class" : "btn btn-primary btn-xs pull-left",
												click: function() {
													var datarow = {id:"",quantity:"",batchNumber:"",expDate:"",mfgDate:""};
										            var newId = $.jgrid.randId();

												    jQuery(grid_selector_stock).jqGrid('addRowData', newId , datarow, "last");
													var editparameters = {
															"keys" : true,
															"oneditfunc" : null,
															"successfunc" : null,
															"url" : "${pageContext.request.contextPath}/openingstockmaster.action?op=add&productId="+prodMasterSelID,
														    "extraparam" : {},
															"aftersavefunc" : function(){
																jQuery(grid_selector_stock).setGridParam( {url:"${pageContext.request.contextPath}/openingstockmaster.action?op=edit&productId="+prodMasterSelID} );
															},
															"errorfunc": null,
															"afterrestorefunc" : null,
															"restoreAfterError" : true,
															"mtype" : "POST"
														};
													jQuery(grid_selector_stock).jqGrid('editRow',newId , editparameters );
												}
											},
											{
												html: "<i class='icon-remove bigger-110'></i>&nbsp; Cancel",
												"class" : "btn btn-xs pull-right",
												click: function() {
													$( this).dialog( "close" );
												}
											}
											
										]
									});
									
									$( "#dialog-stock" ).dialog("open");
								}

								$.extend($.jgrid.edit, {
								    beforeSubmit: function () {
								        $(this).jqGrid("setGridParam", {datatype: "json"});
								        return [true,"",""];
								    }
								});
			});
			
			
				
		</script>