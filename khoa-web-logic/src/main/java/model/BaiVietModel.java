package model;

import core.dto.BaiVietDTO;
import core.dto.DanhMucBaiVietDTO;
import core.web.model.AbstractModel;

import java.util.List;

/**
 * Created by TuanKul on 11/22/2017.
 */
public class BaiVietModel extends AbstractModel<BaiVietDTO> {
    public BaiVietModel() {
        this.pojo = new BaiVietDTO();
    }
    private List<DanhMucBaiVietDTO> danhMucList;
    private Integer danhMucBaiVietId;

    public Integer getDanhMucBaiVietId() {
        return danhMucBaiVietId;
    }

    public void setDanhMucBaiVietId(Integer danhMucBaiVietId) {
        this.danhMucBaiVietId = danhMucBaiVietId;
    }

    public List<DanhMucBaiVietDTO> getDanhMucList() {
        return danhMucList;
    }

    public void setDanhMucList(List<DanhMucBaiVietDTO> danhMucList) {
        this.danhMucList = danhMucList;
    }
}
