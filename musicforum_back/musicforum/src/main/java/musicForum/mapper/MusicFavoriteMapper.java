package musicForum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import musicForum.bean.musicFavorite;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicFavoriteMapper extends BaseMapper<musicFavorite> {
}
