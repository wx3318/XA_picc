/**
 * Created by Tripod Fan on 2018/10/23 16:13
 */

// 绑定事件初始化
$(function() { 
    
    setFormCheck("goods-add-form");
    // 绑定提交按钮
    
    $("#goos-save-btn").on("click",function () {
        

        var $form_ = $('#goods-add-form');
        var checkResult_form_ = $form_.validator('isFormValid');
     
        if (checkResult_form_) {
            var url = "/PICCproject/picc/area/add.ajax";
            var param = $('#goods-add-form').serialize();
            // 验证通过，进行提交操作
            
            simpleAjax(url, param, "json", true, function(result){
                // 新增结果
                
                bkyyAlert(result.msg);

                if(result.success){
                setInterval('  window.location = "/PICCproject/picc/area/list.html"',2000); 
                }
            });
        }
        


    })
})