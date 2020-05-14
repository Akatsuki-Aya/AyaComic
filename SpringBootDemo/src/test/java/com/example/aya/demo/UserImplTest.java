package com.example.aya.demo;


import com.example.aya.demo.dao.*;
import com.example.aya.demo.dao.impl.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserImplTest {
    @Autowired
    private UserImpl userImpl;
    @Autowired
    private ComicImpl comicImpl;
    @Autowired
    private ComicCollectImpl comicCollect;
    @Autowired
    private ComicHistoryImpl comicHistoryImpl;
    @Autowired
    private ComicDetailImpl comicDetailImpl;
    @Autowired
    private UpComicImpl upComicImpl;
    @Test
    public void test() throws Exception{
        List<UpComic> byUserId = upComicImpl.findByUserId(15L);
        System.out.println(byUserId.size());


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
