package com.zlc.springboot;

import com.zlc.springboot.model.Soft;
import com.zlc.springboot.model.User;
import com.zlc.springboot.service.InitOrderService;
import com.zlc.springboot.service.IpAddressService;
import com.zlc.springboot.service.SoftService;
import com.zlc.springboot.unti.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    //注入RedisTemplate模板
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SoftService softService;
    @Autowired
    private InitOrderService initOrderService;
    @Autowired
    private IpAddressService ipAddressService;
    @Autowired
    private Md5Util md5;


    @Test
    void test1() {
        /** redisTemplate 操作不同的数据类型，API 和 Redis 中的是一样的
         * opsForValue 类似于 Redis 中的 String
         * opsForList 类似于 Redis 中的 List
         * opsForSet 类似于 Redis 中的 Set
         * opsForHash 类似于 Redis 中的 Hash
         * opsForZSet 类似于 Redis 中的 ZSet
         * opsForGeo 类似于 Redis 中的 Geospatial
         * opsForHyperLogLog 类似于 Redis 中的 HyperLogLog
         */
        redisTemplate.opsForValue().set("key","haha");
        String value = (String) redisTemplate.opsForValue().get("key");
        System.out.println("value: " + value);
    }

    @Test
    void Test(){
        //实体类写入到Redis
        List<User> userList = new ArrayList<>();
        User user = new User("小明","20");
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        //直接传入一个对象
        redisTemplate.opsForValue().set("userlist",userList);
        System.out.println(redisTemplate.opsForValue().get("userlist"));
    }

    //测试接口缓存
    @Test
    void CacheTest(){
        List<Soft> softList = softService.selectSoftAll("allSoft");
        softList.forEach(soft -> System.out.println(soft));
        System.out.println("走数据库-->");

        List<Soft> softList2 = softService.selectSoftAll("allSoft");
        System.out.println("走缓存-->");
        softList2.forEach(soft -> System.out.println(soft));

    }

    @Test
    void test3(){
      String str = "123";
      String str2 = Md5Util.encodeByMD5(str).substring(0,15);
        System.out.println(str);
        System.out.println(str2);
    }
}
