
<%@page import="com.munsi.util.Constants.DBCollectionEnum"%>
<%@page import="com.munsi.util.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row page-header">

	<div class="col-xs-2 col-lg-1">
		<h1> <strong>Sale</strong> 
		</h1>
	</div>
	<div class="col-xs-10 col-lg-11">
		<div class="col-xs-4 col-lg-3">
			<span class="input-icon input-icon-right"> <input type="text"
				id="idCustomer" placeholder="Enter Customer Name" style="width:180px"/> <i
				class="icon-user green"></i>
			</span>
			<strong><a class="icon-external-link" data-rel="tooltip" title="Show user detail" data-placement="bottom"></a></strong> 
			<input type="hidden" id="idCustomer-id"/><br/>
			Outstanding Amount <span class="bigger-175 blue">Rs. 25</span>
		</div>
		<div class="clearfix center col-xs-5 col-lg-7">
			
			<div class="grid4">
				<span class="bigger-125 blue">Rs. 120000</span><br/>SUBTOTAL 
			</div>
			<div class="grid4">
				<span class="bigger-125 green">Rs. 12</span><br/>TAX
			</div>
			<div class="grid4">
				<span class="bigger-125 blue">Rs. 25</span>
				<br>
				DISCOUNT
			</div>
			<div class="grid4 profile-contact-links">
				<span class="bigger-170 red"><strong>Rs. 120000</strong></span>
				<br>
				Bill Amount
			</div>
		</div>
		<div class="col-xs-3 col-md-3 col-lg-2 pull-right">
			<button class="btn btn-sm btn-light pull-right" type="button" data-rel="tooltip" title="Print Bill (Alt+p)" data-placement="bottom">
				Print <i class="icon-print"></i> 
			</button> 
			<button class="btn btn-sm btn-inverse pull-right" type="button" data-rel="tooltip" title="Save (Alt+s)" data-placement="bottom">
				<i class="icon-save"></i> Save
			</button>
			
		</div>
	</div>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
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
                   {id:"1", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"2", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"3", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"4", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"5", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"6", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"7", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"8", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"9", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"10", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"11", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"12", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"13", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"14", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"15", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"16", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"17", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"18", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"19", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"20", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"21", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"22", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"23", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"24", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"25", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"26", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"27", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"28", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"29", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"30", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"31", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"32", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"33", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"34", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"35", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"36", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"37", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"38", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"39", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"40", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"41", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"42", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"43", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"44", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"45", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"46", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"47", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"48", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"49", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"50", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discountpercent:"",discountamount:"",freequantity:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''}
             ];

			
			
			jQuery(function($) {
				var grid_selector = jQuery("#grid-table_pinvoice");
				var pager_selector = jQuery("#grid-table_pinvoice_toppager");
				var pager_selector_id = "#grid-table_pinvoice_toppager";
				var colModel, i, cmi, tr = "<tr>", skip = 0, ths;
				
			     
				grid_selector.jqGrid({
					
					datatype: "local",
                	data: mydata,
                	height: '328',
					cellsubmit: 'clientArray',
					cellEdit : true,
					colNames:['id','Code','Name', 'Quantity', 'Rate', 'Unit','Tax','%','Rs','Free Qty.',' Total Qty.','Total Amt.','MFG Date','EXP Date','Batch Number'],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", sortable:false, editable: false, hidden:true},
						{name:'code',index:'code', width:100, sortable:false, editable: true,unformat: pickAutoComplete},
						{name:'name',index:'name', width:250, sortable:false, editable: true,unformat: pickAutoComplete},
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
	                	calculateTotalAmount();
	                },
	                loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							updateActionIcons(table);
							updatePagerIcons(table);
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
				 var availableTags = [
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
				];
				
				function pickAutoComplete( cellvalue, options, cell ) {
					setTimeout(function(){
					$(cell) .find('input[type=text]').autocomplete({
						source: availableTags
					});
					}, 0);
				}
			
				//navButtons
				
				
				grid_selector.jqGrid('navGrid',pager_selector_id,
					{ 	//navbar options
						edit: false,
						editicon : 'icon-pencil blue',
						add: false,
						addtext:"Add",
						addtitle: "Add Entry",
						addicon : 'icon-plus-sign purple',
						del: false,
						delicon : 'icon-trash red',
						search: true,
						searchicon : 'icon-search orange',
						refresh: false,
						refreshicon : 'icon-refresh green',
						view: false,
						viewicon : 'icon-zoom-in grey'
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
						recreateForm: false,
						closeOnEscape:true
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
				var editOptions = {
				        keys: false,
				        successfunc: function () {
				            var $self = $(this);
				            setTimeout(function () {
				                $self.trigger("reloadGrid");
				            }, 50);
				        }
				    };
				
				grid_selector.jqGrid("inlineNav", pager_selector_id, { 	//navbar options
					edit: false,
					editicon : 'icon-pencil blue',
					add: true,
					addtext:"Add",
					addtitle: "Add Entry",
					addicon : 'icon-plus-sign purple',
					del: false,
					delicon : 'icon-trash red',
					search: true,
					searchicon : 'icon-search orange',
					refresh: false,
					refreshicon : 'icon-refresh green',
					view: false,
					viewicon : 'icon-zoom-in grey',
				    addParams: {
				        position: "last",
				        addRowParams: editOptions
				    },
				    editParams: editOptions
				});
			
				
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
					});
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
			    
			     objJsonCustomer = JSON.parse(objJsonCustomer.replace("\"_id\"","\"id\"","gm").replace("\"name\"","\"label\"","gm"));
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
			     return $( "<li>" )
			     .append( "<a>" + item.label + "<span class='badge badge-primary pull-right'>"+ item.id  +"</span>"+ "</a>" )
			     .appendTo( ul );
			     };
			     
			    <%--  //------------ AutoComplete Supplier Name--------------
			     var objJsonSupplier = '<%= CommonUtil.getIdLabelJSON(DBCollectionEnum.MAST_SUPPLIER, "_id", "name", "") %>';
			    
			     objJsonSupplier = JSON.parse(objJsonSupplier.replace("\"_id\"","\"id\"","gm").replace("\"name\"","\"label\"","gm"));
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
				     }; --%>
				
			});
			
				
		</script>


