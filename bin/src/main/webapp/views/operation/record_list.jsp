<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>操作记录列表</title>
  </head>
  <body>
        
       <div class="tpl-content-page-title">
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span>操作记录列表
                    </div>

                </div>
                <div class="tpl-block">
                    <div class="am-g">
                        
                        <div class="am-u-sm-12">
							<form id="search-form-goods">
								<!-- 查询条件Start -->
	
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">操作人</span>
										<input type="text"  id="createdName" class="am-form-field "  placeholder="操作人姓名">
									</div>
								</div> 
								
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">功能</span>
										<input type="text"  id="content" class="am-form-field "  placeholder="操作的功能">
									</div>
								</div> 
								
							<!--     <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">签单日期</span>
	                                    <input type="text" id="signDateStart" name="signDateStart" class="am-form-field" placeholder="开始日期"  style="width: 42%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                    <div class="am-form-field am-border-none"  style="width:5%; text-align: center;"><span >-</span></div>
	                                    <input type="text" id="signDateEnd" name="signDateEnd"  class="am-form-field" placeholder="结束日期"   style="width: 42%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                </div>
	                            </div> -->
								
								<!-- 查询条件End -->
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-pro" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i> 查询</button>
											<button type="button"  id="btn-reset-pro"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											
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
                                            <th width="40%"  class="am-text-middle">操作功能</th>
                                            <th width="20%"  class="am-text-middle">操作类型</th>
                                            <th width="20%"  class="am-text-middle">操作人</th>
                                            <th width="20%"  class="am-text-middle">操作时间</th>
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
     <script type="text/javascript" src="${basePath}/statics/js/operation/record_list.js"></script>
  </body>

</html>  