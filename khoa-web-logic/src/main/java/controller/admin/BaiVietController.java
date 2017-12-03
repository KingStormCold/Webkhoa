package controller.admin;

import core.common.utils.UploadUtil;
import core.dto.BaiVietDTO;
import core.dto.DanhMucBaiVietDTO;
import core.web.common.WebConstaint;
import core.web.util.FormUtil;
import core.web.util.RequestUtil;
import core.web.util.SingletonServiceUtil;
import core.web.util.WebCommonUtil;
import model.BaiVietModel;
import model.DanhMucBaiVietModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by TuanKul on 11/21/2017.
 */
@WebServlet(urlPatterns = {"/admin-menu-list.html","/admin-menu-edit.html"})
public class BaiVietController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<DanhMucBaiVietDTO> listMenu = (ArrayList<DanhMucBaiVietDTO>) SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findAll();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        BaiVietModel model = FormUtil.populate(BaiVietModel.class, request);
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        for(DanhMucBaiVietDTO itemMenu : listMenu) {
            int temp = 0;
            if (model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_LIST + itemMenu.getDanhMucBaiVietId() )) {
                temp = itemMenu.getDanhMucBaiVietId();
                if (model.getCrudaction() != null && model.getCrudaction().equals(WebConstaint.REDIRECT_DELETE)) {
                    List<Integer> ids = new ArrayList<Integer>();
                    for (String item : model.getCheckList()) {
                        ids.add(Integer.parseInt(item));
                    }
                    Integer result = SingletonServiceUtil.getBaiVIetServiceInstance().deleteBaiViet(ids);
                    if (result != ids.size()) {
                        model.setCrudaction(WebConstaint.REDIRECT_ERROR);
                    }
                }
                request.setAttribute("menuTitle",itemMenu.getTenDanhMucBaiViet());
                request.setAttribute("menuId",itemMenu.getDanhMucBaiVietId());
                excuteSearchBaiViet(request, model);
                if (model.getCrudaction() != null) {
                    Map<String, String> mapMessage = buidMapRedirectMessage(bundle);
                    WebCommonUtil.addRedirectMessage(request, model.getCrudaction(), mapMessage);
                }
                ArrayList<BaiVietDTO> listNew = new ArrayList<BaiVietDTO>();
                ArrayList<BaiVietDTO> dtos = (ArrayList<BaiVietDTO>) model.getListResult();
                for(BaiVietDTO itemNew: dtos) {
                    if(itemMenu.getDanhMucBaiVietId()==itemNew.getDanhMucs().getDanhMucBaiVietId()) {
                        listNew.add(itemNew);
                    }
                }
                model.setListResult(listNew);
                request.setAttribute(WebConstaint.LIST_ITEM, model);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/menu/list.jsp");
                rd.forward(request, response);
            }
            if (model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_EDIT)) {
                if (model.getPojo() != null && model.getPojo().getIdBaiViet() != null) {
                    model.setPojo(SingletonServiceUtil.getBaiVIetServiceInstance().findByBaiVietId("idBaiViet", model.getPojo().getIdBaiViet()));
                }
                model.setDanhMucList(SingletonServiceUtil.getDanhMucBaiVietServiceInstance().findAll());
                request.setAttribute("a", temp);
                request.setAttribute(WebConstaint.FROM_ITEM, model);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/menu/edit.jsp");
                rd.forward(request, response);
            }
        }
    }

    private Map<String, String> buidMapRedirectMessage(ResourceBundle bundle) {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstaint.REDIRECT_INSERT, bundle.getString("label.message.insert"));
        mapMessage.put(WebConstaint.REDIRECT_UPDATE, bundle.getString("label.message.update"));
        mapMessage.put(WebConstaint.REDIRECT_DELETE, bundle.getString("label.message.delete"));
        mapMessage.put(WebConstaint.REDIRECT_ERROR, bundle.getString("label.message.error"));
        return mapMessage;
    }

    private void excuteSearchBaiViet(HttpServletRequest request, BaiVietModel model) {
        //String la chua truong` muon search,Object la gia tri search
        Map<String, Object> properies = buildMapProperties(model);
        RequestUtil.initSearchBean(request, model);
        model.setMaxPageItems(100);
        Object[] objects = SingletonServiceUtil.getBaiVIetServiceInstance().findBaiVietByProperties(properies, model.getSortExpression(), model.getSortDirection(), model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<BaiVietDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> buildMapProperties(BaiVietModel model) {
        Map<String, Object> properties = new HashMap<String, Object>();
        //hk dc empty
        if (StringUtils.isNotBlank(model.getPojo().getTieuDe())) {
            properties.put("tieuDe", model.getPojo().getTieuDe());
        }
        return properties;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        BaiVietModel model = FormUtil.populate(BaiVietModel.class, request);
        BaiVietDTO pojo = model.getPojo();
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        UploadUtil uploadUtil = new UploadUtil();
        Set<String> valueTitle = buildSetValueListenGuideline();
        Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle, WebConstaint.MENU);
        //đưa dữ liệu từ jsp vào DTO
        boolean checkStatusUploadImage = (Boolean) objects[0];
        if (!checkStatusUploadImage) {
            response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
        } else {
            //không bị empty
            if (StringUtils.isNotBlank(objects[2].toString())) {
                pojo.setHinhAnh(objects[2].toString());
            }
            if(pojo.getGhim() == null) {
                pojo.setGhim(0);
            }
            Map<String, String> mapValue = (Map<String, String>) objects[3];
            pojo = returnValueOfDTO(pojo, mapValue);
            if (pojo != null &&pojo.getIdBaiViet() != null) {
                //update
                BaiVietDTO baiVietDTO = SingletonServiceUtil.getBaiVIetServiceInstance().findByBaiVietId("idBaiViet", pojo.getIdBaiViet());
                if (pojo.getHinhAnh() == null) {
                    pojo.setHinhAnh(baiVietDTO.getHinhAnh());
                }
                pojo.setCreatedDate(baiVietDTO.getCreatedDate());
                BaiVietDTO result = SingletonServiceUtil.getBaiVIetServiceInstance().updateBaiViet(pojo);
                if (result != null) {
                    response.sendRedirect("/admin-menu-list.html?urlType=url_list"+pojo.getDanhMucs().getDanhMucBaiVietId()+"&&crudaction=redirect_update");
                } else {
                    response.sendRedirect("/admin-menu-list.html?urlType=url_list"+pojo.getDanhMucs().getDanhMucBaiVietId()+"&&crudaction=redirect_error");
                }
            } else {
                try {
                    SingletonServiceUtil.getBaiVIetServiceInstance().saveBaiViet(pojo);
                    response.sendRedirect("/admin-menu-list.html?urlType=url_list"+pojo.getDanhMucs().getDanhMucBaiVietId()+"&&crudaction=redirect_insert");
                } catch (ConstraintViolationException e) {
                    log.error(e.getMessage(), e);
                    response.sendRedirect("/admin-menu-list.html?urlType=url_list&&crudaction=redirect_error");
                }
            }
        }
    }

    private BaiVietDTO returnValueOfDTO(BaiVietDTO dto, Map<String, String> mapValue) {
        for (Map.Entry<String, String> item : mapValue.entrySet()) {
            if (item.getKey().equals("pojo.tieuDe")) {
                dto.setTieuDe(item.getValue());
            }
            else if (item.getKey().equals("pojo.tomTat")) {
                dto.setTomTat(item.getValue());
            }
            else if (item.getKey().equals("pojo.status")) {
                dto.setStatus(Integer.parseInt(item.getValue()));
            }
            else if (item.getKey().equals("pojo.noiDung")) {
                dto.setNoiDung(item.getValue());
            }
            else if (item.getKey().equals("pojo.idBaiViet")) {
                dto.setIdBaiViet(Integer.parseInt(item.getValue().toString()));
            }
            else if (item.getKey().equals("danhMucBaiVietId")) {
                DanhMucBaiVietDTO danhMucBaiVietDTO = new DanhMucBaiVietDTO();
                danhMucBaiVietDTO.setDanhMucBaiVietId(Integer.parseInt(item.getValue().toString()));
                dto.setDanhMucs(danhMucBaiVietDTO);
            }
            else if (item.getKey().equals("pojo.ghim")) {
                dto.setGhim(Integer.parseInt(item.getValue().toString()));
            }
        }
        return dto;
    }
    private Set<String> buildSetValueListenGuideline() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.tieuDe");
        returnValue.add("pojo.tomTat");
        returnValue.add("pojo.status");
        returnValue.add("pojo.noiDung");
        returnValue.add("pojo.idBaiViet");
        returnValue.add("danhMucBaiVietId");
        returnValue.add("pojo.ghim");
        return returnValue;

    }
}