var table;
$(function () { 
    
    // 绑定查询事件
    $('#btn-search-day').on('click', function() {
        // 重置table查询
    	table.ajax.reload();
    });
    
    // 绑定重置查询事件
    $('#btn-reset-day').on('click', function() {
        // 重置table条件
        $("#search-form-timeday")[0].reset();
        $("#workDate").attr('value','');    
        // 重置table查询
        table.ajax.reload();
    });
    // 编辑绑定事件
    $('body').on('click', '.btn-updateday-pro', function() {
        var groupId = $(this).attr("bkyy-id");
        var workDate=$("#workDate").val();
          // 跳转页面
       window.open("/PICCproject/picc/worktime/worktimegroup.html?groupId="+ groupId+"&workDate="+workDate, "_blank");
    });


    //导出excel表
    $('#btn-excel-day').on('click',function(){ 
    	exportDayExcel();
    });

    
    // 数据加载url
    var url = "/PICCproject/picc/worktime/workdaylist.ajax";
    
    // table分页，查询初始化
    table = $('#workDayTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            
            data:function ( d ) {
              //打卡日期
                d.workDate = function() {
                    if($("#workDate").val() == '') {
                        return "";
                    }else {
                        return $("#workDate").val();
                    }
                };
            },
            // 解决ajax查询后，按钮无法点击的问题
            complete: function () {
                $('[data-am-dropdown]').dropdown();
              /*  var s= JSON.stringify(data.totalData)
                alert(s)*/
            }
        },
        oLanguage: {
            sProcessing : '<img src="${basePath}/statics/assets/img/loading.gif"/>'	
        },
        columns: [
         /*   {"class" : "am-text-middle", "data": "address"},*/
        	{"class" : "am-text-middle", "data": "groupName"},
            {"class" : "am-text-middle", "data": "name"},
            {"class" : "am-text-middle", "data": "workDate"},
            {"class" : "am-text-middle", "data": "groupSize"},
            {"class" : "am-text-middle", "data": "comeSize"},
            {"class" : "am-text-middle", "data": "lastSize"},
            {"class" : "am-text-middle", "data": "leaveSize"},
            {"class" : "am-text-middle", "data": "askSize"},
            {"class" : "am-text-middle", "data": "groupId"}
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
            }, 
            {
                "targets": 8,
                //备注               
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
    function exportDayExcel() {
        var workDate = $('#workDate').val();
        location.href="/PICCproject/picc/worktime/workdayexport.ajax?workDate="+workDate;
    }
})