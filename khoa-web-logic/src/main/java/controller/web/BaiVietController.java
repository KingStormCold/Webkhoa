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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/30/2017.
 */
@WebServlet(urlPatterns = {"/noi-dung-bai-viet.html"})
public class BaiVietController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BaiVietModel model = FormUtil.populate(BaiVietModel.class,request);
        if (request.getParameter("baivietid") != null) {
            String baiVietId = request.getParameter("baivietid");
            BaiVietDTO exsistBaiViet = SingletonServiceUtil.getBaiVIetServiceInstance().findByBaiVietId("idBaiViet", Integer.parseInt(baiVietId));
            model.setPojo(exsistBaiViet);
            request.setAttribute("baiviet",model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/danhmucbaiviet/detail.jsp");
            rd.forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
