package model;

import core.dto.VideoDTO;
import core.web.model.AbstractModel;

/**
 * Created by TuanKul on 11/13/2017.
 */
public class VideoModel extends AbstractModel<VideoDTO> {
    public VideoModel() {
        this.pojo = new VideoDTO();
    }
}
