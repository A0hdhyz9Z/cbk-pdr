package team.cbk.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.cbk.demo.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();


    User selectUserByname(@Param("username") String username);

    int insertUser(@Param("user") User user);

    User selectUserByCode(@Param("code") String code);

    int updateStatusByUserName(@Param("username") String userName);

    int updateVerificationByUserName(@Param("username") String userName,@Param("userverification") String decode);

    int updatePasswordByUserName(@Param("username") String name,@Param("password") String password1);

}
