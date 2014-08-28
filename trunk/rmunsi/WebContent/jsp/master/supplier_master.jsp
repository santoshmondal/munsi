
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<div class="page-header">
							<h1>
								Supplier Master
								<small>
									<i class="icon-double-angle-right"></i>
									View, Add and Edit Supplier details.
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table-supp"></table>

								<div id="grid-pager-supp"></div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
		
	<script type="text/javascript">
			
			jQuery(function($) {
				var grid_selector = jQuery("#grid-table-supp");
				var pager_selector = jQuery("#grid-pager-supp");
				var pager_selector_id = "#grid-pager-supp";
				grid_selector.jqGrid({
					//direction: "rtl",
					
					url: "${pageContext.request.contextPath}/suppliermaster.action?op=view_all",
					mtype: "POST",
					loadonce: true,
					gridview: true,
					datatype: "json",
					height: 366,
					colNames:['id','Name','E-mail','Telephone','Mobile','City','Pin Code','Address','Description',' '],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", editable: false, hidden:true},
						{name:'name',index:'name', width:110, editrules:{required:true}, editable: true},
						{name:'email',index:'email', width:150,editable: true,editrules:{email:true,required:false},editoptions:{size:"20",maxlength:"15"}},
						{name:'phone',index:'phone', width:150,editable: true,editrules:{number:true,required:false},editoptions:{size:"20",maxlength:"15"}},
						{name:'mobile',index:'mobile', width:150,editable: true,editrules:{number:true,required:false},editoptions:{size:"20",maxlength:"15"}},
						{name:'city',index:'city', width:150,editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'pin',index:'pin', width:150,editable: true,editrules:{edithidden:true,number:true},hidden:true,editoptions:{size:"20",maxlength:"15"}},
						{name:'address',index:'address', width:150, sortable:false,editrules:{edithidden:true},editable: true,hidden:true,edittype:"textarea", editoptions:{rows:"2",cols:"20"}},
						{name:'remark',index:'remark', width:150,editable: true,editoptions:{maxlength:"200"},edittype:"textarea"},
						{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delOptions:{url: "${pageContext.request.contextPath}/suppliermaster.action?op=delete", top:45 , left:((($(window).width() - 300) / 2) + $(window).scrollLeft()), recreateForm: true, closeOnEscape:true,  beforeShowForm:beforeDeleteCallback},
								editformbutton:true, editOptions:{top:45, left:((($(window).width() - 500) / 2) + $(window).scrollLeft()), width:500, recreateForm: true, closeOnEscape:true, beforeShowForm:beforeEditCallback}
							}
						}
					], 
			
					viewrecords : true,
					rownumbers:true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector_id,
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
					},
					afterSubmit: function (response, postdata) {
						console.log("Called afterSubmitCell");
	                    setTimeout(function () {
	                        $("#info_dialog").css({
	                            left: "30px", // new left position of ERROR dialog
	                            top: "10px"   // new top position of ERROR dialog
	                        });
	                    });
	                    return [false, "It's an error text"];
	                },
					editurl: "${pageContext.request.contextPath}/suppliermaster.action?op=edit",
					//caption: "List of areas",
					scrollOffset: 18,
					autowidth: true
			
				});

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
				
				//enable search/filter toolbar
				//grid_selector.jqGrid('filterToolbar',{defaultSearch:true,stringResult:true});
				//grid_selector.jqGrid('filterToolbar', { searchOnEnter: true, enableClear: false });

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
				grid_selector.jqGrid('navGrid',pager_selector_id,
					{ 	//navbar options
						edit: false,
						editicon : 'icon-pencil blue',
						add: true,
						addtext:"Add",
						addtitle: "Add Area",
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
						savekey : [ true, 13 ],
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
						savekey : [ true, 13 ],
						url: "${pageContext.request.contextPath}/customermaster.action?op=add",
						top:45, left:((($(window).width() - 500) / 2) + $(window).scrollLeft()), width:500,
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
					buttons.eq(1).prepend('<i class="icon-remove"></i>')
					
					buttons = form.next().find('.navButton a');
					buttons.find('.ui-icon').remove();
					buttons.eq(0).append('<i class="icon-chevron-left"></i>');
					buttons.eq(1).append('<i class="icon-chevron-right"></i>');		
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
			
				//var selr = grid_selector.jqGrid('getGridParam','selrow');
			
				/* $(window).on('resize', function() {
				    grid_selector.setGridWidth($('#id_EmbedPage').width(), false); 
				    grid_selector.setGridHeight($('#id_EmbedPage').height()-100,false); 
				}).trigger('resize');  
			 */

				//-----> press g for setting focus on jqgrid
				$(document).bind('keydown', 'Alt+g', function(){
				    var	ids = grid_selector.jqGrid("getDataIDs");
					if(ids && ids.length > 0){
						grid_selector.focus();
						grid_selector.jqGrid("setSelection", ids[0]);
					}
			    });

				$.extend($.jgrid.edit, {
				    beforeSubmit: function () {
				        $(this).jqGrid("setGridParam", {datatype: "json"});
				        return [true,"",""];
				    }
				});
			});
		</script>
