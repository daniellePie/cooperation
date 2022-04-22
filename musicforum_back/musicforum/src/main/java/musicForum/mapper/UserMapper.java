package musicForum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import musicForum.bean.users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<users> {
}
