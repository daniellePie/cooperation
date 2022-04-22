package musicForum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class friends {
    private String user1_id;
    private String user2_id;
    private Integer status;
}
