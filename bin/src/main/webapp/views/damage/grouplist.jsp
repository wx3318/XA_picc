<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>核损分中心报表</title>
  </head>
  <body>
       <div class="tpl-content-page-title"></div>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">核损个人报表</div>
                </div>
                <div class="am-u-sm-12">
                <div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">组别</span>
										<select  data-am-selected="{maxHeight: 250}" id="FirstCate"  name="FirstCate" >
										  <option value="">----请-选-择----</option>
										  <c:forEach items="${listCategory}" var="lc">
										    <option value="${lc.id }"> ${lc.groupName }</option>
										   </c:forEach>
										</select>
									</div>
								</div>
					<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">类型</span>
										<select  data-am-selected="{maxHeight: 250}" id="param" name="param">
										  <option value="" >----请-选-择----</option>
										  <option value="1" >月统计</option>
										  <option value="2" >年统计</option>
										</select>
									</div>
								</div>			
					<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
	                                <div class="am-input-group am-input-group-primary am-input-group-sm">
	                                    <span class="am-input-group-label">日期</span>
	                                    <input type="text" id="damageDate" name="damageDate" class="am-form-field" placeholder="选择日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
	                                    <!-- <div class="am-form-field am-border-none"  style="width:5%; text-align: center;"><span >-</span></div> -->
	                                </div>
	                            </div>			
					<!-- 按钮Start @RequiresPermissions("picc.damage.excel.group") -->
								
								<div class="am-u-sm-12 am-custom-top am-padding-left-0">
									<div class="am-btn-group ">
									
										    <button type="button"  id="btn-search-day" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<button type="button"  id="btn-reset-day"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>
											 <shiro:hasPermission name="picc.damage.excel.group">
											<button type="button"  id="btn-excel-day" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
											</shiro:hasPermission>									
									</div>
								</div>
								<!-- 按钮End -->
				</div>																		
                        <div class="am-u-sm-12">
                                <table width="100%" id="userTable" class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
									    <tr>
									      <th width="9%" ></th>
									      <th width="4%" ></th>
									      <th id="time" width="75%" colspan="12" style="text-align: center;">2019年</th>
									    </tr>
									    <tr>
									      <th width="9%" ></th>
									      <th width="4%" ></th>
									      <th width="4%" >件数</th>
									      <th width="4%" >金额</th>
									      <th width="10%" colspan="4" style="text-align: center;">低碳外修</th>
									      <th width="5%" >单均金额</th>
									      <th width="5%" >换件金额</th>
									      <th width="5%" >换件数量</th>
									      <th width="5%" >单均换件数量</th>
									      <th width="5%" >工时金额</th>
									      <th width="5%" >单均工时</th>
									    </tr>
									     <tr>
									       <th width="9%" ></th>
									      <th width="4%" ></th>
									      <th width="4%" ></th>
									      <th width="4%" ></th>
									      <th width="3%" >万元以下件数</th>
									      <th width="3%" >万元以下金额</th>
									      <th width="3%" >万元以上件数</th>
									      <th width="3%" >万元以上金额</th>
									      <th width="5%" ></th>
									      <th width="5%" ></th>
									      <th width="5%" ></th>
									      <th width="5%" ></th>
									      <th width="5%" ></th>
									      <th width="5%" ></th>
									    </tr>
									  </thead>
									  <tbody  id="tbMain">  </tbody>
                                </table>
													
                        </div>											             
                <div class="tpl-alert"></div>
            </div>
             <script type="text/javascript" src="${basePath}/statics/js/damage/groupdamage.js"></script>
  </body>

</html>  