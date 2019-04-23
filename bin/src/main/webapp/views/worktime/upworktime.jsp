<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考勤修改</title>
</head>
<body>
	<div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 天考勤备注                    
                    </div>


                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="worktime-update-form"  >
                            
                              <fieldset> 
                                 <input type="hidden" name="id" id="id" value="${workTime.id}">   
                                <div class="am-form-group">
                                    <label for="groupId" class="am-u-sm-3 am-form-label">组别</label>
                                    <div class="am-u-sm-9">
                                        <select  name="groupId" id="groupId" disabled="disabled">
							            	<option value="">--请选择--</option>
							            	<c:forEach items="${groupList}" var="group">
							            	<option value="${group.id}" ${workTime.groupId==group.id?'selected':''}>${group.groupName}</option>
							            	</c:forEach>
	            						</select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="userName" class="am-u-sm-3 am-form-label">工号</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" value="${workTime.userName }" id="userName" name="userName"   required maxlength="200" readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="uName" class="am-u-sm-3 am-form-label">姓名</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" value="${workTime.uName }" id="uName" name="uName"  required  maxlength="200" readonly>
                                    </div>
                                </div>
								<div class="am-form-group">
                                    <label for="workDate" class="am-u-sm-3 am-form-label">打卡日期</label>
                                    <div class="am-u-sm-9">
                                        <input value="<fmt:formatDate value='${workTime.workDate }' pattern='yyyy-MM-dd' />" 
                                        type="text"   style="background-color: white;" id="workDate" name="workDate"  required readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="startTime" class="am-u-sm-3 am-form-label">上班打卡时间</label>
                                    <div class="am-u-sm-9">
                                         <input value="<fmt:formatDate value='${workTime.startTime }' pattern='HH:mm:ss' />" 
                                        type="text"   style="background-color: white;" id="startTime" name="startTime"  required readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="endTime" class="am-u-sm-3 am-form-label">下班打卡时间</label>
                                    <div class="am-u-sm-9">
                                         <input value="<fmt:formatDate value='${workTime.endTime }' pattern='HH:mm:ss' />" 
                                        type="text"   style="background-color: white;" id="endTime" name="endTime"  required readonly>
                                    </div>
                                </div>
	            				<div class="am-form-group">
                                    <label for="station" class="am-u-sm-3 am-form-label">状态</label>
                                    <div class="am-u-sm-9">
                                        <select  name="station" id="station" >
							            	<option value="1" ${workTime.station==1?'selected':''}>正常</option>
							            	<option value="2" ${workTime.station==2?'selected':''}>请假</option>
							            	<option value="4" ${workTime.station==4?'selected':''}>外出</option>
	            						</select>
                                    </div>
                                </div>
									
                                <div class="am-form-group">
                                    <label for="message" class="am-u-sm-3 am-form-label">备注</label>
                                    <div class="am-u-sm-9">
                                              <input type="text"  value="${workTime.message }"  id="message" name="message" placeholder="请输入备注" required maxlength="50">
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary js-modal-open" id="worktime-update-btn">确定修改</button>
                                    </div>
                                </div>
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>

            </div>
        <script type="text/javascript" src="${basePath}/statics/js/worktime/upworktime.js"></script>	    
</body>
</html>