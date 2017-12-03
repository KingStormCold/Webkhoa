package core.data.daoimpl;

import core.common.constant.CoreConstant;
import core.common.utils.HibernateUtil;
import core.data.dao.GenericDao;
import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class AbstractDao<ID extends Serializable,T> implements GenericDao<ID,T> {

    private Class<T> persistenceClass;
    private final Logger log = Logger.getLogger(this.getClass());
    public AbstractDao() {
        //ParameterizedType là get tất cả tham số của AbstractDao vào
        //AbstractDao<ID,T> nó là 1 cái mảng T là class nên getActualTypeArguments lấy ra vị trí 1 là T để lấy ra dc EntityClass
        this.persistenceClass= (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    //thường câu truy vấn nối chuôi là sql= "select *from User" nhưng khi nối chuỗi sql= "select *from "+getPersistenceCLassName+"";
    //vì persistenceClass có kiểu là Class mà sql lại có kiểu là String nên cần ép về String để thực hiện câu truy vấn
    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            //HQL
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw  e;
        }
        finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
    }

    public T findById(ID id) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = (T)session.get(persistenceClass, id);
            if(result == null) {
                throw new ObjectNotFoundException("NOT fOUND "+ id, null);
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

//limit là số item cần hiển thị trong 1 trang

    public Object[] finByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem = 0;
        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i = 0;
        for(Map.Entry item:property.entrySet()) {
            params[i] = (String) item.getKey();
            values[i] = item.getValue();
            i++;
        }
        try {
            // from UserEntity where property= : value order by sortExpression sortDirection
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where 1=1 ");
            if(property.size() > 0 ) {
                for(int j = 0; j < params.length ; j++){
                    sql1.append(" and ").append("LOWER("+params[j]+") LIKE '%' || :"+params[j]+" || '%'");
                }
            }
            /*if(property != null & value != null) {
                sql1.append(" where ").append(property).append("= :value");
            }*/
            if(sortExpression !=null && sortDirection != null) {
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" " + (sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            if(property.size() > 0){
                for(int j = 0; j < params.length ; j++){
                    query1.setParameter(params[j],values[j]);
                }
            }
            if(offset != null && offset >= 0){
                query1.setFirstResult(offset);
            }
            if(limit != null && limit > 0){
                query1.setMaxResults(limit);
            }
            list = query1.list();
            //đếm kích thước của list
            //select count(*) from getPersistenceClassName where property= :value
            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersistenceClassName()).append(" where 1=1 ");
            if(property.size() > 0 ) {
                for(int j = 0; j < params.length ; j++){
                    if(j == 0) {
                        sql2.append(" and ").append("LOWER("+params[j]+") LIKE '%' || :"+params[j]+" || '%'");
                    }
                }
            }
            Query query2 = session.createQuery(sql2.toString());

            if(property.size() > 0){
                for(int j = 0; j < params.length ; j++){
                    query2.setParameter(params[j],values[j]);
                }
            }
            totalItem = query2.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for(ID item: ids) {
                T id = (T) session.get(persistenceClass,item);
                session.delete(id);
                count++;
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

    public T findEqualUnique(String property, Object value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            String sql = "FROM "+getPersistenceClassName()+" model WHERE model."+property+"= :value";
            Query query = session.createQuery(sql.toString());
            query.setParameter("value",value);
            result = (T) query.uniqueResult();
        }catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
