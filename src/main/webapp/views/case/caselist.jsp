<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>未决案件</title>
</head>
<body>
	<div class="tpl-portlet-components">
	<div class="am-tabs" data-am-tabs>
			  <ul class="am-tabs-nav am-nav am-nav-tabs">
			    <li class="am-active"><a href="#tab1">省内立未理</a></li>
			    <li><a href="#tab2">郊县立未理</a></li>
			    <li><a href="#tab3">省间通赔</a></li>
			    <li><a href="#tab4">城区立未理</a></li>
			    <li><a href="#tab5">辖区立未理</a></li>
			    <li><a href="#tab6">通赔未结案</a></li>
			    <li><a href="#tab7">郊县未结案</a></li>
			    <li><a href="#tab8">城区未结案</a></li>
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
							<form id="search-form-sheng">								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
											<button type="button"  id="btn-reset-sheng"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>											
											  <shiro:hasPermission name="picc.attence.month.export"></shiro:hasPermission>
											 <button type="button"  id="btn-excel-sheng" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>											  						
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="shengLiTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                   <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>                                             
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
							<form id="search-form-jiao">
								<!-- 查询条件Start -->	
							<%-- 	<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uName" name="uName" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div>--%>
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
										<button type="button"  id="btn-reset-jiao"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>										
										<shiro:hasPermission name="picc.attence.day.export"></shiro:hasPermission>
										<button type="button"  id="btn-excel-jiao" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                                <table width="100%" id="jiaoLiTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                        <tr>
                                       <!--      <th class="am-text-middle">地市</th> -->
                                       <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>             
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
    </div>
 	<div class="am-tab-panel am-fade" id="tab3">
       <div class="tpl-block">
                    <div class="am-g">
                        		<template id="table-options-1">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updateday-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i> 编辑</a>
					          	</shiro:hasPermission>
					           </template>
                        		
                        <div class="am-u-sm-12">
							<form id="search-form-tp">
								<!-- 查询条件Start -->	
	                            <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">定损状态</span> <select
											 name="damageCode" id="damageCode">
											<option value="">--请选择--</option>
											<option value="1">已定损</option>
											<option value="2">未定损</option>
										</select>
									</div>
	                            </div>
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-tp" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-tp"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>											
											  <shiro:hasPermission name="picc.attence.day.export"></shiro:hasPermission>
											<button type="button"  id="btn-excel-tp" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12">
                                <table width="100%" id="tpLiTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                        <tr>
                                       <!--      <th class="am-text-middle">地市</th> -->
                                       <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>               
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
    </div>
    
    <div class="am-tab-panel am-fade am-in am-active" id="tab4">
	      <div class="am-u-sm-12">
	      <div class="am-u-sm-12">
	      			<template id="table-options-2">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updatemonth-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i>详情</a>
					          	</shiro:hasPermission>
					           </template>
							<form id="search-form-cq">
								<!-- 查询条件Start -->	
							<%-- 	<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uNameMonth" name="uNameMonth" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div>--%>
								<!-- 查询条件End -->
								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">									
										<button type="button"  id="btn-reset-cq"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>											
										<shiro:hasPermission name="picc.attence.month.export"></shiro:hasPermission>	
										<button type="button"  id="btn-excel-cq" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
															
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="chengLiTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                   <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>              
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
    </div>
    
    <div class="am-tab-panel am-fade am-in am-active" id="tab5">
	      <div class="am-u-sm-12">
	      <div class="am-u-sm-12">
	      			<template id="table-options-2">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updatemonth-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i>详情</a>
					          	</shiro:hasPermission>
					           </template>
							<form id="search-form-xn">
								<!-- 查询条件Start -->	
								<%-- <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uNameMonth" name="uNameMonth" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div>--%>
								<!-- 查询条件End -->
								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
										<button type="button"  id="btn-reset-xn"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
										 <shiro:hasPermission name="picc.attence.month.export"></shiro:hasPermission>
										<button type="button"  id="btn-excel-xn" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>																
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="xiaLiTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                  <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>               
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
    </div>
    
    <div class="am-tab-panel am-fade am-in am-active" id="tab6">
	      <div class="am-u-sm-12">
	      <div class="am-u-sm-12">
	      			<template id="table-options-2">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updatemonth-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i>详情</a>
					          	</shiro:hasPermission>
					           </template>
							<form id="search-form-tpno">
								<!-- 查询条件Start -->	
								<%-- <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uNameMonth" name="uNameMonth" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div> --%>
								<!-- 查询条件End -->								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										   <!--  <button type="button"  id="btn-search-tpno" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button> -->
											<button type="button"  id="btn-reset-tpno"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											 <shiro:hasPermission name="picc.attence.month.export"> </shiro:hasPermission>
											 <button type="button"  id="btn-excel-tpno" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>											 
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="tpNoTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                   <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="5%"  class="am-text-middle">理算员代码</th> 
	                                   <th width="5%"  class="am-text-middle">理算员</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>               
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
    </div>
    
    <div class="am-tab-panel am-fade am-in am-active" id="tab7">
	      <div class="am-u-sm-12">
	      <div class="am-u-sm-12">
	      			<template id="table-options-2">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updatemonth-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i>详情</a>
					          	</shiro:hasPermission>
					           </template>
							<form id="search-form-jiaono">
								<!-- 查询条件Start -->	
								<%-- <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uNameMonth" name="uNameMonth" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div>--%>
								<!-- 查询条件End -->
								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										   <!--  <button type="button"  id="btn-search-jiaono" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button> -->
											<button type="button"  id="btn-reset-jiaono"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>										
											 <shiro:hasPermission name="picc.attence.month.export">  </shiro:hasPermission>
											 <button type="button"  id="btn-excel-jiaono" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
											
						
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="jiaoNoTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                   <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="5%"  class="am-text-middle">理算员代码</th> 
	                                   <th width="5%"  class="am-text-middle">理算员</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>              
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
    </div>
    
    <div class="am-tab-panel am-fade am-in am-active" id="tab8">
	      <div class="am-u-sm-12">
	      <div class="am-u-sm-12">
	      			<template id="table-options-2">
								<shiro:hasPermission name="picc.attence.worktime.update">  
					          	<a class="am-btn am-btn-primary am-btn-xs btn-updatemonth-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-edit"></i>详情</a>
					          	</shiro:hasPermission>
					           </template>
							 <form id="search-form-cqno">
								<!-- 查询条件Start -->	
							<%--	<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">姓名</span>
										<input type="text"  id="uNameMonth" name="uNameMonth" class="am-form-field "  placeholder="请输入员工姓名">
									</div>
								</div> --%>
								<!-- 查询条件End -->
								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										<!--     <button type="button"  id="btn-search-cqno" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button> -->
											<button type="button"  id="btn-reset-cqno"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
											  <shiro:hasPermission name="picc.attence.month.export">  </shiro:hasPermission>
											     	<button type="button"  id="btn-excel-cqno" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>		
									</div>
								</div>
								<!-- 按钮End -->
							</form>
					      </div>
					      <div class="am-g">
	                 <table width="100%" id="chengNoTable" class="am-table am-table-striped am-table-hover table-main">
	                                 <thead>
	                                   <tr>
	                                    <!--      <th class="am-text-middle">地市</th> -->
	                                   <th width="1%"  class="am-text-middle">报案号</th>
	                                   <th width="1%"  class="am-text-middle">立案号</th>
	                                   <th width="1%"  class="am-text-middle">保单号</th>
	                                   <th width="5%"  class="am-text-middle">车牌号</th>
	                                   <th width="5%"  class="am-text-middle">被保人</th>
	                                   <th width="5%"  class="am-text-middle">日期</th>
	                                   <th width="5%"  class="am-text-middle">性质</th>
	                                   <th width="5%"  class="am-text-middle">承包代码</th>
	                                   <th width="1%"  class="am-text-middle">金额</th>
	                                   <th width="5%"  class="am-text-middle">查勘代码</th> 
	                                   <th width="5%"  class="am-text-middle">查勘员</th>
	                                   <th width="5%"  class="am-text-middle">定损代码</th> 
	                                   <th width="5%"  class="am-text-middle">定损员</th>
	                                   <th width="5%"  class="am-text-middle">状态</th>
	                                   <th width="5%"  class="am-text-middle">理算员代码</th> 
	                                   <th width="5%"  class="am-text-middle">理算员</th>
	                                   <th width="6%"  class="am-text-middle">机构</th>             
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
    </div>       
  </div>
</div>
</div>
<script type="text/javascript" src="${basePath}/statics/js/case/shenglwl.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/case/jiaolwl.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/case/tplwl.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/case/cqlwl.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/case/xnlwl.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/case/shengno.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/case/jiaono.js"></script>
<script type="text/javascript" src="${basePath}/statics/js/case/chengno.js"></script>
</body>
</html>