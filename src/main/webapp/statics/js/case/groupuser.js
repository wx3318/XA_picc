    
    // 绑定重置查询事件
    $('#btn-reset-prouser').on('click', function() {
        // 重置table查询
    	var groupId= $("#groupId").val();
    	window.location = "/PICCproject/picc/groupusercase/groupuser.html?groupId="+groupId;
    });
    
  //导出excel表
    $('#btn-excel-prouser').on('click',function(){    	
    	exportExcel();
    });
    function exportExcel() { 	
    	var groupId= $("#groupId").val();
    	window.location = "/PICCproject/picc/groupusercase/caseuserexcel.ajax?groupId="+groupId;
    }