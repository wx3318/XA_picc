var namefalg = false;
var rolefalg = false;
var groupfalg = false;
var unamefalg = false;
window.onload=function(){
	
$("#name").blur(function(){
	if($("#name").val()!=""){
		 $("#name_span").css("color","green");
		 namefalg = true;
	}else{
		 $("#name_span").css("color","red");
	} 
});


$("#roleId").change(function(){
	var rolevalue= $("#roleId").val()

	if($("#roleId").val()!=""){
		 $("#role_span").css("color","green");
		 if(rolevalue==7){
			 
			 $("#group_span").css("color","green");
			 groupfalg = true;
			 $("#groupId").attr("disabled","disabled").css("background-color","#EEEEEE;");
			 $("#groupId").val("")
				 
		 }else{
			 if($("#groupId").val()==""){
				 $("#group_span").css("color","red");
				 $('#groupId').attr("disabled",false);
				 groupfalg = false; 
			 }
			 
		}
		 rolefalg = true;
	}else{
		 $("#role_span").css("color","red");
	} 
});
	
$("#groupId").change(function(){

	if($("#groupId").val()!=""){
		 $("#group_span").css("color","green");
		 groupfalg = true;
	}else{
		 $("#group_span").css("color","red");
	} 
});
$("#phone_show").blur(function(){
	if($("#phone_show").val()!=""){
		 $("#phone_show_span").css("color","green");
		 /*phoneshowfalg = true;*/
	}else{
		 $("#phone_show_span").css("color","red");
	} 
});

//融合工号	
$("#username").blur(function(){
	var reg = /^\d{4,11}$/;
	if(reg.test($("#username").val())){
		var username = $("#username").val();
		 $.ajax({  
	            type:"post",         //请求方式  
	            url:"/PICCproject/picc/user/checkuname",      //连接  
	            data:"username="+username,   //数据加载  
	            dataType:"text",     //数据类型  
	            success:function(data){   //data表示服务器端返回的数据  
	                if(data == "n"){  
	                	 $("#username_span").html("*");
	            		 $("#username_span").css("color","green");
	            		 $("#username_mes").html("符合要求");
	            		 $("#username_mes").css("color","green"); 
	            		 unamefalg = true;
	                }else{ 
	                	$("#username_span").html("*");
	                	$("#username_span").css("color","red");
	                	 $("#username_mes").html("工号已存在");
	            		 $("#username_mes").css("color","red");
	            		 
	                }   
	            }  
	        }); 
	}else{
	     $("#username_mes").html("请输入有效字符");
		 $("#username_mes").css("color","red");
	} 
});
}
		$(function() { 

		    // 绑定提交按钮		    
		    $("#user-save-btn").on("click",function () {
	    		var $modal = $('#your-modal');	
		    	if(namefalg&&rolefalg&&groupfalg&&unamefalg){
		    		var param = $('#user-add-form').serialize(); 
		    		 $("#user-save-btn").attr("disabled","disabled") 
			            var url = "/PICCproject/picc/user/saveuser.ajax";	
			            // 验证通过，进行提交操作			            
			            simpleAjax(url, param, "json", true, function(result){
			                // 新增结果			                	
			                if(result.success){
			                	$('#my-confirm').modal({
			                        relatedTarget: this,
			                        onConfirm: function() {
			                        	window.location = "/PICCproject/picc/user/listuser.html";
					                    setInterval(' window.location = "/PICCproject/picc/user/listuser.html',3000);
					                    return;
			                        },
			                        // closeOnConfirm: false,
			                        onCancel: function() {
					                    window.location = "/PICCproject/picc/user/adduser.html";
					                    setInterval(' window.location = "/PICCproject/picc/user/adduser.html',3000);
					                    return;
			                        }
			                      });
			                	
			                    /**/ 
			                }else{
			                	bkyyAlert(result.msg);
			                }
			            });
		    	}else{
		    	        $modal.modal();
		    	} 


		    })
		})
