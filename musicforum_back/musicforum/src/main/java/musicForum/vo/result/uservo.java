package musicForum.vo.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class uservo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String nickname;
    private String account;
    private Integer age;
    private String sex;
    private String introduction;
    private Integer show;
    //头像，有一个默认头像,写在配置文件中
    private String avator;
}
