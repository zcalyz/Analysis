<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>
<script src="<%=basePath%>js/echart/echarts.min.js"></script>

<script src="<%=basePath%>js/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="<%=basePath%>js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- 日历插件  -->
<link href="<%=basePath%>css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet">


<body>
	<jsp:include page="layout/top_nav.jsp" flush="true" />
	
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="layout/left_nav.jsp" flush="true" />
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Dashboard</h1>
				
				<div class="input-group ">
				<span class="input-group-addon">startTime</span> 
					<div class="date form_datetime"
								data-date="2016-11-16T05:25:07Z"
								data-date-format="yyyy-mm-dd HH:ii"
								data-link-field="dtp_input1">

						<input type="text" id="startTime" name="startTime" class="form-control" placeholder="点击选择日期"/>
						<span class="add-on"><i class="icon-th"></i></span>
						<input type="hidden" id="dtp_input1" value="" />
				    </div>
				    <span class="input-group-addon">endTime</span> 
				    <div class="date form_datetime"
								data-date="2016-12-16T05:25:07Z"
								data-date-format="yyyy-mm-dd hh:ii:ss"
								data-link-field="dtp_input1">

						 <input id="endTime"  name="endTime" type="text" class="form-control" placeholder="点击选择日期"/>
						<span class="add-on"><i class="icon-th"></i></span>
						<input type="hidden" id="dtp_input2" value="" />
				    </div>
				    <span class="input-group-btn">
        				<button class="btn btn-default" type="button" onclick="goAnalysis()">Go!</button>
      				</span>
				      
				</div>

				<script type="text/javascript">
					$('.form_datetime').datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss', //日期格式
						language : 'zh-CN',
						weekStart : 1,
						todayBtn : 1, // 是否有今天的按钮
						autoclose : 1,
						todayHighlight : 1,
						startView : 2,
						forceParse : 0,
						showMeridian : 1, // 区分上午、下午
						minuteStep : 5 // 分钟间隔
					});
				</script>

				<div id="main" style="width: 800px; height: 400px; margin-top: 20px"></div>
				
				<!-- echart 相关js -->
				<script type="text/javascript">
					// 基于准备好的dom，初始化echarts实例
					var myChart = echarts.init(document.getElementById('main'));

					// 指定图表的配置项和数据
					option = {
						title : {
							text : '性能预测',
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
				</script>

				<script type="text/javascript">
					/* 调用分析数据函数  */
					function goAnalysis(){
						 var startTime = document.getElementById('startTime').value;
						 var endTime = document.getElementById('endTime').value;
						 if(startTime.trim() != '' && endTime.trim() != ''){
							 getChartData(startTime, endTime);
						 }else{
							 alert("时间不能为空");
						 }
						 
					}
					// 1. 定时任务
					// timeId = setInterval("getChartData();", 8000);
					// 2. 刚载入时调用
						window.onload=function(){
						getChartData();
					  }
					
					/* 获取分析数据 */
					function getChartData(startTime,endTime) {
						//获得图表的options对象 ;
						var options = myChart.getOption();
						//通过Ajax获取数据 
						$.ajax({
									type : "post",
									async : true, //false 同步执行 
									url : "chart/simpleChart.do",
									data : {"startTime":startTime,"endTime":endTime},
									dataType : "json", //返回数据形式为json
									success : function(result) {
										if (result) {
											options.legend = result.legend;
											options.xAxis = result.xAxis;
											options.series = result.peformanceDataSeries;
											options.yAxis = result.yAxis;

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