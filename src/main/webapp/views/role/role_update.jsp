<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: TripodFan
  Date: 2018/8/23
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
        <title>修改角色</title>
  </head>
  <body>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 修改角色
                    </div>


                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="goods-add-form"  >
                            
                                  <input type="hidden" id="id" name="id" value="${role.id}" required maxlength="50">
                                  
                              <fieldset>

                                <div class="am-form-group">
                                    <label for="orgName" class="am-u-sm-3 am-form-label">角色名称<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="name" name="name" placeholder="请输入角色名称" value="${role.name}" required maxlength="50">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="insuranCode" class="am-u-sm-3 am-form-label">角色描述<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="description" name="description" placeholder="请输入角色描述" value="${role.description}" required maxlength="50">
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
           <script type="text/javascript" src="${basePath}/statics/js/role/role_update.js"></script>
  </body>
 
 </html> 
