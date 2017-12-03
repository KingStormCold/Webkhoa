package core.dao.impl;

import core.common.utils.HibernateUtil;
import core.dao.UserDao;
import core.data.daoimpl.AbstractDao;
import core.persistence.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class UserDaoImpl extends AbstractDao<Integer,UserEntity> implements UserDao {
    public Object[] checkLogin(String userName, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        boolean isUserExist = false;
        String roleName = null;
        try {
            StringBuilder sql = new StringBuilder("FROM UserEntity ue WHERE ue.userName= :userName AND ue.password= :password");
            Query query = session.createQuery(sql.toString());
            query.setParameter("userName", userName);
            query.setParameter("password", password);
            if(query.list().size() >0) {
                isUserExist = true;
                UserEntity userEntity = (UserEntity) query.uniqueResult();
                roleName = userEntity.getRoleEntity().getName();
            }

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{isUserExist,roleName};
    }

    public List<UserEntity> findByUsers(List<String> names) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<UserEntity> userEntities = new ArrayList<UserEntity>();
        try {
            StringBuilder sql = new StringBuilder("FROM UserEntity ue WHERE ue.name IN(:names) ");
            Query query = session.createQuery(sql.toString());
            query.setParameterList("names", names);
            userEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return userEntities;
    }
}
