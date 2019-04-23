/**
 * Created by Tripod w on 2018/09/13 15:00
 */
var tablexnlwl;
$(function () { 
    
    // 绑定查询事件
   /* $('#btn-search-tp').on('click', function() {
        // 重置table查询
    	tablexnlwl.ajax.reload();
    });*/
    
    // 绑定重置查询事件
    $('#btn-reset-xn').on('click', function() {
        // 重置table条件
       $("#search-form-tp")[0].reset(); 
        // 重置table查询
    	tablexnlwl.ajax.reload();
    });
    // 编辑绑定事件
   /* $('body').on('click', '.btn-updateday-pro', function() {
        var id = $(this).attr("bkyy-id");

          // 跳转页面
          window.open("/PICCproject/picc/worktime/upworktime.html?workTimeId="+ id , "_blank");
    });*/


    //导出excel表
    $('#btn-excel-xn').on('click',function(){   	
    	exportExcelxn();
    });

    
    // 数据加载url
    var url = "/PICCproject/picc/pending/xnlwl.ajax";
    
    // table分页，查询初始化
    tablexnlwl = $('#xiaLiTable').DataTable({
        ajax: {
            url: url,
            type:"post",
            
            /*data:function ( d ) {
                //定损状态
                d.damageCode = function() {
                    if($("#damageCode").val() == '') {
                        return "";
                    }else {
                        return $("#damageCode").val();
                    }
                };
            },*/
            // 解决ajax查询后，按钮无法点击的问题
            complete: function () {
                $('[data-am-dropdown]').dropdown();
            }
        },
        oLanguage: {
            sProcessing : '<img src="${basePath}/statics/assets/img/loading.gif"/>'	
        },
        columns: [
         /*  
          * p.report_number,p.registration_number,p.policy_number,p.plate_number,p.assured,p.risk_date,p.case_character
          * ,p.underwriting_code,p.assessment_loss
          * ,p.insurance_code,p.insurance_name,p.damage_code,p.damage_name,c.case_station_name,p.group_id
          *  
          *  {"class" : "am-text-middle", "data": "address"},*/
        	{"class" : "am-text-middle", "data": "report_number"},
            {"class" : "am-text-middle", "data": "registration_number"},
            {"class" : "am-text-middle", "data": "policy_number"},
            {"class" : "am-text-middle", "data": "plate_number"},
            {"class" : "am-text-middle", "data": "assured"},
            {"class" : "am-text-middle", "data": "risk_date"},
            {"class" : "am-text-middle", "data": "case_character"},
            {"class" : "am-text-middle", "data": "underwriting_code"},
            {"class" : "am-text-middle", "data": "assessment_loss"},
            {"class" : "am-text-middle", "data": "insurance_code"},
            {"class" : "am-text-middle", "data": "insurance_name"},
            {"class" : "am-text-middle", "data": "damage_code"},
            {"class" : "am-text-middle", "data": "damage_name"},
            {"class" : "am-text-middle", "data": "case_station_name"},
            {"class" : "am-text-middle", "data": "group_id"}
        ],

        //默认列
        columnDefs:[
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
                    if(data == undefined || data == null) {
                        return "";
                    }else {
                        return data;
                    }
                }
            },
            {
                "targets": 9,
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
                "targets": 10,
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
                "targets": 11,
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
                "targets": 12,
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
                "targets": 13,
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
                "targets": 14,
                //备注
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
    function exportExcelxn() {
    	location.href="/PICCproject/picc/pending/pendingexcel.ajax?areaType=5";
    } 
})