<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>首页列表</title>
  </head>
  <body>
        
       <div class="tpl-content-page-title">
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span>首页列表
                    </div>

                </div>
                <div class="tpl-block">
                    <div class="am-g">
                        
                        <div class="am-u-sm-12">
							 <form id="search-form-goods">
								
								<!-- 按钮Start -->
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										  <shiro:hasPermission name="picc.home.add"> 
										             <button type="button"  id="btn-insert-pro"  class="am-btn am-btn-sm am-radius  am-custom-left-10"><i class="am-icon-plus"></i> 首页配置</button>
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
                                       <!--      <th class="am-text-middle">地市</th> -->
                                            <th width="10%"  class="am-text-middle">编号</th>
                                            <th width="60%"  class="am-text-middle">标题</th>
                                            <th width="20%"  class="am-text-middle">创建时间</th>
                                            <th width="10%"  class="am-text-middle">创建人</th>
                                        </tr>
                                    </thead>
                                </table>

                        </div>

                    </div>
                </div>
                <div class="tpl-alert"></div>
            </div>
          </div>
          
     <script type="text/javascript" src="${basePath}/statics/js/home/home_list.js"></script>
  </body>

</html>  