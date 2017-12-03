package core.common.utils;

import core.common.constant.CoreConstant;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by TuanKul on 9/26/2017.
 */
public class UploadUtil {
    private final int maxMemorySize = 1024 * 1024 * 3;//3Mb
    private final int maxRequestSize = 1024 * 1024 * 50;//50Mb
    private final Logger log = Logger.getLogger(this.getClass());
    public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path) {//co FileUploadException de hk bi treo may
        String address = "/"+ CoreConstant.FOLDERUPLOAD;
        checkCreateFolder(address,path);
        boolean check = true;
        String fileLocation = null;
        String name = null;
        Map<String, String> mapReturnValue = new HashMap<String, String>();
        // Check that we have a file upload request
        // uploadfile trong servlet su dung apache commons
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(!isMultipart) {
            System.out.printf("have not enctype multipart/form-data");
            check = false;
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(maxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));//tao ra 1 bo nho tam

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(maxRequestSize);
        try{
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item:items) {
                if(!item.isFormField()) {//if bang false thi no biet la dang uploadfile true thi la co textbox,checkbox...
                    name = item.getName();
                    if(StringUtils.isNotBlank(name)){
                        File uploadFile = new File(address + File.separator + path + File.separator+ name);
                        fileLocation = address + File.separator + path + File.separator + name;
                        boolean isExist = uploadFile.exists();
                        try {
                            if(isExist) {//neu ton tai se xoa va add moi vao
                                uploadFile.delete();
                                item.write(uploadFile);
                            }else {
                                item.write(uploadFile);
                            }
                        } catch (Exception e) {
                            check = false;
                            log.error(e.getMessage(),e);
                        }
                    }
                }
                else {
                    if(titleValue != null) {
                        String nameField = item.getFieldName();//lay name ben jsp nhu la pojo.name
                        String valueField = item.getString();//lay gia tri cua name
                        try {
                            valueField = item.getString("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            log.error(e.getMessage(),e);
                        }
                        if(titleValue.contains(nameField)) {
                            mapReturnValue.put(nameField,valueField);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            check = false;
            log.error(e.getMessage(),e);
        }
        String fileName = "";
        if(StringUtils.isNotBlank(name)) {
            fileName = path + File.separator + name;
        }
        return  new Object[]{check, fileLocation, fileName, mapReturnValue};
    }

    private void checkCreateFolder(String address, String path) {
        //address la duong dan luu file,path la file cua anh~
        File folderRoot = new File(address);
        if(!folderRoot.exists()) {
            //neu chua co thi tao
            folderRoot.mkdirs();
        }
        File folderChild = new File(address + File.separator + path);
        if(!folderChild.exists()) {
            //neu chua co thi tao
            folderChild.mkdirs();
        }
    }
}
