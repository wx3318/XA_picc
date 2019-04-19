<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>结案案件导入</title>
</head>
<body >
	 <div class="tpl-portlet-components">
	<div class="am-tabs" data-am-tabs>
			  <ul class="am-tabs-nav am-nav am-nav-tabs">
			    <li class="am-active"><a href="#tab1">结案导入</a></li>
			     <li><a href="#tab2">未结案导入</a></li> 
			  </ul>
  <div class="am-tabs-bd">
    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
	      <div class="portlet-title">
				<div class="caption font-green bold">
					<span class="am-icon-code"></span>结案案件导入
				</div>
			</div>
				<div style="margin: 20px;">
					 <form method="POST" enctype="multipart/form-data" onsubmit="return false;"> 
						<div class="am-form-group am-form-file">
							<button type="button" class="am-btn am-btn-danger am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 选择要上传结案文件
							</button>
							<input id="closinglistfile" type="file" name="closinglistfile" >
						</div>
						<div id="file-list-closing"></div>
						<input type="button" value="确定上传" class="am-btn am-btn-primary" id="inport_file_closing" onclick="importClosingRateExcel()">
					</form>
				</div>
    </div>
     <div class="am-tab-panel am-fade" id="tab2">
        <div class="portlet-title">
				<div class="portlet-title">
				<div class="caption font-green bold">
					<span class="am-icon-code"></span> 未决案件导入
				</div>
			</div>
				<div style="margin: 20px;">
					 <form method="POST" enctype="multipart/form-data" onsubmit="return false;"> 
						<div class="am-form-group am-form-file">
							<button type="button" class="am-btn am-btn-danger am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 选择要上传未结案文件
							</button>
							<input id="cpdingfile" type="file" name="cpdingfile">
						</div>
						<div id="file-list-cpding"></div>
						<input type="button" value="确定上传" class="am-btn am-btn-primary" id="import_file_cpding" onclick="importPendingExcel()">
					</form>
				</div>
                <div class="tpl-alert"></div>  
    </div>
  </div>
</div>
</div>



	 <div class="tpl-portlet-components">
    <div class="am-tab-panel am-fade am-in am-active">
	      
    </div> 
</div>
	 <script type="text/javascript" src="${basePath}/statics/js/case/closingrateimport.js"></script>
</body>	
</html>