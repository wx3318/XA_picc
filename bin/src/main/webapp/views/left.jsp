<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<title>Insert title here</title>
</head>
<style type="text/css">
	body{
		background-color: #484848;
	}
	ul {
		padding:0; margin:0;
         list-style: none;
       }
    ul li{
    	
    	padding: 5px;
    	width: 100px;
    }   
	ul li ul { 
	   float:left; 
       padding:0; margin:0;border-right:0px; 
       
       color: #00a0e9; 
       display: none;
        }
     ul li ul li {
        padding:0; margin:0;border-right:0px; 
        background-color:#191970; 
        padding:5px 0px 5px 0px;
        margin-top: 2px; 
     }  
    a{
    	text-decoration: none;
    	color: #FFFFFF;
        padding: 0 10px;
        height: 40px;
    }
    ul li:hover {
	background-color:red;
   }          
</style>
<script type="text/javascript">
window.onload=function(){
	var role = document.getElementById("role").value   
	/* var gr = document.getElementById("gr")
	var xz = document.getElementById("xz")
	var qb = document.getElementById("qb") */
	var gl = document.getElementById("gl")
	var bd_inport = document.getElementById("bd_inport")
	
		if(role=="员工" || role=="组长"){
			/*  xz.style.display="none";
			 qb.style.display="none";	 */
			 gl.style.display="none"; 	
			 bd_inport.style.display="none";
		}
}

function showsubmenu(li){
	var submenu=li.getElementsByTagName("ul")[0];
	if(submenu.style.display=="block"){
		submenu.style.display="none";
	}else{
		submenu.style.display="block";
	}
}


</script>
<body >
<form action="#">
	<input type="hidden" value="${user.role}" id="role">
</form>
  <ul>
  
	  <li><a href="#"  >首页</a></li>
	 <li class="navmenu" onclick="showsubmenu(this)"  id="gl"><a href="#">管理员</a>
	          <ul>
	          	<li><a href="<%=request.getContextPath() %>/views/newuser.jsp" target="mainFrame" >账号注册</a></li>
	          	<li><a href="<%=request.getContextPath() %>/user/listuser" target="mainFrame">员工管理</a></li>
	          </ul>
	  </li>
	  <%-- <li class="navmenu" onclick="showsubmenu(this)"><a href="#">业绩模块</a>
	  	<ul>
	          <li id="gr"><a href="#">个人业绩</a></li>
	          
	      	  <li id="xz"><a href="#">小组业绩</a></li>
	
	          <li id="qb"><a href="<%=request.getContextPath() %>/views/allyj.jsp" target="mainFrame">全部业绩</a></li>
	  	</ul>
	  </li> --%>
	 <!--  <li><a href="#" onclick="showsubmenu(this)">工资管理</a></li>        
	  <li><a href="#" onclick="showsubmenu(this)">佣金管理</a></li>  
	  <li><a href="#" onclick="showsubmenu(this)">续保管理</a></li> -->
	  <li class="navmenu" onclick="showsubmenu(this)"><a href="#"> 保单配送</a>
	  	<ul>
	  		<li id="bd_inport"><a href="<%=request.getContextPath() %>/views/baodan_inport.jsp" target="mainFrame" >数据导入</a></li>
	          
	   		<li id=""><a href="<%=request.getContextPath() %>/tranck/alltk" target="mainFrame"">配送详情</a></li>
	   </ul>
	  </li>
	  
	   <li class="navmenu" onclick="showsubmenu(this)"><a href="#"> 竞回清单</a>
	  	<ul>
	  		<li id="bd_inport"><a href="<%=request.getContextPath() %>/views/backGoods/back_goods_add.jsp" target="mainFrame" >清单录入</a></li>
	          
	   </ul>
	  </li>
	  
	  <!-- <li><a href="#" onclick="showsubmenu(this)">请假管理</a></li>
	  <li><a href="#" onclick="showsubmenu(this)">短信群发</a></li> -->
	  <li class="navmenu" onclick="showsubmenu(this)"><a href="#" >附加功能</a>
	  	<ul>
	  		<li><a  href="<%=request.getContextPath() %>/views/password.jsp" target="mainFrame">密码修改</a></li>
	  	
	  	</ul>
	  
	  </li>
	  
 
  </ul>

</body>

</html>