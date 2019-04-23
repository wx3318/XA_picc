/**
 * 公用函数JS组件
 * create 2016-11-12
 * author Wang
 */

/**
 * 启动严格模式
 */
"use strict";

/**
 * 简化Jquery Ajax操作
 * @author Wang
 *
 * @param url 请求地址
 * @param param 请求参数  key1=value1&key2=value2 的格式
 * @param dataType 返回参数类型
 * @param async 是否需要异步
 * @param callback 成功后的回调函数
 * @param errorBack 失败后的回调函数
 * @param completeBack 请求完成后回调函数
 */
var simpleAjax = function(url, param, dataType, async, callback, errorBack, completeBack){

    async = async == null || async == undefined ? true : async;

    dataType = dataType == null || dataType == undefined || dataType == "" ? "json" : dataType;

    $.ajax({
        type : "post",
        url : url,
        data : param,
        async : async,
        dataType : dataType,
        success : function(data) {

            if (!!data && data.status == 300){
                bkyyAlertCallBack(data.msg, function () {
                    window.location = data.url;
                });
            } else { // 请求成功, 回调返回值

                callback(data);
            }
        },
        error : function(xhr, textStatus, errorThrown) {

            if (errorBack != null && errorBack != undefined) {
                errorBack(xhr, textStatus, errorThrown);
            }
        },
        complete : function(xhr, textStatus) {

            if (completeBack != null && completeBack != undefined) {
                completeBack(xhr, textStatus);
            }
        }
    });
};

/**
 * 简化Jquery文件上传Ajax操作
 * @author Wang
 *
 * @param url
 * @param param
 * @param dataType
 * @param async
 * @param callback
 * @param errorBack
 * @param completeBack
 */
var simpleFileAjax = function(url, param, dataType, async, callback, errorBack, completeBack){

    async = async == null || async == undefined ? true : async;

    dataType = dataType == null || dataType == undefined || dataType == "" ? "json" : dataType;

    $.ajax({
        type : "post",
        url : url,
        data : param,
        async : async,
        cache : false,
        dataType : dataType,
        // 告诉jQuery不要去处理发送的数据
        processData : false,
        // 因为表单中已经设置文件格式，告诉jQuery不要去设置Content-Type请求头
        contentType : false,
        success : function(data) {

            if (!!data && data.status == 300){
                bkyyAlertCallBack(data.msg, function () {
                    window.location = data.url;
                });
            } else { // 请求成功, 回调返回值

                callback(data);
            }
        },
        error : function(xhr, textStatus, errorThrown) {

            if (errorBack != null && errorBack != undefined) {
                errorBack(xhr, textStatus, errorThrown);
            }
        },
        complete : function(xhr, textStatus) {

            if (completeBack != null && completeBack != undefined) {
                completeBack(xhr, textStatus);
            }
        }
    });
};

/**
 * 共通的全选或者反选
 * @param obj 表头的checkbox对象
 * 需要注意的地方：列表中的checkbox 的name="checkbox-num";
 */
function checkboxSelected(obj){

    $(".am-table input[type='checkbox']").each(function () {
        if($(this).attr("disabled") == "disabled"){
            return;
        }
        $(this).prop('checked', $(obj).prop('checked'));
    });
    //$(".am-table input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/**
 * 获取已经选择的复选框
 * @param name checkbox name属性
 * @returns {Array} 返回数据集合
 */
function getCheckBoxSelectedByName(name){

    var array = new Array();

    $("input[name='" + name + "']:checked").each(function() {
        array.push($(this).attr("bkyy-id"));
    });

    return array;
}

/**
 * 字符串替换
 * @author Wang
 */
String.prototype.replaceAll = function(s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
};

/**
 * 字符在字符串的数量
 * @author Wang
 */
String.prototype.getStringCount = function(char){
    var r = new RegExp('\\' + char, "gi");
    return this.match(r).length;
};

function getlen(str,ch){
    var ret=0;
    for(var i=0;i<str.length;i++){if(str.charAt(i)==ch)	  ret++;}
    return ret;
}

/**
 * js统计字符串中包含的特定字符个数
 * @author Wang
 */
function getPlaceholderCount(strSource, strSearch) {
    var array = strSource.split(strSearch)
    return array.length-1;
}

/**
 * 获取UUID
 * @author Wang
 *
 * @returns
 */
function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
        function(c) {
            var r = (d + Math.random() * 16) % 16 | 0;
            d = Math.floor(d / 16);
            return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        });
    return uuid;
};

