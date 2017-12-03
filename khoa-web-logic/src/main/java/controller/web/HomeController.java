package controller.web;

import core.dto.BaiVietDTO;
import core.dto.DanhMucBaiVietDTO;
import core.web.common.WebConstaint;
import core.web.util.FormUtil;
import core.web.util.RequestUtil;
import core.web.util.SingletonServiceUtil;
import model.BaiVietModel;
import model.DanhMucBaiVietModel;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/16/2017.
 */
@WebServlet(urlPatterns = {"/home.html","/web-danh-sach-danh-muc-bai-viet.html"})
public class HomeController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<DanhMucBaiVietDTO> listMenu = (ArrayList<DanhMucBaiVietDTO>) SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findAll();
        ArrayList<BaiVietDTO> listBaiViet = (ArrayList<BaiVietDTO>) SingletonServiceUtil.getBaiVIetServiceInstance().findAll();
        DanhMucBaiVietModel model = FormUtil.populate(DanhMucBaiVietModel.class,request);
        model.setListResult(listMenu);
        request.setAttribute("itemsDanhMuc",model);
        BaiVietModel modelBaiViet = FormUtil.populate(BaiVietModel.class,request);
        modelBaiViet.setListResult(listBaiViet);
        request.setAttribute("itemsBaiViet",modelBaiViet);
        if (request.getParameter("danhmucbaivietid") != null) {
            if(request.getParameter("danhmucbaivietid").equals("")){
                response.sendRedirect("/web-danh-sach-danh-muc-bai-viet.html?danhmucbaivietid=12");
            }
            else
            {
                String danhMucId = request.getParameter("danhmucbaivietid");
                DanhMucBaiVietDTO exsistDanhMuc = SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findByDanhMucId("danhMucBaiVietId", Integer.parseInt(danhMucId));
                model.setPojo(exsistDanhMuc);
                executeBaiViet(request,modelBaiViet,danhMucId);
                request.setAttribute(WebConstaint.LIST_ITEM,modelBaiViet);
                request.setAttribute("idDanhMuc",Integer.parseInt(danhMucId));
                RequestDispatcher rd = request.getRequestDispatcher("/views/web/danhmucbaiviet/list.jsp");
                rd.forward(request, response);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request, response);
    }

    private void executeDanhMucBaiViet(HttpServletRequest request, DanhMucBaiVietModel model) {
        RequestUtil.initSearchBean(request, model);
        model.setMaxPageItems(100);
        Object[] objects = SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findDanhMucBaiVietByProperties(null, model.getSortExpression(), model.getSortDirection(), model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<DanhMucBaiVietDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private void executeBaiViet(HttpServletRequest request, BaiVietModel modelBaiViet, String danhMucId ) {
        Map<String,Object> properies = buildMapProperties(modelBaiViet);
        modelBaiViet.setMaxPageItems(2);
        RequestUtil.initSearchBeanManual(modelBaiViet);
        List<BaiVietDTO> dtos = SingletonServiceUtil.getBaiVIetServiceInstance().findBaiViet(Integer.parseInt(danhMucId));
        modelBaiViet.setListResult(dtos);
        modelBaiViet.setTotalItems(dtos.size());
        modelBaiViet.setTotalPage((int) Math.ceil((double) modelBaiViet.getTotalItems()/modelBaiViet.getMaxPageItems()));
    }
    private Map<String,Object> buildMapProperties(BaiVietModel modelBaiViet) {
        Map<String,Object> properties = new HashMap<String,Object>();
        //hk dc empty
        if(StringUtils.isNotBlank(modelBaiViet.getPojo().getTieuDe())) {
            properties.put("title",modelBaiViet.getPojo().getTieuDe());
        }
        return properties;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
