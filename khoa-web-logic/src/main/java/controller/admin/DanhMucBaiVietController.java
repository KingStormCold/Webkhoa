package controller.admin;

import core.dto.DanhMucBaiVietDTO;
import core.web.common.WebConstaint;
import core.web.util.FormUtil;
import core.web.util.RequestUtil;
import core.web.util.SingletonServiceUtil;
import core.web.util.WebCommonUtil;
import model.DanhMucBaiVietModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by TuanKul on 11/8/2017.
 */

@WebServlet(urlPatterns = {"/admin-danhmucbaiviet-list.html","/admin-danhmucbaiviet-edit.html"})
public class DanhMucBaiVietController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        DanhMucBaiVietModel model = FormUtil.populate(DanhMucBaiVietModel.class,request);
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        if(model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_LIST)) {
            if(model.getCrudaction() != null && model.getCrudaction().equals(WebConstaint.REDIRECT_DELETE)) {
                List<Integer> ids = new ArrayList<Integer>();
                for(String item: model.getCheckList()) {
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getDanhMucBaiVietServiceInstance().deleteDanhMuc(ids);
                if(result != ids.size()) {
                    model.setCrudaction(WebConstaint.REDIRECT_ERROR);
                }
            }
            excuteSearchDanhMucBaiViet(request,model);
            if(model.getCrudaction() != null ) {
                Map<String,String> mapMessage = buidMapRedirectMessage(bundle);
                WebCommonUtil.addRedirectMessage(request,model.getCrudaction(),mapMessage);
            }
            request.setAttribute(WebConstaint.LIST_ITEM, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/danhmucbaiviet/list.jsp");
            rd.forward(request, response);
        }
        else if (model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_EDIT)){
            if(model.getPojo() != null && model.getPojo().getDanhMucBaiVietId() != null) {
                model.setPojo(SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findByDanhMucId("danhMucBaiVietId",model.getPojo().getDanhMucBaiVietId()));
            }
            model.setDanhmucs(SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findAll());
            excuteSearchDanhMucBaiViet(request,model);
            request.setAttribute("list", model.getDanhmucs());
            request.setAttribute(WebConstaint.FROM_ITEM, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/danhmucbaiviet/edit.jsp");
            rd.forward(request, response);
        }
    }
    private Map<String,String> buidMapRedirectMessage(ResourceBundle resourceBundle) {
        Map<String,String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstaint.REDIRECT_INSERT, resourceBundle.getString("label.message.insert"));
        mapMessage.put(WebConstaint.REDIRECT_UPDATE, resourceBundle.getString("label.message.update"));
        mapMessage.put(WebConstaint.REDIRECT_DELETE, resourceBundle.getString("label.message.delete"));
        mapMessage.put(WebConstaint.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        return mapMessage;
    }

    private void excuteSearchDanhMucBaiViet(HttpServletRequest request, DanhMucBaiVietModel model) {
        //String la chua truong` muon search,Object la gia tri search
        Map<String,Object> properies = buildMapProperties(model);
        RequestUtil.initSearchBean(request, model);
        model.setMaxPageItems(100);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        DanhMucBaiVietModel model = FormUtil.populate(DanhMucBaiVietModel.class,request);
        DanhMucBaiVietDTO pojo = model.getPojo();
        if(pojo.getDanhMucBaiVietId() != null) {
            if(pojo != null && pojo.getDanhMucBaiVietId() != null) {
                DanhMucBaiVietDTO result = SingletonServiceUtil.getDanhMucBaiVietServiceInstance().updateDanhMuc(pojo);
                if(result != null) {
                    response.sendRedirect("/admin-danhmucbaiviet-list.html?urlType=url_list&&crudaction=redirect_update");
                }
                else {
                    response.sendRedirect("/admin-danhmucbaiviet-list.html?urlType=url_list&&crudaction=redirect_error");
                }
            }
        }
        else {
            try {
                SingletonServiceUtil.getDanhMucBaiVietServiceInstance().saveDanhMuc(pojo);
                response.sendRedirect("/admin-danhmucbaiviet-list.html?urlType=url_list&&crudaction=redirect_insert");
            }catch (ConstraintViolationException e) {
                log.error(e.getMessage(),e);
                response.sendRedirect("/admin-danhmucbaiviet-list.html?urlType=url_list&&crudaction=redirect_error");
            }
        }
    }
}
