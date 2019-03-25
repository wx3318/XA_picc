
$(function() {     
    $("#worktimeone-update-btn").on("click",function () {  
    	var addressone= $("#workAddressone").val();
    	var workDateone= $("#workDateOne").val();
    	if(addressone!="" && workDateone!="" ){
    		
    	
            var url = "/PICCproject/picc/worktime/updateworktimeone.ajax";
            //序列化form数据
            var param = $('#worktimeone-update-form').serialize();
            // 验证通过，进行提交操作
            $("#worktimeone-update-btn").val("正在更新...");
            $("#worktimeone-update-btn").attr("disabled", true);
           simpleAjax(url, param, "json", true, function(result){
                // 新增结果                
                bkyyAlert(result.msg);
                $("#worktimeone-update-btn").attr("disabled", false);
                $("#worktimeone-update-btn").val("更新");
            });
    	}else{
    		alert("输入不能为空")
    	}
    })
    
    
      $("#worktimetwo-update-btn").on("click",function () {           	
            var url = "/PICCproject/picc/worktime/updateworktimetwo.ajax";
            //序列化form数据
            var param = $('#worktimetwo-update-form').serialize();
            // 验证通过，进行提交操作
           simpleAjax(url, param, "json", true, function(result){
                // 新增结果                
                bkyyAlert(result.msg);
            });
    })
    function checkData(){
    	 var workAddressperson= $("#workAddressperson").val();
    	 var workDatepersonStrat= $("#workDatepersonStrat").val();
    	 var workDatepersonEnd= $("#workDatepersonEnd").val();
    	 var username= $("#username").val();
    	 if(workAddressperson !='' && workDatepersonStrat !='' && workDatepersonEnd !='' && username!=''){
    		 return true;
    	 }else{
    		 return false;
    	 }
    	
    }
    
    $("#worktimeperson-update-btn").on("click",function () {           	
            //var url = "/PICCproject/picc/worktime/updateworktimetwo.ajax";
            //序列化form数据
    	if(checkData()){
            var param = $('#worktimeperson-update-form').serialize();
            // 验证通过，进行提交操作
            alert(param)
           /*simpleAjax(url, param, "json", true, function(result){
                // 新增结果                
                bkyyAlert(result.msg);
            });*/
    	}else{
    		alert("字段不能为空");
    	}   
    })
    
    
    $("#username").blur(function(){
    	var reg = /^\d{4,8}$/;
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
    	            		 $("#username_span").css("color","red");
    	            		 $("#username_mes").html("系统中没有该工号");
    	            		 $("#username_mes").css("color","red"); 
    	            	/*	 unamefalg = true;*/
    	                }else{ 
    	                	$("#username_span").html("*");
    	                	$("#username_span").css("color","green");
    	                	 $("#username_mes").html("系统中有该工号");
    	            		 $("#username_mes").css("color","green");
    	            		 
    	                }   
    	            }  
    	        }); 
    	}else{
    	     $("#username_mes").html("请输入有效字符");
    		 $("#username_mes").css("color","red");
    	} 
    });
    
})