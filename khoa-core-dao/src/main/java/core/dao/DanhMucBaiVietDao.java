package core.dao;

import core.data.dao.GenericDao;
import core.persistence.entity.DanhMucBaiVietEntity;

import java.util.List;

/**
 * Created by TuanKul on 11/8/2017.
 */
public interface DanhMucBaiVietDao extends GenericDao<Integer, DanhMucBaiVietEntity> {
    List<DanhMucBaiVietEntity> menuFather();
    List<DanhMucBaiVietEntity> menuSon();
}
