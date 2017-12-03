package core.service;

import core.dto.SliderDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/15/2017.
 */
public interface SliderService {
    Object[] findSliderByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    SliderDTO findBySliderId(String property, Integer sliderId);
    void saveSlider(SliderDTO dto);
    SliderDTO updateSlider(SliderDTO dto);
    Integer deleteSlider(List<Integer> ids);
}
