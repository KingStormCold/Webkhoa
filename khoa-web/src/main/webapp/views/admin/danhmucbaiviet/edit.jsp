<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="formUrl" value="/admin-danhmucbaiviet-edit.html"/>
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <title><fmt:message key="label.danhmuc.update" bundle="${lang}"/> </title>
    <style>
        .error{
            color: red;
        }
    </style>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.danhmuc.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.danhmuc.update" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row" >
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form action="${formUrl}" method="post" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"><fmt:message key="label.danhmuc.name" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.tenDanhMucBaiViet" id="tenDanhMucBaiViet" value="${item.pojo.tenDanhMucBaiViet}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"><fmt:message key="label.danhmuc.cha" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <div class="md-form">
                                    <c:choose>
                                        <c:when test="${not empty item.pojo.danhMucBaiVietId}">
                                            <select id="danhmnuc" name="pojo.chaDanhMucBaiViet" >\
                                                <option value="0">Không</option>
                                                <c:forEach items="${item.danhmucs}" var="listMenu">
                                                    <option value="${listMenu.danhMucBaiVietId}">${listMenu.tenDanhMucBaiViet}</option>
                                                </c:forEach>

                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select id="danhmnuc" name="pojo.chaDanhMucBaiViet" style="width: 180px">
                                                <c:forEach items="${list}" var="listMenu">
                                                  <option value="${listMenu.danhMucBaiVietId}">${listMenu.tenDanhMucBaiViet}</option>
                                                </c:forEach>
                                                <option value="0">Không</option>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"><fmt:message key="label.danhmuc.cha" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <div class="md-form">
                                    <c:choose>
                                        <c:when test="${not empty item.pojo.danhMucBaiVietId}">
                                            <select id="video" name="pojo.status" >
                                                <c:if test="${item.pojo.status eq 1}">
                                                    <option value="1">Có</option>
                                                    <option value="0">Không</option>
                                                </c:if>
                                                <c:if test="${item.pojo.status eq 0}">
                                                    <option value="0">Không</option>
                                                    <option value="1">Có</option>
                                                </c:if>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select id="video" name="pojo.status">
                                                <option value="1">Có</option>
                                                <option value="0">Không</option>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}" />">
                            </div>
                        </div>
                        <c:if test="${not empty item.pojo.danhMucBaiVietId}">
                            <input type="hidden" name="pojo.danhMucBaiVietId" value="${item.pojo.danhMucBaiVietId}"/>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var IdDanhMuc = '';
    <c:if test="${not empty item.pojo.danhMucBaiVietId}">
    IdDanhMuc = ${item.pojo.danhMucBaiVietId};
    </c:if>
    $(document).ready(function () {
        validateData();
    });
    function validateData() {
        $('#formEdit').validate({
            ignore:[],
            rules: [],
            messages: []
        });
        $( "#tenDanhMucBaiViet" ).rules( "add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>'
            }
        });
        $( "#url" ).rules( "add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>'
            }
        });
    }
</script>
</body>
</html>