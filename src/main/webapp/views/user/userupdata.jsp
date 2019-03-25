<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <script type="text/javascript" src="${basePath}/statics/assets/js/jquery.min.js"></script>
  <link rel="stylesheet" href="${basePath}/statics/assets/css/amazeui.min.css" />
  <link rel="stylesheet" href="${basePath}/statics/assets/css/admin.css">
  <link rel="stylesheet" href="${basePath}/statics/assets/css/app.css">
  <script type="text/javascript" src="${basePath}/statics/assets/javascript/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/statics/js/jquery.min.js"></script>
<title>员工信息修改</title>
<style type="text/css">
 tr td{
 	text-align: right;
 }
select{
	width: 143px;
}

</style>
</head>
<script type="text/javascript">

</script>
<body style="background-color: #C0C0C0">
	<div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 员工信息修改
                        &nbsp; &nbsp; &nbsp; &nbsp;  <font size="3" color="red">(&nbsp;修改信息时务必查看带*号的是否填写&nbsp; )</font>
                    </div>

	
                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="user-update-form"  >
                            
                              <fieldset> 
                                 <input type="hidden" value="${user.user_id}" id="user_id" name="user_id" >
                                 <!--  这里用到createId 存储 初始groupId -->
                                 <input type="hidden" value="${user.groupId}" id="user_id" name="createdId" >
                                <div class="am-form-group">
                                    <label for="name" class="am-u-sm-3 am-form-label">姓名<font id="name_span" color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input value="${user.name} " type="text" id="name" name="name" placeholder="请输入姓名" required  maxlength="200" >
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="role" class="am-u-sm-3 am-form-label">职位<font id="role_span" color="red">*</font></label>
                                    <div class="am-u-sm-9">
						                <select name='roleId' id="roleId">
							            	<option value="">--请选择--</option>
							            	<c:forEach items="${roleList}" var="role"> 
							            		<option value="${role.id }" ${user.role==role.name?'selected':''}>${role.name}</option>
							            	</c:forEach>
							            </select>
                                    </div>
                                </div>
				
	            				<div class="am-form-group">
                                    <label for="group" class="am-u-sm-3 am-form-label">组别<font id="group_span" color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <select  name="groupId" id="groupId">
							            	<option value="">--请选择--</option>
							            	<c:forEach items="${groupList}" var="group">
							            	<option value="${group.id}" ${user.groupId==group.id?'selected':''}>${group.groupName}</option>
							            	</c:forEach>     	
	            						</select>
                                    </div>
                                </div>
                                
	            				<div class="am-form-group">
                                    <label for="station" class="am-u-sm-3 am-form-label">未决<font id="station_span" color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <select  name="station" id="station">
							            	<option value="">--请选择--</option>
							            	  <option value="1">带走(自己)</option>
							            	  <option value="2">留下(所有)</option>
							            	  <option value="3">平局分(所有)</option>	
							            	  <option value="4">带走(两天)</option>	
	            						</select>
                                    </div>
                                </div>                               
                                <div class="am-form-group">
                                    <label for="work_mes" class="am-u-sm-3 am-form-label">在职状况</label>
                                    <div class="am-u-sm-9">
                                        <select name="work_mes"  id="work_mes">
							            	<option value="">--请选择--</option>
							            	<option value="离职" ${user.work_mes=='离职'?'selected':''}>离职</option>
							            	<option value="在职" ${user.work_mes=='在职'?'selected':''}>在职</option>
							            	<option value="休假" ${user.work_mes=='休假'?'selected':''}>休假</option>
							            </select>
                                      
                                    </div>
                                </div>
                               
                                <div class="am-form-group">
                                    <label  for="username" class="am-u-sm-3 am-form-label">系统工号<font id="username_span" color="red">*</font></label>
                                    <div class="am-u-sm-6">
                                        <input value="${user.username}" type="text" id="username" name="username" placeholder="请输入融合系统工号" required maxlength="50">
                                    </div>
                                     <label for="username_1" id="username_mes" class="am-u-sm-3 am-form-label"></label>
                                </div>
                               
                                
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary js-modal-open" id="user-update-btn">确定修改</button>
                                    </div>
                                </div>
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>

            </div>
			<div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
			  <div class="am-modal-dialog">
			    <div class="am-modal-hd">请检查员工信息
			      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
			    </div>
			    <div class="am-modal-bd">
			      带*号的信息不符合
			    </div>
			  </div>
			</div>
	<script type="text/javascript" src="${basePath}/statics/js/user/userupdate.js"></script>
</body>
<script src="${basePath}/statics/assets/js/jquery.min.js"></script>
  <script src="${basePath}/statics/assets/js/amazeui.min.js"></script>
  <script src="${basePath}/statics/assets/js/app.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/login/login.js"></script>
</html>