package core.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "danhmucbaiviet")
public class DanhMucBaiVietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer danhMucBaiVietId;

    @Column(name = "tendanhmucbaiviet")
    private String tenDanhMucBaiViet;

    @Column(name = "chadanhmucbaiviet")
    private Integer chaDanhMucBaiViet;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "danhMucBaiVietEntity",fetch = FetchType.LAZY)
    private List<BaiVietEntity> baiVietEntityList;

    public List<BaiVietEntity> getBaiVietEntityList() {
        return baiVietEntityList;
    }

    public void setBaiVietEntityList(List<BaiVietEntity> baiVietEntityList) {
        this.baiVietEntityList = baiVietEntityList;
    }

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
