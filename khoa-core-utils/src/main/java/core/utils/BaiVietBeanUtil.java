package core.utils;

import core.dto.BaiVietDTO;
import core.persistence.entity.BaiVietEntity;

/**
 * Created by TuanKul on 11/22/2017.
 */
public class BaiVietBeanUtil {
    public static BaiVietDTO entity2Dto (BaiVietEntity entity) {
        BaiVietDTO dto = new BaiVietDTO();
        dto.setIdBaiViet(entity.getBaiVietId());
        dto.setTieuDe(entity.getTieuDe());
        dto.setTomTat(entity.getTomTat());
        dto.setNoiDung(entity.getNoiDung());
        dto.setHinhAnh(entity.getHinhAnh());
        dto.setView(entity.getView());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setGhim(entity.getGhim());
        dto.setDanhMucs(DanhMucBaiVietBeanUtil.entity2Dto(entity.getDanhMucBaiVietEntity()));
        return dto;
    }
    public static BaiVietEntity dto2Entity (BaiVietDTO dto) {
        BaiVietEntity entity = new BaiVietEntity();
        entity.setBaiVietId(dto.getIdBaiViet());
        entity.setTieuDe(dto.getTieuDe());
        entity.setTomTat(dto.getTomTat());
        entity.setNoiDung(dto.getNoiDung());
        entity.setHinhAnh(dto.getHinhAnh());
        entity.setView(dto.getView());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setStatus(dto.getStatus());
        entity.setGhim(dto.getGhim());
        entity.setDanhMucBaiVietEntity(DanhMucBaiVietBeanUtil.dto2Entity(dto.getDanhMucs()));
        return entity;
    }
}
