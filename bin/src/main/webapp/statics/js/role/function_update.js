// 绑定事件初始化

$(function() {  


    var setting = {
            check: {
                enable: true,
                chkboxType: { "Y": "p", "N": "s" }
                
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes = functionList;


        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
        
        
        
        
        // 绑定提交按钮
        
        $("#goos-save-btn").on("click",function () {
            

            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            checkCount = zTree.getCheckedNodes(true);
            var classpurview = "";
            for(var i=0;i<checkCount.length;i++) {
            classpurview += "," + checkCount[i].id
            }
        
            classpurview = classpurview.substring(1,classpurview.length);
            
                var url = "/PICCproject/picc/role/addFunction.ajax";
                var param = $('#goods-add-form').serialize() + "&classpurview="+classpurview;
                // 验证通过，进行提交操作
                
                simpleAjax(url, param, "json", true, function(result){
                    // 新增结果
                    
                    bkyyAlert(result.msg);

                    if(result.success){
                      /*  window.location = "/PICCproject/picc/backGoods/list.html";*/
                      setInterval('  window.location = "/PICCproject/picc/role/list.html"',2000); 
                    }
                });
        })
            
})