<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>
<script src="<%=basePath%>js/echart/echarts.min.js"></script>

<body>
	<jsp:include page="layout/top_nav.jsp" flush="true" />

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="layout/left_nav.jsp" flush="true" />
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Dashboard</h1>
				<div id="main" style="width: 800px; height: 400px;"></div>

				<script type="text/javascript">
					// 基于准备好的dom，初始化echarts实例
					var myChart = echarts.init(document.getElementById('main'));

					// 指定图表的配置项和数据
					option = {
						title : {
							text : '响应时间预测',
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : [ '预测', '真实' ]
						},
						toolbox : {
							show : true,
							feature : {
								magicType : {
									show : true,
									type : [ 'stack', 'tiled' ]
								},
								saveAsImage : {
									show : true
								}
							}
						},
						xAxis : {
							type : 'category',
							boundaryGap : false,
							data : [ '1', '5', '10', '15', '20', '25', '30' ]
						},
						yAxis : {
							type : 'value'
						},
						series : [ {
							name : '预测',
							type : 'line',
							smooth : true,
							data : [ 10, 12, 21, 54, 260, 830, 840 ]
						}, ]
					};

					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);

					myChart.hideLoading();
				 	// timeId = setInterval("getChartData();", 8000);
				 	window.onload=function(){
				 		getChartData();
				 	}
				</script>

				<script type="text/javascript">
					function getChartData() {
						//获得图表的options对象 
						var options = myChart.getOption();
						//通过Ajax获取数据 
						$
								.ajax({
									type : "get",
									async : false, //同步执行 
									url : "chart/simpleChart.do",
									data : {},
									dataType : "json", //返回数据形式为json
									success : function(result) {
										if (result) {
											options.legend = result.legend;
											options.xAxis = result.xAxis;
											options.series = result.peformanceDataSeries;

											myChart.hideLoading();
											myChart.setOption(options);
										}
									},
									error : function(errorMsg) {
										alert("图表请求数据失败啦!");
										myChart.hideLoading();
									}
								});
					}
				</script>
			</div>
		</div>
	</div>
</body>
</html>