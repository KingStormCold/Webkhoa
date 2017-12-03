<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<div class="bg-head">
    <div class="col-md-2 col-sm-3 col-xs-6 col-xs-offset-3 col-sm-offset-0" id="logo">
        <a href="/Home/">

            <img src='/template/web/Upload/Img/logo.png' class="img-responsive" alt="logo" style="" />


        </a>
        <div class="clearfix"></div>
    </div>
    <div class="col-sm-5 col-xs-12 TDTName">

        <img src='/template/web/Upload/Img/tdtName.png' class="img-responsive" alt="tdt" />



        <div class="clearfix"></div>
    </div>
    <div class="col-sm-3 hidden-xs pull-right">
        <ul class="list-inline language pull-right">
            <li style="width: 35px">
                <span class="lang-active vi-VN" dir="ltr"><a href="/en/Home" title='en'>
                    <img src="/template/web/images/icon/eng.png" style="margin-top: -8px" />
                </a></span>
            </li>
            <c:if test="${not empty login_name}">
                <li style="width: 100px; margin-left: 10px"><span>Welcome:${login_name}</span></li>
                    <c:url var="logoutUrl" value="logout.html">
                        <c:param name="action" value="logout"/>
                    </c:url>
                <li>
                    <a href="${logoutUrl}">Logout</a>
                </li>
            </c:if>
            <c:if test="${empty login_name}">
                    <c:url var="loginUrl" value="/login.html">
                        <c:param name="action" value="login"/>
                    </c:url>
                <li style="width: 100px; margin-left: 10px">
                    <a href="${loginUrl}"><fmt:message key="label.login" bundle="${lang}"/> </a>
                </li>
            </c:if>
        </ul>
        <div class="clearfix"></div>

        <form id="SearchForm1" class="SearchForm" action="/search/">
            <input type="text" class="txtInput" name="id" id="id" placeholder="nhập nội dung tìm kiếm..." value="" />
            <button type="submit" id="searchsubmit" class="btn"><i class="fa fa-search"></i></button>
        </form>


        <div class="clearfix"></div>
    </div>
    <div class="clearfix"></div>
</div>
</head>
<div class="clearfix"></div>