package musicForum.controllor;

import musicForum.bean.users;
import musicForum.service.UserService;
import musicForum.utils.Result;
import musicForum.utils.qiniuUtils;
import musicForum.vo.param.login;
import musicForum.vo.param.register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

/**
 * 控制用户登录，注册等用户基本服务
 */
@RestController
@RequestMapping("music/user")
public class userControllor {
    @Autowired
    private UserService userService;
    //登录
    @PostMapping("login")
    public Result login(@RequestBody login loginparam){
        return userService.login(loginparam);
    }

    //注册
    @PostMapping("register")
    public Result register(@RequestBody register registerParam){
        return userService.register(registerParam);
    }

    //获取用户信息
    @GetMapping("view/{id}")
    public Result userDetail(@PathVariable("id") Long id){
        return userService.detail(id);
    }
    //修改用户信息,id的问题，协商传输信息，哪些需要session中获取
    @PostMapping("modify")
    public Result userModify(@RequestBody users user){
        return userService.modify(user);
    }
    //修改用户头像,把loginback结构再返回回去
    @PostMapping("uploadAvator")
    public Result uploagAvator(@RequestParam("image") MultipartFile avator){
        //原始文件名称
        String originalFilename = avator.getOriginalFilename();
        String avatorPath = "music/photo/avator/" + UUID.randomUUID().toString() + "."
                + StringUtils.substringAfter(originalFilename, ".");
        //上传到七牛云服务器
        boolean upload = qiniuUtils.upload(avator, avatorPath);
        if (upload){
            return Result.success(qiniuUtils.url + avatorPath);
        }
        return Result.fail(20001,"上传失败");
    }
}
