<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</title>
    <%--<script type="text/javascript" src="/template/web/js/jquery-1.11.1.min.js"></script>--%>
    <script src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js' />"></script>
    <script type="text/javascript" src="/template/web/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/template/web/js/responsive-slider.js"></script>
    <script type="text/javascript" src="/template/web/js/bootstrap-select.min.js"></script>
    <script src="/template/web/js/tinynav.min.js"></script>
    <script type="text/javascript" src="/template/web/js/mainScript.js"></script>
    <script src="/template/web/js/modernizr.custom.js"></script>
    <script src="/template/web/js/classie.js"></script>
    <script src="/template/web/js/jquery.modern-ticker.min.js"></script>
    <link href="/template/web/test/css/responsive-slider.css" rel="stylesheet" />
    <link href="/template/web/test/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="/template/web/test/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href="/template/web/test/css/bootstrap.css" rel="stylesheet" />
    <link href="/template/web/test/css/include.css" rel="stylesheet" />
    <link href="/template/web/test/css/component.css" rel="stylesheet" />
    <link href="/template/web/test/css/modern-ticker.css" rel="stylesheet" />
    <link rel="stylesheet" href="/template/web/css/style.css" type="text/css" media="all" />
    <link rel="stylesheet" href="/template/web/css/superfish.css" type="text/css" media="all" />
    <link href="/template/web/Font-Awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="/template/web/Font-Awesome/css/font-awesome.min.css" rel="stylesheet" />
    <script src="<c:url value="/template/web/jquery.twbsPagination.js"/>" type="text/javascript"></script>
    <style>
        .navbar-collapse:after {
            display: none;
        }
    </style>

    <style>
        .videoWrapper {
            padding-bottom:55.2%;
        }
    </style>

    <meta name="description" content="Bản tin khoa" />
</head>
<body class="cbp-spmenu-push">
<div class="container bgContainer" style="padding-left: 0px; padding-right: 0px;">
    <%@ include file="/common/web/header.jsp"%>

    <nav class="primary-menu">
    <%@ include file="/common/web/menu.jsp"%>
    </nav>
    <dec:body/>

    <%@ include file="/common/web/footer.jsp"%>
</div>
<!-- =====sidebar mobile========== -->

<!-- ======end sidebar======== -->
<!-- include jquery -->
</body>
</html>

