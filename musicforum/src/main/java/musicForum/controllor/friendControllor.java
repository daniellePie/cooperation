package musicForum.controllor;

import musicForum.service.FriendService;
import musicForum.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("music/friend")
public class friendControllor {
    @Autowired
    FriendService friendService;
    //加关注
    @PostMapping("add")
    public Result addFriend(@RequestParam("user1Id") Long user1Id,
                            @RequestParam("user2Id") Long user2Id){
        return friendService.addFriend(user1Id,user2Id);
    }
    //获取我的关注列表,一个uservo的list
    @GetMapping("getfriend")
    public Result getFriend(@RequestParam("id") Long id){
        return friendService.getFriend(id);
    }
    //获取谁关注了我
    @GetMapping("getbackfriend")
    public Result getBackFriend(@RequestParam("id") Long id){
        return friendService.getBackFriend(id);
    }

}
