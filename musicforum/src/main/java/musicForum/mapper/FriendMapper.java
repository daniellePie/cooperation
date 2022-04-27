package musicForum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import musicForum.bean.friends;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper extends BaseMapper<friends> {
    List<Long> selectFriendIds(Long id);
    List<Long> selectBackFriendIds(Long id);
}
