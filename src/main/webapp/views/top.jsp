<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
.top_bar{
	margin-top:25px;
	margin-right:20px;
	float:right;
	background-color: white;
}
body{
	padding: 0px;
	margin: 0px;
}
		
</style>
<body  >
	<!-- <img alt="" src="../statics/img/top_picc.jpg" height="100%" width="auto"> --> 
	<div id="div" style="width: auto; height: 62px; background-image:url('../statics/img/topbd.jpg');
	background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;">
	<div class="top_bar">
		<span>${sessionScope.user.group}</span> | <span>${sessionScope.user.name}</span > |
		<a href="<%=request.getContextPath() %>/user/newlogin" target="_top">注销</a>
	</div>
	</div>
</body>
</html>