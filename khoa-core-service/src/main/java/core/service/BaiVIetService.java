package core.service;

import core.dto.BaiVietDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/22/2017.
 */
public interface BaiVIetService {
    Object[] findBaiVietByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    BaiVietDTO findByBaiVietId(String property, Integer sliderId);
    void saveBaiViet(BaiVietDTO dto);
    BaiVietDTO updateBaiViet(BaiVietDTO dto);
    Integer deleteBaiViet(List<Integer> ids);
    List<BaiVietDTO> findAll();
}
