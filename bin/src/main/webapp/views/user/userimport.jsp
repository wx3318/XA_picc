<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>员工录入</title>
</head>
<body >
	
<div class="tpl-portlet-components">

<div class="am-tabs-bd">
	      <div class="portlet-title">
				<div class="caption font-green bold">
					<span class="am-icon-code"></span> 员工导入
				</div>
			</div>
				<div style="margin: 20px;">
					 <form method="POST" enctype="multipart/form-data" onsubmit="return false;"> 
						<div class="am-form-group am-form-file">
							<button type="button" class="am-btn am-btn-danger am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 选择要上传文件
							</button>
							<input id="userfile" type="file" name="userfile">
						</div>
						<div id="file-list-user"></div>
						<input type="button" value="确定提交" class="am-btn am-btn-primary" id="inport_file_user" onclick="importUserExcel()">
					</form>
				</div>
 </div>
 
</div>
      <script type="text/javascript" src="${basePath}/statics/js/user/userimport.js"></script>
</body>
	
	

</html>