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
		<li class="open"><a href="#" class="dropdown-toggle"> <i
				class="icon-dashboard"></i> <span class="menu-text"> Vehicle </span> <b
				class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu"  style="display: block;">
				<li class="active" ><a href="#"> <i class="icon-file"></i>
						<span class="menu-text" data-href="/jsp/smart/vehicle_data.jsp">
							My List </span>
				</a></li>
				<li><a href="#"> <i class="icon-hdd"></i>
						<span class="menu-text" data-href="/jsp/smart/vehicle_data.jsp">
							All Data </span>
				</a></li>
				<li ><a href="#"> <i class="icon-star"></i>
						<span class="menu-text" data-href="/jsp/smart/vehicle_detail.jsp">
							Service Overdue </span>
				</a></li>
				<li ><a href="#"> <i class="icon-ok"></i>
						<span class="menu-text" data-href="/jsp/smart/vehicle_appointment.jsp">
							Recently Served</span>
				</a></li>
			</ul>
		</li>
		
		
		<li><a href="#" class="dropdown-toggle"> <i class="icon-book"></i>
				<span class="menu-text"> Appointment </span> <b
				class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu">
				<li><a href="#" data-href="/jsp/template/under_maintenance1.jsp"> <i
						class="icon-double-angle-right"></i> Appointments
				</a></li>

				<li><a href="#" data-href="/jsp/template/under_maintenance2.jsp">
						<i class="icon-double-angle-right"></i> Tomorrow's Appointment
				</a></li>
			</ul>
		</li>
		
		<li><a href="#" class="dropdown-toggle"> <i
				class="icon-cogs"></i> <span class="menu-text"> Administer </span> <b
				class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li ><a> <i class="icon-cloud-upload"></i>
						<span class="menu-text" data-href="/jsp/template/under_maintenance.jsp">
							Upload</span>
				</a></li>
				<li ><a> <i class="icon-check"></i>
						<span class="menu-text" data-href="/jsp/template/under_maintenance.jsp">
							Assign Caller</span>
				</a></li>
				<li ><a> <i class="icon-group"></i>
						<span class="menu-text" data-href="/jsp/template/under_maintenance.jsp">
							Manage User</span>
				</a></li>
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
				</ul>
			</li>
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
			</ul></li>
		
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>
	<div class="navbar-fixed-bottom">
		Developed by <i class="blue">Asyncsolution</i>
	</div>
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed');
		} catch (e) {
		}
	</script>
</div>