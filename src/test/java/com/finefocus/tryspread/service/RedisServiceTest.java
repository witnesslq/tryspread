package com.finefocus.tryspread.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/7/18
 * @Description: ${todo}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void testDelete() throws Exception {

        Long delete = redisService.delete("redis_apk_download_apkid_" + "*");
        System.out.println(delete);
    }

    private void batchDel(Jedis jedis) {
        Set<String> set = jedis.keys("redis_apk_download_apkid_" + "*");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String keyStr = it.next();
            System.out.println(keyStr);
            jedis.del(keyStr);
        }
    }
}