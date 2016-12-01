<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	<!--jsp  -->
	<script src="<%=basePath%>js/jquery/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=basePath%>js/bootstrap/bootstrap.min.js"></script>

	<!--css-->
	<link href="<%=basePath%>css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>css/bootstrap/dashboard.css" rel="stylesheet">

	<head>
		<title>首页</title>
	</head>