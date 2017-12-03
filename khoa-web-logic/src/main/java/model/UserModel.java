package model;

import core.dto.RoleDTO;
import core.dto.UserDTO;
import core.web.model.AbstractModel;

import java.util.List;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class UserModel  extends AbstractModel<UserDTO> {
    public UserModel() {
        this.pojo = new UserDTO();
    }
    private List<RoleDTO> roles;
    private Integer roleId;

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
