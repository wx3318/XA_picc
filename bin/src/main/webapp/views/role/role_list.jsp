<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>角色列表</title>
  </head>
  <body>
        
       <div class="tpl-content-page-title">
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span>角色列表
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

								  <shiro:hasPermission name="picc.role.update">
									  <a class="am-btn am-btn-primary am-btn-xs btn-update-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 编辑</a>
																	&nbsp;
								  </shiro:hasPermission>
								

								  <shiro:hasPermission name="picc.role.function">
								       <a class="am-btn am-btn-primary am-btn-xs btn-edit-pro"  bkyy-id="【data】"  href="javascript:;"><i class="am-icon-cogs"></i> 权限</a>
								  </shiro:hasPermission>
					  	      

					           </template>
                        
                        <div class="am-u-sm-12">
							<form id="search-form-goods">
								<!-- 查询条件Start -->
	
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">角色名称</span>
										<input type="text"  id="roleName" class="am-form-field "  placeholder="角色名称">
									</div>
								</div>
								
								
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-pro" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-pro"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											<shiro:hasPermission name="picc.role.add">
											    	<button type="button"  id="btn-insert-pro"  class="am-btn am-btn-sm am-radius  am-custom-left-10"><i class="am-icon-plus"></i>新增角色</button>
											</shiro:hasPermission>
					
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                                <table width="100%" id="goodsTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                        <tr>
                                            <th width="20%"  class="am-text-middle">角色名称</th>
                                            <th width="30%"  class="am-text-middle">角色描述</th>
                                            <th width="10%"  class="am-text-middle">创建人</th>
                                            <th width="20%"  class="am-text-middle">创建时间</th>
                                            <th width="20%"  class="am-text-middle">操作</th>
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
     <script type="text/javascript" src="${basePath}/statics/js/role/role_list.js"></script>
  </body>

</html>  