package core.service.impl;

import core.dto.DanhMucBaiVietDTO;
import core.persistence.entity.DanhMucBaiVietEntity;
import core.service.DanhMucBaiVietService;
import core.service.utils.SingletonDaoUtil;
import core.utils.DanhMucBaiVietBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/8/2017.
 */
public class DanhMucBaiVietServiceImpl implements DanhMucBaiVietService{
    public Object[] findDanhMucBaiVietByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<DanhMucBaiVietDTO> result = new ArrayList<DanhMucBaiVietDTO>();
        Object[] objects = SingletonDaoUtil.getDanhMucBaiVietDaoInstance().finByProperty(property,sortExpression,sortDirection,offset,limit);
        for (DanhMucBaiVietEntity item: (List<DanhMucBaiVietEntity>)objects[1]) {
            DanhMucBaiVietDTO dto = DanhMucBaiVietBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    public List<DanhMucBaiVietDTO> findAll() {
        List<DanhMucBaiVietEntity> entities = SingletonDaoUtil.getDanhMucBaiVietDaoInstance().findAll();
        List<DanhMucBaiVietDTO> dtos = new ArrayList<DanhMucBaiVietDTO>();
        for(DanhMucBaiVietEntity item:entities) {
            DanhMucBaiVietDTO dto = DanhMucBaiVietBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<DanhMucBaiVietDTO> menuSon() {
        List<DanhMucBaiVietEntity> entities =SingletonDaoUtil.getDanhMucBaiVietDaoInstance().menuSon();
        List<DanhMucBaiVietDTO> dtos = new ArrayList<DanhMucBaiVietDTO>();
        for(DanhMucBaiVietEntity item:entities){
            DanhMucBaiVietDTO dto = DanhMucBaiVietBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<DanhMucBaiVietDTO> menuFather() {
        List<DanhMucBaiVietEntity> entities =SingletonDaoUtil.getDanhMucBaiVietDaoInstance().menuFather();
        List<DanhMucBaiVietDTO> dtos = new ArrayList<DanhMucBaiVietDTO>();
        for(DanhMucBaiVietEntity item:entities){
            DanhMucBaiVietDTO dto = DanhMucBaiVietBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public DanhMucBaiVietDTO findByDanhMucId(String property, Integer sliderId) {
        DanhMucBaiVietEntity entity = SingletonDaoUtil.getDanhMucBaiVietDaoInstance().findEqualUnique(property,sliderId);
        DanhMucBaiVietDTO dto = DanhMucBaiVietBeanUtil.entity2Dto(entity);
        return dto;
    }

    public void saveDanhMuc(DanhMucBaiVietDTO dto) {
        DanhMucBaiVietEntity entity = DanhMucBaiVietBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getDanhMucBaiVietDaoInstance().save(entity);
    }

    public DanhMucBaiVietDTO updateDanhMuc(DanhMucBaiVietDTO dto) {
        DanhMucBaiVietEntity entity = DanhMucBaiVietBeanUtil.dto2Entity(dto);
        entity = SingletonDaoUtil.getDanhMucBaiVietDaoInstance().update(entity);
        dto = DanhMucBaiVietBeanUtil.entity2Dto(entity);
        return dto;
    }

    public Integer deleteDanhMuc(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getDanhMucBaiVietDaoInstance().delete(ids);
        return result;
    }
}
