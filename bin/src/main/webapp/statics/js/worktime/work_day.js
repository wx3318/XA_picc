/**
 * Created by Tripod w on 2018/09/13 15:00
 */
var tableday;
$(function () { 
    
    // 绑定查询事件
    $('#btn-search-day').on('click', function() {
        // 重置table查询
    	tableday.ajax.reload();
    });
    
    // 绑定重置查询事件
    $('#btn-reset-day').on('click', function() {
        // 重置table条件
        $("#search-form-workday")[0].reset();
        $("#month").attr('value',''); 
        // 重置table查询
        tableday.ajax.reload();
    });
    //设置
	 $('#btn-insert-pro').on('click', function() {	        
	        // 跳转页面
	        window.open("/PICCproject/picc/worktime/saveday.html");
	    });
    // 编辑绑定事件
    $('body').on('click', '.btn-updateday-pro', function() {
        var id = $(this).attr("bkyy-id");
        alert("没有开发")
        /*  // 跳转页面
          window.open("/PICCproject/picc/worktime/upworktime.html?workTimeId="+ id , "_blank");*/
    });


    //导出excel表
  /*  $('#btn-excel-pro').on('click',function(){ 
    	//exportDayExcel();
    });*/

    
    // 数据加载url
    var url = "/PICCproject/picc/worktime/workday.ajax";
    
    // table分页，查询初始化
    tableday = $('#workDayTable').DataTable({
        ajax: {
            url: url,
            type:"post",           
            data:function ( d ) {
                //员工姓名
                d.month = function() {
                    if($("#month").val() == '') {
                        return "";
                    }else {
                        return $("#month").val();
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
        	{"class" : "am-text-middle", "data": "month"},
            {"class" : "am-text-middle", "data": "days"},
            {"class" : "am-text-middle", "data": "create_name"},
            {"class" : "am-text-middle", "data": "create_date"},
            {"class" : "am-text-middle", "data": "id"},
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