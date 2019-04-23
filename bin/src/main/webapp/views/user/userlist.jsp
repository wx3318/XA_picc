<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>员工列表</title>
  </head>
  <body>
            <div class="tpl-portlet-components">
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
								
						<a class="am-btn am-btn-primary am-btn-xs btn-update-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 编辑</a>
																																		  	      
					  </template>                        
                        <div class="am-u-sm-12">
							<form id="search-form-user">
								<!-- 查询条件Start -->
	
								<div class="am-u-sm-3 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										
										<span class="am-input-group-label">员工姓名</span>
										<input type="text"  id="name" class="am-form-field "  placeholder="员工名字">
									</div>
								</div>
								<div class="am-u-sm-3 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										
										<span class="am-input-group-label">工号</span>
										<input type="text"  id="username" class="am-form-field "  placeholder="融合工号" >
									</div>
								</div>
								<div class="am-u-sm-4 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
									<span class="am-input-group-label">组别：</span>
										<select  name='groupId' id="groupId">
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
											<shiro:hasPermission name="picc.user.export">
											<button type="button"  id="user-excel-pro" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
											</shiro:hasPermission>
											<shiro:hasPermission name="picc.user.add">
											    	<button type="button"  id="add-user-pro"  class="am-btn am-btn-sm am-radius  am-custom-left-10"><i class="am-icon-plus"></i>新增员工</button>
											</shiro:hasPermission>
												
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                                <table width="100%" id="userTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
									    <tr>
									      <th width="10%">姓名</th>
									      <th width="10%">角色</th>
									      <th width="15%">小组</th>
									      <th width="10%">状态</th>
									      <th width="15%">工号</th>
									      <th width="15%">操作</th> 
									    </tr>
									  </thead>
                                </table>
													
                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
            <script type="text/javascript" src="${basePath}/statics/js/user/usercrud.js"></script>
  </body>

</html>  