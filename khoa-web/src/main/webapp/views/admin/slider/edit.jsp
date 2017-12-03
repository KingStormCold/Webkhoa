<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="formUrl" value="/admin-slider-edit.html"/>
<html>
<head>
    <title><fmt:message key="label.slider.edit" bundle="${lang}"/> </title>
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
                    <a href="#"><fmt:message key="label.slider.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.slider.edit" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form action="${formUrl}" method="post" enctype="multipart/form-data" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.slider.title" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.tieuDe" id="tieuDe" value="${item.pojo.tieuDe}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.slider.image" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadImage"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"></label>
                            <div class="col-sm-9">
                                <c:if test="${not empty item.pojo.hinhAnh}">
                                    <c:set var="hinhAnh" value="/repository/${item.pojo.hinhAnh}"/>
                                </c:if>
                                <img src="${hinhAnh}" id="viewImage" width="150px" height="150px">
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.slider.link" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.link" id="link" value="${item.pojo.link}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.slider.status" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <div class="md-form">
                                    <c:choose>
                                        <c:when test="${not empty item.pojo.sliderId}">
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
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}" />">
                            </div>
                        </div>
                        <c:if test="${not empty item.pojo.sliderId}">
                            <input type="hidden" name="pojo.sliderId" value="${item.pojo.sliderId}"/>
                        </c:if>
                    </form>

                    <%--Demo--%>
                    <%--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.context" bundle="${lang}"/></label>
                        <div class="col-sm-9">
                            <h1>Thais Thanh Tuan</h1>
                            <p class="textHide">51403035</p>
                            <p>0938380039</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button id="clickHide">Click here</button>
                        </div>
                    </div>--%>
                    <%--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.context" bundle="${lang}"/></label>
                        <div class="col-sm-9">
                            <input id="value" value="Thai Thanh Tuan"/>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.context" bundle="${lang}"/></label>
                        <div class="col-sm-9">
                            <p id="getValue">Nothing value</p>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button onclick="usingValAction()">Click here</button>
                        </div>
                    </div>--%>
                    <%--demo css nethod--%>
                    <%--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.context" bundle="${lang}"/></label>
                        <div class="col-sm-9">
                            <p id="cssmethod">Nothing value</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button onclick="demoCssMethod()">Click here</button>
                        </div>
                    </div>--%>
                    <%--demo change method--%>
                    <%--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.context" bundle="${lang}"/></label>
                        <div class="col-sm-9">
                            <input type="checkbox" id="sex" onchange="usingOnChange()"/>
                            <p id="sexChange"></p>
                        </div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<%--Demo--%>
<script>
    var SliderId = '';
    <c:if test="${not empty item.pojo.sliderId}">
    SliderId = ${item.pojo.sliderId};
    </c:if>
    $(document).ready(function () {
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
        $( "#link" ).rules( "add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>'
            }
        });
        if(SliderId == '') {
            $( "#uploadImage" ).rules( "add", {
                required: true,
                messages: {
                    required: '<fmt:message key="label.empty" bundle="${lang}"/>'
                }
            });
        }
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
    /*function hideParagarpWhenClickButton() {
     $("#clickHide").click(function () {
     $(".textHide").hide();
     })
     }
     function usingValAction() {
     var value = $('#value').val();
     $('#getValue').html(value);
     }
     function demoCssMethod() {
     $('#cssmethod').css("color","red");
     }
     //each function
     var arr = [12,5,43,66,7,8];
     $.each(arr, function (index,value) {
     console.log("Value: "+value +" - position:" +index);
     })
     function usingOnChange() {
     if($('#sex').prop('checked') == true) {
     $('#sexChange').html('<h1>Male</h1>');
     }
     else {
     $('#sexChange').html('<h1>Female</h1>');
     }
     }*/

</script>
</body>
</html>
