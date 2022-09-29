package team.cbk.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cbk.demo.mapper.UserMapper;
import team.cbk.demo.pojo.User;
import team.cbk.demo.service.UserService;
import team.cbk.demo.utils.Base64Utils;
import team.cbk.demo.utils.Constants;
import team.cbk.demo.utils.EmailUtils;
import team.cbk.demo.utils.MD5Utils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkedUser(String username) {


        User user=userMapper.selectUserByname(username);

        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public int registerUser(User user) {

        int i = userMapper.insertUser(user);

        EmailUtils.sendEmail(user);

        return i;
    }

    @Override
    public int activeUser(String code,String username) {

        User user = userMapper.selectUserByCode(code);
        if (user == null || !(user.getUserName().equals(username))){
            return Constants.ACTIVE_FAIL;
        }

        if (user.getUserStatus().equals(Constants.USER_ACTIVE)) {
            return Constants.ACTIVE_ALREADY;
        }

        int i = userMapper.updateStatusByUserName(user.getUserName());

        if (i > 0) {
            return Constants.ACTIVE_SUCCESS;
        }

        return Constants.ACTIVE_FAIL;
    }

    @Override
    public User login(String username, String password){

        String md5password = MD5Utils.md5(password);

        User user = userMapper.selectUserByname(username);

        if (user!=null && user.getUserPassword().equals(md5password)){
            return user;
        }
        return null;
    }

    @Override
    public int foget(String userName, String mail) {
        User user = userMapper.selectUserByname(userName);

        if (user!=null && user.getUserEmail().equals(mail)){
            String Verification = EmailUtils.createCode(userName, mail);
            int i = userMapper.updateVerificationByUserName(userName, Base64Utils.decode(Verification));
            if (i>0)
            return Constants.NAME_AND_MAIL_CORRECT;
            else
                return Constants.UNKNOW_ERROR;
        }
        return Constants.NAME_OR_MAIL_ERROR;
    }

    @Override
    public int verify(String username, String verification) {
        User user = userMapper.selectUserByname(username);
        if (user!=null && user.getUserVerification().equals(verification)){
            userMapper.updateVerificationByUserName(username,null);
            return Constants.VERIFY_TRUE;
        }
        return Constants.VERIFY_FALSE;
    }

    @Override
    public int updataPassword(String name, String password1) {
        int i = userMapper.updatePasswordByUserName(name, password1);
        if (i>0){
            return Constants.UPDATE_PASSWORD_SUCCESS;
        }
        return Constants.UPDATE_PASSWORD_FAIL;
    }
}
