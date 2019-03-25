function check(){
	var flag = true;
	var admin = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var message = document.getElementById("message");
	var reg=/^[0-9]*$/;
	if ("" == admin || !reg.test(admin)){
		flag = false;
		message.innerHTML="请输入账号或密码";
		return false;
	}
	else if ("" == password){
		flag = false;
		message.innerHTML="请输入账号或密码";
		return false;
	}
	if(flag == true){
		//form.submit();
		return true;
	}
}
//当提交表单时，会发生 submit 事件。

$('#form_login').submit(function() { 
	
	 //序列化表单，后台可正常通过post方法获取数据
    
    var url = "/PICCproject/login/dologin.ajax";
    var param = $('#form_login').serialize();
    simpleAjax(url, param, "json", true, function(result){
        // 新增结果

        if(result.success){
            window.location = "/PICCproject/index";
        } else {
            bkyyAlert(result.msg);
        }
    });
    
/*    var postData = $("#form_login").serialize();
    $.ajax({
        type: "POST",
        url: "dologin",
        data: postData,
        beforeSend: function() {
            $("#login_sub").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单
            $("#login_sub").val("正在登陆...");
        },
        success: function(URL) {
            if (URL == 0) {
            	$("#login_sub").attr("disabled", false);
                $("#login_sub").val("登陆");
                alert("用户或密码错误！");
            } else {
                window.location.href = URL;      
            }
        },
        error:function(){
        	alert("操作有误")
        }
    });*/
})   
 
