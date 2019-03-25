<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设置记录</title>
</head>
<body>
<div class="tpl-portlet-components">
 <div class="am-u-sm-12">
 		<template id="table-options-1">
			<a class="am-btn am-btn-danger am-btn-xs btn-updateday-pro"  bkyy-id="【data】" href="javascript:;"><i class="am-icon-times"></i> 删除</a>

		</template>
	      <div class="am-u-sm-12">
							<form id="search-form-workday">
								<!-- 查询条件Start -->								
							    <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">考勤日期</span>
	                                   <!--  <input type="text" id="month" name="month" class="am-form-field" placeholder="选择日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly> -->
	                                    <!-- <div class="am-form-field am-border-none"  style="width:5%; text-align: center;"><span >-</span></div> -->
	                               		<input type="text"  id="month"  name="month" class="am-form-field "  placeholder="选择月份" style="background-color: white;"  data-am-datepicker="{format: 'yyyy-mm', viewMode: 'years', minViewMode: 'months'}" placeholder="日历组件" data-am-datepicker readonly>
	                                </div>
	                            </div>
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">									
										    <button type="button"  id="btn-search-day" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-day"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											<button type="button"  id="btn-insert-pro"  class="am-btn am-btn-sm am-radius  am-custom-left-10"><i class="am-icon-plus"></i>考勤设置</button>											
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
	                                   <th width="15%"  class="am-text-middle">上班月份</th>
	                                   <th width="15%"  class="am-text-middle">上班天数</th>
	                                   <th width="15%"  class="am-text-middle">设置人</th>
	                                   <th width="15%"  class="am-text-middle">设置时间</th>
	                                   <th width="15%"  class="am-text-middle">操作</th>             
	                               </tr>
	                         </thead>
	                 </table>
			</div>
        </div>
</div> 
 <script type="text/javascript" src="${basePath}/statics/js/worktime/work_day.js"></script>   
</body>
</html>