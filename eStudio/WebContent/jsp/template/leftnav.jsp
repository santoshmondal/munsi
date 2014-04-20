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
		<li class="active"><a href="#"> <i class="icon-film"></i>
				<span class="menu-text" data-href="/jsp/studio/newinvoice.jsp">
					New Invoice </span>
		</a></li>
	
		<li><a href="#" class="dropdown-toggle"> <i class="icon-book"></i>
				<span class="menu-text"> Orderbook </span> <b
				class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu">
				<li><a href="#" data-href="/jsp/studio/orderbookall.jsp"> <i
						class="icon-double-angle-right"></i> All
				</a></li>
				<li><a href="#" data-href="/jsp/studio/delivered.jsp">
						<i class="icon-double-angle-right"></i> Delivered
				</a></li>
				<li><a href="#" data-href="/jsp/studio/senttoprint.jsp">
						<i class="icon-double-angle-right"></i> Sent to Print
				</a></li>
				<li><a href="#" data-href="/jsp/template/under_maintenance.jsp">
						<i class="icon-double-angle-right"></i> Raw Data
				</a></li>
				<li><a href="#" data-href="/jsp/template/under_maintenance.jsp">
						<i class="icon-double-angle-right"></i> Final Data
				</a></li>
				
				<li><a href="#" data-href="/jsp/template/under_maintenance.jsp">
						<i class="icon-double-angle-right"></i> Received from Print
				</a></li>
				
			</ul>
		</li>
		
		<li><a href="#" class="dropdown-toggle"> <i
				class="icon-list-alt"></i> <span class="menu-text"> Master </span> <b
				class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu">
				<li><a href="#" data-href="/jsp/master/main_acc_master.jsp">
						<i class="icon-double-angle-right"></i>Main Account
				</a></li>
				<li><a href="#" data-href="/jsp/master/manufacturer_master.jsp">
						<i class="icon-double-angle-right"></i> Manufacturer
				</a></li>
				<li><a href="#" data-href="/jsp/master/tax_master.jsp">
						<i class="icon-double-angle-right"></i> TAX
				</a></li>
				<li ><a href="#" 
					data-href="/jsp/template/under_maintenance.jsp"> <i
						class="icon-double-angle-right"></i> <span class="menu-text">
							Van <span class="badge badge-transparent"> <i
								class="icon-truck green" style='font-size: 1.5em'></i>
						</span>
					</span>
				</a></li>
			</ul>
		</li>
		<li><a href="#" class="dropdown-toggle"> <i
				class="icon-bar-chart"></i> <span class="menu-text"> Report </span>
				<b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu">
				<li><a href="#" data-href="/jsp/studio/invoiceprint.jsp"> <i
						class="icon-double-angle-right"></i> Report 1
				</a></li>

				<li><a href="#" data-href="/jsp/studio/invoicedetail.jsp">
						<i class="icon-double-angle-right"></i> Report 2
				</a></li>
			</ul></li>
		
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