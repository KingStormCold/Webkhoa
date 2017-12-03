<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="requestUrl" value="/admin-slider-list.html"/>
<c:url value="/admin-slider-edit.html" var="sliderEditUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url value="/admin-slider-list.html" var="formUrl"/>
<html>
<head>
    <title><fmt:message key="label.slider.list" bundle="${lang}"/> </title>
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
                <li class="active"><fmt:message key="label.slider.list" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
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
                <form action="${formUrl}" method="get" id="formUrl">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="widget-box table-filter">
                                <div class="widget-header">
                                    <h4 class="widget-title"><fmt:message key="label.search" bundle="${lang}"/></h4>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><fmt:message key="label.slider.title" bundle="${lang}"/></label>
                                                <div class="col-sm-8">
                                                    <div class="fg-line">
                                                        <input type="text" value="${items.pojo.tieuDe}" class="form-control input-sm" name="pojo.tieuDe"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"></label>
                                                <div class="col-sm-8">
                                                    <button id="btnSearch"  class="btn btn-sm btn-success">
                                                        <fmt:message key="label.search" bundle="${lang}"/>
                                                        <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <c:url var="addUrl" value="/admin-slider-edit.html">
                                            <c:param name="urlType" value="url_edit"/>
                                        </c:url>
                                        <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" href="${addUrl}">
                                                    <span>
                                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                    </span>
                                        </a>
                                        <button type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" id="deleteAll" disabled onclick="warningBeforeDelete()"
                                                data-toggle="tooltip" title="<fmt:message key='label.delete.all' bundle='${lang}'/>">
                                                     <span>
                                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                                    </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <fmt:bundle basename="ApplicationResources">
                            <display:table id="tableList" name="items.listResult" partialList="true" size="${items.totalItems}"
                                           pagesize="${items.maxPageItems}" export="true" sort="external" requestURI="${requestUrl}"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                <display:column title="<fieldset class='form-group'>
                                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                                            </fieldset>" class="center select-cell" headerClass="center select-cell">
                                    <fieldset>
                                        <input type="checkbox" name="checkList" id="checkbox_${tableList.sliderId}" value="${tableList.sliderId}" class="check-box-element"/>
                                    </fieldset>
                                </display:column>
                                <display:column property="tieuDe" titleKey="label.slider.title" sortable="true" sortName="tieuDe"/>
                                <display:column property="hinhAnh" titleKey="label.slider.image" sortable="true" sortName="hinhAnh"/>
                                <display:column property="link" titleKey="label.slider.link" sortable="true" sortName="link"/>
                                <display:column property="status" titleKey="label.slider.status" sortable="true" sortName="status"/>
                                <display:column headerClass="col-actions" titleKey="label.action">
                                    <c:url var="editUrl" value="/admin-slider-edit.html">
                                        <c:param name="urlType" value="url_edit"/>
                                        <c:param name="pojo.sliderId" value="${tableList.sliderId}"/>
                                    </c:url>
                                    <a class="btn btn-sm btn-primary btn-edit" href="${editUrl}" data-toggle="tooltip" title="<fmt:message key='label.update' bundle='${lang}'/>"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                </display:column>
                            </display:table>
                        </fmt:bundle>
                    </div>
                    <input type="hidden" name="urlType" id="urlType"/>
                    <input type="hidden" name="crudaction" id="crudaction"/>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    $(document).ready(function () {
        $('#btnSearch').click(function () {
            $('#urlType').val('url_list');
            $('#formUrl').submit();
        });
    });
    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            $('#urlType').val('url_list');
            $('#crudaction').val('redirect_delete');
            $('#formUrl').submit();
        });
    }
</script>
</body>
</html>
