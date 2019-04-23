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
        <title>新增功能</title>
  </head>
  <body>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 新增功能
                    </div>


                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="goods-add-form"  >
                            
                              <fieldset>

                                <div class="am-form-group">
                                    <label for="orgName" class="am-u-sm-3 am-form-label">功能名称<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="funName" name="funName" placeholder="请输入功能名称" required maxlength="50">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="insuranCode" class="am-u-sm-3 am-form-label">功能描述<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="funDescription" name="funDescription" placeholder="请输入功能描述" required maxlength="50">
                                    </div>
                                </div>
                                

                                <div class="am-form-group">
                                    <label for="insuranDate" class="am-u-sm-3 am-form-label">类型<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                    <select id="funType" name="funType"  required>
                                        <option value="">-=请选择一项=-</option>
								        <option value="1">菜单</option>
								        <option value="2">按钮</option>
								        <option value="3">子标签</option>
                                    </select>
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <label for="insuranDate" class="am-u-sm-3 am-form-label">上级功能<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                    <select id="funPid" name="funPid"  required data-am-selected="{searchBox: 1,maxHeight: 150}">
                                        <option value="">-=请选择一项=-</option>
                                            <option value="0">主标签</option>
                                           <c:forEach var="functionList" items="${functionList}" varStatus="number">
                                                 <option value="${functionList.id}">${functionList.funName}</option>
                                           </c:forEach>
								       
                                    </select>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="insuranName" class="am-u-sm-3 am-form-label">功能URL</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="funUrl" name="funUrl" placeholder="请输入功能URL"  maxlength="50">
                                    </div>
                                </div>

                               <div class="am-form-group">
                                    <label for="insuranCode" class="am-u-sm-3 am-form-label">权限标识<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="funFlag" name="funFlag" placeholder="请输入权限标识" required maxlength="50">
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <label for="carCode" class="am-u-sm-3 am-form-label">排序</label>
                                    <div class="am-u-sm-9">
                                              <input type="number" id="orderNum"  name ="orderNum"  placeholder="请输入序号"   max="999">
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <label for="carType" class="am-u-sm-3 am-form-label">图标</label>
                                    <div class="am-u-sm-9">
                                              <input type="text" id="icon" name="icon" placeholder="请输入图标"  maxlength="20">
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
           <script type="text/javascript" src="${basePath}/statics/js/function/function_add.js"></script>
  </body>
 
 </html> 
