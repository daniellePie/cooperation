package musicForum.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import musicForum.bean.friends;
import musicForum.bean.users;
import musicForum.mapper.FriendMapper;
import musicForum.mapper.UserMapper;
import musicForum.service.FriendService;
import musicForum.utils.ErrorCode;
import musicForum.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static musicForum.utils.ErrorCode.ACCOUNT_PWD_NOT_EXIST;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    FriendService friendService;

    public Result addFriend(Long user1Id, Long user2Id){
        users user1 = userMapper.selectById(user1Id);
        users user2 = userMapper.selectById(user2Id);
        if(user1==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }else if(user2==null){
            return Result.fail(ErrorCode.ACCOUNT_Friend_ID_Not_EXIST.getCode(),
                    ErrorCode.ACCOUNT_Friend_ID_Not_EXIST.getMsg());
        }else {
            //查找是否已经关注
            QueryWrapper<friends> friendsQueryWrapper = new QueryWrapper<>();
            friendsQueryWrapper.eq("user1_id", user1Id).eq("user1_id", user2Id);
            friends friends = friendMapper.selectOne(friendsQueryWrapper);
            if(friends==null){
                //添加
                musicForum.bean.friends friends1 = new friends(user1Id, user2Id, 0);
                friendMapper.insert(friends1);
                return Result.success(null);
            }else {
                return Result.fail(ErrorCode.ACCOUNT_Friend_ALREADY_EXIST.getCode(),
                        ErrorCode.ACCOUNT_Friend_ALREADY_EXIST.getMsg());
            }
        }
    }

    public Result getFriend(Long id){
        users user = userMapper.selectById(id);
        if(user==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }else {
            //自己写一个只返回user2_id的sql
//            QueryWrapper<friends> friendsQueryWrapper = new QueryWrapper<>();
//            friendsQueryWrapper.eq("user1_id",id);
            //需要的话可以改分页
//            List<friends> friends = friendMapper.selectList(friendsQueryWrapper);
//            List<Long> ids = friendService.getIdsByFriends(friends, 2);
            List<Long> ids = friendMapper.selectFriendIds(id);
            List<users> users = userMapper.selectBatchIds(ids);
            //目前返回一个userslist，之后看情况改map和uservo
            return Result.success(users);
        }
    }

    //获取关注我的用户列表
    public Result getBackFriend(Long id){
        users user = userMapper.selectById(id);
        if(user==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }else {
            List<Long> ids = friendMapper.selectBackFriendIds(id);
            List<users> users = userMapper.selectBatchIds(ids);
            return Result.success(users);
        }
    }

    public List<Long> getIdsByFriends(List<friends> friends, int flag){
        ArrayList<Long> ids = new ArrayList<>();
        if(flag==1) {
            for (musicForum.bean.friends friend : friends) {
                ids.add(friend.getUser1Id());
            }
        }else {
            for (musicForum.bean.friends friend : friends) {
                ids.add(friend.getUser2Id());
            }
        }
        return ids;
    }
}
