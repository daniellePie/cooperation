package musicForum.vo.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDetailReturn {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String account;
    private String nickname;
    private String phone;
    private String email;
    private String sex;
    private Date createTime;
    private String birthday;
    private String introduction;
    // private Integer show; 暂时不处理权限
    //头像，有一个默认头像,写在配置文件中
    private String avatar;
}
