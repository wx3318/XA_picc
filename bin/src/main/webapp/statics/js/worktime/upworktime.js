// 绑定事件初始化

$(function() { 
    // 绑定提交按钮    
    $("#worktime-update-btn").on("click",function () {
    	 	var param = $('#worktime-update-form').serialize();
            var url = "/PICCproject/picc/worktime/update.ajax";
            simpleAjax(url, param, "json", true, function(result){
                // 跟新结果                
                bkyyAlert(result.msg);
                if(result.success){
                    window.location = "/PICCproject/picc/worktime/list.html";
                    setInterval('  window.location = "/PICCproject/picc/worktime/list.html"',2000); 
                }
            });      
    })
})