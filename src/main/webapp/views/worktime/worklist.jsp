<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考勤列表</title>
</head>
<body>
	<div class="tpl-portlet-components">
	<div class="am-tabs" data-am-tabs>
			  <ul class="am-tabs-nav am-nav am-nav-tabs">
			    <li class="am-active"><a href="#tab1">每月累计打卡</a></li>
			    <li><a href="#tab2">每天打卡详情</a></li>
			  </ul>
  <div class="am-tabs-bd">
    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
	      <div class="am-u-sm-12">
	      <div class="am-u-sm-12">
	      			<template id="table-options-2">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updatemonth-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i>详情</a>
					          	</shiro:hasPermission>
					           </template>
							<form id="search-form-worktime-month">
								<!-- 查询条件Start -->
	
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uNameMonth" name="uNameMonth" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div>
								
							    <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">考勤月份</span>
	                                    <input type="text" id="workTimeMonth" name="workTimeMonth" class="am-form-field" placeholder="选择月份"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                    <!-- <div class="am-form-field am-border-none"  style="width:5%; text-align: center;"><span >-</span></div> -->
	                                </div>
	                            </div>
	                            <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">团队</span>
	                                    <select 
											 name='groupId' id="groupId">
											<option value="">--请选择--</option>
											 <c:forEach items="${groupList}" var="group">
												<option value="${group.id}">${group.groupName}</option>
											</c:forEach> 
										</select>
									</div>
	                            </div>
								<!-- 查询条件End -->
								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-month" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-month"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											  <shiro:hasPermission name="picc.attence.month.export">
											     	<button type="button"  id="btn-excel-month" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
											  </shiro:hasPermission>
						
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="workTimeMonthTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                   <th width="6%"  class="am-text-middle">团队名称</th>
	                                   <th width="5%"  class="am-text-middle">工号</th>
	                                   <th width="4%"  class="am-text-middle">姓名</th>
	                                   <th width="6%"  class="am-text-middle">打卡月份</th>
	                                   <th width="6%"  class="am-text-middle">上班天数</th>
	                                   <th width="6%"  class="am-text-middle">缺勤天数</th>
	                                   <th width="6%"  class="am-text-middle">迟到次数</th>
	                                   <th width="8%"  class="am-text-middle">上班未打卡次数</th>
	                                   <th width="8%"  class="am-text-middle">下班未打卡次数</th>
	                                   <th width="6%"  class="am-text-middle">请假次数</th> 
	                                   <th width="4%"  class="am-text-middle">操作</th>               
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
    </div>
    <div class="am-tab-panel am-fade" id="tab2">
       <div class="tpl-block">
                    <div class="am-g">
                        		<template id="table-options-1">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updateday-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 编辑</a>
					          	</shiro:hasPermission>
					           </template>
                        		
                        <div class="am-u-sm-12">
							<form id="search-form-worktime">
								<!-- 查询条件Start -->	
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uName" name="uName" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div>
								
							    <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">打卡日期</span>
	                                    <input type="text" id="startWorkDate" name="startWorkDate" class="am-form-field" placeholder="开始日期"  style="width: 42%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                    <div class="am-form-field am-border-none"  style="width:5%; text-align: center;"><span >-</span></div>
	                                    <input type="text" id="endWorkDate" name="endWorkDate"  class="am-form-field" placeholder="结束日期"   style="width: 42%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                </div>
	                            </div>
	                            <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">团队</span> <select
											 name="uGroup" id="uGroup">
											<option value="">--请选择--</option>
											 <c:forEach items="${groupList}" var="group">
												<option value="${group.id}">${group.groupName}</option>
											</c:forEach>
										</select>
									</div>
	                            </div>
	                            <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">考勤状态</span> <select
											 name="workTion" id="workTion">
											<option value="">--请选择--</option>
												<option value="5">请假</option>
												<option value="1">迟到</option>
												<option value="2">缺勤</option>
												<option value="3">下班未打卡</option>
												<option value="4">上班未打卡</option>
										</select>
									</div>
	                            </div>
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-pro1" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-worktime"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											  <shiro:hasPermission name="picc.attence.day.export">
											      		 <button type="button"  id="btn-excel-day" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
											  </shiro:hasPermission>
			
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                                <table width="100%" id="workTimeTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                        <tr>
                                       <!--      <th class="am-text-middle">地市</th> -->
                                            <th width="8%"   class="am-text-middle">团队名称</th>
                                            <th width="8%"   class="am-text-middle">工号</th>
                                            <th width="8%"   class="am-text-middle">姓名</th>
                                            <th width="8%"   class="am-text-middle">打卡日期</th>
                                            <th width="10%"  class="am-text-middle">上班打卡时间</th>
                                            <th width="10%"  class="am-text-middle">下班打卡时间</th>
                                            <th width="5%"   class="am-text-middle">状态</th>
                                            <th width="18%"  class="am-text-middle">备注</th>
                                            <th width="8%"   class="am-text-middle">操作</th>               
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
    </div>
  </div>
</div>
</div>
<script type="text/javascript" src="${basePath}/statics/js/worktime/worktimemonth.js"></script>	
<script type="text/javascript" src="${basePath}/statics/js/worktime/worktimelist.js"></script>	
</body>
</html>