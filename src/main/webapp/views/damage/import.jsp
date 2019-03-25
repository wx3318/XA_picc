<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
        <title>案件归属人</title>
  </head>
  <body>
      <div class="tpl-portlet-components">
	<div class="am-tabs" data-am-tabs>
			  <ul class="am-tabs-nav am-nav am-nav-tabs">
			    <li class="am-active"><a href="#tab1">定损导入</a></li>
			    <li><a href="#tab2">低碳导入</a></li>
			     <li><a href="#tab3">清单导入</a></li>
			  </ul>
  <div class="am-tabs-bd">
    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
	     <div class="portlet-title">
				<div class="caption font-green bold">
					<span class="am-icon-code"></span> 定损导入
				</div>
			</div>
				<div style="margin: 20px;">
					 <form method="POST" enctype="multipart/form-data" onsubmit="return false;"> 
						<div class="am-form-group am-form-file">
							<button type="button" class="am-btn am-btn-danger am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 选择要上传文件
							</button>
							<input id="damagefile" type="file" name="damagefile">
						</div>
						<div id="file-list-damage"></div>
						<input type="button" value="确定上传" class="am-btn am-btn-primary" id="inport_file_damage" onclick="importDamageExcel()">
					</form>
				</div>
    </div>
    <div class="am-tab-panel am-fade" id="tab2">
	     <div class="portlet-title">
				<div class="caption font-green bold">
					<span class="am-icon-code"></span> 低碳导入
				</div>
			</div>
				<div style="margin: 20px;">
					 <form method="POST" enctype="multipart/form-data" onsubmit="return false;"> 
						<div class="am-form-group am-form-file">
							<button type="button" class="am-btn am-btn-danger am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 选择要上传文件
							</button>
							<input id="damagedtfile" type="file" name="damagedtfile">
						</div>
						<div id="file-list-damagedt"></div>
						<input type="button" value="确定上传" class="am-btn am-btn-primary" id="inport_file_damagedt" onclick="importDamagedtExcel()">
					</form>
				</div>

    </div>
    <div class="am-tab-panel am-fade" id="tab3">
    		<div class="portlet-title">
				<div class="caption font-green bold">
					<span class="am-icon-code"></span> 清单导入
				</div>
			</div>
				<div style="margin: 20px;">
					 <form method="POST" enctype="multipart/form-data" onsubmit="return false;"> 
						<div class="am-form-group am-form-file">
							<button type="button" class="am-btn am-btn-danger am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 选择要上传文件
							</button>
							<input id="damagezpfile" type="file" name="damagezpfile">
						</div>
						<div id="file-list-damagezp"></div>
						<input type="button" value="确定上传" class="am-btn am-btn-primary" id="inport_file_damagezp" onclick="importDamagezpExcel()">
					</form>
				</div>
    </div>
  </div>
</div>
</div>
    <script type="text/javascript" src="${basePath}/statics/js/damage/import.js"></script>
</body>

</html>  