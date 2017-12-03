package core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/7/2017.
 */
public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    T update(T entity);

    void save(T entity);

    T findById(ID id);

    Object[] finByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);

    Integer delete(List<ID> ids);

    T findEqualUnique(String property, Object value);

}
