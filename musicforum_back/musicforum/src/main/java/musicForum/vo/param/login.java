package musicForum.vo.param;

import lombok.Data;

@Data
public class login {
    private Long id;
    private String account;
    private String nickname;
    private String password;

}
