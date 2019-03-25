<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- logo -->
<%--<div class="am-fl tpl-header-logo">
	<strong>Amaze UI</strong> <small>后台管理模板</small>
	&lt;%&ndash;<a href="javascript:;"><img src="${staticDomain}/assets/javascript/amaze/images/logo.png" alt=""></a>&ndash;%&gt;
</div>--%>

    <header class="am-topbar am-topbar-inverse admin-header">
        <div class="am-topbar-brand">
            <a href="javascript:;" class="tpl-logo">
                <img src="${basePath}/statics/assets/img/logo.png" alt="">
            </a>
        </div>
        <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

        </div>

        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
        <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
			
            <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
              
                <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen" class="tpl-header-list-link"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
				
                <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                	<span id="role_select" user_role="${sessionScope.user.role}"></span>
                    <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                        <span class="tpl-header-list-user-nick" >${sessionScope.user.name}</span>
                    </a>
                    
                    
                    
                    <ul class="am-dropdown-content">
                        <li><a href="${basePath}/picc/user/changepwd.html"><span class="am-icon-cog"></span> 密码修改</a></li>
                        <li><a href="${basePath}/login/login"><span class="am-icon-power-off"></span> 退出</a></li>
                    </ul>
                </li>
                <li><a href="###" class="tpl-header-list-link"><span class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
            </ul>
        </div>
    </header>
