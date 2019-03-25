	
	  $(function() {
		//文件名字显示
		    $('#pdingfile').on('change', function() {
		      var fileNames = '';
		      $.each(this.files, function() {
		        fileNames += '<span class="am-badge">' + this.name + '</span> ';
		      });
		      $('#file-list-pding').html(fileNames);
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
	  //判断文件是否为Excel文件
	  function checkDataPding(){
		  
			var fileDir = $("#pdingfile").val();
			var suffix = fileDir.substr(fileDir.lastIndexOf("."));
			if("" == fileDir){
				bkyyAlert("选择需要导入的Excel文件！");
				return false;
			}
			if(".xls" != suffix && ".xlsx" != suffix ){
				bkyyAlert("选择Excel格式的文件导入！");
				return false;
			}
			return true;
		}
	//判断文件是否为Excel文件
	  function checkcDatacPding(){
		  
			var fileDir = $("#cpdingfile").val();
			var suffix = fileDir.substr(fileDir.lastIndexOf("."));
			if("" == fileDir){
				bkyyAlert("选择需要导入的Excel文件！");
				return false;
			}
			if(".xls" != suffix && ".xlsx" != suffix ){
				bkyyAlert("选择Excel格式的文件导入！");
				return false;
			}
			return true;
		}
	  //初次导入
	  function importcPendingExcel() {
			if(checkcDatacPding()){
			   	var formData = new FormData();
			    var name = $("#cpdingfile").val();
			    formData.append("file",$("#cpdingfile")[0].files[0]);
			    formData.append("name",name);
			     $.ajax({
			        url : '/PICCproject/picc/pending/cimportexcel.ajax',
			        type : 'POST',
			        async : true,
			        data : formData,
			        // 告诉jQuery不要去处理发送的数据
			        processData : false,
			        // 告诉jQuery不要去设置Content-Type请求头
			        contentType : false,
			        beforeSend: function() {
			        	$("#inport_file_cpding").val("正在上传...");
			            $("#inport_file_cpding").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
			        },
			        success : function(responseStr) {
			            $("#inport_file_cpding").attr("disabled", false);
		                $("#inport_file_cpding").val("确定提交");
		                $("#cpdingfile").val("")
		                bkyyAlert(responseStr);
		                $('#file-list-cpding').html("") 
			        },
			        error : function(){
			        	$("#inport_file_cpding").attr("disabled", false);
		                $("#inport_file_cdping").val("确定提交");
						bkyyAlert("数据导入失败");
						$("#cpdingfile").val("")
				    } 
			    });
			}		    
		}
	  
	  
	  
	//ajax 文件上传
	function importPendingExcel() {
		if(checkDataPding()){
		   	var formData = new FormData();
		    var name = $("#pdingfile").val();
		    formData.append("file",$("#pdingfile")[0].files[0]);
		    formData.append("name",name);
		     $.ajax({
		        url : '/PICCproject/picc/pending/importexcel.ajax',
		        type : 'POST',
		        async : true,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,
		        beforeSend: function() {
		        	$("#inport_file_pding").val("正在上传...");
		            $("#inport_file_pding").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
		        },
		        success : function(responseStr) {
		            $("#inport_file_pding").attr("disabled", false);
	                $("#inport_file_pding").val("确定提交");
	                $("#pdingfile").val("")
	                bkyyAlert(responseStr);
	                $('#file-list-pding').html("") 
		        },
		        error : function(){
		        	$("#inport_file_pding").attr("disabled", false);
	                $("#inport_file_dping").val("确定提交");
					bkyyAlert("数据导入失败");
					$("#pdingfile").val("")
			    } 
		    });
		}		    
	}