package core.dao;

import core.data.dao.GenericDao;
import core.persistence.entity.RoleEntity;

import java.util.List;

/**
 * Created by TuanKul on 11/7/2017.
 */
public interface RoleDao extends GenericDao<Integer, RoleEntity> {
    List<RoleEntity> findByRoles(List<String> roles);
}
