package team.cbk.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.cbk.demo.mapper.UserMapper;
import team.cbk.demo.pojo.User;

//@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {

        int da1 = userMapper.updateStatusByUserName("da1");
        System.out.println(da1);
    }

}