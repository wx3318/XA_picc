// 绑定事件初始化

$(function() { 
    setFormCheck("usercase-add-form");
    // 绑定提交按钮
    
    $("#usercase-save-btn").on("click",function () {
        

        var $form_ = $('#usercase-add-form');
        var checkResult_form_ = $form_.validator('isFormValid');
     
        if (checkResult_form_) {
        		 var url = "/PICCproject/picc/groupusercase/update.ajax";
                 var param = $('#usercase-add-form').serialize();
                // 验证通过，进行提交操作               
                 simpleAjax(url, param, "json", true, function(result){
                     // 新增结果      
                     bkyyAlert(result.msg);
                     if(result.success){
                         window.location = "/PICCproject/picc/groupusercase/caseuserlist.html";
                         setInterval('  window.location = "/PICCproject/picc/groupusercase/caseuserlist.html"',2000); 
                     }
                 })
        }
    })
})