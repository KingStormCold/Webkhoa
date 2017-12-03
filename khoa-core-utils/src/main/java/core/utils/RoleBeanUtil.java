package core.utils;

import core.dto.RoleDTO;
import core.persistence.entity.RoleEntity;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class RoleBeanUtil {
    //entity qua dto
    public static RoleDTO entity2Dto (RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setName(entity.getName());
        return dto;
    }
    public static RoleEntity dto2Entity (RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setName(dto.getName());
        return entity;
    }
}
