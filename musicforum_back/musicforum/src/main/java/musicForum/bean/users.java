package musicForum.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class users {
    @TableId
    private Long id;

    private String account;
    private String nickname;
    private String password;
    private String phone;
    private String email;
    private Integer age;
    private String sex;
    private Date createTime;
    private String birthday;
    private String introduction;
    private Integer show;
    //头像，有一个默认头像,写在配置文件中
    private String avatar;

}
