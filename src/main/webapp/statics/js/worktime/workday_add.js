$(function() { 
    // 绑定提交按钮
    
       $("#workday-save-btn").on("click",function () {
 
             var url = "/PICCproject/picc/worktime/addworkday.ajax";
             var param = $("#workday-add-form").serialize() + "&workDays=" + hDays;
            // 验证通过，进行提交操作
             $("#workday-save-btn").attr("disabled","disabled")
            simpleAjax(url, param, "json", true, function(result){
                // 新增结果
                bkyyAlert(result.msg)
                if(result.success){
                	window.location = "/PICCproject/picc/worktime/savedaylist.html";
                	setInterval('  window.location = "/PICCproject/picc/worktime/savedaylist.html',2000); 
                }
            });
    })
})