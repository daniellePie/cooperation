package musicForum.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class musicUpload {
    @TableId
    private Long id;

    private Long upUserId;
    private String name;
    private Date upTime;
    private String savePath;
    private String photoPath;
    private String description;
    private Integer type;
    private Integer duration;
    private Integer permission;
}
