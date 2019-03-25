<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<frameset rows="62,*" cols="*" frameborder="no" border="0" framespacing="0">
2     <frame src="<%=request.getContextPath() %>/views/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
3     <frameset cols="150,*" frameborder="no" border="0" framespacing="0" />
4         <frame src="<%=request.getContextPath() %>/views/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" />
5         <frame src="<%=request.getContextPath() %>/views/main.jsp" name="mainFrame" id="mainFrame" />
6     </frameset>
7 </frameset>
</html>