/**
 * 清空文件域的value
 * @author Wang
 *
 * @param file input object
 */
function clearInputFile(f){
    if(f.value){
        try{
            f.value = ''; //for IE11, latest Chrome/Firefox/Opera...
        }catch(err){
        }
        if(f.value){ //for IE5 ~ IE10
            var form = document.createElement('form'), ref = f.nextSibling, p = f.parentNode;
            form.appendChild(f);
            form.reset();
            p.insertBefore(f,ref);
        }
    }
}

/**
 * button等按钮的页面跳转
 * @author Wang
 */
function pageJump(url) {

    window.location = url;
}

/**
 * 加载遮罩
 */
function bkyyLoading(title, uuid){

    var html = '';

    html += '<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="bkyy-my-modal-loading-'+uuid+'">';
    html += '<div class="am-modal-dialog">';
    html += '<div class="am-modal-hd">'+title+'</div>';
    html += '<div class="am-modal-bd">';
    html += '<span class="am-icon-spinner am-icon-spin"></span>';
    html += '</div>';
    html += '</div>';
    html += '</div>';

    $(document.body).append(html);

    $('#bkyy-my-modal-loading-' + uuid).modal({
        // 点击遮罩层时关闭 Modal
        closeViaDimmer: false,
        cancelable: false
    });
}

/**
 * 显示提示窗口
 * @author Wang
 *
 * @param message 需要显示的内容
 */
function bkyyAlert(message){

    var html = '';

    var uuid = generateUUID();

    html += '<div class="am-modal am-modal-alert" tabindex="-1" id="bkyy-my-alert-'+uuid+'">';
    html += '<div class="am-modal-dialog">';
    html += '<div class="am-modal-hd">确认</div>';
    html += '<div id="my-basic-content" class="am-modal-bd am-border-bottom-0 ">';
    html += message;
    html += '</div>';
    html += '<div class="am-modal-footer">';
    html += '<span class="am-modal-btn">确定</span>';
    html += '</div>';
    html += '</div>';
    html += '</div>';

    $(document.body).append(html);

    $('#bkyy-my-alert-' + uuid).modal();

    $('#bkyy-my-alert-' + uuid).on('closed.modal.amui', function(){
        $('#bkyy-my-alert-' + uuid).remove();
    });
}

/**
 * 显示提示窗口提供回调函数
 * @author Wang
 *
 * @param message 需要显示的内容
 * @param callback
 */
function bkyyAlertCallBack(message, callback){

    var html = '';

    var uuid = generateUUID();

    html += '<div class="am-modal am-modal-alert" tabindex="-1" id="bkyy-my-alert-call-'+uuid+'">';
    html += '<div class="am-modal-dialog">';
    html += '<div class="am-modal-hd">确认</div>';
    html += '<div id="my-basic-content" class="am-modal-bd am-border-bottom-0 ">';
    html += message;
    html += '</div>';
    html += '<div class="am-modal-footer">';
    html += '<span class="am-modal-btn" data-am-modal-confirm>确定</span>';
    html += '</div>';
    html += '</div>';
    html += '</div>';

    $(document.body).append(html);

    var myalert = $('#bkyy-my-alert-call-' + uuid);

    var confirm = myalert.data('amui.modal');

    myalert.on('closed.modal.amui', function(){
        myalert.remove();
    });

    var onConfirm = function() {
        myalert.modal({
            dimmer:false
        });
        myalert.modal('close');
        callback(true);
    };

    if (confirm) {
        confirm.options.onConfirm =  onConfirm;
        confirm.toggle(this);
    } else {
        myalert.modal({
            relatedElement: this,
            onConfirm: onConfirm
        });
    }
}

