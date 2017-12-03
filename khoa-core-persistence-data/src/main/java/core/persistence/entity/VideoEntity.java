package core.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name="video")
public class VideoEntity {
    @Id
    @Column(name = "idvideo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVideo;

    @Column(name = "tieude")
    private String tieuDe;

    @Column(name = "link")
    private String link;

    @Column(name = "status")
    private Integer status;

    @Column(name = "file")
    private String file;


    public Integer getVideoId() {
        return idVideo;
    }

    public void setVideoId(Integer videoId) {
        this.idVideo = videoId;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
