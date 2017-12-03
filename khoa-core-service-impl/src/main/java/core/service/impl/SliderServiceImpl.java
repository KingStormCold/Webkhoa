package core.service.impl;

import core.dto.SliderDTO;
import core.persistence.entity.SliderEntity;
import core.service.SliderService;
import core.service.utils.SingletonDaoUtil;
import core.utils.SliderBeanUtil;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/15/2017.
 */
public class SliderServiceImpl implements SliderService {
    public Object[] findSliderByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<SliderDTO> result = new ArrayList<SliderDTO>();
        Object[] objects = SingletonDaoUtil.getSliderDaoInstance().finByProperty(property,sortExpression,sortDirection,offset,limit);
        for (SliderEntity item: (List<SliderEntity>)objects[1]){
            SliderDTO dto = SliderBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    public SliderDTO findBySliderId(String property, Integer sliderId) {
        SliderEntity entity = SingletonDaoUtil.getSliderDaoInstance().findEqualUnique(property,sliderId);
        SliderDTO dto = SliderBeanUtil.entity2Dto(entity);
        return dto;
    }

    public void saveSlider(SliderDTO dto) throws ConstraintViolationException {
        SliderEntity entity = SliderBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getSliderDaoInstance().save(entity);
    }

    public SliderDTO updateSlider(SliderDTO dto) {
        SliderEntity entity = SliderBeanUtil.dto2Entity(dto);
        entity = SingletonDaoUtil.getSliderDaoInstance().update(entity);
        dto = SliderBeanUtil.entity2Dto(entity);
        return dto;
    }

    public Integer deleteSlider(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getSliderDaoInstance().delete(ids);
        return result;
    }
}
