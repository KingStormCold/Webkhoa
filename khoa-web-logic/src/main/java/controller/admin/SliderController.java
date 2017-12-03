package controller.admin;


import core.common.utils.UploadUtil;
import core.dto.SliderDTO;
import core.web.common.WebConstaint;
import core.web.util.FormUtil;
import core.web.util.RequestUtil;
import core.web.util.SingletonServiceUtil;
import core.web.util.WebCommonUtil;
import model.SliderModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

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
 * Created by TuanKul on 11/15/2017.
 */
@WebServlet(urlPatterns = {"/admin-slider-list.html","/admin-slider-edit.html"})
public class SliderController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SliderModel model = FormUtil.populate(SliderModel.class,request);
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        if(model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_LIST)) {
            if(model.getCrudaction() != null && model.getCrudaction().equals(WebConstaint.REDIRECT_DELETE)) {
                List<Integer> ids = new ArrayList<Integer>();
                for(String item: model.getCheckList()) {
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getSliderServiceInstance().deleteSlider(ids);
                if(result != ids.size()) {
                    model.setCrudaction(WebConstaint.REDIRECT_ERROR);
                }
            }
            excuteSearchSlider(request,model);
            if(model.getCrudaction() != null ) {
                Map<String,String> mapMessage = buidMapRedirectMessage(bundle);
                WebCommonUtil.addRedirectMessage(request,model.getCrudaction(),mapMessage);
            }
            request.setAttribute(WebConstaint.LIST_ITEM,model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/slider/list.jsp");
            rd.forward(request, response);
        }
        else if (model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_EDIT)){
            if(model.getPojo() != null && model.getPojo().getSliderId() != null) {
                model.setPojo(SingletonServiceUtil.getSliderServiceInstance().findBySliderId("sliderId",model.getPojo().getSliderId()));
            }
            request.setAttribute(WebConstaint.FROM_ITEM, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/slider/edit.jsp");
            rd.forward(request, response);
        }
    }

    private void excuteSearchSlider(HttpServletRequest request, SliderModel model) {
        Map<String,Object> properies = buildMapProperties(model);
        RequestUtil.initSearchBean(request, model);
        Object[] objects = SingletonServiceUtil.getSliderServiceInstance().findSliderByProperties(properies, model.getSortExpression(),model.getSortDirection(),model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<SliderDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String,Object> buildMapProperties(SliderModel model) {
        Map<String,Object> properties = new HashMap<String,Object>();
        //hk dc empty
        if(StringUtils.isNotBlank(model.getPojo().getTieuDe())) {
            properties.put("tieuDe",model.getPojo().getTieuDe());
        }
        return properties;
    }

    private Map<String,String> buidMapRedirectMessage(ResourceBundle resourceBundle) {
        Map<String,String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstaint.REDIRECT_INSERT, resourceBundle.getString("label.message.insert"));
        mapMessage.put(WebConstaint.REDIRECT_UPDATE, resourceBundle.getString("label.message.update"));
        mapMessage.put(WebConstaint.REDIRECT_DELETE, resourceBundle.getString("label.message.delete"));
        mapMessage.put(WebConstaint.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        return mapMessage;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SliderModel model = FormUtil.populate(SliderModel.class,request);
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        UploadUtil uploadUtil = new UploadUtil();
        HttpSession session = request.getSession();
        Set<String> valueTitle = buildSetValueListenGuideline();
        Object[] objects = uploadUtil.writeOrUpdateFile(request,valueTitle,WebConstaint.SLIDER);
        //đưa dữ liệu từ jsp vào DTO
        boolean checkStatusUploadImage = (Boolean)objects[0];
        if(!checkStatusUploadImage) {
            response.sendRedirect("/admin-slider-list.html?urlType=url_list&&crudaction=redirect_error");
        }
        else {
            SliderDTO pojo = model.getPojo();
            //không bị empty
            if (StringUtils.isNotBlank(objects[2].toString())) {
                pojo.setHinhAnh(objects[2].toString());
            }
            Map<String,String> mapValue = (Map<String, String>) objects[3];
            pojo = returnValueOfDTO(pojo, mapValue);
            if (model.getPojo().getStatus() == 0) {
                pojo.setStatus(0);
            }
            else{
                pojo.setStatus(1);
            }
            if(pojo != null) {
                if (pojo.getSliderId() != null) {
                    //update
                    SliderDTO sliderDTO = SingletonServiceUtil.getSliderServiceInstance().findBySliderId("sliderId", pojo.getSliderId());
                    if(pojo.getHinhAnh() == null) {
                        pojo.setHinhAnh(sliderDTO.getHinhAnh());
                    }
                    SliderDTO result = SingletonServiceUtil.getSliderServiceInstance().updateSlider(pojo);
                    if(result != null) {
                        response.sendRedirect("/admin-slider-list.html?urlType=url_list&&crudaction=redirect_update");
                    }
                    else {
                        response.sendRedirect("/admin-slider-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }
                else {
                    try{
                        SingletonServiceUtil.getSliderServiceInstance().saveSlider(pojo);
                        response.sendRedirect("/admin-slider-list.html?urlType=url_list&&crudaction=redirect_insert");
                    } catch (ConstraintViolationException e) {
                        log.error(e.getMessage(),e);
                        response.sendRedirect("/admin-slider-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }
            }
        }
    }

    private SliderDTO returnValueOfDTO(SliderDTO dto, Map<String, String> mapValue) {
        for (Map.Entry<String,String> item: mapValue.entrySet()) {
            if(item.getKey().equals("pojo.tieuDe")) {
                dto.setTieuDe(item.getValue());
            } else if(item.getKey().equals("pojo.link")) {
                dto.setLink(item.getValue());
            } else if(item.getKey().equals("pojo.status")){
                dto.setStatus(Integer.parseInt(item.getValue().toString()));
            } else if(item.getKey().equals("pojo.sliderId")) {
                dto.setSliderId(Integer.parseInt(item.getValue().toString()));
            }
        }
        return dto;
    }

    private Set<String> buildSetValueListenGuideline() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.tieuDe");
        returnValue.add("pojo.link");
        returnValue.add("pojo.status");
        returnValue.add("pojo.sliderId");
        return returnValue;
    }
}
