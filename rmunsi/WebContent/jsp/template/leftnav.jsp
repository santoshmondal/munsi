<div class="sidebar sidebar-fixed" id="sidebar">


	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class="active"><a href="#"> <i class="icon-dashboard"></i>
				<span class="menu-text" data-href="/jsp/dashboard/dashboard.jsp">
					Dashboard </span>
		</a></li>

		
		<li><a href="#" data-href="/jsp/sales/salesadd.jsp"> <i class="icon-star" style="color:#f0ad4e"></i>
				<span class="menu-text"> Sale </span>
			</a>
		</li>
		<li><a href="#" data-href="/jsp/purchase/purchaseadd.jsp"> <i class="icon-star" style="color:#5cb85c"></i>
				<span class="menu-text"> Purchase </span>
			</a>
		</li>
		<li><a href="#" class="dropdown-toggle"> <i
				class="icon-inbox"></i> <span class="menu-text"> Invoice Register </span> <b
				class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li><a href="#" data-href="/jsp/sales/listsales.jsp">
						<i class="icon-double-angle-right"></i> Sales Invoices
				</a></li>
				<li><a href="#" data-href="/jsp/purchase/listpurchase.jsp">
						<i class="icon-double-angle-right"></i> Purchase Invoices
				</a></li>
			</ul>
		</li>
		<li><a href="#" class="dropdown-toggle"> <i
				class="icon-list-alt"></i> <span class="menu-text"> Master </span> <b
				class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu">
				<li><a href="#" data-href="/jsp/master/customer_acc_master.jsp">
						<i class="icon-double-angle-right"></i> Customer
				</a></li>
				
				<li><a href="#" data-href="/jsp/master/product_master.jsp">
						<i class="icon-double-angle-right"></i> Product
				</a></li>
				<li><a href="#" data-href="/jsp/master/product_grouping.jsp">
						<i class="icon-double-angle-right"></i> Product Grouping
				</a></li>
				<li><a href="#" data-href="/jsp/master/supplier_master.jsp">
						<i class="icon-double-angle-right"></i> Supplier
				</a></li>
				<li><a href="#" data-href="/jsp/master/manufacturer_master.jsp">
						<i class="icon-double-angle-right"></i> Manufacturer
				</a></li>
				<li><a href="#" data-href="/jsp/master/tax_master.jsp">
						<i class="icon-double-angle-right"></i> TAX
				</a></li>
<!-- 				<li ><a href="#" 
					data-href="/jsp/template/under_maintenance.jsp"> <i
						class="icon-double-angle-right"></i> <span class="menu-text">
							Van <span class="badge badge-transparent"> <i
								class="icon-truck green" style='font-size: 1.5em'></i>
						</span>
					</span>
				</a></li> -->
			</ul>
		</li>
		<li><a href="#" class="dropdown-toggle"> <i
				class="icon-bar-chart"></i> <span class="menu-text"> Report </span>
				<b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu">
				<li><a href="#" data-href="/jsp/template/under_maintenance2.jsp"> <i
						class="icon-double-angle-right"></i> Report 1
				</a></li>

				<li><a href="#" data-href="/jsp/template/under_maintenance1.jsp">
						<i class="icon-double-angle-right"></i> Report 2
				</a></li>
			</ul>
		</li>
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed');
		} catch (e) {
		}
	</script>
</div>