package musicForum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class friends {
    private Long user1Id;
    private Long user2Id;
    private Integer status;
}
