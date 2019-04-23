<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
<%--
  Created by Eclipse.
  User: wangXi
  Date: 2018/12/28
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
        <title>未决个人任务修改</title>
  </head>
  <body>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 个人任务修改
                    </div>

                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">

                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="usercase-add-form"  >
                            
                              <fieldset>
                              <input type="hidden" id="id" name="id"  value="${userCase.id}">
								<div class="am-form-group">
                                    <label for="groupId" class="am-u-sm-3 am-form-label">机构<font id="group_span" color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="groupName" name="groupName" value='${userCase.groupName}' readonly>
                                    </div>
                                </div> 
                                <div class="am-form-group">
                                    <label for="createDate" class="am-u-sm-3 am-form-label">月份<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                       <input value="<fmt:formatDate value='${userCase.createDate}' pattern='yyyy-MM-dd' />" 
                                        type="text"   style="background-color: white;" id="createDate" name="createDate"  required readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="startingNumber" class="am-u-sm-3 am-form-label">归属人<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="userName" name="userName" value='${userCase.userName}' readonly>
                                    </div>
                                </div>                             
                                 <div class="am-form-group">
                                    <label for="startingNumber" class="am-u-sm-3 am-form-label">起始数<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="startingNumber" name="startingNumber" value='${userCase.startingNumber}' placeholder="请输入起始数" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>
                                 <div class="am-form-group">
                                    <label for="startTargetNumber" class="am-u-sm-3 am-form-label">起始目标<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="startTargetNumber" name="startTargetNumber" value='${userCase.startTargetNumber}' placeholder="请输入起始目标" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>
                                 <div class="am-form-group">
                                    <label for="budgetTargetNumber" class="am-u-sm-3 am-form-label">预算目标<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="budgetTargetNumber" name="budgetTargetNumber" value='${userCase.budgetTargetNumber}' placeholder="请输入预算目标" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>
                                 <div class="am-form-group">
                                    <label for="challengNumber" class="am-u-sm-3 am-form-label">挑战目标<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="challengNumber" name="challengNumber" value='${userCase.challengNumber}'  placeholder="请输入挑战目标" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary" id="usercase-save-btn">保存</button>
                                    </div>
                                </div>
                              </fieldset>                                
                            </form>                        
                        </div>
                    </div>
                </div>
            </div>
          <script type="text/javascript" src="${basePath}/statics/js/case/updateusercase.js"></script> 
  </body>
 
 </html> 
