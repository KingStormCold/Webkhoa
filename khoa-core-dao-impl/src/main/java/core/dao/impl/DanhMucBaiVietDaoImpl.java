package core.dao.impl;

import core.common.utils.HibernateUtil;
import core.dao.DanhMucBaiVietDao;
import core.data.daoimpl.AbstractDao;
import core.persistence.entity.DanhMucBaiVietEntity;
import core.persistence.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanKul on 11/8/2017.
 */
public class DanhMucBaiVietDaoImpl extends AbstractDao<Integer, DanhMucBaiVietEntity> implements DanhMucBaiVietDao{
    public List<DanhMucBaiVietEntity> menuSon() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<DanhMucBaiVietEntity> danhMucBaiVietEntities = new ArrayList<DanhMucBaiVietEntity>();
        try {
            //SELECT *from danhmucbaiviet where chadanhmucbaiviet in (SELECT danhmucbaivietid FROM danhmucbaiviet where danhmucbaivietid in (select chadanhmucbaiviet from danhmucbaiviet));

            StringBuilder sql = new StringBuilder("FROM DanhMucBaiVietEntity where chaDanhMucBaiViet in (SELECT danhMucBaiVietId FROM DanhMucBaiVietEntity where danhMucBaiVietId in (select chaDanhMucBaiViet from DanhMucBaiVietEntity))");
            Query query = session.createQuery(sql.toString());
            danhMucBaiVietEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return  danhMucBaiVietEntities;
    }

    public List<DanhMucBaiVietEntity> menuFather() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<DanhMucBaiVietEntity> danhMucBaiVietEntities = new ArrayList<DanhMucBaiVietEntity>();
        try {
            //SELECT *from danhmucbaiviet where chadanhmucbaiviet in (SELECT danhmucbaivietid FROM danhmucbaiviet where danhmucbaivietid in (select chadanhmucbaiviet from danhmucbaiviet));

            StringBuilder sql = new StringBuilder("FROM DanhMucBaiVietEntity where chaDanhMucBaiViet not in (SELECT danhMucBaiVietId FROM DanhMucBaiVietEntity where danhMucBaiVietId in (select chaDanhMucBaiViet from DanhMucBaiVietEntity))");
            Query query = session.createQuery(sql.toString());
            danhMucBaiVietEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return  danhMucBaiVietEntities;
    }
}
