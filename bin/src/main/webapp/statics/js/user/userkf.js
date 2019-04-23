
var tableday;
$(function () { 
    
    // 绑定查询事件
    $('#btn-search-pro').on('click', function() {
        // 重置table查询
    	tableday.ajax.reload();
    });
    
    // 绑定重置查询事件
    $('#btn-reset-pro').on('click', function() {
        
        // 重置table条件
        $("#search-form-user")[0].reset();
        $("#name").attr('value','');
        $("#username").attr('value','');
        // 重置table查询
        tableday.ajax.reload();
    });    
    // 数据加载url
    var url = "/PICCproject/picc/user/seachuser.ajax";
    
    // table分页，查询初始化
    tableday = $('#userTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            
            data:function ( d ) {
                //员工姓名
                d.name = function() {
                    if($("#name").val() == '') {
                        return "";
                    }else {
                        return $("#name").val();
                    }
                };
              //通话开始
                d.username = function() {
                    if($("#username").val() == '') {
                        return "";
                    }else {
                        return $("#username").val();
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
            {"class" : "am-text-middle", "data": "group_name"},
            {"class" : "am-text-middle", "data": "sex"},
            {"class" : "am-text-middle", "data": "phone"},
            {"class" : "am-text-middle", "data": "username"},
            {"class" : "am-text-middle", "data": "yx_id"}
        ],

        //默认列
        columnDefs:[
       /*     {
                "targets": 0,
                //地市
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },*/
            {
                "targets": 0,
                //团队
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 1,
                //营销工号
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 2,
                //姓名
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 3,
                //通话日期
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
                //上午首通
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 5,
                //下午末通
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
