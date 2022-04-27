package musicForum.service;

import musicForum.bean.users;
import musicForum.utils.Result;
import musicForum.vo.param.login;
import musicForum.vo.param.register;

public interface UserService {
    Result login(login loginparam);
    Result register(register registerParam);
    Result detail(Long id);
    Result modify(users user);
}
