package musicForum.controllor;

import musicForum.service.MusicUpdateService;
import musicForum.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("music/musicUpdate")
public class musicUpdateControllor {
    @Autowired
    MusicUpdateService musicUpdateService;

    //评论
    @PostMapping("comment")
    public Result comment(@RequestParam("id") Long id,@RequestParam("audioId")Long audioId
                          ,@RequestParam("parentId") Long parentId
                            ,@RequestParam("myComment") String myComment){
        //id，音频链接（远程），图片链接，发布时间，音频名称，发布者id，点赞数，评论内容
        return musicUpdateService.comment(id,audioId,parentId,myComment);
    }
    //点赞
    @PostMapping("like")
    public Result like(@RequestParam("id") Long id, @RequestParam("audioId") Long audioId){
        //记得更新我的点赞状态，不要重复点赞
        return musicUpdateService.like(id,audioId);
    }
    //取消点赞
    @PostMapping("notlike")
    public Result notLike(@RequestParam("id") Long id, @RequestParam("audioId") Long audioId){
        //记得更新我的点赞状态，不要重复点赞
        return musicUpdateService.notLike(id,audioId);
    }
    //收藏
    @PostMapping("favorite")
    public Result favorite(@RequestParam("id") Long id, @RequestParam("audioId") Long audioId){
        //记得更新我的点赞状态，不要重复点赞
        return musicUpdateService.favorite(id,audioId);
    }
    //取消收藏
    @PostMapping("notfavorite")
    public Result notFavorite(@RequestParam("id") Long id, @RequestParam("audioId") Long audioId){
        //记得更新我的点赞状态，不要重复点赞
        return musicUpdateService.notFavorite(id,audioId);
    }
}
