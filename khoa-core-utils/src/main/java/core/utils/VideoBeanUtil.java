package core.utils;

import core.dto.VideoDTO;
import core.persistence.entity.VideoEntity;

/**
 * Created by TuanKul on 11/13/2017.
 */
public class VideoBeanUtil {
    public static VideoDTO entity2Dto (VideoEntity entity) {
        VideoDTO dto = new VideoDTO();
        dto.setIdVideo(entity.getVideoId());
        dto.setTieuDe(entity.getTieuDe());
        dto.setLink(entity.getLink());
        dto.setStatus(entity.getStatus());
        dto.setFile(entity.getFile());
        return dto;
    }
    public static VideoEntity dto2Entity (VideoDTO dto) {
        VideoEntity entity = new VideoEntity();
        entity.setVideoId(dto.getIdVideo());
        entity.setTieuDe(dto.getTieuDe());
        entity.setLink(dto.getLink());
        entity.setStatus(dto.getStatus());
        entity.setFile(dto.getFile());
        return entity;
    }
}
