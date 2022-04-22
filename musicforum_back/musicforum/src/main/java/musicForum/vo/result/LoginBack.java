package musicForum.vo.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginBack {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String nickname;
    private String account;
}
