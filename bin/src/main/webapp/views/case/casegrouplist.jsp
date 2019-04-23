<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>月任务设置</title>
  </head>
  <body>
        
       <div class="tpl-content-page-title">
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span>任务列表
                    </div>

                </div>
                <div class="tpl-block">
                    <div class="am-g">
                     <!--    <div class="am-u-sm-12 am-u-md-6">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" class="am-btn am-btn-default am-btn-success"><span class="am-icon-plus"></span> 新增</button>
                                    <button type="button" class="am-btn am-btn-default am-btn-secondary"><span class="am-icon-save"></span> 保存</button>
                                    <button type="button" class="am-btn am-btn-default am-btn-warning"><span class="am-icon-archive"></span> 审核</button>
                                    <button type="button" class="am-btn am-btn-default am-btn-danger"><span class="am-icon-trash-o"></span> 删除</button>
                                </div>
                            </div>
                        </div> -->
                        
                        
                        	<template id="table-options-1">

								  <shiro:hasPermission name="picc.role.update"> </shiro:hasPermission>
									  <a class="am-btn am-btn-primary am-btn-xs btn-update-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 编辑</a>
											&nbsp;&nbsp;
								  <shiro:hasPermission name="picc.role.function"> </shiro:hasPermission>
								       <a class="am-btn am-btn-danger am-btn-xs btn-remove-pro"  bkyy-id="【data】"  href="javascript:;"><i class="am-icon-remove"></i>删除</a>
								 
					           </template>
                        
                        <div class="am-u-sm-12">
							<form id="search-form-groupcase">
								<!-- 查询条件Start -->
							 <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">机构</span>
										<select  name="groupId" id="groupId">
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
									
										    <button type="button"  id="btn-search-pro" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-pro"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											<shiro:hasPermission name="picc.role.add"></shiro:hasPermission>
											 <button type="button"  id="btn-insert-pro"  class="am-btn am-btn-sm am-radius  am-custom-left-10"><i class="am-icon-plus"></i>新增任务</button>
												
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
                                            <th width="15%"  class="am-text-middle">机构</th>
                                            <th width="10%"  class="am-text-middle">月份</th>
                                            <th width="10%"  class="am-text-middle">起始数</th>
                                            <th width="12%"  class="am-text-middle">起始目标数</th>
                                            <th width="12%"  class="am-text-middle">预算目标数</th>
                                            <th width="12%"  class="am-text-middle">挑战目标数</th>  
                                            <th width="12%"  class="am-text-middle">初始结案数量</th>                                           
                                            <th width="30%"  class="am-text-middle">操作</th>
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
      
      <script type="text/javascript" src="${basePath}/statics/js/case/groupcaselist.js"></script>
  </body>

</html>  