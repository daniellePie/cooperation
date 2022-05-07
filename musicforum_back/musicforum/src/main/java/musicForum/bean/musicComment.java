package musicForum.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class musicComment {
    @TableId
    private Long id;

    private Long upUserId;
    private String content;
    private Date commentTime;
    private Long musicId;
    private Long toUserId;
    private Long parentId;
    private Integer level;
}
