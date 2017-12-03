package core.dto;

import java.io.Serializable;

/**
 * Created by TuanKul on 11/8/2017.
 */
public class DanhMucBaiVietDTO implements Serializable {
    private Integer danhMucBaiVietId;
    private String tenDanhMucBaiViet;
    private Integer chaDanhMucBaiViet;
    private String status;

    public Integer getDanhMucBaiVietId() {
        return danhMucBaiVietId;
    }

    public void setDanhMucBaiVietId(Integer danhMucBaiVietId) {
        this.danhMucBaiVietId = danhMucBaiVietId;
    }

    public String getTenDanhMucBaiViet() {
        return tenDanhMucBaiViet;
    }

    public void setTenDanhMucBaiViet(String tenDanhMucBaiViet) {
        this.tenDanhMucBaiViet = tenDanhMucBaiViet;
    }

    public Integer getChaDanhMucBaiViet() {
        return chaDanhMucBaiViet;
    }

    public void setChaDanhMucBaiViet(Integer chaDanhMucBaiViet) {
        this.chaDanhMucBaiViet = chaDanhMucBaiViet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
