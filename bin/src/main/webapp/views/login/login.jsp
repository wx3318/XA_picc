<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录</title>
 <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <script type="text/javascript" src="${basePath}/statics/assets/js/jquery.min.js"></script>
  <link rel="stylesheet" href="${basePath}/statics/assets/css/amazeui.min.css" />
  <link rel="stylesheet" href="${basePath}/statics/assets/css/admin.css">
  <link rel="stylesheet" href="${basePath}/statics/assets/css/app.css">
  <script type="text/javascript" src="${basePath}/statics/assets/javascript/common.js"></script>
  

</head>
<style type="text/css">
	.submit{
		margin-left:60px;
		width: 100px;
	
	}
	body{	
		background-color: #F5F5F5;
	}
	.logindiv{
		margin-top: 130px;
		margin-left: 580px;
		background-color: #DCDCDC;
		width: 400px;
		height: 300px;
	}
</style>
<body>
	<div class="am-g myapp-login">
		<div class="myapp-login-logo-block  tpl-login-max">
			<div class="myapp-login-logo-text">
				<div class="myapp-login-logo-text" style="color: red;">
					PICC 西安市<span style="color: red;"> 车险理赔</span>

				</div>
			</div>
			<div class="login-font">
				<i> </i>
			</div>
			<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">

				<form class="am-form" id="form_login" onsubmit="return false;">
					<label style="color: white;">工号:</label> <input type="text"
						name="username" id="username" placeholder="融合系统的工号" 
						pattern="^\d{3,12}$" required  > <br> 
					<label style="color: white;">密码:</label> <input type="password"
						name="password" id="password"  placeholder="请输入密码" required><br> 
					<div class="am-cf" style="margin-left: 100px;">
						<input type="submit" id="" value="登 录" onclick="return check()"
							class="am-btn am-btn-primary am-btn-sm am-fl">
					</div>
					
				</form>
				
			</div>
		</div>
	</div>		
</body>

  <script src="${basePath}/statics/assets/js/jquery.min.js"></script>
  <script src="${basePath}/statics/assets/js/amazeui.min.js"></script>
  <script src="${basePath}/statics/assets/js/app.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/login/login.js"></script>
</html>