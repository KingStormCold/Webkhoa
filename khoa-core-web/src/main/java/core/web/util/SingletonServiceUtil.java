package core.web.util;

import core.service.impl.*;

/**
 * Created by TuanKul on 10/25/2017.
 */
public class SingletonServiceUtil {
    private static UserServiceImpl userServiceImpl = null;
    private static RoleServiceImpl roleServiceImpl = null;
    private static DanhMucBaiVietServiceImpl danhMucBaiVietServiceImpl = null;
    private static VideoServiceImpl videoServiceImpl = null;
    private static SliderServiceImpl sliderServiceImpl = null;
    private static BaiVIetServiceImpl baiVIetServiceImpl = null;

    public static UserServiceImpl getUserServiceInstance () {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }
    public static RoleServiceImpl getRoleServiceInstance () {
        if (roleServiceImpl == null) {
            roleServiceImpl = new RoleServiceImpl();
        }
        return roleServiceImpl;
    }
    public static DanhMucBaiVietServiceImpl getDanhMucBaiVietServiceInstance () {
        if (danhMucBaiVietServiceImpl == null) {
            danhMucBaiVietServiceImpl = new DanhMucBaiVietServiceImpl();
        }
        return danhMucBaiVietServiceImpl;
    }
    public static VideoServiceImpl getVideoServiceInstance () {
        if (videoServiceImpl == null) {
            videoServiceImpl = new VideoServiceImpl();
        }
        return videoServiceImpl;
    }
    public static SliderServiceImpl getSliderServiceInstance () {
        if (sliderServiceImpl == null) {
            sliderServiceImpl = new SliderServiceImpl();
        }
        return sliderServiceImpl;
    }
    public static BaiVIetServiceImpl getBaiVIetServiceInstance () {
        if (baiVIetServiceImpl == null) {
            baiVIetServiceImpl = new BaiVIetServiceImpl();
        }
        return baiVIetServiceImpl;
    }
}
