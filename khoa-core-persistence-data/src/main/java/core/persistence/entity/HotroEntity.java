package core.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "hotro")
public class HotroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hoTroId;

    @Column(name = "yahoo")
    private String yahoo;

    @Column(name = "zalo")
    private String zalo;

    @Column(name = "facebook")
    private String facebook;

    public Integer getHoTroId() {
        return hoTroId;
    }

    public void setHoTroId(Integer hoTroId) {
        this.hoTroId = hoTroId;
    }

    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    public String getZalo() {
        return zalo;
    }

    public void setZalo(String zalo) {
        this.zalo = zalo;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
