<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@include file="/layout/header.jsp" %>

<body>
	<jsp:include page="/layout/top_nav.jsp" flush="true"/>

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/layout/left_nav.jsp" flush="true"/>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">${resultList.stationName}</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<c:forEach items="${resultList.transactionNames}" var="transactionName">
									<th>${transactionName}</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>residenceTime</td>
								<c:forEach items="${resultList.residenceTimes}" var="residenceTime">
									<th>${residenceTime}</th>
								</c:forEach>
							</tr>
							<tr>
								<td>queueLength</td>
								<c:forEach items="${resultList.queueLengths}" var="queueLength">
									<th>${queueLength}</th>
								</c:forEach>
							</tr>
							<tr>
								<td>utilization</td>
								<c:forEach items="${resultList.utilizations}" var="utilization">
									<th>${utilization}</th>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>