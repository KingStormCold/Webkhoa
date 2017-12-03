package core.service.utils;

import core.dao.impl.*;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class SingletonDaoUtil {
    private static UserDaoImpl userDaoImpl = null;
    private static RoleDaoImpl roleDaoImpl = null;
    private static DanhMucBaiVietDaoImpl danhMucBaiVietDaoImpl = null;
    private static VideoDaoImpl videoDaoImpl = null;
    private static SliderDaoImpl sliderDaoImpl = null;
    private static BaiVietDaoImpl baiVietDaoImpl = null;

    public static UserDaoImpl getUserDaoInstance () {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }
    public static RoleDaoImpl getRoleDaoInstance () {
        if (roleDaoImpl == null) {
            roleDaoImpl = new RoleDaoImpl();
        }
        return roleDaoImpl;
    }
    public static DanhMucBaiVietDaoImpl getDanhMucBaiVietDaoInstance () {
        if (danhMucBaiVietDaoImpl == null) {
            danhMucBaiVietDaoImpl = new DanhMucBaiVietDaoImpl();
        }
        return danhMucBaiVietDaoImpl;
    }
    public static VideoDaoImpl getVideoDaoInstance () {
        if (videoDaoImpl == null) {
            videoDaoImpl = new VideoDaoImpl();
        }
        return videoDaoImpl;
    }
    public static SliderDaoImpl getSliderDaoInstance () {
        if (sliderDaoImpl == null) {
            sliderDaoImpl = new SliderDaoImpl();
        }
        return sliderDaoImpl;
    }
    public static BaiVietDaoImpl getBaiVietDaoInstance () {
        if (baiVietDaoImpl == null) {
            baiVietDaoImpl = new BaiVietDaoImpl();
        }
        return baiVietDaoImpl;
    }
}
