function include(url) {
    "use strict";
    document.write('<script src="' + url + '" type="text/javascript"></script>');
}
var winDow = $(window);
winDow.load(function () {
    var mainDiv = $('.loadContent'),
        preloader = $('.preloader');

    preloader.fadeOut(2000, function () {
        mainDiv.delay(800).addClass('setAcive');
    });
});

$(document).ready(function () {
    $('.tooltip').tooltip();
    $("#tinyNav").tinyNav();
    $("#tinynav1").addClass("selectpicker");
    $('.selectpicker').selectpicker();   
    $('.tip').tooltip();
    if ($(window).width() >= 768) {
        $('#mainMenuId >li').click(function () {
            var link = $(this).find('a').attr('href');
            window.location.href = link;
        })
    }
    else {
        $('#mainMenuId >li').click(function () { })
    }
    $(window).load(function () {
        $('.topEvent').height($('#mainSlider').height() + 10);
    });


    $(window).resize(function () {
        $('.topEvent').height($('#mainSlider').height() + 10);
        if ($(window).width() >= 768) {
            $('#mainMenuId >li').click(function () {
                var link = $(this).find('a').attr('href');
                window.location.href = link;
            })
        }
        else {
            $('#mainMenuId >li').click(function () { })
        }
    });
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.backToTop').addClass("setRight");
        } else {
            $('.backToTop').removeClass("setRight");
        }
    });
    $('.backToTop').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 800);
        return false;
    });
    //$(window).load(function () {
    //    var mainDiv = $('body'),
    //		preloader = $('.preloader');

    //    preloader.fadeOut(400, function () {
    //        mainDiv.delay(400).addClass('active');
    //    });
    //});
});

// Vo Thanh Vinh added this code
$(document).ready(function () {
	var url = window.location.href;
	if(url.indexOf("/en") >= 0)
	{
		$('title').html( "Faculty of Information Technology | Ton Duc Thang University" );
		var markup = document.documentElement.innerHTML;
		if(markup.indexOf("/images/icon/eng.png") >= 0)
		{
			$('.language').html('<li style="width: 35px"><span class="vi-VN" dir="ltr"><a href="/Home" title="vi"><img src="/images/icon/vi.png" style="margin-top: -8px" /></a></span></li><li style="width: 60px;"><span><a href="/en/contactus/" title="en" style="margin-top: -5px;">Feedback</a></span></li>');
		}
		

		$('.footer').find('span:eq(0)').html('Office: Room C004, Nguyen Huu Tho Street, Tan Phong Ward, District 7, Ho Chi Minh City');
		$('.footer').find('span:eq(1)').html('Telephone: (08) 37755046 - (08) 37755046');

	}
	
	$(".news").remove();
	
	$("a[href^='/tuyen-sinh'],a[href^='/en/tuyen-sinh']").each(function(){
		$(this).attr('href','http://tuyensinh.tdt.edu.vn');
		$(this).attr('target','_blank');
	});
});