<script type="text/javascript">
	<%--  var mydata = [
                   {id:"1", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"2", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"3", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"4", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"5", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"6", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"7", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"8", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"9", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''},
                   {id:"10", code:"",name:"",  quantity:"",  rate:"",unit:"",tax:"",discount:"",totalquantity:'',totalamount:'',batchno:'',mfgdate:'',expdate:''}
              ];

			
			
			jQuery(function($) {
				var grid_selector = jQuery("#grid-table_pinvoice");
				var pager_selector = jQuery("#grid-table_pinvoice_toppager");
				var pager_selector_id = "#grid-table_pinvoice_toppager";
				
				
			     
				grid_selector.jqGrid({
					
					datatype: "local",
                	data: mydata,
					height: '328',
					toppager:true,
					cellsubmit: 'clientArray',
					'cellEdit' : true,
					colNames:['id','Code','Name', 'Quantity', 'Rate', 'Unit','Tax','Discount',' Total Qty.','Total Amount'],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int", editable: false, hidden:true},
						{name:'code',index:'code', width:100, editable: true,unformat: pickAutoComplete},
						{name:'name',index:'name', width:250, editable: true,unformat: pickAutoComplete},
						{name:'quantity',index:'quantity', align:'right', width:90,editable: true, formatter:'integer', sorttype:'int'},
						{name:'rate',index:'rate', width:90,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}},
						{name:'unit',index:'unit',width:80, editable: true, edittype:"select",editoptions:{ dataInit: function(elem) {$(elem).width(160);}, value:"Box:Box;Piece:Piece;KG:KG"}},
						{name:'tax',index:'tax', width:80,align:'right', editable: true,formatter:'currency', formatoptions:{decimalSeparator:".",  suffix: " %"}},
						{name:'discount', width:130,editable: false,align:'center', formatter:function(){ return '<a href="action=manage_discount">Click here</a>';}},
						{name:'totalquantity',index:'totalquantity', align:'right', width:100,editable: true,formatter:'integer'},
						{name:'totalamount',index:'totalamount', align:'right', width:120,editable: true,formatter:'currency', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "Rs "}}
					], 
			
					viewrecords : true,
					rowNum:1000,
					recordtext: "Total Entries {2}",
					pgbuttons: false,
					pgtext: null,
					pager : pager_selector_id,
					altRows: true,

					multiselect: false,
			        gridComplete: function () {
			        	calculateTotalAmount();
	                },
	                afterSaveCell: function (rowid, name, val, iRow, iCol) {
	                	calculateTotalAmount();
	                },
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							updateActionIcons(table);
							updatePagerIcons(table);
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
					autowidth: true,
					afterSaveCell: function (rowid, cellname, value) {
						calculateTotalAmount();
	                    /* var amount, tax, $this;
	                    if (cellname === 'amount' || cellname === 'tax') {
	                        $this = $(this);
	                        amount = parseFloat($this.jqGrid("getCell", rowid, 'amount'));
	                        tax = parseFloat($this.jqGrid("getCell", rowid, 'tax'));
	                        $this.jqGrid("setCell", rowid, 'total', amount + tax);
	                    } */
	                }
			
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
				 var availableTags = [
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
				];
				
				function pickAutoComplete( cellvalue, options, cell ) {
					setTimeout(function(){
					$(cell) .find('input[type=text]').autocomplete({
						source: availableTags
					});
					}, 0);
				}
			
				//navButtons
				
				
				grid_selector.jqGrid('navGrid',pager_selector_id,
					{ 	//navbar options
						edit: false,
						editicon : 'icon-pencil blue',
						add: false,
						addtext:"Add",
						addtitle: "Add Entry",
						addicon : 'icon-plus-sign purple',
						del: false,
						delicon : 'icon-trash red',
						search: true,
						searchicon : 'icon-search orange',
						refresh: false,
						refreshicon : 'icon-refresh green',
						view: false,
						viewicon : 'icon-zoom-in grey'
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
						recreateForm: false,
						closeOnEscape:true
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
				var editOptions = {
				        keys: false,
				        successfunc: function () {
				            var $self = $(this);
				            setTimeout(function () {
				                $self.trigger("reloadGrid");
				            }, 50);
				        }
				    };
				
				grid_selector.jqGrid("inlineNav", pager_selector_id, { 	//navbar options
					edit: false,
					editicon : 'icon-pencil blue',
					add: true,
					addtext:"Add",
					addtitle: "Add Entry",
					addicon : 'icon-plus-sign purple',
					del: false,
					delicon : 'icon-trash red',
					search: true,
					searchicon : 'icon-search orange',
					refresh: false,
					refreshicon : 'icon-refresh green',
					view: false,
					viewicon : 'icon-zoom-in grey',
				    addParams: {
				        position: "last",
				        addRowParams: editOptions
				    },
				    editParams: editOptions
				});
			
				
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
					});
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
				
				//--------- Beat JQGrid------
				
				var grid_selector_beat = "#grid-table-beat";
				
				jQuery(grid_selector_beat).jqGrid({
					//direction: "rtl",
					
					mtype: "POST",
					loadonce: true,
					gridview: true,
					datatype: "json",
					colNames:['id','Name','Description',' '],
					colModel:[
						{name:'id',index:'id', width:30, sorttype:"int", hidden:true, editrules:{required:false, addhidden:true}, editable: false},
						{name:'name',index:'name', width:150,sortable:false,editable: true,editoptions:{size:"20",maxlength:"130"}},
						{name:'description',index:'description',width:300, sortable:false, editable: true},
						{name:'myac',index:'', width:70, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delOptions:{top:45 ,url: "${pageContext.request.contextPath}/beatmaster.action?op=delete", left:((($(window).width() - 300) / 2) + $(window).scrollLeft()), recreateForm: true, closeOnEscape:true, beforeShowForm:beforeDeleteCallback},
							}
						}
					], 
			
					viewrecords : true,
					
					pager : "",
					altRows: false,
					
					multiselect: false,
			        multiboxonly: true,
			        height: 'auto',
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
						
					},
					caption: "Beat",
					scrollOffset: 18,
					autowidth: true,
					autoheight:true
				});
				
				jQuery(grid_selector_beat).jqGrid('bindKeys', {"onEnter":function( rowid ) {  
					editingRowId = rowid;
                    jQuery(grid_selector_beat).find('#jEditButton_'+editingRowId).click();
				} } );
				
				//----------------------------------------
				//----------Beat Related JS ------------
				//----------------------------------------
						function showBeatDialog(row) {
							
							$( "#dialog-beat" ).removeClass('hide').dialog({
								resizable: false,
								modal: true,
								autoOpen: false,
								title: "",
								height: 400,
								width: 550,
								title_html: true,
								open: function() {
									$(".ui-dialog-title").empty().append("<div class='widget-header'><span class='ui-jqdialog-title' style='float: left;'>Manage Beat</span> </div>");
								    $(".ui-dialog-buttonset").addClass('col-lg-12');
								    $(this).find(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
								    var productMasterRowData = grid_selector.jqGrid('getRowData',row.split('&')[0]);
								    prodMasterSelID = productMasterRowData.id;
								    jQuery(grid_selector_beat).setGridParam( {editurl: "${pageContext.request.contextPath}/beatmaster.action?op=edit&areaId="+prodMasterSelID, datatype:"json", url:"${pageContext.request.contextPath}/beatmaster.action?op=view&areaId="+productMasterRowData.id} );
								    jQuery(grid_selector_beat).jqGrid('setCaption', "Beat for "+productMasterRowData.name);

								    jQuery(grid_selector_beat).jqGrid("clearGridData");
								    jQuery(grid_selector_beat).trigger("reloadGrid");
								},
								buttons: [
									{
										html: "<i class='icon-plus bigger-110'></i>&nbsp; Add",
										"class" : "btn btn-primary btn-xs pull-left",
										click: function() {
											var datarow = {id:"",name:"",description:""};
								            var newId = $.jgrid.randId();
											jQuery(grid_selector_beat).jqGrid('addRowData', newId , datarow, "last");
											var editparameters = {
													"keys" : true,
													"oneditfunc" : null,
													"successfunc" : null,
													"url" : "${pageContext.request.contextPath}/beatmaster.action?op=add&areaId="+prodMasterSelID,
												    "extraparam" : {},
													"aftersavefunc" : function(){
														jQuery(grid_selector_beat).setGridParam( {url:"${pageContext.request.contextPath}/beatmaster.action?op=edit&areaId="+prodMasterSelID} );
													},
													"errorfunc": null,
													"afterrestorefunc" : null,
													"restoreAfterError" : true,
													"mtype" : "POST"
												};
											jQuery(grid_selector_beat).jqGrid('editRow',newId , editparameters );
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
							
							$( "#dialog-beat" ).dialog( "open" );
						};
						
						function  getTextFromCell(cellNode) {
							var text = cellNode.childNodes[0].nodeName === "INPUT"?
		                            cellNode.childNodes[0].value:
			                            cellNode.textContent || cellNode.innerText;
		                    return text.replace("Rs","").replace("%","").replace(",","","gm");
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
					    
					     objJsonCustomer = JSON.parse(objJsonCustomer.replace("\"_id\"","\"id\"","gm").replace("\"name\"","\"label\"","gm"));
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
					     return $( "<li>" )
					     .append( "<a>" + item.label + "<span class='badge badge-primary pull-right'>"+ item.id  +"</span>"+ "</a>" )
					     .appendTo( ul );
					     };
			});
			

			 --%>
				
		</script>