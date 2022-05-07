package musicForum.service;

import musicForum.bean.musicUpload;
import musicForum.utils.Result;
import musicForum.vo.result.musicDetailReturn;
import org.springframework.web.bind.annotation.PathVariable;

public interface MusicInfoService {
    Result getMusicDetail(Long id,Long userId);
    musicDetailReturn musicUploadToVo(musicUpload musicUpload);
}
