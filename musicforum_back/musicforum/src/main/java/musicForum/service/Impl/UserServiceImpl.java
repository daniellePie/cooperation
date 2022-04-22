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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
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
            LoginBack loginBack = new LoginBack(users.getId(), users.getNickname(), users.getAccount());
            return Result.success(loginBack);
        }
    }
    @Override
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
            user_create.setCreate_date(date);
            user_create.setAccount(account);
            user_create.setNickname(nickname);
            user_create.setPassword(pwd);
            user_create.setPhone(phone);
            userMapper.insert(user_create);
            return Result.success(null);
        }else {
            //重复了
            return Result.fail(ErrorCode.ACCOUNT_ALREADY_EXIST.getCode(),
                    ErrorCode.ACCOUNT_ALREADY_EXIST.getMsg());
        }
    }
    @Override
    public Result detail(Long id){
        users users = userMapper.selectById(id);
        if(users!=null){
            return Result.success(users);
        }else {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
    }
    public Result modify(users user){
        //协商返回什么，这里先不返回
        int i = userMapper.updateById(user);
        if(i==1){
            return Result.success(null);
        }else {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
    }
}
