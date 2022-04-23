package musicForum.utils;

public enum ErrorCode {
    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户不存在"),
    ACCOUNT_ALREADY_EXIST(10003,"账号已经存在，注册失败"),
    ACCOUNT_Friend_ID_Not_EXIST(20001,"关注者不存在"),
    ACCOUNT_Friend_ALREADY_EXIST(20002,"已关注"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),;
    private int code;
    private String msg;
    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}