
/**
 * Created by Tripod Fan on 2018/08/10 10:05
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
    // 数据加载url
    var url = "/PICCproject/picc/operationRecord/queryAll.ajax";
    
    // table分页，查询初始化
     table = $('#goodsTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            data:function ( d ) {
                //被保人
                d.createdName = function() {
                    if($("#createdName").val() == '') {
                        return "";
                    }else {
                        return $("#createdName").val();
                    }
                };
              //操作功能
                d.content = function() {
                    if($("#content").val() == '') {
                        return "";
                    }else {
                        return $("#content").val();
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
            {"class" : "am-text-middle", "data": "content"},
            {"class" : "am-text-middle", "data": "typeName"},
            {"class" : "am-text-middle", "data": "createdName"},
            {"class" : "am-text-middle", "data": "createdDateName"}
        ],

        //默认列
        columnDefs:[
            {
                "targets": 0,
                //操作功能
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 1,
                //操作类型
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 2,
                //操作人
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 3,
                //操作时间
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            }
        ]
    });
})
