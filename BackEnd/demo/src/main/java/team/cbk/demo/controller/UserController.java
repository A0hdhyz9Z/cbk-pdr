package team.cbk.demo.controller;

import team.cbk.demo.pojo.RespBean;
import team.cbk.demo.pojo.User;
import team.cbk.demo.service.UserService;

public class UserController {

    private UserService userService;

    public RespBean register(@PathVariable("name") String name, @PathVariable("password1") String password1,
                             @PathVariable("password2") String password2, @PathVariable("mail") String mail){

        if(name==null || password1==null || password2==null || mail==null){
            return RespBean.error("输入框中存在空值");
        }
        if (!password1.equals(password2)){
            return RespBean.error("密码不一致");
        }
        if (name.length() <6 || name.length() > 16){
            return RespBean.error("用户名的长度为6-16个字符");
        }
        if (password1.length()<6 || password1.length()>25){
            return RespBean.error("密码长度位6-25个字符");
        }
        if (!EmailCheckUtils.isEmail(mail)){
            return RespBean.error("邮箱格式错误");
        }
        boolean b = userService.checkedUser(name);
        if (b){
            //用户存在
            return RespBean.error("用户名已存在");
        }

        User user = new User(name, password1, mail, null, null,null);
        user.setUserStatus(Constants.USER_NOT_ACTIVE);//默认未被激活
        user.setUserCode(RandomUtils.createActive());//邮箱激活代码
        user.setUserPassword(MD5Utils.md5(user.getUserPassword()));//处理密码为MD5格式存储

        int i =userService.registerUser(user);
        if (i==0){
            return RespBean.success("注册失败，请检查网络连接");
        }
        return RespBean.success("注册成功，请激活账户");
    }
    public RespBean active(@ApiParam("用户名") @PathParam("username") String username,@ApiParam("激活码") @PathParam("c") String c){

        String code = Base64Utils.decode(c);
        int i = userService.activeUser(code,username);
        if (i ==Constants.ACTIVE_FAIL) {
            return RespBean.error("无效激活码");
        }else if (i== Constants.ACTIVE_SUCCESS){
            return RespBean.success("激活成功",username);
        }else {
            return RespBean.error("重复激活",username);
        }
    }

    public RespBean login(HttpServletRequest req,@PathVariable("username") String userName,@PathVariable("password") String password){
        if (userName == null){
            return RespBean.error("请输入用户名");
        }
        if (password==null){
            return RespBean.error("请输入密码");
        }
        HttpSession session = req.getSession();
        User user = userService.login(userName, password);

        if (user == null){
            return RespBean.error("账号或者密码错误");
        }
        if (user.getUserStatus().equals(Constants.USER_NOT_ACTIVE)){
            return RespBean.error("账号未激活");
        }

        session.setAttribute("loginUser",user);

        return RespBean.success("登陆成功",user);
    }

    public RespBean forgetPassword(@PathVariable("username") String userName,@PathVariable("mail") String mail){
        if (userName == null){
            return RespBean.error("请输入用户名");
        }
        if (mail==null){
            return RespBean.error("请输入邮箱地址");
        }
        if (!EmailCheckUtils.isEmail(mail)){
            return RespBean.error("邮箱格式错误");
        }
        int foget = userService.foget(userName, mail);
        if (foget==Constants.UNKNOW_ERROR){
            return RespBean.error("发生了一个未知错误");
        }else if(foget==Constants.NAME_OR_MAIL_ERROR){
            return RespBean.error("用户名或邮箱输入错误");
        }else
            return RespBean.success("验证码已发送到用户邮箱");
    }

    public RespBean verify(HttpServletRequest req,@PathVariable("username") String username,@PathVariable("verification") String verification){
        if (username==null){
            return RespBean.error("请输入用户名");
        }
        if (verification==null){
            return RespBean.error("请输入验证码");
        }
        int verify = userService.verify(username, Base64Utils.decode(verification));
        if (verify==Constants.VERIFY_FALSE){
            return RespBean.error("验证码输入错误");
        }
        HttpSession session = req.getSession();
        session.setAttribute("verify",username);
        return RespBean.success("验证码正确");
    }

    public RespBean updatePassword(HttpServletRequest req,@PathVariable("password1") String password1,@PathVariable("password2") String password2){
        HttpSession session = req.getSession();
        String name;
        String verify = (String) session.getAttribute("verify");
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null && verify == null){
            return RespBean.error("非法访问");
        }
        if (loginUser != null)
            name=loginUser.getUserName();
        else name=verify;

        if (!password1.equals(password2)){
            return RespBean.error("密码不一致");
        }
        if (password1.length()<6 || password1.length()>24){
            return RespBean.error("密码长度位6-24个字符");
        }
        int i = userService.updataPassword(name, MD5Utils.md5(password1));
        if (i==Constants.UPDATE_PASSWORD_FAIL){
            return RespBean.error("密码修改失败，未知原因");
        }
        return RespBean.success("密码修改成功");
    }
}
