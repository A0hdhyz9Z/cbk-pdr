package team.cbk.demo.controller;

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
        if (password1.length()<6 || password1.length()>24){
            return RespBean.error("密码长度位6-24个字符");
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
        us
            return RespBean.error("请输入密码");
        }
        HttpSe

        return RespBean.success("登陆成功",user);
    }

            return RespBean.error("邮箱格式错误");
        

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
