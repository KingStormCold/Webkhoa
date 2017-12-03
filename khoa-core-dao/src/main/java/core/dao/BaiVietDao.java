package core.dao;

import core.data.dao.GenericDao;
import core.persistence.entity.BaiVietEntity;

import java.util.List;

/**
 * Created by TuanKul on 11/22/2017.
 */
public interface BaiVietDao extends GenericDao<Integer, BaiVietEntity> {
    List<BaiVietEntity> findAllById(Integer id);
}
