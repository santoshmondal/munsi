
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<div class="page-header">
							
							<h1>Product Groups
								<small>
									<i class="icon-double-angle-right"></i>
									manage the product groups and subgroups.
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="tabbable">
									<ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
										<li class="active">
											<a data-toggle="tab" href="#faq-tab-1">
												<i class="blue icon-key bigger-120"></i>
												Main Group
											</a>
										</li>

										<li>
											<a data-toggle="tab" href="#faq-tab-2">
												<i class="green  icon-key bigger-120"></i>
												Sub Group
											</a>
										</li>

									</ul>

									<div class="tab-content no-border padding-24">
										<div id="faq-tab-1" class="tab-pane fade in active">
											<h4 class="blue">
												<i class="icon-ok bigger-110"></i>
												Main Group
											</h4>

											<div class="space-8"></div>

											<div class="row">
												<div class="col-xs-12">
													<!-- PAGE CONTENT BEGINS -->
					
													<table id="grid-table-maingroup"></table>
					
													<div id="grid-pager-maingroup"></div>
					
													<script type="text/javascript">
														var $path_base = "/";//this will be used in gritter alerts containing images
													</script>
					
													<!-- PAGE CONTENT ENDS -->
												</div><!-- /.col -->
											</div><!-- /.row -->
										</div>

										<div id="faq-tab-2" class="tab-pane fade in active">
											<h4 class="blue">
												<i class="green icon-user bigger-110"></i>
												Sub Group
											</h4>

											<div class="space-8"></div>

											<div class="row">
												<div class="col-xs-12">
													<!-- PAGE CONTENT BEGINS -->
					
													<table id="grid-table-subgroup"></table>
					
													<div id="grid-pager-subgroup"></div>
					
													<script type="text/javascript">
														var $path_base = "/";//this will be used in gritter alerts containing images
													</script>
					
													<!-- PAGE CONTENT ENDS -->
												</div><!-- /.col -->
											</div><!-- /.row -->
										</div>

									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->

	<script type="text/javascript">
			var grid_data = 
			[ 
				{id:"1",description:"Desktop Computer",name:"name MA 1"},
				{id:"2",description:"Trading Income +ve",name:"name MA 2"},
				{id:"3",description:"Trading Income +ve",name:"name MA 3"},
				{id:"4",description:"Trading Income +ve",name:"name MA 4"},
				{id:"5",description:"Trading Income +ve",name:"name MA 5"},
				{id:"6",description:"Desktop Computer",name:"note"}
				
			];	
			
			jQuery(function($) {
				var grid_selector = "#grid-table-maingroup";
				var pager_selector = "#grid-pager-maingroup";
			
				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					url: "${pageContext.request.contextPath}/productgroup.action?op=view_all&level=1",
					mtype: "POST",
					loadonce: true,
					gridview: true,
					datatype: "json",
					height: 266,
					colNames:['id','Name','Description',' '],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", editable: false, hidden:true},
						{name:'name',index:'name', width:110, editrules:{required:true}, editable: true},
						{name:'description',index:'description', width:150,editable: true,editoptions:{maxlength:"200"}},
						{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delOptions:{recreateForm: true,  url: "${pageContext.request.contextPath}/productgroup.action?op=delete&level=1", beforeShowForm:beforeDeleteCallback},
								//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
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
					editurl: "${pageContext.request.contextPath}/productgroup.action?op=edit&level=1",//nothing is saved
					//caption: "List of areas",
					scrollOffset: 18,
					autowidth: true
			
				});

				jQuery(grid_selector).jqGrid('bindKeys', {"onEnter":function( rowid ) {  
					//alert("You enter a row with id: " + rowid);
                    editingRowId = rowid;
                    // we use aftersavefunc to restore focus
                    jQuery(grid_selector).jqGrid('editRow',rowid,true,null, null, null, {},function(){
                        setTimeout(function(){
                        	jQuery(grid_selector).focus();
                        },100);
                    },
                    null,
                    function(){
                        setTimeout(function(){
                        	jQuery(grid_selector).focus();
                        },100);
                    });
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
						url: "${pageContext.request.contextPath}/productgroup.action?op=add&level=1",
						top:(($(window).height() - 300) / 2), left:((($(window).width() - 500) / 2) + $(window).scrollLeft()), width:500,
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
			
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			
				/* $(window).on('resize', function() {
				    jQuery(grid_selector).setGridWidth($('#id_EmbedPage').width(), false); 
				    jQuery(grid_selector).setGridHeight($('#id_EmbedPage').height()-100,false); 
				}).trigger('resize');  
			 */

				//-----> press g for setting focus on jqgrid
				$(document).bind('keydown', 'Alt+g', function(){
				    var	ids = jQuery(grid_selector).jqGrid("getDataIDs");
					if(ids && ids.length > 0){
						jQuery(grid_selector).focus();
						jQuery(grid_selector).jqGrid("setSelection", ids[0]);
					}
			    });
				
				
	//-----------------------------------
	//----------- Sub Group JQGrid
	//-----------------------------------
	
				var grid_selector_sub = "#grid-table-subgroup";
				var pager_selector_sub = "#grid-pager-subgroup";
			
				jQuery(grid_selector_sub).jqGrid({
					//direction: "rtl",
					
					url: "${pageContext.request.contextPath}/productgroup.action?op=view_all&level=2",
					mtype: "POST",
					loadonce: true,
					gridview: true,
					datatype: "json",
					height: 266,
					colNames:['id','Name','Description',' '],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", editable: false, hidden:true},
						{name:'name',index:'name', width:110, editrules:{required:true}, editable: true},
						{name:'description',index:'description', width:150,editable: true,editoptions:{maxlength:"200"}},
						{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delOptions:{recreateForm: true, url: "${pageContext.request.contextPath}/productgroup.action?op=delete&level=2", beforeShowForm:beforeDeleteCallback},
								//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
							}
						}
					], 
			
					viewrecords : true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector_sub,
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
					editurl: "${pageContext.request.contextPath}/productgroup.action?op=edit&level=2",//nothing is saved
					//caption: "List of areas",
					scrollOffset: 18,
					autowidth: true
			
				});

				jQuery(grid_selector_sub).jqGrid('bindKeys', {"onEnter":function( rowid ) {  
					//alert("You enter a row with id: " + rowid);
                    editingRowId = rowid;
                    // we use aftersavefunc to restore focus
                    jQuery(grid_selector_sub).jqGrid('editRow',rowid,true,null, null, null, {},function(){
                        setTimeout(function(){
                        	jQuery(grid_selector_sub).focus();
                        },100);
                    },
                    null,
                    function(){
                        setTimeout(function(){
                        	jQuery(grid_selector_sub).focus();
                        },100);
                    });
				} } );
				

				//navButtons
				jQuery(grid_selector_sub).jqGrid('navGrid',pager_selector_sub,
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
						url: "${pageContext.request.contextPath}/productgroup.action?op=add&level=2",
						top:(($(window).height() - 300) / 2), left:((($(window).width() - 500) / 2) + $(window).scrollLeft()), width:500,
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

				$.extend($.jgrid.edit, {
				    beforeSubmit: function () {
				        $(this).jqGrid("setGridParam", {datatype: "json"});
				        return [true,"",""];
				    }
				});
			});
		</script>