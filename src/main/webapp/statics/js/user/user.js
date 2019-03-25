var falgpwd=false;
	function checkpwd(pwdinput){
	 	var user_id = document.getElementById("userid").value;
		var password = pwdinput.value;
		 $.ajax({  	
            type:"post",         //请求方式  
            url:"/PICCproject/picc/user/checkpwd.ajax",      //连接  
            data:"password="+password+"&user_id="+user_id,   //数据加载  
            dataType:"text",     //数据类型  
            success:function(data){   //data表示服务器端返回的数据  
                if(data == "n"){                 	
                	$("#div_pwd").removeClass("am-form-error")
                	$("#div_pwd").addClass("am-form-success")
                	$("#span_pwd").removeClass("am-icon-times")
                    $("#span_pwd").addClass("am-icon-check")
                    falgpwd=true;                   
                }else{
                	$("#div_pwd").removeClass("am-form-success")
                	$("#div_pwd").addClass("am-form-error")
                	$("#span_pwd").removeClass("am-icon-check")
                    $("#span_pwd").addClass("am-icon-times") 
                	falgpwd=false;
                }   
            }  
        });
	}
	var falgnewpwd = false;
	function checkagpwd(agpwdinput){
		var newpwd= document.getElementById("newpassword").value;
		if(newpwd!=null && newpwd!=""){
			if(agpwdinput.value==newpwd){
				$("#div_pwdg").removeClass("am-form-error")
            	$("#div_pwdg").addClass("am-form-success")
            	$("#span_pwdg").removeClass("am-icon-times")
                $("#span_pwdg").addClass("am-icon-check")
				falgnewpwd=true;
			}else{
				$("#div_pwdg").removeClass("am-form-success")
            	$("#div_pwdg").addClass("am-form-error")
            	$("#span_pwdg").removeClass("am-icon-check")
                $("#span_pwdg").addClass("am-icon-times") 
				falgnewpwd=false;
			}
		}
	}
	function check(){
		if(falgpwd==true && falgnewpwd==true){
			return true;
		}else{
			return false;
		}
	}