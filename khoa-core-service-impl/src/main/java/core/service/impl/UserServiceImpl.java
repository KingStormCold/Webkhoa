package core.service.impl;

import core.dto.CheckLogin;
import core.dto.UserDTO;
import core.persistence.entity.UserEntity;
import core.service.UserService;
import core.service.utils.SingletonDaoUtil;
import core.utils.UserBeanUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class UserServiceImpl implements UserService {

    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().finByProperty(property,sortExpression,sortDirection,offset,limit);
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for(UserEntity item : (List<UserEntity>)objects[1]) {
            UserDTO userDTO = UserBeanUtil.entity2Dto(item);
            userDTOS.add(userDTO);
        }
        objects[1]= userDTOS;
        return objects;
    }

    public UserDTO findById(Integer userID) {
        UserEntity userEntity = SingletonDaoUtil.getUserDaoInstance().findById(userID);
        UserDTO dto = UserBeanUtil.entity2Dto(userEntity);
        return dto;
    }

    public void saveUser(UserDTO userDTO) {
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(createdDate);
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        entity = SingletonDaoUtil.getUserDaoInstance().update(entity);
        userDTO = UserBeanUtil.entity2Dto(entity);
        return userDTO;
    }

    public CheckLogin checkLogin(String name, String password) {
        CheckLogin checkLogin = new CheckLogin();
        if(name != null && password !=null){
            Object[] objects = SingletonDaoUtil.getUserDaoInstance().checkLogin(name,password);
            checkLogin.setUserExist((Boolean) objects[0]);
            if (checkLogin.isUserExist()) {
                checkLogin.setRoleName(objects[1].toString());
            }
        }
        return checkLogin;
    }

    /*public void validateImportUser(List<UserImportDTO> userImportDTOS) {
        List<String> names = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();
        for (UserImportDTO item : userImportDTOS) {
            if(item.isValid()) {
                names.add(item.getUserName());
                if(!roles.contains(item.getRoleName())) {
                    roles.add(item.getRoleName());
                }
            }
        }
        Map<String, UserEntity> userEntityMap = new HashMap<String, UserEntity>();
        Map<String, RoleEntity> roleEntityMap = new HashMap<String, RoleEntity>();

        if(names.size()>0) {
            List<UserEntity> userEntities = SingletonDaoUtil.getUserDaoInstance().findByUsers(names);
            for (UserEntity item:userEntities) {
                userEntityMap.put(item.getName().toUpperCase(), item);
            }
        }
        if(roles.size()>0) {
            List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDaoInstance().findByRoles(roles);
            for (RoleEntity item:roleEntities) {
                roleEntityMap.put(item.getName().toUpperCase(), item);
            }
        }
        for(UserImportDTO item: userImportDTOS) {
            String message = item.getError();
            if(item.isValid()) {
                UserEntity userEntity = userEntityMap.get(item.getUserName().toUpperCase());
                if(userEntity != null) {
                    message += "<br/>";
                    message += "Tên đăng nhập tồn tại";
                }
                RoleEntity roleEntity = roleEntityMap.get(item.getRoleName().toUpperCase());
                if(roleEntity == null) {
                    message += "<br/>";
                    message += "Vai trò không tồn tại";
                }
                //hk bị empty
                if(StringUtils.isNotBlank(message)) {
                    item.setValid(false);
                    item.setError(message.substring(5));
                }

            }
        }
    }

    public void saveUserImport(List<UserImportDTO> userImportDTOS) {
        for (UserImportDTO item : userImportDTOS) {
            if(item.isValid()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(item.getUserName());
                userEntity.setPassword(item.getPassword());
                userEntity.setFullName(item.getFullName());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                userEntity.setCreatedDate(timestamp);
                RoleEntity roleEntity = SingletonDaoUtil.getRoleDaoInstance().findEqualUnique("name",item.getRoleName().toUpperCase());
                userEntity.setRoleEntity(roleEntity);
                SingletonDaoUtil.getUserDaoInstance().save(userEntity);
            }
        }
    }*/
}
