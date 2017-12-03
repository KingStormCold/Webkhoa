package core.dao.impl;

import core.common.utils.HibernateUtil;
import core.dao.RoleDao;
import core.data.daoimpl.AbstractDao;
import core.persistence.entity.RoleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class RoleDaoImpl extends AbstractDao<Integer,RoleEntity> implements RoleDao {
    public List<RoleEntity> findByRoles(List<String> roles) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
        try {
            StringBuilder sql = new StringBuilder("FROM RoleEntity re WHERE re.name IN(:roles) ");
            Query query = session.createQuery(sql.toString());
            query.setParameterList("roles", roles);
            roleEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return roleEntities;
    }
}
