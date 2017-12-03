package core.web.util;

import core.web.model.AbstractModel;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by TuanKul on 9/24/2017.
 */
public class RequestUtil {
    //API initSearchBean để phân trang
    public static void initSearchBean(HttpServletRequest request , AbstractModel bean) {
        //tất cả dữ liệu từ client truyền về đều là kiểu chuỗi
        //request.getParameter là lấy dữ liệu từ jsp truyền về severlet
        if(bean != null){
            String sortExpression = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
            String sortDirection = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
            String pageStr = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));

            Integer page = 1;
            if(StringUtils.isNotBlank(pageStr)){
                try{
                    page = Integer.valueOf(pageStr);
                }catch (Exception e){
                    //ignore
                }
            }
            bean.setPage(page);
            bean.setSortDirection(sortDirection);
            bean.setSortExpression(sortExpression);
            bean.setFirstItem((bean.getPage()-1) * bean.getMaxPageItems());
        }
    }
    public static void initSearchBeanManual(AbstractModel model) {
        if(model != null) {//command phải có giá trị
            Integer page = 1;
            if(model.getPage() != 0) {
                page = model.getPage();
            }
            model.setPage(page);
            model.setFirstItem((model.getPage()-1) * model.getMaxPageItems());
        }
    }
}
