<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.admin.HomeController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="core.dto.DanhMucBaiVietDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/admin-danhmucbaiviet-list.html" var="danhMucListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/admin-video-list.html" var="videoListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/admin-slider-list.html" var="sliderListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/admin-home.html" var="homeUrl">
</c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="sidebar" class="sidebar responsive ace-save-state">
    <script type="text/javascript">
        try{ace.settings.loadState('sidebar')}catch(e){}
    </script>
    <div class="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>
        <div class="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>

    <ul class="nav nav-list">
        <li class="">
            <a href="${danhMucListUrl}"><fmt:message key="label.danhmuc.home" bundle="${lang}"/></a>
        </li>
        <%
            ArrayList<DanhMucBaiVietDTO> listMenu = (ArrayList<DanhMucBaiVietDTO>)HomeController.getMainMenu();
            for(DanhMucBaiVietDTO item : listMenu) {
            %>
                <li class="">
                    <a href="/admin-menu-list.html?urlType=url_list<%=item.getDanhMucBaiVietId()%>" style="font-size: 15px">
                            <%=item.getTenDanhMucBaiViet()%>
                    </a>
                    <ul class="">
                    <%
                        ArrayList<DanhMucBaiVietDTO> subMenu = (ArrayList<DanhMucBaiVietDTO>)HomeController.getSubMenu(item.getDanhMucBaiVietId());
                        for(DanhMucBaiVietDTO itemSub : subMenu) {
                        %>
                        <li class="">
                            <a href="/admin-menu-list.html?urlType=url_list<%=itemSub.getDanhMucBaiVietId()%>" style="font-size: 15px">
                                <%=itemSub.getTenDanhMucBaiViet()%><br/>
                            </a>
                            <ul>
                                <%
                                    ArrayList<DanhMucBaiVietDTO> subSubMenu = (ArrayList<DanhMucBaiVietDTO>)HomeController.getSubMenu(itemSub.getDanhMucBaiVietId());
                                    for(DanhMucBaiVietDTO itemSubSub : subSubMenu) {
                                %>
                                <li>
                                    <a href="/admin-menu-list.html?urlType=url_list<%=itemSubSub.getDanhMucBaiVietId()%>" style="font-size: 15px">
                                        <%=itemSubSub.getTenDanhMucBaiViet()%><br/>
                                    </a>
                                </li>
                                <%}%>
                            </ul>
                        </li>
                        <%}%>
                    </ul>
                </li>
            <%
            }
            %>
        <%--<c:forEach items="${mainMenu}" var="list">
            <li class="">
                <a href="#">${list.tenDanhMucBaiViet}</a>
            </li>
            <c:forEach items="${subMenu}" var="item">
                <c:if test="${list.danhMucBaiVietId eq item.chaDanhMucBaiViet}">
                    <b class="arrow"></b>
                    <ul class="submenu">
                        <li class="">
                            <a href="${userListUrl}">
                                <i class="menu-icon fa fa-caret-right"></i>
                                    ${item.tenDanhMucBaiViet}<br/>
                            </a>
                            <b class="arrow"></b>
                        </li>
                    </ul>
                </c:if>

            </c:forEach>
        </c:forEach>--%>


    <%--<c:forEach items="${list}" var="list">
            <c:forEach items="${listMenu}" var="listMenu">
                <c:if test="${listMenu.chaDanhMucBaiViet eq list.danhMucBaiVietId}">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-list"></i>
                            <span class="menu-text"></span>
                            ${list.tenDanhMucBaiViet}<br/>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a href="${userListUrl}">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    ${listMenu.tenDanhMucBaiViet}<br/>
                                </a>
                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${listMenu.chaDanhMucBaiViet ne list.danhMucBaiVietId}">
                    <li class="">
                        <a href="${danhMucListUrl}">
                            ${list.tenDanhMucBaiViet}<br/>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:forEach>--%>

        <li class="">
            <a href="${videoListUrl}"><fmt:message key="label.video.home" bundle="${lang}"/></a>
        </li>
        <li class="">
            <a href="${sliderListUrl}"><fmt:message key="label.slider.home" bundle="${lang}"/></a>
        </li>

    </ul>
    <div class="sidebar-toggle sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>