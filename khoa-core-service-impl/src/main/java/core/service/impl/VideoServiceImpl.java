package core.service.impl;

import core.dto.VideoDTO;
import core.persistence.entity.VideoEntity;
import core.service.VideoService;
import core.service.utils.SingletonDaoUtil;
import core.utils.VideoBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/13/2017.
 */
public class VideoServiceImpl implements VideoService{
    public Object[] findVideoByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<VideoDTO> result = new ArrayList<VideoDTO>();
        Object[] objects = SingletonDaoUtil.getVideoDaoInstance().finByProperty(property,sortExpression,sortDirection,offset,limit);
        for (VideoEntity item: (List<VideoEntity>)objects[1]) {
            VideoDTO dto = VideoBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    public List<VideoDTO> findAll() {
        List<VideoEntity> entities = SingletonDaoUtil.getVideoDaoInstance().findAll();
        List<VideoDTO> dtos = new ArrayList<VideoDTO>();
        for(VideoEntity item:entities) {
            VideoDTO dto = VideoBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public VideoDTO findByVideoId(String property, Integer videoId) {
        VideoEntity entity = SingletonDaoUtil.getVideoDaoInstance().findEqualUnique(property,videoId);
        VideoDTO dto = VideoBeanUtil.entity2Dto(entity);
        return dto;
    }

    public void saveVideo(VideoDTO videoDTO) {
        VideoEntity entity = VideoBeanUtil.dto2Entity(videoDTO);
        SingletonDaoUtil.getVideoDaoInstance().save(entity);
    }

    public VideoDTO updateVideo(VideoDTO videoDTO) {
        VideoEntity entity = VideoBeanUtil.dto2Entity(videoDTO);
        entity = SingletonDaoUtil.getVideoDaoInstance().update(entity);
        videoDTO = VideoBeanUtil.entity2Dto(entity);
        return videoDTO;
    }

    public Integer deleteVideo(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getVideoDaoInstance().delete(ids);
        return result;
    }

}
