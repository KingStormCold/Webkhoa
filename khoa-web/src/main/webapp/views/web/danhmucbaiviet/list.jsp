<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:url var="urlList" value="/web-danh-sach-danh-muc-bai-viet.html"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.cntt" bundle="${lang}"/></title>
</head>
<body>
<section id="mainContent">
    <div class=" bg-content">
        <div class="row">
            <form action="${urlList}" method="get" id="formUrl">
                <div id="content" class="col-sm-8" style="">
                    <div class="postContent row" style="min-height:710px">
                        <c:forEach var="item" items="${items.listResult}">
                            <div class="col-sm-12">
                                <ul class="firstNews">
                                    <li>
                                        <div class="col-sm-2 pic no-padding-left">
                                            <c:url value="/noi-dung-bai-viet.html" var="detailUrl">
                                                <c:param name="baivietid" value="${item.idBaiViet}"/>
                                            </c:url>
                                            <a href="${detailUrl}">
                                                <img src="<c:url value="/repository/${item.hinhAnh}"/>" style="width: 100px;height: 100px" />
                                                <span><i class="fa fa-link"></i></span>
                                            </a>
                                        </div>
                                        <div class="col-sm-8 description no-padding-left">
                                            <h2><span><a href="${detailUrl}" style="font-size: 20px">${item.tieuDe}</a></span> </h2>
                                            <div class="date"><span>${item.createdDate}</span> </div>
                                            <p><span id="ContentPlaceHolder1_Lst_ND_Lbl_ND_0"></span> </p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </li>
                                </ul>
                            </div>
                        </c:forEach>
                    </div>
                    <ul id="pagination-demo" class="pagination-sm"></ul>
                </div>
                <input type="hidden" id="danhmucbaivietid" name="danhmucbaivietid"/>
                <input type="hidden" id="page" name="page"/>

            </form>
            <div id="sidebar" class="col-sm-4">
                <div class="mod hidden-xs">
                    <div class="modHead">
                        <h2>Tin tức mới</h2>
                    </div>
                    <div class="modContent">
                        <div class="tab-content">
                            <div class="tab-pane active fade in" id="Tab1Id">
                                <ul class="news-list">


                                    <li>
                                        <h2><i class="fa fa-angle-double-right"></i><a href="http://it.tdt.edu.vn/cong-ty-tnhh-hao-phuong-tuyen-dung-(28-11-2017)">Công ty TNHH Hạo Phương tuyển dụng (28/11/2017)</a>
                                            <span class="clearfix"></span>28/11/2017</h2>
                                    </li>

                                    <li>
                                        <h2><i class="fa fa-angle-double-right"></i><a href="http://it.tdt.edu.vn/mot-so-hinh-anh-cua-3-doi-thi-tdt-it-tai-cuoc-hackathon-binh-duong-2017-(http-hackathon-binhduongsmartcity-vn-)">Một số hình ảnh của 3 đội thi TDT IT tại cuộc Hackathon Bình Dượng 2017 (http://hackathon.binhduongsmartcity.vn/)</a>
                                            <span class="clearfix"></span>25/11/2017</h2>
                                    </li>

                                    <li>
                                        <h2><i class="fa fa-angle-double-right"></i><a href="http://it.tdt.edu.vn/cong-ty-fujinet-tuyen-dung-thuc-tap-(22-11-2017)">Công ty Fujinet tuyển dụng thực tập (22/11/2017)</a>
                                            <span class="clearfix"></span>22/11/2017</h2>
                                    </li>

                                    <li>
                                        <h2><i class="fa fa-angle-double-right"></i><a href="http://it.tdt.edu.vn/cong-ty-cmc-sisg-tuyen-dung">Công ty CMC SISG tuyển dụng</a>
                                            <span class="clearfix"></span>16/11/2017</h2>
                                    </li>

                                    <li>
                                        <h2><i class="fa fa-angle-double-right"></i><a href="http://it.tdt.edu.vn/gameloft-tuyen-dung-2-vi-tri-thuc-tap-c-c">GameLoft tuyển dụng 2 vị trí thực tập C++/C# (06/11/2017)</a>
                                            <span class="clearfix"></span>06/11/2017</h2>
                                    </li>



                                </ul>

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="mod hidden-xs">
                    <div class="modHead">
                        <h2>Liên kết web</h2>
                    </div>
                    <div class="modContent">
                        <div class="panel-group" id="accordion">



                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="http://it.tdt.edu.vn/thong-bao-hoc-vu#Div3"><i class="fa fa-plus-circle"></i>DÀNH CHO SINH VIÊN
                                        </a>

                                    </h4>
                                </div>
                                <div id="Div3" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="list-item">


                                            <li><a href="https://student.tdt.edu.vn/" target="_blank">Hệ thống thông tin sinh viên</a> </li>

                                            <li><a href="https://mail.tdt.edu.vn/" target="_blank">Hệ thống mail</a> </li>

                                            <li><a href="http://dkmh.tdt.edu.vn/" target="_blank">Đăng ký môn học</a> </li>



                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="http://it.tdt.edu.vn/thong-bao-hoc-vu#Div4"><i class="fa fa-plus-circle"></i>LIÊN KẾT WEB
                                        </a>

                                    </h4>
                                </div>
                                <div id="Div4" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="list-item">


                                            <li><a href="http://www.tdt.edu.vn/" target="_blank">Đại học Tôn Đức Thắng</a> </li>

                                            <li><a href="http://pdt.tdt.edu.vn/" target="_blank">Weblsite phòng đào tạo</a> </li>

                                            <li><a href="http://ptc.tdt.edu.vn/" target="_blank">Website phòng tài chính</a> </li>



                                        </ul>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
</section>
</div><!-- /.main-content -->
<script type="text/javascript">
    var totalPages = ${items.totalPage};
    var startPage = ${items.page};
    var visiblePages = ${items.maxPageItems};
    $(document).ready(function () {
        $('#danhmucbaivietid').val(${idDanhMuc})
    });
    $(function () {
        var obj = $('#pagination-demo').twbsPagination({
            totalPages: totalPages,
            startPage: startPage,
            visiblePages: visiblePages,
            onPageClick: function (event, page) {
                if(page != startPage) {
                    $('#page').val(page);
                    $('#formUrl').submit();
                }
            }
        });
    });
</script>
</body>
</html>