/**
 * 显示提示窗口
 * @author Fanyan
 * @update Wang
 *
 * @param message 需要显示的内容
 */
function bkyyHTMLAlert(message){

    var html = '';

    var uuid = generateUUID();

    html += '<div class="am-modal am-modal-alert" tabindex="-1" id="bkyy-my-alert-'+uuid+'">';
    html += '<div class="am-modal-dialog">';
    html += '<div id="my-basic-content" class="am-modal-bd">';
    html += message;
    html += '</div>';
    html += '<div class="am-modal-footer">';
    html += '<span class="am-modal-btn">确定</span>';
    html += '</div>';
    html += '</div>';
    html += '</div>';

    $(document.body).append(html);

    $('#bkyy-my-alert-' + uuid).modal();

    $('#bkyy-my-alert-' + uuid).on('closed.modal.amui', function(){
        $('#bkyy-my-alert-' + uuid).remove();
    });
}

/**
 * 显示是否确认窗口
 * @author Wang
 *
 * @param title 需要显示的标题
 * @param message 需要显示的内容
 * @param callback 按钮后的回调函数 true、false
 */
function bkyyConfirm(title, message, callback){

    var html = '';

    var uuid = generateUUID();

    html += '<div class="am-modal am-modal-confirm" tabindex="-1" id="bkyy-my-confirm-'+uuid+'">';
    html += '<div class="am-modal-dialog">';
    html += '<div class="am-modal-hd">'+title+'</div>';
    html += '<div class="am-modal-bd am-border-bottom-0">';
    html += message;
    html += '</div>';
    html += '<div class="am-modal-footer">';
    html += '<span class="am-modal-btn" data-am-modal-confirm>确定</span>';
    html += '<span class="am-modal-btn" data-am-modal-cancel>取消</span>';
    html += '</div>';
    html += '</div>';
    html += '</div>';

    $(document.body).append(html);

    var myconfirm = $('#bkyy-my-confirm-' + uuid);

    var confirm = myconfirm.data('amui.modal');

    myconfirm.on('closed.modal.amui', function(){
        myconfirm.remove();
    });

    var onConfirm = function() {
        myconfirm.modal({
            dimmer:false
        });
        myconfirm.modal('close');
        callback(true);
    };
    var onCancel = function() {
        myconfirm.modal({
            dimmer:false
        });
        myconfirm.modal('close');
        callback(false);
    }

    if (confirm) {
        confirm.options.onConfirm =  onConfirm;
        confirm.options.onCancel =  onCancel;
        confirm.toggle(this);
    } else {
        myconfirm.modal({
            relatedElement: this,
            onConfirm: onConfirm,
            onCancel: onCancel
        });
    }
}


/**
 * 显示是否确认窗口
 * @author Fanyan
 * @update Wang
 *
 * @param title 需要显示的标题
 * @param message 需要显示的内容或HTML元素
 * @param width 默认窗口宽度
 * @param height 默认窗口高度
 * @param callback 按钮后的回调函数 true、false
 * @param initCallback 窗口完成页面初始化后执行的回调函数 非必填
 */
