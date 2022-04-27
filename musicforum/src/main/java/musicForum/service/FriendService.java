package musicForum.service;

import musicForum.bean.friends;
import musicForum.utils.Result;

import java.util.List;

public interface FriendService {
    Result addFriend(Long user1Id, Long user2Id);
    Result getFriend(Long id);
    Result getBackFriend(Long id);
    List<Long> getIdsByFriends(List<friends> friends, int flag);
}
