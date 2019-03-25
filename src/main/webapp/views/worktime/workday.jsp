<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>考勤设置</title>
    <link href="${basePath}/statics/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/statics/css/skin.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/statics/css/fontSize12.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/statics/css/calendar.css" rel="stylesheet" type="text/css"/>
    
    <script type="text/javascript" src="${basePath}/statics/js/calendar.js"></script>
</head>
<body>
	
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 考勤设置
                    </div>
					

                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal" id="workday-add-form"  >
                            
                              <fieldset>
								<div class="am-form-group">
                                    <label for="month" class="am-u-sm-3 am-form-label">上班月份</label>
                                    <div class="am-u-sm-9">
                                              <input type="text" style="background-color: white;" id="month" name="month" placeholder="请选择月份" required  data-am-datepicker="{format: 'yyyy-mm', viewMode: 'years', minViewMode: 'months'}"  data-am-datepicker readonly>
                                    </div>
                                </div>
                                
                                <div class="am-form-group">
                                       <div class="am-u-sm-9">
								               <TABLE class="biao" width="800px">
								                <TBODY>
								                <TR>
								                    <TD class="calTit" colSpan=7 style="height:30px;padding-top:3px;text-align:center;">
								
								                        <a href="#" title="上一年" id="nianjian" class="ymNaviBtn lsArrow"></a>
								                        <a href="#" title="上一月" id="yuejian" class="ymNaviBtn lArrow"></a>
								
								                        <div style="width:650px; float:left; padding-left:230px;">
								                                            <span id="dateSelectionRili" class="dateSelectionRili"
								                                                  style="cursor:hand;color: white; border-bottom: 1px solid white;"
								                                                  onclick="dateSelection.show()">
																			<span id="nian" class="topDateFont"></span><span
								                                                    class="topDateFont">年</span><span id="yue"
								                                                                                      class="topDateFont"></span><span
								                                                    class="topDateFont">月</span>
																			<span class="dateSelectionBtn cal_next"
								                                                  onclick="dateSelection.show()">▼</span></span> &nbsp;&nbsp;<font id=GZ
								                                                                                                                   class="topDateFont"></font>
								                        </div>
								
								                        <!--新加导航功能-->
								                        <div style="left: 250px; display: none;" id="dateSelectionDiv">
								                            <div id="dateSelectionHeader"></div>
								                            <div id="dateSelectionBody">
								                                <div id="yearList">
								                                    <div id="yearListPrev" onclick="dateSelection.prevYearPage()">&lt;</div>
								                                    <div id="yearListContent"></div>
								                                    <div id="yearListNext" onclick="dateSelection.nextYearPage()">&gt;</div>
								                                </div>
								                                <div id="dateSeparator"></div>
								                                <div id="monthList">
								                                    <div id="monthListContent"><span id="SM0" class="month"
								                                                                     onclick="dateSelection.setMonth(0)">1</span><span
								                                            id="SM1" class="month" onclick="dateSelection.setMonth(1)">2</span><span
								                                            id="SM2" class="month" onclick="dateSelection.setMonth(2)">3</span><span
								                                            id="SM3" class="month" onclick="dateSelection.setMonth(3)">4</span><span
								                                            id="SM4" class="month" onclick="dateSelection.setMonth(4)">5</span><span
								                                            id="SM5" class="month" onclick="dateSelection.setMonth(5)">6</span><span
								                                            id="SM6" class="month" onclick="dateSelection.setMonth(6)">7</span><span
								                                            id="SM7" class="month" onclick="dateSelection.setMonth(7)">8</span><span
								                                            id="SM8" class="month" onclick="dateSelection.setMonth(8)">9</span><span
								                                            id="SM9" class="month" onclick="dateSelection.setMonth(9)">10</span><span
								                                            id="SM10" class="month" onclick="dateSelection.setMonth(10)">11</span><span
								                                            id="SM11" class="month curr" onclick="dateSelection.setMonth(11)">12</span>
								                                    </div>
								                                    <div style="clear: both;"></div>
								                                </div>
								                                <div id="dateSelectionBtn">
								                                    <div id="dateSelectionTodayBtn" onclick="dateSelection.goToday()">今天</div>
								                                    <div id="dateSelectionOkBtn" onclick="dateSelection.go()">确定</div>
								                                    <div id="dateSelectionCancelBtn" onclick="dateSelection.hide()">取消</div>
								                                </div>
								                            </div>
								                            <div id="dateSelectionFooter"></div>
								                        </div>
								                        <a href="#" id="nianjia" title="下一年" class="ymNaviBtn rsArrow" style="float:right;"></a>
								                        <a href="#" id="yuejia" title="下一月" class="ymNaviBtn rArrow" style="float:right;"></a>
								                        <!--	<a id="jintian" href="#" title="今天" class="btn" style="float:right; margin-top:-2px; font-size:12px; text-align:center;">今天</a>-->
								
								                    </TD>
								                </TR>
								                <TR class="calWeekTit" style="font-size:12px; height:20px;text-align:center;">
								                    <TD width="100" class="red">星期日</TD>
								                    <TD width="100">星期一</TD>
								                    <TD width="100">星期二</TD>
								                    <TD width="100">星期三</TD>
								                    <TD width="100">星期四</TD>
								                    <TD width="100">星期五</TD>
								                    <TD width="100" class="red">星期六</TD>
								                </TR>
								                <SCRIPT language="JavaScript">
								
								                    var gNum;
								                    for (var i = 0; i < 6; i++) {
								                        document.write('<tr align=center height="50" id="tt">');
								                        for (var j = 0; j < 7; j++) {
								                            gNum = i * 7 + j ;
								                            document.write('<td  id="GD' + gNum + '" on="0" ><font  id="SD' + gNum + '" style="font-size:22px;"  face="Arial"');
								                            if (j == 0)  document.write('color=red');
								                            if (j == 6)
								                                if (i % 2 == 1)  document.write('color=red');
								                                else  document.write('color=red');
								                            document.write('  TITLE="">  </font><br><font  id="LD' + gNum + '"  size=2  style="white-space:nowrap;overflow:hidden;cursor:default;">  </font></td>');
								                        }
								                        document.write('</tr>');
								                    }
								                </SCRIPT>
								                </tbody>
								            </TABLE>
								            <table class="biao" width="800px">
								            <tr>
								              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
								                  <tr align="center">
								                    <td><input type=button value="元旦节" class="button6"  onclick=dateSelection.goHoliday(0)></td>
								                    <td><input type=button value='春  节' class="button6"  onclick=dateSelection.goHoliday(1)></td>
								                    <td><input type=button value='清明节' class="button6"  onclick=dateSelection.goHoliday(3)></td>
								                    <td><input type=button value='五一节' class="button6"  onclick=dateSelection.goHoliday(4)></td>
								                    <td><input type=button value='端午节' class="button6"  onclick=dateSelection.goHoliday(5)></td>
								                    <td><input type=button value='中秋节' class="button6"  onclick=dateSelection.goHoliday(8)></td>
								                    <td><input type=button value="国庆节" class="button6"  onclick=dateSelection.goHoliday(9)></td>
								                  </tr>
								              </table></td>
								            </tr>
								          </table>
								      
                                  </div>
                                </div>
                                
                                <!--  <div class="am-form-group">
                                    <label for="days" class="am-u-sm-3 am-form-label">工作日</label>
                                    <div class="am-u-sm-9">
                                        <input type="text"    id="days" name="days" placeholder="输入月工作日" required >
                                    </div>
                                </div> -->
                               
                                
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary" id="workday-save-btn">保存</button>
                                    </div>
                                </div>
                              </fieldset>  
                              
                            </form>
                            
                        </div>
                    </div>
                </div>

            </div>
        <script type="text/javascript" src="${basePath}/statics/js/worktime/workday_add.js"></script>	     
</body>
<SCRIPT language="JavaScript">
	//提交
	function h_submit(){
		alert(hDays);
	}
  //重置
 function rebuild(){
	hDays=[];
	}          
 </SCRIPT>
</html>