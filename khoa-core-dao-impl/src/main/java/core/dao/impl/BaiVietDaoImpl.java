package core.dao.impl;

import core.common.utils.HibernateUtil;
import core.dao.BaiVietDao;
import core.data.daoimpl.AbstractDao;
import core.persistence.entity.BaiVietEntity;
import core.persistence.entity.DanhMucBaiVietEntity;
import core.persistence.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanKul on 11/22/2017.
 */
public class BaiVietDaoImpl extends AbstractDao<Integer, BaiVietEntity> implements BaiVietDao {
    public List<BaiVietEntity> findAllById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BaiVietEntity> baiVietEntities = new ArrayList<BaiVietEntity>();
        try {
            StringBuilder sql = new StringBuilder("FROM BaiVietEntity WHERE danhMucBaiVietEntity.danhMucBaiVietId= :id");
            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            baiVietEntities = query.list();

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return baiVietEntities;
    }
}
