<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.cntt" bundle="${lang}"/></title>
</head>
<body>
    <section id="slideshow">
        <div class="row" style="margin-top: 10px; padding-bottom: 10px;background-color: #0066b2">
            <div class="col-sm-8">
                <div class="responsive-slider" id="mainSlider" data-spy="responsive-slider" data-autoplay="true">
                    <div class="slides" data-group="slides" >
                        <ul>
                            <li>
                                <div class="slide-body" data-group="slide">
                                    <img src="/uploadfile/excel/hinh-cun-yeu-7.jpg">
                                    <div class="caption header" data-animate="slideAppearRightToLeft" data-delay="500" data-length="300">
                                        <div class="caption sub" data-animate="slideAppearLeftToRight" data-delay="800" data-length="300">Thông tin cuộc thi RoboGame 2017 - /robogame-2017</div>
                                    </div>
                                </div>
                            </li>


                        </ul>
                    </div>
                    <a class="slider-control left" href="#" data-jump="prev"><i class="fa fa-angle-left"></i></a>
                    <a class="slider-control right" href="#" data-jump="next"><i class="fa fa-angle-right"></i></a>
                    <div class="pages">
                        <a class="page" href="#" data-jump-to="1">1</a>
                        <a class="page" href="#" data-jump-to="2">2</a>
                        <a class="page" href="#" data-jump-to="3">3</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 hidden-xs">
                <div class="topEvent" style="height: 480px !important">
                    <div class="inner">
                        <div class="videoWrapper">
                            <!-- Copy & Pasted from YouTube -->
                            <iframe width="560" height="315" src="/uploadfile/excel/Avicii%20-%20The%20Nights.mp4" frameborder="0" allowfullscreen></iframe>
                        </div>
                        <div class="videoWrapper">
                            <!-- Copy & Pasted from YouTube -->
                            <iframe width="560" height="315" src="/uploadfile/excel/Avicii%20-%20The%20Nights.mp4" frameborder="0" allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </section>
    <section id="mainContent">
        <div class=" bg-content">
            <div class="row">
                <div id="content" class="col-sm-8" style="">
                    <c:forEach var="item" items="${itemsDanhMuc.listResult}">
                        <c:if test="${item.status eq 1}">
                            <div class="post">
                                <div class="postHeader">
                                    <h2><a href="/ban-tin-khoa">
                                        <span id="ContentPlaceHolder1_Lst_cat_Lbl_ND_0">${item.tenDanhMucBaiViet}</span>
                                    </a></h2>
                                </div>
                                <c:forEach var="itemSub" items="${itemsBaiViet.listResult}" >
                                    <c:if test="${itemSub.danhMucs.danhMucBaiVietId eq item.danhMucBaiVietId}">
                                        <c:if test="${itemSub.ghim eq 1}">
                                            <div class="postContent row">
                                                <div class="col-sm-2">
                                                    <ul class="firstNews">
                                                        <li>
                                                            <img src="<c:url value="/repository/${itemSub.hinhAnh}"/>" style="width: 100px;height: 100px" />
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="col-sm-4 others">
                                                    <ul class="news-list">
                                                            ${itemSub.tieuDe}
                                                                <div class="date"><span>${itemSub.createdDate}</span> </div>
                                                    </ul>
                                                </div>
                                            </div>
                                        </c:if>

                                    </c:if>
                                </c:forEach>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
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
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="mod">
                        <a href="/su-kien-sap-toi">
                            <img src='/template/web/images/SKST.png' class="img-responsive img-thumbnail" alt="pic" />
                        </a>
                        <div class="clearfix"></div>
                    </div>
                    <div class="mod">
                        <a href="/microsoft-imagine">
                            <img src='/template/web/images/DREAMSPARK.png' class="img-responsive img-thumbnail" alt="pic" />
                        </a>
                        <div class="clearfix"></div>
                    </div>
                    <div class="mod">
                        <a href="/tuyen-sinh">
                            <img src='/template/web/Upload/Img/banner-tuyensinh2.jpg' class="img-responsive img-thumbnail" alt="pic" />
                        </a>
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
                                            <a data-toggle="collapse" data-parent="#accordion" href="#Div3"><i class="fa fa-plus-circle"></i>DÀNH CHO SINH VIÊN
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
                                            <a data-toggle="collapse" data-parent="#accordion" href="#Div4"><i class="fa fa-plus-circle"></i>LIÊN KẾT WEB
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
</body>
</html>