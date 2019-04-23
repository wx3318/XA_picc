<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考勤更新</title>
</head>
<body>
<div class="tpl-portlet-components">
	<div class="am-tabs" data-am-tabs>
			  <ul class="am-tabs-nav am-nav am-nav-tabs">
			    <li class="am-active"><a href="#tab1">手动更新全天（未读到）</a></li>
			    <li><a href="#tab2">手动更新晚上（已读到）</a></li>
			    <li><a href="#tab3">手动更新个人考勤（时间段）</a></li>
			  </ul>
  <div class="am-tabs-bd">
  
    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
	       <div class="tpl-block ">
					<div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 全天未读                    
                    </div>
                </div>
                    <div class="am-g tpl-amazeui-form">
                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="worktimeone-update-form"  >
                            
                              <fieldset> 
                                <div class="am-form-group">
                                    <label for="workAddressone" class="am-u-sm-3 am-form-label">考勤机</label>
                                    <div class="am-u-sm-9">
                                        <select  name="workAddressone" id="workAddressone" >
							            	<option value="">--请选择--</option>
							            	<option value="1" >内勤</option>
							            	<option value="2" >新兵营</option>
							            	<option value="3" >续保1-5组</option>
							            	<option value="4" >转保1-5组</option>
							            	<option value="5" >转保6-10组</option>
	            						</select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="month" class="am-u-sm-3 am-form-label">考勤日期</label>
                                    <div class="am-u-sm-9">
 										<input type="text" id="workDateOne" name="workDateOne" class="am-form-field" placeholder="选择日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary js-modal-open" id="worktimeone-update-btn">更新</button>
                                    </div>
                                </div>
                               
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>
    </div>
    <div class="am-tab-panel am-fade" id="tab2">
       <div class="tpl-block ">
				 <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 下班未读                    
                    </div>
                </div>
                    <div class="am-g tpl-amazeui-form">
                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="worktimetwo-update-form"  >
                            
                              <fieldset> 
                                <div class="am-form-group">
                                    <label for="workAddresstwo" class="am-u-sm-3 am-form-label">考勤机</label>
                                    <div class="am-u-sm-9">
                                        <select  name="workAddresstwo" id="workAddresstwo" >
							            	<option value="">--请选择--</option>
							            	<option value="1" >内勤</option>
							            	<option value="2" >新兵营</option>
							            	<option value="3" >续保1-5组</option>
							            	<option value="4" >转保1-5组</option>
							            	<option value="5" >转保6-10组</option>
	            						</select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="month" class="am-u-sm-3 am-form-label">考勤日期</label>
                                    <div class="am-u-sm-9">
 										<input type="text" id="workDateTwo" name="workDateTwo" class="am-form-field" placeholder="选择日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary js-modal-open" id="worktimetwo-update-btn">更新</button>
                                    </div>
                                </div>
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>
                <div class="tpl-alert"></div>
    </div>
 	<div class="am-tab-panel am-fade" id="tab3">
       <div class="tpl-block ">
				 <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 时间段未读到                    
                    </div>
                </div>
                    <div class="am-g tpl-amazeui-form">
                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="worktimeperson-update-form"  >
                            
                              <fieldset> 
                                <div class="am-form-group">
                                    <label for="workAddressperson" class="am-u-sm-3 am-form-label">考勤机</label>
                                    <div class="am-u-sm-9">
                                        <select  name="workAddressperson" id="workAddressperson" >
							            	<option value="">--请选择--</option>
							            	<option value="1" >内勤</option>
							            	<option value="2" >新兵营</option>
							            	<option value="3" >续保1-5组</option>
							            	<option value="4" >转保1-5组</option>
							            	<option value="5" >转保6-10组</option>
	            						</select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="month" class="am-u-sm-3 am-form-label">考勤日期</label>
                                    <div class="am-u-sm-9">
 										<input type="text" width="40%" id="workDatepersonStrat" name="workDatepersonStrat" class="am-form-field" placeholder="选择开始日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
                                    	<span>-</span>
                                    	<input type="text" width="40%"  id="workDatepersonEnd" name="workDatepersonEnd" class="am-form-field" placeholder="选择结束日期"  style="width: 100%;background-color: white;" data-am-datepicker="{format: 'yyyy-mm-dd'}"  readonly>
                                    </div>
                                </div>
                                
                                 <div class="am-form-group">
                                    <label for="username" class="am-u-sm-3 am-form-label">融合系统工号<!-- <font id="username_span" color="red">*</font> --></label>
                                    <div class="am-u-sm-6">
                                        <input type="text" id="username" name="username" placeholder="请输入融合系统工号" required maxlength="50">
                                    </div>
                                     <label for="username_1" id="username_mes" class="am-u-sm-3 am-form-label"></label>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary js-modal-open" id="worktimeperson-update-btn">更新</button>
                                    </div>
                                </div>
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>
                <div class="tpl-alert"></div>
    </div>
  </div>
</div>
</div>            
       <script type="text/javascript" src="${basePath}/statics/js/worktime/update.js"></script> 	    
</body>
</html>