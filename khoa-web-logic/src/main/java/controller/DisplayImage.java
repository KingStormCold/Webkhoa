package controller;

import core.common.constant.CoreConstant;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by TuanKul on 11/28/2017.
 */
public class DisplayImage extends HttpServlet{
    private final String imageBase = "/"+ CoreConstant.FOLDERUPLOAD;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //khi mà click vào edit thì nó sẽ chạy 1 cái servlet  thì lúc đó thằng request gửi tới xml
        //trong xml sẽ có 1 cái urlpattern thì nó sẽ truyền tất cả những thằng nào có /repository/
        //sau đó nó sẽ trỏ đến thằng displayImage sau đó nó sẽ chạy từng dòng bên dưới để đọc image từ folder bên ngoài để view cho mình xem
        String imageUrl = request.getRequestURI();
        String relativeImagePath = imageUrl.substring("/repository/".length());
        ServletOutputStream outStream;
        outStream = response.getOutputStream();
        FileInputStream inStream = new FileInputStream(imageBase + File.separator + relativeImagePath);
        BufferedInputStream bin = new BufferedInputStream(inStream);
        BufferedOutputStream bout = new BufferedOutputStream(outStream);
        int ch = 0;
        while ((ch = bin.read()) !=  -1)
            bout.write(ch);
        bin.close();
        inStream.close();
        bout.close();
        outStream.close();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
