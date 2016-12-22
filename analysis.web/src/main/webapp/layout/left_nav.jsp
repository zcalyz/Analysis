
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li id="li_main" class="active"><a href="main.jsp?active_li=li_main">Overview</a></li>
		<li id="li_panel"><a href="panel.jsp?active_li=li_panel">Analysis</a></li>
		<li id="li_resource"><a href="resource.jsp?active_li=li_resource">utilazation</a></li>
		<li><a href="#">Export</a></li>
	</ul>
	<ul class="nav nav-sidebar">
		<li><a href="">Nav item</a></li>
		<li><a href="">Nav item again</a></li>
		<li><a href="">One more nav</a></li>
		<li><a href="">Another nav item</a></li>
		<li><a href="">More navigation</a></li>
	</ul>
	<ul class="nav nav-sidebar">
		<li><a href="">Nav item again</a></li>
		<li><a href="">One more nav</a></li>
		<li><a href="">Another nav item</a></li>
	</ul>
</div>

<input id="active_li_id" type="text" value="<%=request.getParameter("active_li") %>"></input>

<script>
	var activeLi = $("#active_li_id").val();

	if (activeLi != "li_main") {
		$("#li_main").removeClass("active");
		$("#" + activeLi + "").addClass("active");
	}
</script>
