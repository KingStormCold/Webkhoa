package core.dao;

import core.data.dao.GenericDao;
import core.persistence.entity.UserEntity;

import java.util.List;

/**
 * Created by TuanKul on 11/7/2017.
 */
public interface UserDao extends GenericDao<Integer, UserEntity> {
    Object[] checkLogin(String name, String password);
    List<UserEntity> findByUsers(List<String> names);
}
