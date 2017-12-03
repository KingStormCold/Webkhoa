package core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "baiviet")
public class BaiVietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBaiViet;

    @Column(name = "tieude")
    private String tieuDe;

    @Column(name = "tomtat")
    private String tomTat;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "hinhAnh")
    private String hinhAnh;

    @Column(name = "view")
    private Integer view;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "ghim")
    private Integer ghim;

    @ManyToOne
    @JoinColumn(name="danhmucbaivietid")
    private DanhMucBaiVietEntity danhMucBaiVietEntity;

    @OneToMany(mappedBy = "baiVietEntity",fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    public Integer getBaiVietId() {
        return idBaiViet;
    }

    public void setBaiVietId(Integer idBaiViet) {
        this.idBaiViet = idBaiViet;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DanhMucBaiVietEntity getDanhMucBaiVietEntity() {
        return danhMucBaiVietEntity;
    }

    public void setDanhMucBaiVietEntity(DanhMucBaiVietEntity danhMucBaiVietEntity) {
        this.danhMucBaiVietEntity = danhMucBaiVietEntity;
    }

    public Integer getGhim() {
        return ghim;
    }

    public void setGhim(Integer ghim) {
        this.ghim = ghim;
    }
}
