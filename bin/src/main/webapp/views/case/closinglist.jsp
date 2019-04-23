<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

	<head>
		<title>车险结案报表</title>
	</head>

	<body>
		<div class="tpl-content-page-title">
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span>车险结案报表
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g">
						<div class="am-u-sm-12">
							<form id="search-form-usercase">
								<!-- 查询条件Start -->
								<div class="am-u-sm-6 am-u-lg-3 am-custom-top am-padding-left-0">
									<div class="am-input-group am-input-group-primary am-input-group-sm">
										<span class="am-input-group-label">机构</span>
										<select name="groupId" id="groupId">
											<option value=0>--请选择--</option>
											<c:forEach items="${groupList}" var="group">
												<option value="${group.id}">
													          ${group.groupName}
												</option>
											</c:forEach>
										</select>
										<%--<span class="am-input-group-label">查勘人</span>
										<select name="userId" id="userId">
											<option value=0>全部</option>
										</select> --%>
										<span class="am-input-group-label"> 年</span>
										<select name="year" id="year">
											<option>--请选择--</option>
											<c:forEach items="${listYear}" var="year">
												<option value="${year}">
													          ${year}
												</option>
											</c:forEach>
										</select>
										<span class="am-input-group-label">月</span>
										<select name="month" id="month">
											<option value="0">全部</option>
												<option value="01">1</option>
												<option value="02">2</option>
												<option value="03">3</option>
												<option value="04">4</option>
												<option value="05">5</option>
												<option value="06">6</option>
												<option value="07">7</option>
												<option value="08">8</option>
												<option value="09">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												</select>
											<%-- <span class="am-input-group-label">案件类型</span>
										    <select name="closingListCaseType" id="closingListCaseType">
										    <option value="0">全部</option>
											<c:forEach items="${closingListCaseType}" var="caseItems">
												<option value="${caseItems.closingListCaseType}">
													<c:choose>
													       <c:when test="${caseItems.closingListCaseType=='L'}">
													             自赔
													       </c:when>
													       <c:when test="${caseItems.closingListCaseType=='T'}">
													             省内
													       </c:when>
													       <c:otherwise>
													            省间
													       </c:otherwise>
													 </c:choose>
												</option>
											</c:forEach>
									  </select> --%>
									  	<span class="am-input-group-label">金额范围</span>
										<select name="moneyType" id="moneyType">
											<option value=0>--请选择--</option>
											<option vaule=10000>10000</option>
											<option value=5000>5000</option>
									  </select>
									</div>
								<div>
									<input type="hidden" id="date" value="${date}">
									<div class="am-u-sm-12 am-custom-top am-padding-left-0">
										<div class="am-btn-group ">
											<button type="button" id="btn-search-pro" class="am-btn am-btn-primary am-btn-sm am-radius"><i class="am-icon-search"></i>查询</button>
											<!--<button type="button"  id="btn-reset-pro"  class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-repeat"></i> 重置</button>-->
											<button type="button" id="btn-excel-pro" class="am-btn am-btn-primary am-btn-sm am-radius am-custom-left-10"><i class="am-icon-share"></i>导出</button>
										</div>

									</div>
									
									<!-- 按钮End -->
							</form>
							</div>

						</div>
						<div class="am-g">
							<div class="am-u-sm-12">

								<table width="100%" border="0" id="groupcaseTable" class="am-table am-table-striped am-table-hover table-main">
									<thead id="countId1">
										<tr>
											<th width="100%" style="font-size: 25px; text-align:center;" colspan="13">${dateMonth}车险结案报表</th>
										</tr>
										<tr>
											<td rowspan="3" style="vertical-align:middle" >案件总数(件)</td>
											<td rowspan="3" style="vertical-align:middle">总金额(元)</td>
											<td rowspan="3" style="vertical-align:middle">当期(天)</td>
											<td colspan="2" style="text-align:center;">年报年结</td>
											<td colspan="2" style="text-align:center;">月报月结</td>
											<td colspan="2" style="text-align:center;">年报月结</td>
										</tr>
										<tr>
											<td style="text-align:center;">天数(天)</td>
											<td style="text-align:center;">同比(天)</td>
											<td style="text-align:center;">天数(天)</td>
											<td style="text-align:center;">同比(天)</td>
											<td style="text-align:center;">天数(天)</td>
											<td style="text-align:center;">同比(天)</td>
										</tr>
										<table width="100%" border="0" id="groupcaseTable1" class="am-table am-table-striped am-table-hover table-main" style="table-layout: fixed;">
										</table>
									</thead>
							</div>

						</div>
					</div>
					<div class="tpl-alert"></div>
				</div>
				<script type="text/javascript" src="${basePath}/statics/js/case/closinglist.js"></script>
				<%-- <script type="text/javascript" src="${basePath}/statics/js/case/userinfo.js"></script>
      <script type="text/javascript" src="${basePath}/statics/js/case/userinfono.js"></script> --%>
	</body>

</html>