
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<div class="page-header">
							
							<h1>Product Master
								<small>
									<i class="icon-double-angle-right"></i>
									
									add, remove or edit product from the master records.
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<table id="grid-table-prodmaster"></table>

								<div id="grid-pager-prodmaster"></div>
								
								<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>
								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->

	<script type="text/javascript">
			var grid_data = 
			[ 
				{id:"11",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"12",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"13",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"1234567890sdfsdf",servicechargetax:"",scheme:''},
				{id:"14",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"15",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"16",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"17",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"18",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"19",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''},
				{id:"10",name:"MA 1",weight:"0.019",group:"G 1",subgroup:"SG 1",taxes:"12.50",margin:"",openingstock:"10",mrp:"1.00",salerate:"0.76",purchaseunit:"Box",purchaserate:"",pack:"",lockitem:"",unit:"",batchyn:"",vattype:"",servicechargetax:"",scheme:''}
			];	
			
			jQuery(function($) {
				var grid_selector = "#grid-table-prodmaster";
				var pager_selector = "#grid-pager-prodmaster";
			
				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					data: grid_data,
					datatype: "local",
					height: 366,
					colNames:['id','Name','Weight','Margin (%)','M.R.P. (Rs)','Sale Rate (Rs)','Sale Unit','Purchase Rate (Rs)','Purchase Unit','Pack','Lock Item','Batch Y/N','Taxes','VAT Type','Service Charge (%)','Group','Subgroup','Opening Stock','Scheme',' '],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", editrules:{required:false, addhidden:true}, editable: false, hidden:false},
						{name:'name',index:'name', width:250,editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'weight',index:'weight', width:100, editable: true,editoptions:{size:"10",maxlength:"130"}},
						{name:'margin',index:'margin',width:130, editable: true,editoptions:{size:"10",maxlength:"130"}},
						
						{name:'mrp',index:'mrp',width:120, editable: true,editoptions:{size:"10",maxlength:"130"}},
						{name:'salerate',index:'salerate',width:155,  editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'unit',index:'unit',  editable: true, hidden:true,  editrules:{required:false, edithidden:true},formoptions:{label:'Unit', rowpos:5, colpos:2}, edittype:"select",editoptions:{value:"TE+:Trading Expense +ve;TE-:Trading Expense -ve;TI+:Trading Income +ve;TI-:Trading Income -ve;PE+:Profit Loss Expense;PI+:Profit Loss Income"}},
						
						{name:'purchaserate',index:'purchaserate',width:200,  editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'purchaseunit',index:'purchaseunit', editable: true,hidden:true,editrules:{required:false, edithidden:true},formoptions:{label:'Unit', rowpos:7, colpos:2}, edittype:"select",editoptions:{value:"TE+:Trading Expense +ve;TE-:Trading Expense -ve;TI+:Trading Income +ve;TI-:Trading Income -ve;PE+:Profit Loss Expense;PI+:Profit Loss Income"}},
						{name:'pack',index:'pack',  sorttype:"int", sortable:true,hidden:true, editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'lockitem',index:'lockitem', editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
						{name:'batchyn',index:'batchyn', editable: true,edittype:"checkbox",hidden:true, editoptions: {value:"Yes:No"},unformat: aceSwitch},
						{name:'taxes',index:'taxes', editable: true, edittype:"select", hidden:true, editrules:{required:false, edithidden:true},editoptions:{ multiple: true,  value:"MA 1:name MA 1(MA 1);MA 2:name MA 2(MA 2);MA 3:name MA 3(MA 3);MA 4:name MA 4(MA 4);MA 5:name MA 5(MA 5)", size: 3}},
						
						{name:'vattype',index:'vattype', editable: true, edittype:"select",editoptions:{   value:"MA 1:name MA 1(MA 1);MA 2:name MA 2(MA 2);MA 3:name MA 3(MA 3);MA 4:name MA 4(MA 4);MA 5:name MA 5(MA 5)"}},
						{name:'servicechargetax',index:'servicechargetax', sorttype:"int", sortable:true,editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'group',index:'group', width:150,editable: true,edittype:"select", hidden:true,editrules:{required:false, edithidden:true},editoptions:{value:"MA 1:name MA 1(MA 1);MA 2:name MA 2(MA 2);MA 3:name MA 3(MA 3);MA 4:name MA 4(MA 4);MA 5:name MA 5(MA 5)"}},
						{name:'subgroup',index:'subgroup', width:150,editable: true,edittype:"select", hidden:true,editrules:{required:false, edithidden:true},editoptions:{value:"MA 1:name MA 1(MA 1);MA 2:name MA 2(MA 2);MA 3:name MA 3(MA 3);MA 4:name MA 4(MA 4);MA 5:name MA 5(MA 5)"}},
						
						{name:'openingstock',index:'openingstock',width:190, sortable:false,editable: false, formatter:'showlink', formatoptions:{baseLinkUrl:'#', addParam: '&action=manage_opening_stock'}},
						{name:'scheme',index:'scheme', width:130,editable: false, formatter:'showlink', formatoptions:{baseLinkUrl:'#', addParam: '&action=manage_scheme'}},
						
						{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delOptions:{top:45 , left:((($(window).width() - 300) / 2) + $(window).scrollLeft()), recreateForm: true, closeOnEscape:true, beforeShowForm:beforeDeleteCallback},
								editformbutton:true, editOptions:{top:45, left:((($(window).width() - 600) / 2) + $(window).scrollLeft()), width:600, recreateForm: true, closeOnEscape:true, beforeShowForm:beforeEditCallback}
							}
						}
					], 
			
					viewrecords : true,
					rowNum:10,
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
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
						var ids = jQuery(grid_selector).getDataIDs();
	                    var myGridNode = jQuery(grid_selector)[0];
	                    for (var i = 0, idCount = ids.length; i < idCount; i++) {
	                        var a = $("#"+ids[i]+" a",myGridNode);
	                        a.html("Manage");
	                        a.click(function(e) {
	                            var hash=e.currentTarget.hash;// string like "#?id=0"
	                            if (hash.substring(0,5) === '#?id=') {
	                                var id = hash.substring(5,hash.length);
	                                var text = "clicked the row with id='"+id+"' and hash is:"+hash;
	                                alert(text);
	                                //$("#log").append(text+"<br />");
	                            }
	                            e.preventDefault();
	                        });
	                    }   
					},
					editurl: $path_base+"/dummy.html",//nothing is saved
					//caption: "List of areas",
					scrollOffset: 18,
					autowidth: true
			
				});

				jQuery(grid_selector).jqGrid('bindKeys', {"onEnter":function( rowid ) {  
					//alert("You enter a row with id: " + rowid);
                    editingRowId = rowid;
                    // we use aftersavefunc to restore focus
                    jQuery(grid_selector).find('#jEditButton_'+editingRowId).click();/* 
                    jQuery(grid_selector).jqGrid('editRow',rowid,true,null, null, null, {},function(){
                        
                        	jQuery(grid_selector).focus();
                        
                    },
                    null,
                    function(){
                        	jQuery(grid_selector).focus();
                        
                    });*/ 
				} } );
				
				//enable search/filter toolbar
				//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true});
				//jQuery(grid_selector).jqGrid('filterToolbar', { searchOnEnter: true, enableClear: false });

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
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
							
							style_edit_form(form);
						}
					},
					{
						//new record form
						closeAfterAdd: true,
						recreateForm: true,
						top:45, left:((($(window).width() - 600) / 2) + $(window).scrollLeft()), width:600,
						closeOnEscape:true,
						viewPagerButtons: false,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" ></div>')
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
							
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
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
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
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
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
						}
					}
				)
			
			
				
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
					buttons.eq(1).prepend('<i class="icon-remove"></i>')
				}
				
				function style_search_filters(form) {
					form.find('.delete-rule').val('X');
					form.find('.add-rule').addClass('btn btn-xs btn-primary');
					form.find('.add-group').addClass('btn btn-xs btn-success');
					form.find('.delete-group').addClass('btn btn-xs btn-danger');
				}
				function style_search_form(form) {
					var dialog = form.closest('.ui-jqdialog');
					var buttons = dialog.find('.EditTable')
					buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
					buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
					buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
				}
				
				function beforeDeleteCallback(e) {
					var form = $(e[0]);
					if(form.data('styled')) return false;
					
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
					style_delete_form(form);
					
					form.data('styled', true);
				}
				
				function beforeEditCallback(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
					style_edit_form(form);
				}
			
			
			
				//it causes some flicker when reloading or navigating grid
				//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
				//or go back to default browser checkbox styles for the grid
				function styleCheckbox(table) {
				/**
					$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
			
			
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');
				*/
				}
				
			
				//unlike navButtons icons, action icons in rows seem to be hard-coded
				//you can change them like this in here if you want
				function updateActionIcons(table) {
					/**
					var replacement = 
					{
						'ui-icon-pencil' : 'icon-pencil blue',
						'ui-icon-trash' : 'icon-trash red',
						'ui-icon-disk' : 'icon-ok green',
						'ui-icon-cancel' : 'icon-remove red'
					};
					$(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
					*/
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
					})
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
				}
			
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			
				/* $(window).on('resize', function() {
				    jQuery(grid_selector).setGridWidth($('#id_EmbedPage').width(), false); 
				    jQuery(grid_selector).setGridHeight($('#id_EmbedPage').height()-100,false); 
				}).trigger('resize');  
			 */

				//-----> press g for setting focus on jqgrid
				$(document).bind('keydown', 'g', function(){
				    var	ids = jQuery(grid_selector).jqGrid("getDataIDs");
				    //console.log(ids);
					if(ids && ids.length > 0){
						//jQuery(grid_selector).focus();
						//jQuery(grid_selector).jqGrid("setSelection", 1);
						//alert(ids[0]);
						jQuery(grid_selector).jqGrid("setSelection", ids[0]);
						jQuery(grid_selector).focus();
					}
			    });
				
				
			});
		</script>