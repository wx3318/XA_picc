
/**
 * Created by Tripod Fan on 2018/10/24 10:05
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
        $("#signDateStart").attr('value','');
        $("#signDateEnd").attr('value','');
        // 重置table查询
        table.ajax.reload();
    });
    
    // 绑定新增事件
    $('#btn-insert-pro').on('click', function() {
        
        // 跳转页面
        window.open("/PICCproject/picc/area/add.html");
    });
    
    // 编辑绑定事件
    $('body').on('click', '.btn-update-pro', function() {

       var id = $(this).attr("bkyy-id");

        // 跳转页面
        window.open("/PICCproject/picc/area/update.html?areaId="+ id , "_blank");
    });

    
    // 数据加载url
    var url = "/PICCproject/picc/area/queryAll.ajax";
    
    // table分页，查询初始化
     table = $('#goodsTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            data:function ( d ) {
                //区域名称
                d.areaName = function() {
                    if($("#areaName").val() == '') {
                        return "";
                    }else {
                        return $("#areaName").val();
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
            {"class" : "am-text-middle", "data": "areaId"},
            {"class" : "am-text-middle", "data": "areaName"},
            {"class" : "am-text-middle", "data": "userName"},
            {"class" : "am-text-middle", "data": "groupName"},
            {"class" : "am-text-middle", "data": "areaId"}
        ],

        //默认列
        columnDefs:[
            {
                "targets": 0,
                //编号
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 1,
                //区域名称
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 2,
                //主管姓名
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 3,
                //负责组别
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


