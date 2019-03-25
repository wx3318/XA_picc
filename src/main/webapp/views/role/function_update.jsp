<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: TripodFan
  Date: 2018/8/27
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
        <title>赋予权限</title>
        
	      <link rel="stylesheet" href="${basePath}/statics/assets/zTree/zTreeStyle.css" type="text/css">
          <script type="text/javascript" src="${basePath}/statics/assets/zTree/jquery.ztree.core.js"></script>
          <script type="text/javascript" src="${basePath}/statics/assets/zTree/jquery.ztree.excheck.js"></script>
  </head>
	
  <body>
  
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 赋予权限
                    </div>


                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="goods-add-form"  >
                            
                                 <input type="hidden" name="roleId" value="${roleId}">                            
                              <fieldset>

                                <div class="am-form-group">
                                    <div class="content_wrap">
										<div class="zTreeDemoBackground left">
											<ul id="treeDemo" class="ztree"></ul>
										</div>

                                     </div>
                                </div>


                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary" id="goos-save-btn">修改</button>
                                    </div>
                                </div>
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>

            </div>
            
     
								
								

   <script type="text/javascript" src="${basePath}/statics/js/role/function_update.js"></script>
      <script>
            var functionList = eval('${functionList}');
        </script>
  </body>
 </html> 
