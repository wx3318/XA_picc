/**
 * Created by Tripod w on 2018/09/13 15:00
 */
var tableday;
$(function () { 
    
    // 绑定查询事件
    $('#btn-search-pro1').on('click', function() {
        // 重置table查询
    	tableday.ajax.reload();
    });
    
    // 绑定重置查询事件
    $('#btn-reset-worktime').on('click', function() {
        // 重置table条件
        $("#search-form-worktime")[0].reset();
        $("#uName").attr('value','');
        $("#startWorkDate").attr('value','');
        $("#endWorkDate").attr('value','');
        $("#uGroup").attr('value','');
        $("#workTion").attr('value','');     
        // 重置table查询
        tableday.ajax.reload();
    });
    // 编辑绑定事件
    $('body').on('click', '.btn-updateday-pro', function() {
        var id = $(this).attr("bkyy-id");

          // 跳转页面
          window.open("/PICCproject/picc/worktime/upworktime.html?workTimeId="+ id , "_blank");
    });


    //导出excel表
    $('#btn-excel-day').on('click',function(){ 
    	
    	exportDayExcel();
    });

    
    // 数据加载url
    var url = "/PICCproject/picc/worktime/worktimelist.ajax";
    
    // table分页，查询初始化
    tableday = $('#workTimeTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            
            data:function ( d ) {
                //员工姓名
                d.uName = function() {
                    if($("#uName").val() == '') {
                        return "";
                    }else {
                        return $("#uName").val();
                    }
                };
              //打卡开始
                d.startWorkDate = function() {
                    if($("#startWorkDate").val() == '') {
                        return "";
                    }else {
                        return $("#startWorkDate").val();
                    }
                };
              //打卡结束
                d.endWorkDate = function() {
                    if($("#endWorkDate").val() == '') {
                        return "";
                    }else {
                        return $("#endWorkDate").val();
                    }
                };
                //组别
                d.groupId = function() {
                    if($("#uGroup").val() == '') {
                        return "";
                    }else {
                        return $("#uGroup").val();
                    }
                };
                //状态
                d.workTion = function() {
                    if($("#workTion").val() == '') {
                        return "";
                    }else {
                        return $("#workTion").val();
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
        	{"class" : "am-text-middle", "data": "group_name"},
            {"class" : "am-text-middle", "data": "username"},
            {"class" : "am-text-middle", "data": "u_name"},
            {"class" : "am-text-middle", "data": "work_date"},
            {"class" : "am-text-middle", "data": "start_time"},
            {"class" : "am-text-middle", "data": "end_time"},
            {"class" : "am-text-middle", "data": "station"},
            {"class" : "am-text-middle", "data": "message"},
            {"class" : "am-text-middle", "data": "id"}
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
    function exportDayExcel() {
       var uName=  $("#uName").val();
       var startWorkDate=  $("#startWorkDate").val();
       var endWorkDate=  $("#endWorkDate").val();
       var uGroup=  $("#uGroup").val();
       var workTion=  $("#workTion").val();
       location.href="/PICCproject/picc/worktime/worktimeexport.ajax?uName="+uName +"&startWorkDate="+startWorkDate
        			+"&endWorkDate="+endWorkDate+"&uGroup="+uGroup+"&workTion="+workTion;
    } 
})