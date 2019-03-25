
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
        $("#work_mes").attr('value','');
        $("#groupId").attr('value','');
        $("#mesDate").attr('value','');
        $("#startDate").attr('value','');
        $("#endDate").attr('value','');
        // 重置table查询
        tableday.ajax.reload();
    });
    
    
    // 编辑绑定事件
    $('body').on('click', '.btn-update-pro', function() {
        var id = $(this).attr("bkyy-id");

          // 跳转页面/picc/user/updateuser.html?userid=${user.user_id}
          window.open("/PICCproject/picc/user/updateuser.html?userid="+ id , "_blank");
    });
    
    
    
	 // 绑定新增事件
	    $('#add-user-pro').on('click', function() {
	        
	        // 跳转页面
	        window.open("/PICCproject/picc/user/adduser.html");
	    });

    //导出excel表
    $('#user-excel-pro').on('click',function(){
    	exportUserExcel();
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
            {"class" : "am-text-middle", "data": "name"},
            {"class" : "am-text-middle", "data": "role_name"},
            {"class" : "am-text-middle", "data": "group_name"},
            {"class" : "am-text-middle", "data": "work_mes"},
            {"class" : "am-text-middle", "data": "username"},
            {"class" : "am-text-middle", "data": "user_id"}
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
            },{
                "targets": 5,
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
    function exportUserExcel() {
        var proName = $('#name').val();
        var proGroup = $('#groupId').val();
        var username = $('#username').val();
        location.href="/PICCproject/picc/user/userexcel.ajax?uName="+proName+"&username="+username+"&uGroup="+proGroup;
        /*var uGroup = $('#uGroup').val();
        location.href="/PICCproject/picc/Talking/callDayexcel.ajax?uName="+uName +"&startCall_date="+startCall_date
        			+"&endCall_date="+endCall_date+"&uGroup="+uGroup;*/
    }
})
