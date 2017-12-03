package core.service.impl;

import core.dto.BaiVietDTO;
import core.persistence.entity.BaiVietEntity;
import core.service.BaiVIetService;
import core.service.utils.SingletonDaoUtil;
import core.utils.BaiVietBeanUtil;
import core.utils.DanhMucBaiVietBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/22/2017.
 */
public class BaiVIetServiceImpl implements BaiVIetService {
    public Object[] findBaiVietByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<BaiVietDTO> result = new ArrayList<BaiVietDTO>();
        Object[] objects = SingletonDaoUtil.getBaiVietDaoInstance().finByProperty(property,sortExpression,sortDirection,offset,limit);
        for (BaiVietEntity item: (List<BaiVietEntity>)objects[1]) {
            BaiVietDTO dto = BaiVietBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    public BaiVietDTO findByBaiVietId(String property, Integer sliderId) {
        BaiVietEntity entity = SingletonDaoUtil.getBaiVietDaoInstance().findEqualUnique(property,sliderId);
        BaiVietDTO dto = BaiVietBeanUtil.entity2Dto(entity);
        return dto;
    }

    public void saveBaiViet(BaiVietDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(timestamp);
        BaiVietEntity entity = BaiVietBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getBaiVietDaoInstance().save(entity);
    }

    public BaiVietDTO updateBaiViet(BaiVietDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(timestamp);
        BaiVietEntity entity = BaiVietBeanUtil.dto2Entity(dto);
        entity = SingletonDaoUtil.getBaiVietDaoInstance().update(entity);
        dto = BaiVietBeanUtil.entity2Dto(entity);
        return dto;
    }

    public Integer deleteBaiViet(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getBaiVietDaoInstance().delete(ids);
        return result;
    }
    public List<BaiVietDTO> findBaiViet(Integer id){
        List<BaiVietEntity> entities =SingletonDaoUtil.getBaiVietDaoInstance().findAllById(id);
        List<BaiVietDTO> dtos = new ArrayList<BaiVietDTO>();
        for(BaiVietEntity item:entities){
            BaiVietDTO dto = BaiVietBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }
    public List<BaiVietDTO> findAll() {
        List<BaiVietEntity> entities = SingletonDaoUtil.getBaiVietDaoInstance().findAll();
        List<BaiVietDTO> dtos = new ArrayList<BaiVietDTO>();
        for(BaiVietEntity item:entities) {
            BaiVietDTO dto = BaiVietBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }
}
