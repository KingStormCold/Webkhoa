package core.utils;

import core.dto.DanhMucBaiVietDTO;
import core.persistence.entity.DanhMucBaiVietEntity;

/**
 * Created by TuanKul on 11/8/2017.
 */
public class DanhMucBaiVietBeanUtil {
    //entity qua dto
    public static DanhMucBaiVietDTO entity2Dto (DanhMucBaiVietEntity entity) {
        DanhMucBaiVietDTO dto = new DanhMucBaiVietDTO();
        dto.setDanhMucBaiVietId(entity.getDanhMucBaiVietId());
        dto.setTenDanhMucBaiViet(entity.getTenDanhMucBaiViet());
        dto.setChaDanhMucBaiViet(entity.getChaDanhMucBaiViet());
        dto.setStatus(entity.getStatus());
        return dto;
    }
    public static DanhMucBaiVietEntity dto2Entity (DanhMucBaiVietDTO dto) {
        DanhMucBaiVietEntity entity = new DanhMucBaiVietEntity();
        entity.setDanhMucBaiVietId(dto.getDanhMucBaiVietId());
        entity.setTenDanhMucBaiViet(dto.getTenDanhMucBaiViet());
        entity.setChaDanhMucBaiViet(dto.getChaDanhMucBaiViet());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
