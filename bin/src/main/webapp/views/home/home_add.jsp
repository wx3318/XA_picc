<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
  Created by Eclipse.
  User: TripodFan
  Date: 2018/11/29
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
        <title>首页内容</title>
   
  </head>
 
  <body>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 首页内容
                    </div>


                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="goods-add-form"  >
                            
                              <fieldset>
                              
                                <div class="am-form-group">
                                    <label for="orgName" class="am-u-sm-3 am-form-label">标题<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="title" name="title" placeholder="请输文章标题" required maxlength="50">
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                    <label for="insuranCode" class="am-u-sm-3 am-form-label">文章内容<font color="red">*</font></label>
                                    <div class="am-u-sm-9">
                                       <textarea type="text/plain" id="content" ></textarea>
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
            
                    <%-- Ueditor插件 --%>
		    <script type="text/javascript" src="${basePath}/statics/assets/UEditor/ueditor.config.js"></script>
		    <script type="text/javascript" src="${basePath}/statics/assets/UEditor/ueditor.all.js"></script>
		    <script type="text/javascript" src="${basePath}/statics/assets/UEditor/lang/zh-cn/zh-cn.js"></script>
           <script type="text/javascript" src="${basePath}/statics/js/home/home_add.js"></script>
  </body>
 
 </html> 
