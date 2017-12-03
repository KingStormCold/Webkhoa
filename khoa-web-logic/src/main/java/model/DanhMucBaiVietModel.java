package model;

import core.dto.DanhMucBaiVietDTO;
import core.web.model.AbstractModel;

import java.util.List;

/**
 * Created by TuanKul on 11/8/2017.
 */
public class DanhMucBaiVietModel extends AbstractModel<DanhMucBaiVietDTO> {
    public DanhMucBaiVietModel() {
        this.pojo = new DanhMucBaiVietDTO();
    }
    private List<DanhMucBaiVietDTO> danhmucs;

    public List<DanhMucBaiVietDTO> getDanhmucs() {
        return danhmucs;
    }

    public void setDanhmucs(List<DanhMucBaiVietDTO> danhmucs) {
        this.danhmucs = danhmucs;
    }
}
