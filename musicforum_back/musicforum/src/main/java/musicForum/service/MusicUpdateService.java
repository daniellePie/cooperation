package musicForum.service;

import musicForum.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface MusicUpdateService {
    Result comment(Long id,Long audioId,Long parentId, String myComment);
    Result like(Long userId,Long audioId);
    Result notLike(Long userId,Long audioId);
    Result favorite(Long userId,Long audioId);
    Result notFavorite(Long userId,Long audioId);
}
