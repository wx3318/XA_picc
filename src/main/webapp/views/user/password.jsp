<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path= request.getContextPath(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style>
	
	.submit{
		margin-left: 100px;
	}
</style>

<body style="background-color: #C0C0C0">
	<div class="tpl-block ">
                    <div class="am-g tpl-amazeui-form">
                        <div class="am-u-sm-12 am-u-md-9">
                
                            <form class="am-form am-form-horizontal" method="post" 
                            		action="${basePath}/picc/user/savepwd.html" >
                            	<input type="hidden" id="userid" name="userid" value="${sessionScope.user.user_id}">
                                <div class="am-form-group">
                                    <label for="address" class="am-u-sm-3 am-form-label">原密码</label>
                                    <div id="div_pwd" class="am-u-sm-9  am-form-group am-form-icon am-form-feedback">
                                        <input type="password" id="password" name="password" 
                                        		onblur="checkpwd(this)" placeholder="请输入原始秘密">
                                    	<span id="span_pwd" class=""></span>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="address" class="am-u-sm-3 am-form-label">新密码</label>
                                    <div class="am-u-sm-9 ">
                                        <input type="password" id="newpassword" name="newpassword" placeholder="请输入新秘密">
										<span class=""></span>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="address" class="am-u-sm-3 am-form-label">再输入密码</label>
                                    <div id="div_pwdg" class="am-u-sm-9 am-form-group am-form-icon am-form-feedback">
                                        <input type="password" id="agpassword" name="agpassword" 
                                        onblur="checkagpwd(this)" placeholder="请在输入一次新秘密">
                                    	<span id="span_pwdg" class=""></span>
                                    </div>
                                </div>

								<br>
								<div id="am-form-group">
									<div class="am-u-sm-12" style="margin-left:250px;">
									<input type="submit" value="确定修改" onclick="return check()"
										class="am-btn am-btn-primary">
									</div>	
								</div>
							</form>
                        </div>
                    </div>
                </div>
<script type="text/javascript" src="${basePath}/statics/js/user/user.js"></script>
</body>

</html>