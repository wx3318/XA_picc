/**
 * Created by Tripod Fan on 2018/11/29 16:03
 */

var article_content;
// 绑定事件初始化
$(function() { 
    
    
    // 文章内容
    article_content = UE.getEditor('content', {
        UEDITOR_HOME_URL: basePath + '/statics/assets/UEditor/' //为编辑器实例添加一个路径
        , serverUrl: basePath + '/statics/assets/UEditor/jsp/controller.jsp'  //编辑器统一请求接口路径
        , textarea: 'content' //提交表单时，服务器获取编辑器提交内容的所用的参数
        , initialContent: ''
        , pasteplain: true        //纯文本粘贴
        , maximumWords: 4000       //允许的最大字符数
    });
    
    setFormCheck("goods-add-form");
    // 绑定提交按钮
    
    $("#goos-save-btn").on("click",function () {
        

        var $form_ = $('#goods-add-form');
        var checkResult_form_ = $form_.validator('isFormValid');
     
        if (checkResult_form_) {
            var url = "/PICCproject/picc/home/add.ajax";
            var param = $('#goods-add-form').serialize();
            // 验证通过，进行提交操作
            
            simpleAjax(url, param, "json", true, function(result){
                // 新增结果
                
                bkyyAlert(result.msg);

                if(result.success){
                  /*  window.location = "/PICCproject/picc/backGoods/list.html";*/
                setInterval('  window.location = "/PICCproject/picc/home/list.html"',2000); 
                }
            });
        }
        


    })
})