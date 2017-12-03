package core.dto;

import core.persistence.entity.DanhMucBaiVietEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by TuanKul on 11/22/2017.
 */
public class BaiVietDTO implements Serializable {
    private Integer idBaiViet;
    private String tieuDe;
    private String tomTat;
    private String noiDung;
    private String hinhAnh;
    private Integer view;
    private Timestamp createdDate;
    private Integer status;
    private Integer ghim;
    private DanhMucBaiVietDTO danhMucs;

    public Integer getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(Integer idBaiViet) {
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

    public DanhMucBaiVietDTO getDanhMucs() {
        return danhMucs;
    }

    public void setDanhMucs(DanhMucBaiVietDTO danhMucs) {
        this.danhMucs = danhMucs;
    }

    public Integer getGhim() {
        return ghim;
    }

    public void setGhim(Integer ghim) {
        this.ghim = ghim;
    }
}
