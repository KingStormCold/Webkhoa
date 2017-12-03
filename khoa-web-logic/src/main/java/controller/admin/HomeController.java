package controller.admin;

import core.dto.DanhMucBaiVietDTO;
import core.web.common.WebConstaint;
import core.web.util.FormUtil;
import core.web.util.RequestUtil;
import core.web.util.SingletonServiceUtil;
import model.DanhMucBaiVietModel;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by TuanKul on 11/7/2017.
 */
@WebServlet("/admin-home.html")
public class HomeController extends HttpServlet {
    public HomeController() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DanhMucBaiVietModel model = FormUtil.populate(DanhMucBaiVietModel.class,request);
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        excuteSearchDanhMucBaiViet(request,model);
        request.setAttribute(WebConstaint.LIST_ITEM, model);
        ArrayList<DanhMucBaiVietDTO> haveSubMenu = new ArrayList<DanhMucBaiVietDTO>();
        ArrayList<DanhMucBaiVietDTO> noHaveSubMenu = new ArrayList<DanhMucBaiVietDTO>();
        ArrayList<DanhMucBaiVietDTO> listMenu = (ArrayList<DanhMucBaiVietDTO>)getMainMenu();
        ArrayList<DanhMucBaiVietDTO> subMenu = (ArrayList<DanhMucBaiVietDTO>) SingletonServiceUtil.getDanhMucBaiVietServiceInstance().menuSon();
        for(DanhMucBaiVietDTO i :subMenu) {
            for (DanhMucBaiVietDTO j : listMenu) {
                if(j.getDanhMucBaiVietId() == i.getChaDanhMucBaiViet()) {
                    haveSubMenu.add(j);
                }
                else {
                    noHaveSubMenu.add(j);
                }
            }
        }
        for(DanhMucBaiVietDTO item : listMenu) {
            getSubMenu(item.getDanhMucBaiVietId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
        rd.forward(request, response);
    }
    public static ArrayList<DanhMucBaiVietDTO> getMainMenu() {
        ArrayList list = new ArrayList();
        ArrayList<DanhMucBaiVietDTO> dtos = (ArrayList<DanhMucBaiVietDTO>) SingletonServiceUtil.getDanhMucBaiVietServiceInstance().menuFather();
        for (DanhMucBaiVietDTO item : dtos) {
            list.add(item);
        }
        return list;
    }
    public static ArrayList<DanhMucBaiVietDTO> getSubMenu(Integer id){
        ArrayList list = new ArrayList();
        ArrayList<DanhMucBaiVietDTO> subMenu = (ArrayList<DanhMucBaiVietDTO>) SingletonServiceUtil.getDanhMucBaiVietServiceInstance().menuSon();
        for(DanhMucBaiVietDTO item : subMenu) {
            if(id==item.getChaDanhMucBaiViet()) {
                list.add(item);
            }
        }
        return list;
    }
    private void excuteSearchDanhMucBaiViet(HttpServletRequest request, DanhMucBaiVietModel model) {
        //String la chua truong` muon search,Object la gia tri search
        Map<String,Object> properies = buildMapProperties(model);
        RequestUtil.initSearchBean(request, model);
        model.setMaxPageItems(1000);
        Object[] objects = SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findDanhMucBaiVietByProperties(properies, model.getSortExpression(), model.getSortDirection(), model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<DanhMucBaiVietDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }
    private Map<String,Object> buildMapProperties(DanhMucBaiVietModel model) {
        Map<String,Object> properties = new HashMap<String,Object>();
        //hk dc empty
        if(StringUtils.isNotBlank(model.getPojo().getTenDanhMucBaiViet())) {
            properties.put("tenDanhMucBaiViet",model.getPojo().getTenDanhMucBaiViet());
        }
        return properties;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}