<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>员工列表</title>
  </head>
  <body>
            <div class="tpl-portlet-components">
                <div class="tpl-block">
                    <div class="am-g">                                            
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
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-pro" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-pro"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
												
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
									      <th width="15%">姓名</th>
									      <th width="15%">小组</th>
									      <th width="15%">性别</th>
									      <th width="15%">电话</th>
									      <th width="15%">融合工号</th>
									      <th width="15%">营销工号</th>
									    </tr>
									  </thead>
                                </table>
													
                        </div>
                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
           <script type="text/javascript" src="${basePath}/statics/js/user/userkf.js"></script> 
  </body>

</html>  