<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="formUrl" value="/admin-menu-edit.html"/>
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <title><%=session.getAttribute("abc").toString()%></title>
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
                    <a href="#"><%=session.getAttribute("abc").toString()%></a>
                </li>
                <c:if test="${not empty item.pojo.idBaiViet}">
                    <li class="active"><fmt:message key="label.update.home" bundle="${lang}"/></li>
                </c:if>
                <c:if test="${empty item.pojo.idBaiViet}">
                    <li class="active"><fmt:message key="label.insert.home" bundle="${lang}"/></li>
                </c:if>
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
                    <form action="${formUrl}" enctype="multipart/form-data" method="post" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"><fmt:message key="label.menu.title" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.tieuDe" id="tieuDe" value="${item.pojo.tieuDe}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"><fmt:message key="label.menu.tomtat" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.tomTat" id="tomTat" value="${item.pojo.tomTat}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"><fmt:message key="label.menu.hinhanh" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadImage"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"></label>
                            <div class="col-sm-9">
                                <c:if test="${not empty item.pojo.hinhAnh}">
                                    <c:set var="image" value="/repository/${item.pojo.hinhAnh}"/>
                                </c:if>
                                <img src="${image}" id="viewImage" width="150px" height="150px">
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"><fmt:message key="label.menu.status" bundle="${lang}"/></label>
                            <div class="col-sm-4">
                                <div class="md-form">
                                    <c:choose>
                                        <c:when test="${not empty item.pojo.idBaiViet}">
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
                            <div class="col-sm-1">
                                <div class="md-form checkbox">
                                    <c:choose>
                                        <c:when test="${not empty item.pojo.idBaiViet}">
                                            <c:if test="${item.pojo.ghim eq 1}">
                                                <input type="checkbox" id="a" name="pojo.ghim" checked value="1"/><p>Ghim</p>
                                            </c:if>
                                            <c:if test="${item.pojo.ghim eq 0}">
                                                <input type="checkbox" id="a" name="pojo.ghim" value="0"/>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" id="a" name="pojo.ghim" value="0" /><p>Ghim</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.noiDung}">
                                    <c:set var="noiDung" value="${item.pojo.noiDung}"/>
                                </c:if>
                                <textarea name="pojo.noiDung" cols="100" rows="10" id="NoiDungBaiViet">${noiDung}</textarea>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}" />">
                            </div>
                        </div>
                        <c:if test="${not empty item.pojo.idBaiViet}">
                            <input type="hidden" name="pojo.idBaiViet" value="${item.pojo.idBaiViet}"/>
                        </c:if>
                        <input type="hidden" name="danhMucBaiVietId" value="<%=session.getAttribute("menuCha").toString()%>">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var idBaiViet = '';
    <c:if test="${not empty item.pojo.idBaiViet}">
        idBaiViet = ${item.pojo.idBaiViet};
    </c:if>
    $(document).ready(function () {
        var editor = CKEDITOR.replace('NoiDungBaiViet');
        CKFinder.setupCKEditor( editor, '/ckfinder/' );
        validateData();
        $('#uploadImage').change(function () {
            readURL(this, "viewImage");
        });
    });
    function validateData() {
        $('#formEdit').validate({
            ignore:[],
            rules: [],
            messages: []
        });
        $( "#tieuDe" ).rules( "add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>'
            }
        });
        $( "#tomTat" ).rules( "add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>'
            }
        });
        if(idBaiViet == '') {
            $( "#uploadImage" ).rules( "add", {
                required: true,
                messages: {
                    required: '<fmt:message key="label.empty" bundle="${lang}"/>'
                }
            });
        }
        $( "#NoiDungBaiViet" ).rules( "add", {
            required: function () {
                CKEDITOR.instances.NoiDungBaiViet.updateElement();
            },
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>'
            }
        });
    }
    function readURL(input, imageId) {
        /*doc file va view file len khi them*/
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageId).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    };
    $('#a').on('change',function () {
        if(this.checked) {
            $(this).val('1');
        }
        else {
            $(this).val('0');
        }
    });
</script>
</body>
</html>