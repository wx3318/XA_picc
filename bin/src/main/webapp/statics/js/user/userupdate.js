var namefalg = true;
var rolefalg = true;
var groupfalg = true;
var unamefalg = true;
var stafalg = true;
window.onload=function(){
$("#name_span").css("color","green");
$("#name").blur(function(){
	if($("#name").val()!=""){
		 $("#name_span").css("color","green");
		 namefalg = true;
	}else{
		namefalg = false;
		 $("#name_span").css("color","red");
	} 
});
$("#role_span").css("color","green");
$("#roleId").change(function(){
	var rolevalue= $("#roleId").val()

	if($("#roleId").val()!=""){
		 $("#role_span").css("color","green");		
		 rolefalg = true;
	}else{
		rolefalg = false;
		 $("#role_span").css("color","red");
	} 
});
$("#group_span").css("color","green");	
$("#groupId").change(function(){

	if($("#groupId").val()!=""){
		 $("#group_span").css("color","green");
		 stafalg = true;
	}else{
		stafalg = false;
		 $("#group_span").css("color","red");
	} 
});
$("#station_span").css("color","green");	
$("#station").change(function(){

	if($("#station").val()!=""){
		 $("#station_span").css("color","green");
		 groupfalg = true;
	}else{
		groupfalg = false;
		 $("#station_span").css("color","red");
	} 
});
//融合工号
$("#username_span").html("*");
$("#username_span").css("color","green");	
$("#username").blur(function(){
	var reg = /^\d{4,11}$/;
	if(reg.test($("#username").val())){
		var username = $("#username").val();
		$("#username_span").html("*");
		 $("#username_span").css("color","green");
	}else{
		unamefalg = false;
	     $("#username_mes").html("请输入有效字符");
		 $("#username_mes").css("color","red");
	} 
});

	
}


$(function() { 

    // 绑定提交按钮
    
    $("#user-update-btn").on("click",function () {
		var $modal = $('#your-modal');	
    	if(namefalg&&rolefalg&&groupfalg&&unamefalg&&stafalg){
    		var param = $('#user-update-form').serialize();
	            var url = "/PICCproject/picc/user/updateuser.ajax";
	            // 验证通过，进行提交操作	            
	            simpleAjax(url, param, "json", true, function(result){
	                // 新增结果	                
	                bkyyAlert(result.msg);	                	
	                if(result.success){
	                    window.location = "/PICCproject/picc/user/listuser.html";
	                    setInterval(' window.location = "/PICCproject/picc/user/listuser.html',6000); 
	                }
	            });
    	}else{
    	        $modal.modal();
    	}

    	
    })
})

