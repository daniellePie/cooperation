### 音频卡片前后端交互
- 前端负责人：何梓欣
- 后端处理人：刘梓池
- **服务均未上线**

前端需求服务：
1. 返回当前用户数据
HTTP请求：GET
url: 47.103.149.25:8081/music/user/detail/{id}
传入参数：用户id在路径中传入
返回数据：
 {
    "code":200,
    "msg":"success",
    data:{
       "id":,
       "account":,
       "nickname":,
       "phone":,
       "email":,
       "sex":,
       "createTime":,
       "birthday":,
       "introduction":,
       "avatar":,
    }
 }
用户不存在：
{
    "code":10002,
    "msg":"用户不存在",
    data:
 }

2.提交更改过的内容
HTTP请求：POST
url: 47.103.149.25:8081/music/user/modify
传入参数：与服务一传入参数名称相同，不用传入头像，建议修改头像单独一个接口服务
返回数据：
 {
    "code":200,
    "msg":"success",
    data:
 }
 用户不存在：
{
    "code":10002,
    "msg":"用户不存在",
    data:
 }
- **后端必填项不是很多，对于空我认为可以前端处理，特殊情况，或是对于手机号，eamil这样的信息，可以前端尝试限制，需要的话之后后端再加入对信息的检查**