function bkyyScannerConfirm(title, message, width, height, callback, initCallback){

    var html = '';

    var uuid = generateUUID();

    width = width == null || width == undefined ? 600 : width;

    height = height == null || height == undefined ? 200 : height;

    var maxHeight = document.body.offsetHeight * 0.8;

    height = parseInt(height) > parseInt(maxHeight) ? maxHeight : height;

    // 如果最大高度小于现在传递的参数，则使用最大高度
    html += '<div class="am-modal am-modal-confirm" tabindex="-1" id="bkyy-my-scanner-confirm-'+uuid+'">';
    html += '<div class="am-modal-dialog">';
    html += '<div class="am-modal-hd " style="background-color: #0e90d2; color: white;">'+title+'</div>';
    html += '<div class="am-modal-bd am-border-bottom-0">';
    html += message;
    html += '</div>';
    html += '<div class="am-modal-footer">';
    html += '<span class="am-modal-btn" data-am-modal-confirm>确定</span>';
    html += '<span class="am-modal-btn" data-am-modal-cancel>取消</span>';
    html += '</div>';
    html += '</div>';
    html += '</div>';

    $(document.body).append(html);

    // 非必填项，在页面初始化完成后进行赋值操作。
    // 执行在确认取消回调函数之前
    if(initCallback != null && initCallback != undefined && initCallback != ''){
        initCallback();
    }

    var myconfirm = $('#bkyy-my-scanner-confirm-' + uuid);

    var confirm = myconfirm.data('amui.modal');

    myconfirm.on('closed.modal.amui', function(){
        myconfirm.remove();
    });

    var onConfirm = function() {
        callback(true, myconfirm);
    };
    var onCancel = function() {
        callback(false, myconfirm);
    }

    if (confirm) {
        confirm.options.onConfirm =  onConfirm;
        confirm.options.onCancel =  onCancel;
        confirm.options.width = width;
        confirm.options.height = height;
        confirm.toggle(this);
    } else {

        myconfirm.modal({
            width: width,
            height: height,
            relatedElement: this,
            closeOnConfirm:false
        });

        onConfirm = function() {
            callback(true, myconfirm);
        };
        onCancel = function() {
            callback(false, myconfirm);
        };

        var confirm = myconfirm.data('amui.modal');

        confirm.options.onConfirm =  onConfirm;
        confirm.options.onCancel =  onCancel;
    }

    myconfirm.find(".am-modal-dialog").each(function () {
        $(this).css("overflow-y", "auto");
    });
}


/**
 * 日期初始化函数
 * @author Wang
 * @param id
 * @param format
 * @param startView
 * @param minview
 * @param maxView
 */
function initTimeInputById(id, format, startView, minview, maxView) {
    $('#' + id).datetimepicker({
        format: format // 格式
        , startView: startView // 默认显示级别
        , minView: minview // 最小显示级别
        , maxView: maxView //  最大显示级别
        , language: 'zh-CN' //语言
    });
}

/**
 * 日期销毁函数
 * @author Wang
 * @param id
 */
function removeTimeInputById(id) {
    $('#' + id).datetimepicker('remove');
};

/**
 * 查询结果处理
 * @author Wang
 * @param dataResult 查询的数据结果集合
 * @param size 数据个数
 * @param colspan 每行的tr个数, 进行不存在的数据跨列
 * @param tmpname
 */
function resultToHTML(dataResult, size, colspan, tmpid){

    if(tmpid == null || tmpid == undefined || tmpid == ""){

        tmpid = "html-template";
    }

    var html = "";

    if(dataResult == null || dataResult == ""){

        html += "";
    } else {

        for (var i = 0; i < size; i++) {

            var thtml = $('#' + tmpid).html();

            var m = dataResult[i];

            for (var mp in m) {

                thtml = thtml.replaceAll("【" + mp + "】", m[mp]);
            }

            html += thtml;
        }
    }

    var htmlSplit = html;

    var arrayes = new Array();

    arrayes = splitCustem(htmlSplit, arrayes);

    for(var array in arrayes){

        html = html.replaceAll(arrayes[array] ," ");
    }

    return html;
}

/**
 *  对数据结果中的null值进行处理
 * @author Wang
 * @param htmles
 * @param arrayes
 * @returns {*}
 */
function splitCustem(htmles, arrayes){

    var startIndex = htmles.indexOf("【");

    var endIndex = htmles.indexOf("】");

    if(startIndex != -1 && endIndex != -1 ){

        var splitHtml = htmles.substring(startIndex, endIndex+1);

        htmles = htmles.substring(endIndex+1, htmles.length);

        arrayes.push(splitHtml);

        splitCustem(htmles, arrayes);
    }

    return arrayes;
}


