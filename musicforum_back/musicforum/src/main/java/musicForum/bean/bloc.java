package musicForum.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class bloc {
    @TableId
    private Long id;

    private Long userId;
    private Long musicId;
    private String text;
    private Date upTime;
    private Integer favNum;
    private Integer likeNum;
    private Integer permission;
}
