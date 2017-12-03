package core.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "slider")
public class SliderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sliderId;

    @Column(name = "tieude")
    private String tieuDe;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "link")
    private String link;

    @Column(name = "status")
    private Integer status;

    public Integer getSliderId() {
        return sliderId;
    }

    public void setSliderId(Integer sliderId) {
        this.sliderId = sliderId;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
