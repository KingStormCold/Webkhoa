package core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by TuanKul on 11/7/2017.
 */
@Entity
@Table(name="user")
public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer userId;

        @Column(name = "username")
        private String userName;

        @Column(name = "password")
        private String password;

        @Column(name = "fullname")
        private String fullName;

        @Column(name = "email")
        private String email;

        @Column(name = "phone")
        private String phone;

        @Column(name = "createddate")
        private Timestamp createdDate;

        @ManyToOne
        @JoinColumn(name = "roleid")
        private RoleEntity roleEntity;

        @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY)
        private List<CommentEntity> commentEntityList;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Timestamp getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Timestamp createdDate) {
            this.createdDate = createdDate;
        }

        public RoleEntity getRoleEntity() {
            return roleEntity;
        }

        public void setRoleEntity(RoleEntity roleEntity) {
            this.roleEntity = roleEntity;
        }
}
