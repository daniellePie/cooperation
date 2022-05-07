package musicForum.controllor;

import musicForum.service.MusicInfoService;
import musicForum.utils.Result;
import musicForum.vo.param.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 获取设置音频信息
 */
@RestController
@RequestMapping("music/musicInfo")
public class MusicInfoControllor {
    @Autowired
    MusicInfoService musicInfoService;

    //返回音乐详细信息
    @GetMapping("detail/{id}")
    public Result getMusicDetail(@PathVariable("id") Long id,@RequestParam("userId") Long userId){
        //id，音频链接（远程），图片链接，发布时间，音频名称，发布者id，点赞数，评论内容
        return musicInfoService.getMusicDetail(id,userId);
    }
}
