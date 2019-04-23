
/**
 * Created by Tripod Fan on 2018/08/23 17:51
 */
var table;
$(function () { 
    
    // 绑定查询事件
    $('#btn-search-pro').on('click', function() {
        // 重置table查询
        table.ajax.reload();
    });
    
    // 绑定重置查询事件
    $('#btn-reset-pro').on('click', function() {
        
        // 重置table查询
        
        $("#search-form-goods")[0].reset();
        // 重置table查询
        table.ajax.reload();
    });
    
    // 绑定新增事件
    $('#btn-insert-pro').on('click', function() {
        
        // 跳转页面
        window.open("/PICCproject/picc/function/add.html");
    });
    
    // 编辑绑定事件
    $('body').on('click', '.btn-update-pro', function() {

       var id = $(this).attr("bkyy-id");

        // 跳转页面
        window.open("/PICCproject/picc/function/update.html?functionId="+ id , "_blank");
    });
    
    // 绑定删除事件
    $('body').on('click', '.btn-remove-pro', function() {
       var id = $(this).attr("bkyy-id");
       bkyyConfirm("是否删除", "确定删除该功能吗？", function(result){
           if (result) {
               var url = "/PICCproject/picc/function/delete.ajax";
               var param = "id="+ id;
               // 验证通过，进行提交操作
               
               simpleAjax(url, param, "json", true, function(result){
                   // 新增结果
                   
                   bkyyAlert(result.msg);

                   if(result.success){
                  /*      setInterval('  window.location = "/PICCproject/picc/function/list.html"',2000);  */
                       table.ajax.reload();
                   }
               });
           }
       });
    });
    
    // 数据加载url
    var url = "/PICCproject/picc/function/queryAll.ajax";
    
    // table分页，查询初始化
     table = $('#goodsTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            data:function ( d ) {
                //功能名称
                d.funName = function() {
                    if($("#funName").val() == '') {
                        return "";
                    }else {
                        return $("#funName").val();
                    }
                };
            },
            // 解决ajax查询后，按钮无法点击的问题
            complete: function () {
                $('[data-am-dropdown]').dropdown();
            }
        },
        oLanguage: {
            sProcessing : '<img src="${basePath}/statics/assets/img/loading.gif"/>'
        },
        columns: [
         /*   {"class" : "am-text-middle", "data": "address"},*/
            {"class" : "am-text-middle", "data": "funName"},
            {"class" : "am-text-middle", "data": "funDescription"},
            {"class" : "am-text-middle", "data": "funType"},
            {"class" : "am-text-middle", "data": "funUrl"},
            {"class" : "am-text-middle", "data": "orderNum"},
            {"class" : "am-text-middle", "data": "funFlag"},
            {"class" : "am-text-middle", "data": "id"}
        ],

        //默认列
        columnDefs:[
            {
                "targets": 0,
                //功能名称
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 1,
                //功能描述
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 2,
                //类型
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 3,
                //功能URL
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 4,
                //排序
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 5,
                //权限标识
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 6,
                //操作
                
                "render" : function ( data, type, full, meta ) {
                    
                    var tempHtml = "";
                    
                    tempHtml = $("#table-options-1").html();
                    // 处理数据
                    tempHtml = tempHtml.replaceAll("【data】", data);
                    
                    return tempHtml;
                }
            }
        ]
    });
})



