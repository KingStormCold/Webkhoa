package core.service;

import core.dto.VideoDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/13/2017.
 */
public interface VideoService {
    Object[] findVideoByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    List<VideoDTO> findAll();
    VideoDTO findByVideoId(String property, Integer videoId);
    void saveVideo(VideoDTO videoDTO);
    VideoDTO updateVideo(VideoDTO videoDTO);
    Integer deleteVideo(List<Integer> ids);
}
