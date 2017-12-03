package core.utils;

import core.dto.SliderDTO;
import core.persistence.entity.SliderEntity;

/**
 * Created by TuanKul on 11/15/2017.
 */
public class SliderBeanUtil {
    //entity qua dto
    public static SliderDTO entity2Dto (SliderEntity entity) {
        SliderDTO dto = new SliderDTO();
        dto.setSliderId(entity.getSliderId());
        dto.setTieuDe(entity.getTieuDe());
        dto.setHinhAnh(entity.getHinhAnh());
        dto.setLink(entity.getLink());
        dto.setStatus(entity.getStatus());
        return dto;
    }
    public static SliderEntity dto2Entity (SliderDTO dto) {
        SliderEntity entity = new SliderEntity();
        entity.setSliderId(dto.getSliderId());
        entity.setTieuDe(dto.getTieuDe());
        entity.setHinhAnh(dto.getHinhAnh());
        entity.setLink(dto.getLink());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
