<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>每日统计</title>
</head>
<body>
<div class="tpl-portlet-components">
	<div class="portlet-title">
		<div class="caption font-green bold">
			<span class="am-icon-code"></span> 日统计：
			 <font color="black" size="4">
               <c:forEach items="${sessionScope.countMap}" var="i">
				  ${i.key} ${i.value}
				</c:forEach>
               </font>
		</div>
	</div>
 <div class="am-u-sm-12">
	      <div class="am-u-sm-12">
	      					<template id="table-options-1">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updateday-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 详情</a>
					          	</shiro:hasPermission>
					           </template>
							<form id="search-form-timeday">
								<!-- 查询条件Start -->								
							    <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">考勤日期</span>
	                                    <input type="text" id="workDate" name="workDate" class="am-form-field" placeholder="选择日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                    <!-- <div class="am-form-field am-border-none"  style="width:5%; text-align: center;"><span >-</span></div> -->
	                                </div>
	                            </div>
								<!-- 查询条件End -->
								<div class="caption font-green bold">
									<span class="am-icon-code"></span> 自营统计：
									 <font color="black" size="3">
						               <c:forEach items="${sessionScope.countMapOne}" var="i">
										  ${i.key} ${i.value}
										</c:forEach>
						               </font>
						               <br>
						               <span class="am-icon-code"></span> 续转保统计：
									 <font color="black" size="3">
						               <c:forEach items="${sessionScope.countMapTwo}" var="i">
										  ${i.key} ${i.value}
										</c:forEach>
						               </font>
								</div>
								<!-- 按钮Start -->
								
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-day" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-day"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											<button type="button"  id="btn-excel-day" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="workDayTable" class="am-table am-table-striped am-table-hover table-main">
	                            <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                   <th width="8%"  class="am-text-middle">组别</th>
	                                   <th width="8%"  class="am-text-middle">组长姓名</th>
	                                   <th width="8%"  class="am-text-middle">考勤日期</th>
	                                   <th width="10%"  class="am-text-middle">应到人数</th>
	                                   <th width="10%"  class="am-text-middle">实到人数</th>
	                                   <th width="10%"  class="am-text-middle">缺勤人数</th>
	                                   <th width="10%"  class="am-text-middle">迟到人数</th>
	                                   <th width="10%"  class="am-text-middle">请假人数</th>
	                                   <th width="10%"  class="am-text-middle">操作</th>               
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
</div> 
<script type="text/javascript" src="${basePath}/statics/js/worktime/worktimeday.js"></script>	       
</body>
</html>