<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
  Created by Eclipse.
  User: wangXi
  Date: 2018/12/28
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
        <title>未决个人任务设置</title>
  </head>
  <body>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 个人任务设置
                    </div>

                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">

                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="usercase-add-form"  >
                            
                              <fieldset>
								<div class="am-form-group">
                                    <label for="groupId" class="am-u-sm-3 am-form-label">机构<font id="group_span" color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <select  name="groupId" id="groupId">
							            	<option value="">--请选择--</option>
							            	<c:forEach items="${groupList}" var="group">
							            		<option value="${group.id}">${group.groupName}</option>
							            	</c:forEach>
	            						</select>
                                    </div>
                                </div> 
                                <div class="am-form-group">
                                    <label for="createDate" class="am-u-sm-3 am-form-label">月份<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}"   style="background-color: white;" id="createDate" name="createDate" placeholder="请输入设置月份" required readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="startingNumber" class="am-u-sm-3 am-form-label">归属人工号<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="userId" name="userId" placeholder="请输入归属人工号" required  min="1" max="9999999999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>                             
                                 <div class="am-form-group">
                                    <label for="startingNumber" class="am-u-sm-3 am-form-label">起始数<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="startingNumber" name="startingNumber" placeholder="请输入起始数" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>
                                 <div class="am-form-group">
                                    <label for="startTargetNumber" class="am-u-sm-3 am-form-label">起始目标<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="startTargetNumber" name="startTargetNumber" placeholder="请输入起始目标" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>
                                 <div class="am-form-group">
                                    <label for="budgetTargetNumber" class="am-u-sm-3 am-form-label">预算目标<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="budgetTargetNumber" name="budgetTargetNumber" placeholder="请输入预算目标" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
                                    </div>
                                </div>
                                 <div class="am-form-group">
                                    <label for="challengNumber" class="am-u-sm-3 am-form-label">挑战目标<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="number" id="challengNumber" name="challengNumber" placeholder="请输入挑战目标" required  min="1" max="99999" pattern="^\+?[1-9][0-9]*$">
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
          <script type="text/javascript" src="${basePath}/statics/js/case/addgroupusercase.js"></script>   
  </body>
 
 </html> 
