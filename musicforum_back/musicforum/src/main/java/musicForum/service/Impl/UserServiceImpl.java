package musicForum.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import musicForum.bean.users;
import musicForum.mapper.UserMapper;
import musicForum.service.UserService;
import musicForum.utils.ErrorCode;
import musicForum.utils.Result;
import musicForum.vo.param.login;
import musicForum.vo.param.register;
import musicForum.vo.result.LoginBack;
import musicForum.vo.result.userDetailReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Value("${user.default-avatar}")
    private String dafaultAvatarPath;
    @Value("${user.avatar-parent-path}")
    private String userAvatarParentPath;

    public Result login(login loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        //可以加盐以使md5安全
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        QueryWrapper<users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account).eq("password",password);
        users users = userMapper.selectOne(queryWrapper);
        if(users==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }else {
            //可能跟返回uservo会更好，消息更多
            LoginBack loginBack = new LoginBack(users.getId(), users.getNickname(), users.getAccount());
            return Result.success(loginBack);
        }
    }
    public Result register(register registerParam){
        String account = registerParam.getAccount();
        String nickname = registerParam.getNickname();
        String password = registerParam.getPassword();
        String phone = registerParam.getPhone();
        //可以加盐以使md5安全
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //先查找账号是否重复，再向数据库添加信息
        QueryWrapper<users> usersQueryWrapper = new QueryWrapper<users>();
        usersQueryWrapper.eq("account",account);
        users users = userMapper.selectOne(usersQueryWrapper);
        if(users==null){
            //没有找到，可以正常注册
            Date date = new Date();
            musicForum.bean.users user_create = new users();
            user_create.setCreateTime(date);
            user_create.setAccount(account);
            user_create.setNickname(nickname);
            user_create.setPassword(pwd);
            user_create.setPhone(phone);
            //可能需要单独处理show
            user_create.setShow(0);
            //单独处理头像,单独写一个方法,七牛云服务器
            user_create.setAvatar(dafaultAvatarPath);
            userMapper.insert(user_create);
            return Result.success(null);
        }else {
            //重复了
            return Result.fail(ErrorCode.ACCOUNT_ALREADY_EXIST.getCode(),
                    ErrorCode.ACCOUNT_ALREADY_EXIST.getMsg());
        }
    }
    public Result detail(Long id){
        users user = userMapper.selectById(id);
        if(user!=null){
            //转成userDetailReturn后返回
            userDetailReturn detail = userToDetailVo(user);
            return Result.success(detail);
        }else {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
    }
    public userDetailReturn userToDetailVo(users user){
        userDetailReturn detail = new userDetailReturn();
        detail.setAccount(user.getAccount());
        detail.setAvatar(userAvatarParentPath+user.getAvatar());
        detail.setCreateTime(user.getCreateTime());
        detail.setNickname(user.getNickname());
        detail.setPhone(user.getPhone());
        detail.setBirthday(user.getBirthday());
        detail.setEmail(user.getEmail());
        detail.setSex(user.getSex());
        detail.setId(user.getId());
        detail.setIntroduction(user.getIntroduction());
        return detail;
    }

    /**
     * 可以添加对user的检查，检查各个字段是否符合规范
     * @param user
     * @return
     */
    public Result modify(users user){
        if(user.getEmail()!=null) {
            String tegex="[a-zA-Z0-9_]+@\\w+(\\.com|\\.cn){1}";
            boolean flag = user.getEmail().matches(tegex);
            if (!flag) {
                System.out.println("邮箱格式有误");
                return Result.fail(ErrorCode.EMAIL_ERROR.getCode(),
                        ErrorCode.EMAIL_ERROR.getMsg());
            }
        }
        int i = userMapper.updateById(user);
        if(i==1){
            return Result.success(null);
        }else {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
    }
}