/**
 * 给form绑定验证操作
 * @author Wang
 * @param id 绑定的formid
 * @param isSubmit 是否使用原生方式提交 默认true
 */
function setFormCheck(id, callback){

    var $form_ = $("#" + id);
    // form绑定验证操作
    $form_.validator({
        onValid: function(validity) {
            $(validity.field).closest('.am-form-group').find('.am-alert').hide();
        },

        onInValid: function(validity) {
            var $field = $(validity.field);
            var $group = $field.closest('.am-form-group');
            var $alert = $group.find('.am-alert');
            // 使用自定义的提示信息 或 插件内置的提示信息
            var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

            if (!$alert.length) {
                $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
                appendTo($group);
            }

            $alert.html(msg).show();
        },
        validate: function(validity) {
            // 验证输入空值问题
            if(validity.field.value != ""){
                if(validity.field.value.trim() == ""){
                    validity.valid = false;
                    $(validity.field).attr("data-validation-message", "不能只输入空格");
                }else{
                    $(validity.field).attr("data-validation-message", "");
                }
            }
        },
        submit: function() {
            return callback();
        }
    });
}

/**
 * 给form绑定验证操作 （重写setFormCheck 增加validate自定义验证 isFormValid() 返回 Promise）
 * @param id 绑定的formid
 * @param isSubmit 是否使用原生方式提交 默认true
 * @param callMarkValid 自定义验证函数
 */
function setFormCheckByMarkValid(id, callback, callMarkValid){

    var $form_ = $("#" + id);
    // form绑定验证操作
    $form_.validator({
        onValid: function(validity) {
            $(validity.field).closest('.am-form-group').find('.am-alert').hide();
        },

        onInValid: function(validity) {
            var $field = $(validity.field);
            var $group = $field.closest('.am-form-group');
            var $alert = $group.find('.am-alert');
            // 使用自定义的提示信息 或 插件内置的提示信息
            var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

            if (!$alert.length) {
                $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
                appendTo($group);
            }

            $alert.html(msg).show();
        },
        submit: function() {
            return callback();
        },
        // 域验证通过时添加的操作，通过该接口可定义各种验证提示
        validate: function(validity) {
            if (!!callMarkValid)
                validity = callMarkValid(validity)
            return validity;
        },
    });
}

/**
 * 常用正则表达式
 * @author Wang
 * @type {RegExp}
 */
// 最新手机号码
var rexPhone = /^0?(13[0-9]|15[012356789]|17[0135678]|18[0-9]|14[57])[0-9]{8}$/;

// 最新的身份证号码
var rexCode = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function isNotNumber(d) {
    if(isNaN($(d).val())){
        $(d).val("");
    }
}


/**
 * 给form绑定验证操作
 * @author Wang
 * @param id 绑定的formid
 * @param isSubmit 是否使用原生方式提交 默认true
 */
function setFormCheck(id, callback){
    var $form_ = $("#" + id);
    // form绑定验证操作
    $form_.validator({
        onValid: function(validity) {
            $(validity.field).closest('.am-form-group').find('.am-alert').hide();
        },

        onInValid: function(validity) {
            var $field = $(validity.field);
            var $group = $field.closest('.am-form-group');
            var $alert = $group.find('.am-alert');
            // 使用自定义的提示信息 或 插件内置的提示信息
            var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

            if (!$alert.length) {
                $alert = $('<div class="am-u-sm-9 am-alert am-alert-danger"></div>').hide().
                appendTo($group);
            }

            $alert.html(msg).show();
        },
        validate: function(validity) {
            // 验证输入空值问题
            if(validity.field.value != ""){
                if(validity.field.value.trim() == ""){
                    validity.valid = false;
                    $(validity.field).attr("data-validation-message", "不能只输入空格");
                }else{
                    $(validity.field).attr("data-validation-message", "");
                }
            }
        },
        submit: function() {
            return callback();
        }
    });
}