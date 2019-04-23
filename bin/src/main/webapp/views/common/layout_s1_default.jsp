<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title><decorator:title /></title>
		<meta name="description" content="">
		<meta name="keywords" content="index">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-cache" />
		<link rel="icon" type="image/png" href="${basePath}/statics/assets/img/favicon.png">
		<link rel="apple-touch-icon-precomposed" href="${basePath}/statics/assets/plugins/amaze/images/app-icon72x72@2x.png">
		<meta name="apple-mobile-web-app-title" content="Amaze UI" />
		
		 <jsp:include page="layout_s1_css.jsp"/> 
		<script>
            var basePath = '${basePath}';
		</script>
		  <script type="text/javascript" src="${basePath}/statics/assets/js/jquery.min.js"></script>
<%--        <script src="${basePath}/statics/assets/js/jquery.min.js"></script>
          <script src="${basePath}/statics/assets/javascript/common.js"></script> --%>
		<decorator:head />

	</head>

	<body data-type="generalComponents">
			<!-- 头部 -->
			<header>
				<jsp:include page="layout_s1_header.jsp"/>
			</header>
			<!-- 侧边导航栏 -->
		    <div class="tpl-page-container tpl-page-header-fixed">
				<!-- 用户信息 -->
				<%--<div class="tpl-sidebar-user-panel">
					<div class="tpl-user-panel-slide-toggleable">
						<div class="tpl-user-panel-profile-picture">
							<img src="${imagePath}${loginUser.pictureUrl}" alt="">
						</div>
						<span class="user-panel-logged-in-text"> <i
							class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
							${loginUser.name}
						</span> <a href="javascript:;" class="tpl-user-panel-action-link"> &lt;%&ndash;<span
							class="am-icon-pencil"></span> 账号设置&ndash;%&gt;
						</a>
					</div>
				</div>--%>
	
				<!-- 菜单 -->
				   <jsp:include page="layout_s1_menu.jsp"/>
			
	
	
					<!-- 内容区域 -->
					<div class="tpl-content-wrapper">
							<decorator:body />
					</div>
			 </div>		
	</body>
	
	<jsp:include page="layout_s1_javascript.jsp"/>
</html>