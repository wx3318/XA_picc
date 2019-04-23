<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>车险未决报表</title>
  </head>
  <body>
        
       <div class="tpl-content-page-title">
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span>车险未决日报表
                    </div>

                </div>
                <div class="tpl-block">
                    <div class="am-g">                        
 <%--                        
                        	<template id="table-options-1">

								  <shiro:hasPermission name="picc.role.update"> </shiro:hasPermission>
									  <a class="am-btn am-btn-primary am-btn-xs btn-update-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 编辑</a>
								 
					           </template>
                         --%>
                        <div class="am-u-sm-12">
							<form id="search-form-usercase" >
								<!-- 查询条件Start -->
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">截止日期</span>
	                                    <input type="text" id="createDate" name="createDate" class="am-form-field" placeholder="日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                </div>
	                            </div>
						 	<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">结案日期</span>
	                                   	<input type="text" id="startWorkDate" name="startDate" class="am-form-field" placeholder="开始日期"  style="width: 42%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                    <div class="am-form-field am-border-none"  style="width:5%; text-align: center;"><span >-</span></div>
	                                    <input type="text" id="endWorkDate" name="endDate"  class="am-form-field" placeholder="结束日期"   style="width: 42%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                </div>
	                            </div>	
	                            <input type="hidden" id="groupId" name="groupId" value="${groupId}">						
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								 <div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">									
										<button type="submit"  id="btn-search-prouser" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
										<button type="button"  id="btn-reset-prouser"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
										<button type="button"  id="btn-excel-prouser" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
									</div>
								</div> 
								<!-- 按钮End -->
							</form>
					      </div>
					
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                                <table width="100%" id="groupcaseTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                     	<tr>
                                            <th width="100%" style="font-size: 25px; text-align:center;" colspan="13">${dateMonth}车险未决个人报表</th>
                                        </tr>
                                        <tr>
                                            <th width="10%"  class="am-text-middle">机构</th>
                                            <th width="7%"  class="am-text-middle">归属人</th>
                                            <th width="7%"  class="am-text-middle">起始数</th>
                                            <th width="7%"  class="am-text-middle">每日新增</th>
                                            <th width="7%"  class="am-text-middle">每日结案</th>
                                            <th width="8%"  class="am-text-middle">结案总计</th>
                                            <th width="10%"  class="am-text-middle">截止<font color="red">${dateDay}</font>未决</th>                                          
                                            <th width="7%"  class="am-text-middle">起始目标</th>
                                            <th width="7%"  class="am-text-middle " style="color: red;">差距</th>
                                            <th width="7%"  class="am-text-middle">预算目标</th>
                                            <th width="7%"  class="am-text-middle" style="color: red;">差距</th>
                                            <th width="7%"  class="am-text-middle">挑战目标</th>
                                            <th width="5%"  class="am-text-middle"style="color: red;">差距</th>
                                            <th width="5%"  class="am-text-middle">排名</th>                                          
                                        </tr>
                                    </thead>  
                                     <c:forEach items="${userMonthcase}" var="projUser">
										<tr class="a1">
											<th class="am-text-middle">
												<span>${projUser.group_name}</span>
											</th>
											<th class="am-text-middle">${projUser.NAME}</th>
											<th class="am-text-middle">${projUser.starting_number}</th>
											<th class="am-text-middle">${projUser.dayNewCase}</th>
											<th class="am-text-middle">${projUser.dayEndCase}</th>
											<th class="am-text-middle">${projUser.endAllCase}</th>
											<th class="am-text-middle" >${projUser.allNewCase}</th>
											<th class="am-text-middle" >${projUser.start_target_number}</th>
											<th class="am-text-middle" style="color: red;">${projUser.diffstart}</th>
											<th class="am-text-middle" >${projUser.budget_target_number}</th>
											<th class="am-text-middle" style="color: red;">${projUser.diffbudget}</th>
											<th class="am-text-middle">${projUser.challeng_number}</th>
											<th class="am-text-middle" style="color: red;">${projUser.diffchalleng}</th>
											<th class="am-text-middle">${projUser.relt}</th>
										</tr>							
									</c:forEach>                         
                                </table>
                        </div>
                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
       <script type="text/javascript" src="${basePath}/statics/js/case/groupuser.js"></script>
  </body>

</html>  