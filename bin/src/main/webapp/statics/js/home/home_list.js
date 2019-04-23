
/**
 * Created by Tripod Fan on 2018/11/30 14:18
 */
var table;
$(function () { 
    
    // 查看文章绑定事件
    $('body').on('click', '.btn-detail-pro', function() {

       var id = $(this).attr("bkyy-id");
        // 跳转页面
        window.open("/PICCproject/picc/home/detail.html?detailId="+ id , "_blank");
    });
    
    // 绑定新增事件
    $('#btn-insert-pro').on('click', function() {       
        // 跳转页面
        window.open("/PICCproject/picc/home/add.html");
    });
    
    // 数据加载url
  var url = "/PICCproject/picc/home/queryAll.ajax";
    
    // table分页，查询初始化
    table = $('#goodsTable').DataTable({
       ajax: {
           url: url,
           type:"post",
           // 解决ajax查询后，按钮无法点击的问题
           complete: function () {
               $('[data-am-dropdown]').dropdown();
           }
       },
       oLanguage: {
           sProcessing : '<img src="${basePath}/statics/assets/img/loading.gif"/>'
       },
       columns: [
                 {"class" : "am-text-middle", "data": "id"},
                 {"class" : "am-text-middle", "data": "title"},
                 {"class" : "am-text-middle", "data": "createdDateName"},
                 {"class" : "am-text-middle", "data": "createdName"}
       ],

       //默认列
       columnDefs:[
           {
               "targets": 0,
               //ID编号
               "render" : function ( data, type, full, meta ) {
                   if(data == undefined || data == null) {
                       return "";
                   }else {
                       return data;
                   }
               }
           },{
               "targets": 1,
               //标题
               "render" : function ( data, type, full, meta ) {
                   if(data == undefined || data == null) {
                       return "";
                   }else {
                       return "<a  class='btn-detail-pro' bkyy-id = '"+full.id+"' href='javascript:;'>" + data + "</a>";
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
           }
       ]
   });
})

