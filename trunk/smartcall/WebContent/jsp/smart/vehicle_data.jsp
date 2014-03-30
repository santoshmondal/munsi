
<%@page import="com.async.util.Constants.DBCollectionEnum"%>
<%@page import="com.async.util.CommonUtil"%>
<%@page import="com.async.util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<div class="page-header">
							
							<h1>Vehicle Data
								<small>
									<i class="icon-double-angle-right"></i>
									Below is the list of vehicle in the sorted with days of service..
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<table id="grid-table-data"></table>

								<div id="grid-pager-data"></div>
							
								
								<!-- Dialog Scheme -->
								<div id="dialog-scheme" class="hide">
									<table id="grid-table-scheme"></table>
									
								</div>	
							</div><!-- /.col -->
						</div><!-- /.row -->

	<script type="text/javascript">
	$(document).ready(function () {
        var myData = [
				{id:"11",customerName:"Cust1",mobileNo:"547896525",model:"Audi",variant:"x1",vin:"asadfd224411545",engineNo:"AASDFX545",regNovehNo:"XXAX12154",invoiceDateSale:"1-12-2012",vehicleDeliveryDate:"25-12-2012",dealerName:"",dealerState:"",dealerCity:"",odometerReading:"200",status:"Ringing"},
				{id:"2",customerName:"Cust12",mobileNo:"547896525",model:"VW",variant:"x1",vin:"vvfgfgs44411545",engineNo:"AASDFX545",regNovehNo:"XXAX12154",invoiceDateSale:"1-02-2012",vehicleDeliveryDate:"05-02-2012",dealerName:"",dealerState:"",dealerCity:"",odometerReading:"200",status:"Ringing"},
				{id:"3",customerName:"Cust13",mobileNo:"547896525",model:"VW",variant:"x1",vin:"asasdgfjgk25545",engineNo:"AASDFX545",regNovehNo:"XXAX12154",invoiceDateSale:"1-12-2014",vehicleDeliveryDate:"25-12-2014",dealerName:"",dealerState:"",dealerCity:"",odometerReading:"200",status:"Ringing"},
				{id:"4",customerName:"Cust21",mobileNo:"547896525",model:"Audi",variant:"x1",vin:"jhkj22s44411545",engineNo:"AASDFX545",regNovehNo:"XXAX12154",invoiceDateSale:"1-12-2012",vehicleDeliveryDate:"25-12-2012",dealerName:"",dealerState:"",dealerCity:"",odometerReading:"200",status:"Ringing"},
				{id:"5",customerName:"Cust221",mobileNo:"547896525",model:"Hundai",variant:"x1",vin:"asdfbd544411545",engineNo:"AASDFX545",regNovehNo:"XXAX12154",invoiceDateSale:"1-12-2012",vehicleDeliveryDate:"25-12-2012",dealerName:"",dealerState:"",dealerCity:"",odometerReading:"200",status:"Ringing"},
				{id:"6",customerName:"Cust12",mobileNo:"547896525",model:"Hundai",variant:"x1",vin:"asdfg7488811545",engineNo:"AASDFX545",regNovehNo:"XXAX12154",invoiceDateSale:"1-12-2012",vehicleDeliveryDate:"25-12-2012",dealerName:"",dealerState:"",dealerCity:"",odometerReading:"200",status:"Ringing"},
				{id:"7",customerName:"Cust121",mobileNo:"547896525",model:"Hundai",variant:"x1",vin:"asadfg548411545",engineNo:"AASDFX545",regNovehNo:"XXAX12154",invoiceDateSale:"1-12-2012",vehicleDeliveryDate:"25-12-2012",dealerName:"",dealerState:"",dealerCity:"",odometerReading:"200",status:"Ringing"}
            ],
            myGrid = $("#grid-table-data");

        myGrid.jqGrid({
            datatype:'local',
            data: myData,
			colNames:['id','Customer Name','Mobile No.','Model','Variant','VIN','Engine No.','Reg No./Veh no.','Inv. Date Sale','Veh. Delivery Date','Dealer Name','Dealer State','Dealer City','Odometer Reading','Status',' '],
			colModel:[
				{name:'id',index:'id', width:60, sorttype:"int", editrules:{required:false, addhidden:true}, editable: false, hidden:true},
				{name:'customerName',index:'customerName', width:170,editable: false},
				{name:'mobileNo',index:'mobileNo', width:150,editable: false},
				{name:'model',index:'model', width:100, editable: false},
				{name:'variant',index:'variant',width:100, editable: false},
				{name:'vin',index:'vin',width:120, editable: true,editoptions:{size:"20",maxlength:"130"}},
				{name:'engineNo',index:'engineNo',width:120,  editable: true,editoptions:{size:"20",maxlength:"130"}},
				{name:'regNovehNo',index:'regNovehNo',  editable: true, editrules:{required:false, edithidden:true},formoptions:{label:'Sale Unit', rowpos:6, colpos:2}, edittype:"select",editoptions:{ value:"Box:Box;Piece:Piece;KG:KG"}},
				{name:'invoiceDateSale',index:'invoiceDateSale',width:100,sorttype:'date', formatter:'date', formatoptions: {newformat:'d-M-Y'}, datefmt: 'd-M-Y',unformat: pickDate},
				{name:'vehicleDeliveryDate',index:'vehicleDeliveryDate',width:130, formatter:'date', formatoptions: {newformat:'d-M-Y'}, datefmt: 'd-M-Y',unformat: pickDate},
				{name:'dealerName',index:'dealerName',width:100,hidden:true},
				{name:'dealerState',index:'dealerState',width:100,hidden:true},
				{name:'dealerCity',index:'dealerCity',width:100,hidden:true},
				{name:'odometerReading',index:'odometerReading',width:100,hidden:true},
				{name:'status',index:'status',width:100, editable: true, formatter:'select',edittype:"select",stype:'select',editoptions:{value:"Ringing:Ringing;Callback:Callback"}, searchoptions:{value:"Ringing:Ringing;Callback:Callback"}},
				{ name: 'act', index: 'act', frozen : true,width:70, search:false, align: 'center', sortable: false, formatter: 'actions',
                    formatoptions: {editbutton:false,delbutton:false,
                        keys: false
                    }
                }
			], 
			loadComplete : function() {
				var table = this;
				setTimeout(function(){	
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
				
				var iCol = getColumnIndexByName(myGrid, 'act');
                $(this).find(">tbody>tr.jqgrow>td:nth-child(" + (iCol + 1) + ")")
                    .each(function() {
                        $("<div>", {
                            title: "Schedule Appointment",
                            mouseover: function() {
                                $(this).addClass('ui-state-hover');
                            },
                            mouseout: function() {
                                $(this).removeClass('ui-state-hover');
                            },
                            click: function(e) {
                                alert("'Appointment' button is clicked in the rowis="+
                                    $(e.target).closest("tr.jqgrow").attr("id") +" !");
                            }
                        }
                      ).css({"margin-right": "5px", float: "left", cursor: "pointer"})
                       .addClass("ui-pg-div ui-inline-custom")
                       .append('<span class="icon-calendar bigger-120" style="color:blue"></span>')
                       .prependTo($(this).children("div"));
                        
                        $("<div>", {
                            title: "Show Details",
                            mouseover: function() {
                                $(this).addClass('ui-state-hover');
                            },
                            mouseout: function() {
                                $(this).removeClass('ui-state-hover');
                            },
                            click: function(e) {
                                alert("'Detail' button is clicked in the rowis="+
                                    $(e.target).closest("tr.jqgrow").attr("id") +" !");
                            }
                        }
                      ).css({"margin-right": "5px", float: "left", cursor: "pointer"})
                       .addClass("ui-pg-div ui-inline-custom")
                       .append('<span class="icon-eye-open bigger-120" style="color:black"></span>')
                       .prependTo($(this).children("div"));
                });
			},
			gridComplete : function() {
				myGrid.find(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
			},
			rowNum:10,
            rowList:[10,20,30],
            pager: '#grid-pager-data',
            gridview:true,
            ignoreCase:true,
            rownumbers:true,
            sortname: 'invdate',
            scrollOffset: 18,
			viewrecords: true,
            autowidth:true,
            sortorder: 'desc',
            height: '366'
        });
        myGrid.jqGrid('filterToolbar', {stringResult: true, searchOnEnter: false, defaultSearch : "cn"});
        myGrid.jqGrid('setFrozenColumns');
        
        function getColumnIndexByName(grid, columnName) {
            var cm = grid.jqGrid('getGridParam', 'colModel'), i, l = cm.length;
            for (i = 0; i < l; i++) {
                if (cm[i].name === columnName) {
                    return i; // return the index
                }
            }
            return -1;
        };
        
      //enable datepicker
    	function pickDate( cellvalue, options, cell ) {
    		setTimeout(function(){
    			$(cell) .find('input[type=text]')
    					.datepicker({format:'dd-mm-yyyy' , autoclose:true}); 
    		}, 0);
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
	
    });
				
		</script>