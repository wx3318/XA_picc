
/**
 * Created by Tripod Fan on 2018/08/24 15:57
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
        window.open("/PICCproject/picc/role/add.html");
    });
    
    // 编辑绑定事件
    $('body').on('click', '.btn-update-pro', function() {

       var id = $(this).attr("bkyy-id");

        // 跳转页面
        window.open("/PICCproject/picc/role/update.html?roleId="+ id , "_blank");
    });
    
    
    // 编辑绑定事件
    $('body').on('click', '.btn-edit-pro', function() {

       var id = $(this).attr("bkyy-id");

        // 跳转页面
        window.open("/PICCproject/picc/role/function.html?roleId="+ id , "_blank");
    });

    
    // 数据加载url
    var url = "/PICCproject/picc/role/queryAll.ajax";
    
    // table分页，查询初始化
     table = $('#goodsTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            data:function ( d ) {
                //功能名称
                d.name = function() {
                    if($("#roleName").val() == '') {
                        return "";
                    }else {
                        return $("#roleName").val();
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
            {"class" : "am-text-middle", "data": "name"},
            {"class" : "am-text-middle", "data": "description"},
            {"class" : "am-text-middle", "data": "created_name"},
            {"class" : "am-text-middle", "data": "created_date"},
            {"class" : "am-text-middle", "data": "id"}
        ],

        //默认列
        columnDefs:[
            {
                "targets": 0,
                //角色名称
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 1,
                //角色描述
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 2,
                //创建人
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 3,
                //创建时间
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 4,
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



