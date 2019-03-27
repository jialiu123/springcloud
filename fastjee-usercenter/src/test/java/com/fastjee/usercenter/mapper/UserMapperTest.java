package com.fastjee.usercenter.mapper;

import com.fastjee.common.entity.User;
import com.fastjee.common.entity.enums.UserStatus;
import com.fastjee.usercenter.UserCenterApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author by wenzewoo on 2018/2/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void findByUsername() throws Exception {

        User admin = userMapper.findByUsername("admin");

        System.out.println("admin = " + admin);

        admin.getRoleSet().forEach(System.out::println);
        Assert.assertEquals(admin.getStatus().getName(),"启用");
    }

    @Test
    public void testSave() {
        User user = new User()
            .setUsername("111")
            .setPassword("222")
            .setDescription("3333")
            .setNickname("4444")
            .setStatus(UserStatus.LOCKED);
        userMapper.insert(user);

        assert user.getId() != null;

        System.out.println("user = " + user);

        Assert.assertEquals("1:admin", user.getCreateAt());
        Assert.assertEquals(1, user.getStatus().getCode());
    }

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        System.out.println("encoder.encode(\"admin\") = " + encoder.encode("admin"));;
    }

}