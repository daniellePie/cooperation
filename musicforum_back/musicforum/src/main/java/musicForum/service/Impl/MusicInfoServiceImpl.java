package musicForum.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import musicForum.bean.musicComment;
import musicForum.bean.musicFavorite;
import musicForum.bean.musicLike;
import musicForum.bean.musicUpload;
import musicForum.mapper.MusicCommentMapper;
import musicForum.mapper.MusicFavoriteMapper;
import musicForum.mapper.MusicLikeMapper;
import musicForum.mapper.MusicUploadMapper;
import musicForum.service.MusicInfoService;
import musicForum.utils.Result;
import musicForum.vo.result.musicCommentReturn;
import musicForum.vo.result.musicDetailReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicInfoServiceImpl implements MusicInfoService {
    @Autowired
    private MusicUploadMapper musicUploadMapper;
    @Autowired
    private MusicLikeMapper musicLikeMapper;
    @Autowired
    private MusicFavoriteMapper musicFavoriteMapper;
    @Autowired
    private MusicCommentMapper musicCommentMapper;
    @Value("${music.remote-storage-parent-path}")
    private String musicParentPath;
    @Value("${music.musicImg-parent-path}")
    private String musicImgParentPath;

    /**
     * 之后可以改成按照时间排序返回评论
     * @param id
     * @param userId
     * @return
     */
    public Result getMusicDetail(Long id,Long userId){
        //分别查出内容，汇总到返回结构
        musicUpload musicUpload = musicUploadMapper.selectById(id);
        musicDetailReturn musicReturn = musicUploadToVo(musicUpload);
        //查出点赞总数和收藏总数
        QueryWrapper<musicLike> musicLikeQueryWrapper = new QueryWrapper<>();
        musicLikeQueryWrapper.eq("music_id",id);
        musicReturn.setLikeNum(musicLikeMapper.selectCount(musicLikeQueryWrapper));
        QueryWrapper<musicFavorite> musicFavoriteQueryWrapper = new QueryWrapper<>();
        musicFavoriteQueryWrapper.eq("music_id",id);
        musicReturn.setFavNum(musicFavoriteMapper.selectCount(musicFavoriteQueryWrapper));
        if(userId!=null){
            musicLikeQueryWrapper.eq("user_id",userId);
            musicLike musicLike = musicLikeMapper.selectOne(musicLikeQueryWrapper);
            if(musicLike!=null){
                musicReturn.setIsMyLike(1);
            }else {
                musicReturn.setIsMyLike(0);
            }
            musicFavoriteQueryWrapper.eq("user_id",userId);
            musicFavorite musicFavorite = musicFavoriteMapper.selectOne(musicFavoriteQueryWrapper);
            if(musicFavorite!=null){
                musicReturn.setIsMyFav(1);
            }else {
                musicReturn.setIsMyFav(0);
            }
        }
        //查出评论,先用一个函数查出评论列表，包括子评论，再用一个函数转为return值的列表
        musicReturn.setComments(getComments(id));

        return Result.success(musicReturn);
    }
    public musicDetailReturn musicUploadToVo(musicUpload musicUpload){
        musicDetailReturn musicDetailReturn = new musicDetailReturn();
        musicDetailReturn.setAudioId(musicUpload.getId());
        musicDetailReturn.setAudioImg(musicImgParentPath+musicUpload.getPhotoPath());
        musicDetailReturn.setAudioName(musicUpload.getName());
        musicDetailReturn.setAudioTime(musicUpload.getUpTime());
        musicDetailReturn.setAudioUrl(musicParentPath+musicUpload.getSavePath());
        musicDetailReturn.setAuthorId(musicUpload.getUpUserId());
        musicDetailReturn.setDescription(musicUpload.getDescription());
        return musicDetailReturn;
    }
    public List<musicCommentReturn> getComments(Long id){
        QueryWrapper<musicComment> musicCommentQueryWrapper = new QueryWrapper<>();
        musicCommentQueryWrapper.eq("music_id",id).eq("level",1).orderByAsc("comment-time");
        List<musicComment> musicComments = musicCommentMapper.selectList(musicCommentQueryWrapper);
        //转return再查子评论
        ArrayList<musicCommentReturn> musicCommentReturns = new ArrayList<>();
        for (musicComment musiccomment : musicComments) {
            musicCommentReturns.add(commentToVo(musiccomment));
        }
        return musicCommentReturns;
    }
    public musicCommentReturn commentToVo(musicComment comment){
        musicCommentReturn musiccommentReturn = new musicCommentReturn();
        musiccommentReturn.setCommentTime(comment.getCommentTime());
        musiccommentReturn.setContent(comment.getContent());
        musiccommentReturn.setId(comment.getId());
        musiccommentReturn.setLevel(comment.getLevel());
        musiccommentReturn.setUpUserId(comment.getUpUserId());
        if(comment.getLevel()==1) {
            musiccommentReturn.setChildrenComment(getChildrenComment(comment.getMusicId(), comment.getId()));
        }
        return musiccommentReturn;
    }
    public List<musicCommentReturn> getChildrenComment(Long musicId,Long parentId){
        QueryWrapper<musicComment> musicCommentQueryWrapper = new QueryWrapper<>();
        musicCommentQueryWrapper.eq("music_id",musicId).eq("parent_id",parentId).eq("level",2).orderByAsc("comment-time");
        List<musicComment> musicComments = musicCommentMapper.selectList(musicCommentQueryWrapper);
        ArrayList<musicCommentReturn> musicCommentReturns = new ArrayList<>();
        for (musicComment musiccomment : musicComments) {
            musicCommentReturns.add(commentToVo(musiccomment));
        }
        return musicCommentReturns;
    }
}
