<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>功能列表</title>
  </head>
  <body>
        
       <div class="tpl-content-page-title">
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span>功能列表
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

								  <shiro:hasPermission name="picc.function.update">
								  
								     	<a class="am-btn am-btn-primary am-btn-xs btn-update-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 编辑</a>
																	&nbsp;
								  </shiro:hasPermission>
							

			              		  <a class="am-btn am-btn-primary am-btn-xs btn-remove-pro"        bkyy-id="【data】"  href="javascript:;"><i class="am-icon-remove"></i> 删除</a> 

					           </template>
                        
                        <div class="am-u-sm-12">
							<form id="search-form-goods">
								<!-- 查询条件Start -->
	
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">功能名称</span>
										<input type="text"  id="funName" class="am-form-field "  placeholder="功能名称">
									</div>
								</div>
								
								
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-pro" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i> 查询</button>
											<button type="button"  id="btn-reset-pro"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											  <shiro:hasPermission name="picc.function.add">
											         <button type="button"  id="btn-insert-pro"  class="am-btn am-btn-sm am-radius  am-custom-left-10"><i class="am-icon-plus"></i> 新增功能</button>
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
                                            <th width="15%"  class="am-text-middle">功能名称</th>
                                            <th width="15%"  class="am-text-middle">功能描述</th>
                                            <th width="10%"  class="am-text-middle">类型</th>
                                            <th width="15%"  class="am-text-middle">URL</th>
                                            <th width="10%"  class="am-text-middle">排序</th>
                                            <th width="15%"  class="am-text-middle">权限标识</th>
                                            <th width="20%"  class="am-text-middle">操作</th>
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
     <script type="text/javascript" src="${basePath}/statics/js/function/function_list.js"></script>
  </body>

</html>  