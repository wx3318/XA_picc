
/**
 * Created by Tripod Fan on 2018/10/24 10:56
 */
// 绑定事件初始化

$(function() { 
    
    setFormCheck("goods-update-form");
    // 绑定提交按钮
    
    $("#goods-update-btn").on("click",function () {
        var $form_ = $('#goods-update-form');
        var checkResult_form_ = $form_.validator('isFormValid');
        if (checkResult_form_) { 
            
            var url = "/PICCproject/picc/area/update.ajax";
            var param = $('#goods-update-form').serialize();

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