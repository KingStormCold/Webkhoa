<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglib.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="controller.admin.HomeController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="core.dto.DanhMucBaiVietDTO" %>
<%@include file="/common/taglib.jsp"%>
    <div class="out-menu">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle tip pull-left" data-toggle="collapse" data-target="#mainMenu" data-placement="right" title="menu">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <button id="showRightPush" class="sb-toggle-right navbar-toggle tip" type="button" data-placement="left" title="Sidebar">
                <span class="sr-only">Menu Sidebar</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="clearfix"></div>
        </div>
        <div class="collapse navbar-collapse" id="mainMenu" >
            <ul id="mainMenuId" class="menu sf-menu">
                <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item">
                    <a href="/home.html">Trang chủ</a>
                </li>
                <%
                    ArrayList<DanhMucBaiVietDTO> listMenu = (ArrayList<DanhMucBaiVietDTO>)HomeController.getMainMenu();
                    for(DanhMucBaiVietDTO item : listMenu) {
                %>
                <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item">
                    <c:url value="/web-danh-sach-danh-muc-bai-viet.html" var="danhMucUrl">
                        <c:param name="danhmucbaivietid" value="<%=item.getDanhMucBaiVietId().toString()%>"/>
                    </c:url>
                    <a href="${danhMucUrl}">
                        <%=item.getTenDanhMucBaiViet()%>
                    </a>
                    <ul>
                        <%
                            ArrayList<DanhMucBaiVietDTO> subMenu = (ArrayList<DanhMucBaiVietDTO>)HomeController.getSubMenu(item.getDanhMucBaiVietId());
                            for(DanhMucBaiVietDTO itemSub : subMenu) {
                        %>
                        <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item">
                            <c:url value="/web-danh-sach-danh-muc-bai-viet.html" var="danhMucUrl">
                                <c:param name="danhmucbaivietid" value="<%=itemSub.getDanhMucBaiVietId().toString()%>"/>
                            </c:url>
                            <a href="${danhMucUrl}">
                                <%=itemSub.getTenDanhMucBaiViet()%>
                            </a>
                            <ul>
                                <%
                                    ArrayList<DanhMucBaiVietDTO> subSubMenu = (ArrayList<DanhMucBaiVietDTO>)HomeController.getSubMenu(itemSub.getDanhMucBaiVietId());
                                    for(DanhMucBaiVietDTO itemSubSub : subSubMenu) {
                                %>
                                <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item">
                                    <c:url value="/web-danh-sach-danh-muc-bai-viet.html" var="danhMucUrl">
                                        <c:param name="danhmucbaivietid" value="<%=itemSubSub.getDanhMucBaiVietId().toString()%>"/>
                                    </c:url>
                                    <a href="${danhMucUrl}">
                                        <%=itemSubSub.getTenDanhMucBaiViet()%>
                                    </a>
                                </li>
                                <%}%>
                            </ul>
                        </li>
                        <%}%>
                    </ul>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
<div class="clearfix" style="background-color: #0066b2"></div>