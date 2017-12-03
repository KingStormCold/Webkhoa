package core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name="userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="idbaiviet")
    private BaiVietEntity baiVietEntity;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BaiVietEntity getBaiVietEntity() {
        return baiVietEntity;
    }

    public void setBaiVietEntity(BaiVietEntity baiVietEntity) {
        this.baiVietEntity = baiVietEntity;
    }
}


