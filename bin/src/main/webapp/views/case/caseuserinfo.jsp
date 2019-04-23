<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>案件归属人</title>
  </head>
  <body>
      <div class="tpl-portlet-components">
	<div class="am-tabs" data-am-tabs>
			  <ul class="am-tabs-nav am-nav am-nav-tabs">
			    <li class="am-active"><a href="#tab1">已分配归属人</a></li>
			    <li><a href="#tab2">未分配归属人</a></li>
			     <li><a href="#tab3">未决归属人导入</a></li>
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
							<form id="search-form-userInfo">
								<!-- 查询条件Start -->
	
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">归属人工号</span>
										<input type="text"  id="userInfo" name="userInfo" class="am-form-field "  placeholder="请输入工号">
									</div>
								</div>
								
							    <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">立案号</span>
										<input type="text"  id="registrationNumber" name="registrationNumber" class="am-form-field "  placeholder="请输入立案号">
									</div>
								</div>
	                            <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">机构</span>
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
									
										    <button type="button"  id="btn-search-info" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-info"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											<shiro:hasPermission name="picc.pending.excelg">
											<button type="button"  id="btn-excel-info" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>																						     	
											 </shiro:hasPermission> 
						
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="groupUserTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                   <th width="6%"  class="am-text-middle">机构</th> 
                                       <th width="10%"  class="am-text-middle">报案号</th>      
	                                   <th width="10%"  class="am-text-middle">立案号</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>	                                   
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>	                                   
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="5%"  class="am-text-middle">归属人</th>                
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
							<form id="search-form-noUserInfo">
								<!-- 查询条件Start -->									
							    <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">立案号</span>
										<input type="text"  id="registrationNumberNo" name="registrationNumberNo" class="am-form-field "  placeholder="请输入立案号">
									</div>
								</div>
	                            <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">机构</span> <select
											 name="uGroup" id="uGroup">
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
									
										    <button type="button"  id="btn-search-prono" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-prono"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
										 	<shiro:hasPermission name="picc.pending.excelg">
										 	<button type="button"  id="btn-excel-prono" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button> 
											</shiro:hasPermission>											      		 														
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                                <table width="100%" id="groupUserNoTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                       <tr>
                                       <!--      <th class="am-text-middle">地市</th> -->
                                       <th width="10%"  class="am-text-middle">机构</th> 
                                       <th width="10%"  class="am-text-middle">报案号</th>      
	                                   <th width="10%"  class="am-text-middle">立案号</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="8%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="8%"  class="am-text-middle">归属人</th>   
	                                              
                                       </tr>
                                    </thead>
                                </table>
                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
    </div>
    <div class="am-tab-panel am-fade" id="tab3">
    		<div class="portlet-title">
				<div class="caption font-green bold">
					<span class="am-icon-code"></span> 未决归属人导入
				</div>
			</div>
				<div style="margin: 20px;">
					 <form method="POST" enctype="multipart/form-data" onsubmit="return false;"> 
						<div class="am-form-group am-form-file">
							<button type="button" class="am-btn am-btn-danger am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 选择要上传文件
							</button>
							<input id="pdingfile" type="file" name="pdingfile">
						</div>
						<div id="file-list-pding"></div>
						<input type="button" value="确定上传" class="am-btn am-btn-primary" id="inport_file_pding" onclick="importPendingExcel()">
					</form>
				</div>
    </div>
  </div>
</div>
</div>
     <script type="text/javascript" src="${basePath}/statics/js/case/userinfo.js"></script>
     <script type="text/javascript" src="${basePath}/statics/js/case/userinfono.js"></script>
     <script type="text/javascript" src="${basePath}/statics/js/case/userinfoimport.js"></script>
</body>

</html>  