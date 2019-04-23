// 绑定事件初始化

$(function() { 
    setFormCheck("groupcase-add-form");
    // 绑定提交按钮
    
    $("#groupcase-save-btn").on("click",function () {
        

        var $form_ = $('#groupcase-add-form');
        var checkResult_form_ = $form_.validator('isFormValid');
     
        if (checkResult_form_) {
        	if($("#groupId").val()!=''){
        		 var url = "/PICCproject/picc/groupcase/save.ajax";
                 var param = $('#groupcase-add-form').serialize();
                // 验证通过，进行提交操作               
                 simpleAjax(url, param, "json", true, function(result){
                     // 新增结果      
                     bkyyAlert(result.msg);
                     if(result.success){
                         window.location = "/PICCproject/picc/groupcase/list.html";
                         setInterval('  window.location = "/PICCproject/picc/groupcase/list.html"',2000);
                     }
                 })
        	}else{
        		bkyyAlert('机构不能为空');
        	}
           
        }

    })
})