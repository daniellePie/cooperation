package musicForum.vo.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class musicDetailReturn {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long audioId;
    private String audioUrl;
    private String audioImg;
    private Date audioTime;
    private String audioName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long authorId;
    private String authorName;
    private String description;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long likeNum;
    private Integer isMyLike; //0代表不喜欢，1代表喜欢
    @JsonSerialize(using = ToStringSerializer.class)
    private Long favNum;
    private Integer isMyFav; //0代表没有收藏，1代表收藏
    //comment处理
    private List<musicCommentReturn> comments;
}
