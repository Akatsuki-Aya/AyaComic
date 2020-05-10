package com.example.aya.demo;


import com.example.aya.demo.dao.User;
import com.example.aya.demo.dao.impl.UserImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserImplTest {
    @Autowired
    private UserImpl userImpl;
    @Test
    public void test() throws Exception{
        Date date = new Date();
        DateFormat dateFormat =DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        /*userImpl.save(new User("aa1", "aa@126.com", "aa", "aa123456",formattedDate));
        userImpl.save(new User("bb2", "bb@126.com", "bb", "bb123456",formattedDate));
        userImpl.save(new User("cc3", "cc@126.com", "cc", "cc123456",formattedDate));
        Assert.assertEquals("bb2",userImpl.findByUserNameOrEmail("bb","xxx126.com").getNickName());
        userImpl.delete(userImpl.findByUserName("aa"));*/

    }

    @Test
    public void GenderTest() {
        User user = new User("aya", "111111", "jxw", "310105192401151248", "18930378979", "xiaowen@qq.com", 0);
        System.out.println(user);
    }
}
