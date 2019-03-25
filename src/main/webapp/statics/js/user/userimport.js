

	
	  $(function() {
		    $('#userfile').on('change', function() {
		      var fileNames = '';
		      $.each(this.files, function() {
		        fileNames += '<span class="am-badge">' + this.name + '</span> ';
		      });
		      $('#file-list-user').html(fileNames);
		    });
		  });

//ajax 方式上传用户
	function importUserExcel() {
		if(checkDataUser()){
		   	var formData = new FormData();
		    var name = $("#userfile").val();
		    formData.append("file",$("#userfile")[0].files[0]);
		    formData.append("name",name);
		     $.ajax({
		       url : '/PICCproject/upload/userexcel.ajax',
		        type : 'POST',
		        async : true,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,
		        beforeSend: function() {
		        	$("#inport_file_user").val("正在上传...");
		            $("#inport_file_user").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
		        },
		        success : function(responseStr) {
		            $("#inport_file_user").attr("disabled", false);
	                $("#inport_file_user").val("确定提交");
	                $("#userfile").val("")
	                alert(responseStr)
	                $('#file-list-user').html("") 
		        },
		        error : function(){
		        	$("#inport_file_user").attr("disabled", false);
	                $("#inport_file_user").val("确定提交");
					alert("数据导入失败")
					$("#userfile").val("")
			    } 
		    });
		}		    
	}
	
	//JS校验form表单信息
	function checkDataUser(){
		var fileDir = $("#userfile").val();
		var suffix = fileDir.substr(fileDir.lastIndexOf("."));
		if("" == fileDir){
			alert("选择需要导入的Excel文件！");
			return false;
		}
		if(".xls" != suffix && ".xlsx" != suffix ){
			alert("选择Excel格式的文件导入！");
			return false;
		}
		return true;
	}

	
	




/*

	 function importUserExcel() {
		if(checkData()){
		   	var formData = new FormData();
		    var name = $("#userfile").val();
		    formData.append("file",$("#userfile")[0].files[0]);
		    formData.append("name",name);
		     $.ajax({
		        url : '/PICCproject/upload/userexcel',
		        type : 'POST',
		        async : false,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,
		        beforeSend:function(){
		            console.log("正在进行，请稍候");
		        },
		        success : function(responseStr) {
		            alert(responseStr);
		        },
		        error : function(){
					alert("数据导入失败")
			        } 
		    });
		}		    
}

	//JS校验form表单信息
	function checkData(){
		var fileDir = $("#userfile").val();
		var suffix = fileDir.substr(fileDir.lastIndexOf("."));
		if("" == fileDir){
			alert("选择需要导入的Excel文件！");
			return false;
		}
		if(".xls" != suffix && ".xlsx" != suffix ){
			alert("选择Excel格式的文件导入！");
			return false;
		}
		return true;
	} */