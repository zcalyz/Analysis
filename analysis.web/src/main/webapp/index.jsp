<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>简洁漂亮的jquery日期选择控件代码 - 素材家园（www.sucaijiayuan.com）</title>
<link href="<%=basePath%>css/datePicker/datePicker.css" rel="stylesheet" type="text/css" media="all" />
<style type="text/css"> 
*{ margin:0; padding:0;}
body { font:12px/1.5 Arial; color:#666; background:#fff;}
ul,li{ list-style:none;}
img{border:0 none;}
</style> 
<script src="<%=basePath%>js/datePicker/jquery.min.js"></script>
<script src="<%=basePath%>js/datePicker/jquery.date_input.pack.js"></script>

</head>
<body>
<script type="text/javascript">
$(function(){
	$('#datePickerStart').date_input();
	$('#datePicker2').date_input();
	})
</script>
<center style="margin-top:100px;">
<h3 style="margin:30px;">点击下面的输入框，会弹出日期选择控件！</h3>
<input type="text" id="datePickerStart" class="date_picker" placeholder="点击选择日期"/>
<input type="text" id="datePicker2" class="date_picker" placeholder="点击选择日期"/>
</center>
</body> 
</html>