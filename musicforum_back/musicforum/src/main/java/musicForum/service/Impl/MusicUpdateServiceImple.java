package musicForum.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import musicForum.bean.musicComment;
import musicForum.bean.musicFavorite;
import musicForum.bean.musicLike;
import musicForum.mapper.MusicCommentMapper;
import musicForum.mapper.MusicFavoriteMapper;
import musicForum.mapper.MusicLikeMapper;
import musicForum.mapper.MusicUploadMapper;
import musicForum.service.MusicInfoService;
import musicForum.service.MusicUpdateService;
import musicForum.utils.ErrorCode;
import musicForum.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MusicUpdateServiceImple implements MusicUpdateService {
    @Autowired
    private MusicUploadMapper musicUploadMapper;
    @Autowired
    private MusicFavoriteMapper musicFavoriteMapper;
    @Autowired
    private MusicLikeMapper musicLikeMapper;
    @Autowired
    private MusicCommentMapper musicCommentMapper;

    public Result comment(Long userId, Long audioId, Long parentId, String myComment){
        musicComment comment = new musicComment();
        comment.setCommentTime(new Date());
        comment.setContent(myComment);
        comment.setMusicId(audioId);
        comment.setUpUserId(userId);
        if(parentId==null){
            comment.setLevel(1);
        }else {
            comment.setLevel(2);
            comment.setParentId(parentId);
            musicComment parentComment = musicCommentMapper.selectById(parentId);
            if(parentComment!=null){
                comment.setToUserId(parentComment.getUpUserId());
            }
        }
        musicCommentMapper.insert(comment);
        //返回评论的id即可
        Map<String, Long> result = new HashMap<>();
        result.put("commentId",comment.getId());
        return Result.success(result);
    }

    public Result like(Long userId,Long audioId){
        // 可以之后更新查找music和user存不存在，暂时不搞，基本不会有问题
        QueryWrapper<musicLike> musicLikeQueryWrapper = new QueryWrapper<>();
        musicLikeQueryWrapper.eq("user_id",userId).eq("music_id",audioId);
        musicLike like = musicLikeMapper.selectOne(musicLikeQueryWrapper);
        if(like==null){
            musicLike musicLike = new musicLike();
            musicLike.setMusicId(audioId);
            musicLike.setUserId(userId);
            musicLike.setLikeTime(new Date());
            musicLikeMapper.insert(musicLike);
            return Result.success(null);
        }else {
            return Result.fail(ErrorCode.MUSIC_ALREADY_LIKE.getCode()
                    , ErrorCode.MUSIC_ALREADY_LIKE.getMsg());
        }
    }

    public Result notLike(Long userId,Long audioId){
        // 可以之后更新查找music和user存不存在，暂时不搞，基本不会有问题
        QueryWrapper<musicLike> musicLikeQueryWrapper = new QueryWrapper<>();
        musicLikeQueryWrapper.eq("user_id",userId).eq("music_id",audioId);
        musicLike like = musicLikeMapper.selectOne(musicLikeQueryWrapper);
        if(like!=null){
            musicLikeMapper.delete(musicLikeQueryWrapper);
            return Result.success(null);
        }else {
            return Result.fail(ErrorCode.MUSIC_ALREADY_NOTLIKE.getCode()
                    , ErrorCode.MUSIC_ALREADY_NOTLIKE.getMsg());
        }
    }

    public Result favorite(Long userId,Long audioId){
        // 可以之后更新查找music和user存不存在，暂时不搞，基本不会有问题
        QueryWrapper<musicFavorite> musicFavQueryWrapper = new QueryWrapper<>();
        musicFavQueryWrapper.eq("user_id",userId).eq("music_id",audioId);
        musicFavorite favorite = musicFavoriteMapper.selectOne(musicFavQueryWrapper);
        if(favorite==null){
            musicFavorite musicFavorite = new musicFavorite();
            musicFavorite.setMusicId(audioId);
            musicFavorite.setUserId(userId);
            musicFavorite.setFavTime(new Date());
            musicFavoriteMapper.insert(musicFavorite);
            return Result.success(null);
        }else {
            return Result.fail(ErrorCode.MUSIC_ALREADY_FAV.getCode()
                    , ErrorCode.MUSIC_ALREADY_FAV.getMsg());
        }
    }

    public Result notFavorite(Long userId,Long audioId){
        QueryWrapper<musicFavorite> musicFavQueryWrapper = new QueryWrapper<>();
        musicFavQueryWrapper.eq("user_id",userId).eq("music_id",audioId);
        musicFavorite favorite = musicFavoriteMapper.selectOne(musicFavQueryWrapper);
        if(favorite!=null){
            musicFavoriteMapper.delete(musicFavQueryWrapper);
            return Result.success(null);
        }else {
            return Result.fail(ErrorCode.MUSIC_ALREADY_NOTFAV.getCode()
                    , ErrorCode.MUSIC_ALREADY_NOTFAV.getMsg());
        }
    }
}
