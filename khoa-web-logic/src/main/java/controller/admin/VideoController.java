package controller.admin;


import core.common.utils.UploadUtil;
import core.dto.SliderDTO;
import core.dto.VideoDTO;
import core.web.common.WebConstaint;
import core.web.util.FormUtil;
import core.web.util.RequestUtil;
import core.web.util.SingletonServiceUtil;
import core.web.util.WebCommonUtil;
import model.VideoModel;
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

@WebServlet(urlPatterns = {"/admin-video-list.html","/admin-video-edit.html"})
public class VideoController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VideoModel model = FormUtil.populate(VideoModel.class,request);
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        if(model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_LIST)) {
            if(model.getCrudaction() != null && model.getCrudaction().equals(WebConstaint.REDIRECT_DELETE)) {
                List<Integer> ids = new ArrayList<Integer>();
                for(String item: model.getCheckList()){
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getVideoServiceInstance().deleteVideo(ids);
                if(result != ids.size()) {
                    model.setCrudaction(WebConstaint.REDIRECT_ERROR);
                }
            }
            excuteSearchVideo(request,model);
            if(model.getCrudaction() != null) {
                Map<String,String> mapMessage = buidMapRedirectMessage(bundle);
                WebCommonUtil.addRedirectMessage(request,model.getCrudaction(),mapMessage);
            }
            request.setAttribute(WebConstaint.LIST_ITEM, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/video/list.jsp");
            rd.forward(request, response);
        }
        else if (model.getUrlType() != null && model.getUrlType().equals(WebConstaint.URL_EDIT)) {
            if (model.getPojo() != null && model.getPojo().getIdVideo() != null) {
                model.setPojo(SingletonServiceUtil.getVideoServiceInstance().findByVideoId("idVideo",model.getPojo().getIdVideo()));
            }
            request.setAttribute(WebConstaint.FROM_ITEM, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/video/edit.jsp");
            rd.forward(request, response);
        }
    }
    private Map<String,String> buidMapRedirectMessage(ResourceBundle bundle) {
        Map<String,String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstaint.REDIRECT_INSERT, bundle.getString("label.message.insert"));
        mapMessage.put(WebConstaint.REDIRECT_UPDATE, bundle.getString("label.message.update"));
        mapMessage.put(WebConstaint.REDIRECT_DELETE, bundle.getString("label.message.delete"));
        mapMessage.put(WebConstaint.REDIRECT_ERROR, bundle.getString("label.message.error"));
        return mapMessage;
    }

    private void excuteSearchVideo(HttpServletRequest request, VideoModel model) {
        Map<String,Object> properies = buildMapProperties(model);
        RequestUtil.initSearchBean(request, model);
        Object[] objects = SingletonServiceUtil.getVideoServiceInstance().findVideoByProperties(properies, model.getSortExpression(), model.getSortDirection(), model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<VideoDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String,Object> buildMapProperties(VideoModel model) {
        Map<String,Object> properties = new HashMap<String,Object>();
        //hk dc empty
        if(StringUtils.isNotBlank(model.getPojo().getTieuDe())) {
            properties.put("tieuDe",model.getPojo().getTieuDe());
        }
        return properties;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VideoModel model = FormUtil.populate(VideoModel.class,request);
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        UploadUtil uploadUtil = new UploadUtil();
        HttpSession session = request.getSession();
        Set<String> valueTitle = buildSetValueListenGuideline();
        Object[] objects = uploadUtil.writeOrUpdateFile(request,valueTitle,WebConstaint.VIDEO);
        //đưa dữ liệu từ jsp vào DTO
        boolean checkStatusUploadImage = (Boolean)objects[0];
        if(!checkStatusUploadImage) {
            response.sendRedirect("/admin-video-list.html?urlType=url_list&&crudaction=redirect_error");
        }
        else {
            VideoDTO pojo = model.getPojo();
            //không bị empty
            if (StringUtils.isNotBlank(objects[2].toString())) {
                pojo.setFile(objects[2].toString());
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
                if (pojo.getIdVideo() != null) {
                    //update
                    VideoDTO videoDTO = SingletonServiceUtil.getVideoServiceInstance().findByVideoId("idVideo", pojo.getIdVideo());
                    if(pojo.getFile() == null) {
                        pojo.setFile(videoDTO.getFile());
                    }
                    VideoDTO result = SingletonServiceUtil.getVideoServiceInstance().updateVideo(pojo);
                    if(result != null) {
                        response.sendRedirect("/admin-video-list.html?urlType=url_list&&crudaction=redirect_update");
                    }
                    else {
                        response.sendRedirect("/admin-video-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }
                else {
                    try{
                        SingletonServiceUtil.getVideoServiceInstance().saveVideo(pojo);
                        response.sendRedirect("/admin-video-list.html?urlType=url_list&&crudaction=redirect_insert");
                    } catch (ConstraintViolationException e) {
                        log.error(e.getMessage(),e);
                        response.sendRedirect("/admin-video-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }
            }
        }
    }
    private VideoDTO returnValueOfDTO(VideoDTO dto, Map<String, String> mapValue) {
        for (Map.Entry<String,String> item: mapValue.entrySet()) {
            if(item.getKey().equals("pojo.tieuDe")) {
                dto.setTieuDe(item.getValue());
            } else if(item.getKey().equals("pojo.link")) {
                dto.setLink(item.getValue());
            } else if(item.getKey().equals("pojo.status")){
                dto.setStatus(Integer.parseInt(item.getValue().toString()));
            } else if(item.getKey().equals("pojo.idVideo")) {
                dto.setIdVideo(Integer.parseInt(item.getValue().toString()));
            }
        }
        return dto;
    }

    private Set<String> buildSetValueListenGuideline() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.tieuDe");
        returnValue.add("pojo.link");
        returnValue.add("pojo.status");
        returnValue.add("pojo.idVideo");
        return returnValue;
    }
}
