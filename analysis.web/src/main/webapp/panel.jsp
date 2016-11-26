<%@include file="layout/header.jsp"%>

<body>
	<jsp:include page="layout/top_nav.jsp" flush="true" />

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="layout/left_nav.jsp" flush="true" />
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="panel panel-default">
					<div class="panel-heading">Panel heading without title</div>
					<div class="panel-body">Panel content</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>