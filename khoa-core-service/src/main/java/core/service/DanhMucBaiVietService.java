package core.service;

import core.dto.DanhMucBaiVietDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/8/2017.
 */
public interface DanhMucBaiVietService {
    Object[] findDanhMucBaiVietByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    List<DanhMucBaiVietDTO> findAll();
    List<DanhMucBaiVietDTO> menuSon();
    List<DanhMucBaiVietDTO> menuFather();
    DanhMucBaiVietDTO findByDanhMucId(String property, Integer sliderId);
    void saveDanhMuc(DanhMucBaiVietDTO dto);
    DanhMucBaiVietDTO updateDanhMuc(DanhMucBaiVietDTO dto);
    Integer deleteDanhMuc(List<Integer> ids);
}
