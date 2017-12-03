package controller.admin;

import core.common.utils.SessionUtil;
import core.dto.CheckLogin;
import core.dto.UserDTO;
import core.web.common.WebConstaint;
import core.web.util.FormUtil;
import core.web.util.SingletonServiceUtil;
import model.UserModel;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by TuanKul on 11/7/2017.
 */
@WebServlet(urlPatterns = {"/login.html","/logout.html"})
public class LoginController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = FormUtil.populate(UserModel.class, request);
        UserDTO pojo = model.getPojo();
        CheckLogin login = SingletonServiceUtil.getUserServiceInstance().checkLogin(pojo.getUserName(), pojo.getPassword());
        //trên url có 1 cái parameter có tên là action thì khi gọi request.getParameter sẽ lấy giá trị của action đó
        //dựa vào action này là ta mún là login hay là logout
        String action = request.getParameter("action");
        if(action.equals(WebConstaint.LOGIN)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
            rd.forward(request, response);
        }
        else if(action.equals(WebConstaint.LOGOUT)) {
            SessionUtil.getInstance().remove(request, WebConstaint.LOGIN_NAME);
            response.sendRedirect("/home.html");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = FormUtil.populate(UserModel.class, request);
        UserDTO pojo = model.getPojo();
        if (pojo != null) {
            CheckLogin login = SingletonServiceUtil.getUserServiceInstance().checkLogin(pojo.getUserName(), pojo.getPassword());
            if(login.isUserExist()) {
                SessionUtil.getInstance().putValue(request, WebConstaint.LOGIN_NAME, pojo.getUserName());
                if(login.getRoleName().equals(WebConstaint.ROLE_ADMIN)) {
                    response.sendRedirect("/admin-home.html");
                } else if(login.getRoleName().equals(WebConstaint.ROLE_USER)) {
                    response.sendRedirect("/home.html");
                }
            } else {
                request.setAttribute(WebConstaint.ALERT,WebConstaint.TYPE_ERROR);
                request.setAttribute(WebConstaint.MESSAGE_RESPONSE,bundle.getString("label.name.password.wrong"));
                RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
                rd.forward(request, response);
            }
        }
    }
}
