package musicForum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import musicForum.bean.musicComment;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicCommentMapper extends BaseMapper<musicComment> {
}
