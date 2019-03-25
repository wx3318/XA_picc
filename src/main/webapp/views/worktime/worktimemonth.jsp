<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考勤详情</title>
</head>
<body>
<div class="tpl-portlet-components">
 <div class="am-u-sm-12">
 <div class="portlet-title">
	<div class="caption font-green bold">
			<span class="am-icon-code"></span> 月考勤明细
	</div>
</div>	
   <div >
    <font size="5">缺勤人日期：${levaeDate}</font>
   </div>
	 <div>
    <font size="5">迟到日期：${lastDate}</font>
   </div>
   <div>
    <font size="5">上班未打卡日期：${startDate}</font>
   </div>
   <div>
    <font size="5">下班未打卡日期：${endDate}</font>
   </div>
   <div>
    <font size="5">请假日期：${askDate}</font>
   </div>	
 </div>
</div> 
</body>
</html>