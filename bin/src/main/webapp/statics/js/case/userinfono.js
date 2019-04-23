
/**
 * Created 
 */
var notable;
$(function () { 
    
    // 绑定查询事件
    $('#btn-search-prono').on('click', function() {
        // 重置table查询
    	notable.ajax.reload();
    });
    
    // 绑定重置查询事件
    $('#btn-reset-prono').on('click', function() {
        // 重置table查询
        $("#search-form-noUserInfo")[0].reset();
        // 重置table查询
        notable.ajax.reload();
    });
    
    
    // 导出
    $('#btn-excel-prono').on('click', function() {
    	location.href="/PICCproject/picc/pending/casegroupexcel.ajax?userInfo=2";
    });
    // 编辑绑定事件
    $('body').on('change', '.userInfo', function() {
    	
       var id = $(this).attr("pdid");
       var userInfo = $(this).val();
       var reg = /^\d{3,10}$/;
       if(reg.test(userInfo)){
    	  var pending = {
    			  	userInfo:userInfo,
    			  	id:id
    	    };
    	   $.ajax({  
	            type:"post",         //请求方式  
	            url:"/PICCproject/picc/pending/updateuserinfo.ajax",      //连接  
	            dataType:'text',
	            data: pending, 
	            success:function(data){   //data表示服务器端返回的数据  
	                if(data == "1"){                 		
	                	notable.ajax.reload();
	                	alert("保存成功")
	                }else{ 
	                	alert("保存失败") 
	                }   
	            }  
	        });
    	   
       }else{
    	   alert("请输入有效字符") 
       }
    });

    
    // 数据加载url
    var url = "/PICCproject/picc/pending/pendinggroup.ajax";
    
    // table分页，查询初始化
    notable = $('#groupUserNoTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            data:function ( d ) {
                //归属人
                d.groupId = function() {
                    if($("#uGroup").val() == '') {
                        return "";
                    }else {
                        return $("#uGroup").val();
                    }
                };
                //功能名称
                d.registrationNumber = function() {
                    if($("#registrationNumberNo").val() == '') {
                        return "";
                    }else {
                        return $("#registrationNumberNo").val();
                    }
                };
                //功能名称
                d.userInfo = "2";
                d.createDate=null;
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
            {"class" : "am-text-middle", "data": "report_number"},
            {"class" : "am-text-middle", "data": "registration_number"},
            {"class" : "am-text-middle", "data": "underwriting_code"},
            {"class" : "am-text-middle", "data": "plate_number"},
            {"class" : "am-text-middle", "data": "assured"},
            {"class" : "am-text-middle", "data": "risk_date"},
            {"class" : "am-text-middle", "data": "case_character"},
            {"class" : "am-text-middle", "data": "insurance_name"},
            {"class" : "am-text-middle", "data": "case_station_name"},
            {"class" : "am-text-middle", "data": "name"}
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
            },
            {
                "targets": 4,
                //创建时间
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
                //创建时间
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
                //创建时间
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
                //创建时间
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
                //创建时间
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 9,
                //创建时间
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
                //创建时间
                "render" : function ( data, type, full, meta ) {
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return "<input type='text' class='userInfo' name='userInfo' pdid='"+full.id+"'>";
                    }
                }
            }
           /* {
                "targets": 7,
                //操作
                
                "render" : function ( data, type, full, meta ) {
                    
                    var tempHtml = "";
                    
                    tempHtml = $("#table-options-1").html();
                    // 处理数据
                    tempHtml = tempHtml.replaceAll("【data】", data);
                    
                    return tempHtml;
                }
            }*/
        ]
    });
})



