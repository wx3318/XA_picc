/**
 * 
 */
	//重置
	$('#btn-reset-pro').on('click',function(){    	
		window.location = "/PICCproject/picc/groupcase/monthcase.html";
    });
	//导出excel表
    $('#btn-excel-pro').on('click',function(){    	
    	exportExcel();
    });
    function exportExcel() { 	
    	var createDate= $("#date").val();
    	location.href="/PICCproject/picc/groupcase/monthcaseexcel.ajax?createDate="+createDate;
    }