package core.utils;

import core.dto.UserDTO;
import core.persistence.entity.UserEntity;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class UserBeanUtil {
    public static UserDTO entity2Dto (UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setRoleDTO(RoleBeanUtil.entity2Dto(entity.getRoleEntity()));
        return dto;
    }
    public static UserEntity dto2Entity (UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setRoleEntity(RoleBeanUtil.dto2Entity(dto.getRoleDTO()));
        return entity;
    }
}
