	
	  $(function() {
		//文件名字显示
		    $('#damagefile').on('change', function() {
		      var fileNames = '';
		      $.each(this.files, function() {
		        fileNames += '<span class="am-badge">' + this.name + '</span> ';
		      });
		      $('#file-list-damage').html(fileNames);
		    });
		  });
	  
	  $(function() {
			//文件名字显示
			    $('#damagedtfile').on('change', function() {
			      var fileNames = '';
			      $.each(this.files, function() {
			        fileNames += '<span class="am-badge">' + this.name + '</span> ';
			      });
			      $('#file-list-damagedt').html(fileNames);
			    });
			  });
	  $(function() {
			//文件名字显示
			    $('#damagezpfile').on('change', function() {
			      var fileNames = '';
			      $.each(this.files, function() {
			        fileNames += '<span class="am-badge">' + this.name + '</span> ';
			      });
			      $('#file-list-damagezp').html(fileNames);
			    });
			  });
	  
	  //判断文件是否为Excel文件
	  function checkDataDamage(){
		  
			var fileDir = $("#damagefile").val();
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
	  function checkcDataDamagedt(){
		  
			var fileDir = $("#damagedtfile").val();
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
	  function checkcDataDamagezp(){
		  
			var fileDir = $("#damagezpfile").val();
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
	  //定损导入
	  function importDamageExcel() {
		 
			if(checkDataDamage()){
			   	var formData = new FormData();
			    var name = $("#damagefile").val();
			    formData.append("file",$("#damagefile")[0].files[0]);
			    formData.append("name",name);	
			    alert("导入")
			     $.ajax({
			        url : '/PICCproject/picc/damage/importdamage.ajax',
			        type : 'POST',
			        async : true,
			        data : formData,
			        // 告诉jQuery不要去处理发送的数据
			        processData : false,
			        // 告诉jQuery不要去设置Content-Type请求头
			        contentType : false,
			        beforeSend: function() {
			        	$("#inport_file_damage").val("正在上传...");
			            $("#inport_file_damage").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
			        },
			        success : function(responseStr) {
			            $("#inport_file_damage").attr("disabled", false);
		                $("#inport_file_damage").val("确定提交");
		                $("#damagefile").val("")
		                bkyyAlert(responseStr);
		                $('#file-list-damage').html(""); 
			        },
			        error : function(){
			        	$("#inport_file_damage").attr("disabled", false);
		                $("#inport_file_damage").val("确定提交");
						bkyyAlert("数据导入失败");
						$("#damagefile").val("")
				    } 
			    });
			}		    
		}
	  
	  
	  
	//低碳导入 ajax
	function importDamagedtExcel() {
		if(checkcDataDamagedt()){
		   	var formData = new FormData();
		    var name = $("#damagedtfile").val();
		    formData.append("file",$("#damagedtfile")[0].files[0]);
		    formData.append("name",name);
		      $.ajax({
		        url : '/PICCproject/picc/damage/importdamagedt.ajax',
		        type : 'POST',
		        async : true,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,
		        beforeSend: function() {
		        	$("#inport_file_damagedt").val("正在上传...");
		            $("#inport_file_damagedt").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
		        },
		        success : function(responseStr) {
		            $("#inport_file_damagedt").attr("disabled", false);
	                $("#inport_file_damagedt").val("确定提交");
	                $("#damagedtfile").val("")
	                bkyyAlert(responseStr);
	                $('#file-list-damagedt').html("") 
		        },
		        error : function(){
		        	$("#inport_file_damagedt").attr("disabled", false);
	                $("#inport_file_damagedt").val("确定提交");
					bkyyAlert("数据导入失败");
					$("#damagedtfile").val("")
			    } 
		    });
		}		    
	}
	function importDamagezpExcel() {
		if(checkcDataDamagezp()){
		   	var formData = new FormData();
		    var name = $("#damagezpfile").val();
		    formData.append("file",$("#damagezpfile")[0].files[0]);
		    formData.append("name",name);
		      $.ajax({
		        url : '/PICCproject/picc/damage/importdamagetk.ajax',
		        type : 'POST',
		        async : true,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,
		        beforeSend: function() {
		        	$("#inport_file_damagezp").val("正在上传...");
		            $("#inport_file_damagezp").attr("disabled", true);//提交表单前的处理，防止用户多次点击【登陆】，重复提交表单    
		        },
		        success : function(responseStr) {
		            $("#inport_file_damagezp").attr("disabled", false);
	                $("#inport_file_damagezp").val("确定提交");
	                $("#damagezpfile").val("")
	                bkyyAlert(responseStr);
	                $('#file-list-damagezp').html("") 
		        },
		        error : function(){
		        	$("#inport_file_damagezp").attr("disabled", false);
	                $("#inport_file_damagezp").val("确定提交");
					bkyyAlert("数据导入失败");
					$("#damagezpfile").val("")
			    } 
		    });
		}		    
	}