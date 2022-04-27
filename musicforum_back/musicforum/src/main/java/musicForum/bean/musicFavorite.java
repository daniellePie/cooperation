package musicForum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class musicFavorite {
    private Long userId;
    private Long musicId;
    private Date favTime;
    private Integer type;
}
