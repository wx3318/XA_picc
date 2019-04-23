

	
	  $(function() {
		  //文件名字显示
		    $('#closinglistfile').on('change', function() {
		      var fileNames = '';
		      $.each(this.files, function() {
		        fileNames += '<span class="am-badge">' + this.name + '</span> ';
		      });
		      $('#file-list-closing').html(fileNames);
		    });
		  });
	  $(function() {
		  //文件名字显示
		    $('#cpdingfile').on('change', function() {
		      var fileNames = '';
		      $.each(this.files, function() {
		        fileNames += '<span class="am-badge">' + this.name + '</span> ';
		      });
		      $('#file-list-cpding').html(fileNames);
		    });
		  });


//ajax 方式上传用户
	function importClosingRateExcel() {
		if(checkDataClosingList()){
		   	var formData = new FormData();
		    var name = $("#closinglistfile").val();
		    formData.append("file",$("#closinglistfile")[0].files[0]);
		    formData.append("name",name);
		     $.ajax({
		       url : '/PICCproject/picc/closing/closingrateexcel.ajax',
		        type : 'POST',
		        async : true,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,
		        beforeSend: function() {
		        	$("#inport_file_closing").val("正在上传...");
		            $("#inport_file_closing").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
		        },
		        success : function(responseStr) {
		            $("#inport_file_closing").attr("disabled", false);
	                $("#inport_file_closing").val("确定提交");
	                $("#closinglistfile").val("")
	                alert(responseStr)
	                $('#file-list-closing').html("") 
		        },
		        error : function(){
		        	$("#inport_file_closing").attr("disabled", false);
	                $("#inport_file_closing").val("确定提交");
					alert("数据导入失败")
					$("#closinglistfile").val("")
			    } 
		    });
		}		    
	}
	
	//ajax 方式上传用户
	function importPendingExcel() {
		if(checkDataClosingListPending()){
		   	var formData = new FormData();
		    var name = $("#cpdingfile").val();
		    formData.append("file",$("#cpdingfile")[0].files[0]);
		    formData.append("name",name);
		     $.ajax({
		       url : '/PICCproject/picc/closing/closingratependingexcel.ajax',
		        type : 'POST',
		        async : true,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,
		        beforeSend: function() {
		        	$("#import_file_cpding").val("正在上传...");
		            $("#import_file_cpding").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
		        },
		        success : function(responseStr) {
		            $("#import_file_cpding").attr("disabled", false);
	                $("#import_file_cpding").val("确定提交");
	                $("#cpdingfile").val("")
	                alert(responseStr)
	                $('#import_file_cpding').html("") 
		        },
		        error : function(){
		        	$("#import_file_cpding").attr("disabled", false);
	                $("#import_file_cpding").val("确定提交");
					alert("数据导入失败")
					$("#cpdingfile").val("")
			    } 
		    });
		}		    
	}
	
	//JS校验form表单信息
	function checkDataClosingList(){
		var fileDir = $("#closinglistfile").val();
		
		var suffix = fileDir.substr(fileDir.lastIndexOf("."));
		
		if("" == fileDir){
			alert("选择需要导入的Excel文件！");
			return false;
		}
		if(".xls" != suffix && ".xlsx" != suffix){
			alert("选择Excel格式的文件导入！");
			return false;
		}
		return true;
	}
	//JS校验form表单信息
	function checkDataClosingListPending(){
		var fileDir = $("#cpdingfile").val();
		var suffix = fileDir.substr(fileDir.lastIndexOf("."));
		if("" == fileDir){
			alert("选择需要导入的Excel文件！");
			return false;
		}
		if(".xls" != suffix && ".xlsx" != suffix){
			alert("选择Excel格式的文件导入！");
			return false;
		}
		return true;
	}
	
	



