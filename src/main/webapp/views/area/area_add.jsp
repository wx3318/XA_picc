<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
  Created by Eclipse.
  User: TripodFan
  Date: 2018/10/23
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
        <title>新增主管</title>
        <style>
	         .am-ucheck-icons {
	         line-height: 35px;
	         }
        </style>
  </head>
 
  <body>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 新增区域主管
                    </div>

                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="goods-add-form"  >
                            
                              <fieldset>

                                <div class="am-form-group">
                                    <label for="insuranDate" class="am-u-sm-3 am-form-label">选择主管<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                    <select id="userId" name="userId"  required data-am-selected="{searchBox: 1,maxHeight: 150, btnWidth:300}">
                                        <option value="">-=请选择一项=-</option>
                                           <c:forEach var="userList" items="${userList}" varStatus="number">
                                                 <option value="${userList.userId}">${userList.userCode} ${userList.userName}</option>
                                           </c:forEach>
                                    </select>
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <label for="orgName" class="am-u-sm-3 am-form-label">区域名称<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="areaName" name="areaName" placeholder="请输入区域名称" required maxlength="20">
                                    </div>
                                </div>
                                
                                 <div class="am-form-group">
                                    <label for="orgName" class="am-u-sm-3 am-form-label">选择分组<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <c:forEach var="groupList" items="${groupList}" varStatus="number">
                                                  <label class="am-checkbox">
												      <input type="checkbox" name="groupId" value="${groupList.groupId}" data-am-ucheck required > ${groupList.groupName}
												    </label>
                                           </c:forEach>
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary" id="goos-save-btn">保存</button>
                                    </div>
                                </div>
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>

            </div>
            
           <script type="text/javascript" src="${basePath}/statics/js/area/area_add.js"></script>
  </body>
 
 </html> 
