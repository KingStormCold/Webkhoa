package core.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "thongtinkhoa")
public class ThongTinKhoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer khoaId;

    @Column(name = "tenkhoa")
    private String tenKhoa;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "email")
    private String email;

    public Integer getKhoaId() {
        return khoaId;
    }

    public void setKhoaId(Integer khoaId) {
        this.khoaId = khoaId;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

