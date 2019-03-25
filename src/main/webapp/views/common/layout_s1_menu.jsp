<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="tpl-left-nav tpl-left-nav-hover">
            <div class="tpl-left-nav-title">
                             功能菜单
            </div>
            <div class="tpl-left-nav-list">
                <ul class="tpl-left-nav-menu">
                
                  <li class="tpl-left-nav-item">
                       
                        <a href="${basePath}/index" class="nav-link">
                            <i class="am-icon-home"></i>
                            <span>首页</span>
                        </a>
                    </li>
                
                <c:forEach items="${sessionScope.menus}" var="item" >
                
                
	                <c:if test="${item.funPid == 0  && item.id != 8}">
	              
	                      <li class="tpl-left-nav-item">
	                      
	                       <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
	                            <i class="am-icon-${item.icon}"></i>
	                            <span> ${item.funName}</span>
	                           <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
	                        </a>
	                        
	                        <ul class="tpl-left-nav-sub-menu">
		                       	<c:forEach items="${sessionScope.menus}" var="son" >
		                       	
		                       		<c:if test="${son.funPid == item.id}">
		                       		
		                       		  	<li class="sidebar-nav-link">
			                                <a href="${basePath}${son.funUrl}" <c:if test="${fn:contains(pageUrl, son.funUrl)}" >class="active"</c:if>>
			                                    <i class="am-icon-angle-right"></i>
			                                    <span>${son.funName}</span>
			                                    <i class="tpl-left-nav-content tpl-badge-success">
			             						</i>
			 								</a>
			                            </li>	                            
		                       		</c:if>
		                     
	                            
		                       	</c:forEach>
	                       	</ul>
	                      </li>
	                  </c:if>
                
                </c:forEach>
                    
                   
                </ul>
            </div>
        </div>
        
        
 <script>$('.active').parent().parent().css('display', 'block').prev().find('i').eq(1).addClass('tpl-left-nav-more-ico-rotate');</script>