var table;
$(function () { 
    
    // 绑定查询事件
    $('#btn-search-month').on('click', function() {
        // 重置table查询
    	table.ajax.reload();
    });
    
    // 绑定重置查询事件
    $('#btn-reset-month').on('click', function() {
        // 重置table条件
        $("#search-form-worktime-month")[0].reset();
        $("#uNameMonth").attr('value','');
        $("#workTimeMonth").attr('value','');
        $("#groupId").attr('value','');    
        // 重置table查询
        table.ajax.reload();
    });
    // 编辑绑定事件
    $('body').on('click', '.btn-updatemonth-pro', function() {
        var id = $(this).attr("bkyy-id");
        var date= $("#workTimeMonth").val()
          // 跳转页面
        window.open("/PICCproject/picc/worktime/worktimemonth.html?username="+ id+"&&workDate="+date , "_blank");
    });


    //导出excel表
    $('#btn-excel-month').on('click',function(){ 
    	exportMonthExcel();
    });
    
    
    // 数据加载url
    var url = "/PICCproject/picc/worktime/workmonthlist.ajax";
    
    // table分页，查询初始化
    table = $('#workTimeMonthTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            data:function ( d ) {
                //员工姓名
                d.uName = function() {
                    if($("#uNameMonth").val() == '') {
                        return "";
                    }else {
                        return $("#uNameMonth").val();
                    }
                };
              //打卡月份
                d.workDate = function() {
                    if($("#workTimeMonth").val() == '') {
                        return "";
                    }else {
                        return $("#workTimeMonth").val();
                    }
                };
                //组别
                d.groupId = function() {
                    if($("#groupId").val() == '') {
                        return "";
                    }else {
                        return $("#groupId").val();
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
        	{"class" : "am-text-middle", "data": "groupName"},
            {"class" : "am-text-middle", "data": "userName"},
            {"class" : "am-text-middle", "data": "name"},
            {"class" : "am-text-middle", "data": "workDate"},
            {"class" : "am-text-middle", "data": "workSize"},
            {"class" : "am-text-middle", "data": "leatSize"},
            {"class" : "am-text-middle", "data": "lastSize"},
            {"class" : "am-text-middle", "data": "startSize"},
            {"class" : "am-text-middle", "data": "endSize"},
            {"class" : "am-text-middle", "data": "askSize"},
            {"class" : "am-text-middle", "data": "userName"}
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
                //打卡日期
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
                //首次打卡
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
                //未次打卡
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
                //备注
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 7,
                //备注
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 8,
                //请假
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },{
                "targets": 9,
                //请假
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 10,
                //操作
                
                "render" : function ( data, type, full, meta ) {
                	 var tempHtml = "";                     
                     tempHtml = $("#table-options-2").html();
                     // 处理数据
                     tempHtml = tempHtml.replaceAll("【data】", data);
                     
                     return tempHtml;
               
                }
            } 
        ]
    });
    
    function exportMonthExcel() {
        var uName = $("#uNameMonth").val();
        var workDate = $("#workTimeMonth").val();
        var groupId = $('#groupId').val();
        location.href="/PICCproject/picc/worktime/workmonthexport.ajax?uName="+uName +"&workDate="+workDate
        			+"&groupId="+groupId;
    } 
})