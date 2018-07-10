package redis;

import com.silita.biaodaa.service.ConfigTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by dh on 2018/7/6.
 */
public class TestRedis extends ConfigTest {

    @Autowired
    @Qualifier("jedisTemplate")
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("jedisStringTemplate")
    RedisTemplate jedisStringTemplate;

    @Test
    public void testRedisString(){
        jedisStringTemplate.opsForValue().set("ddddtet","11123234",100, TimeUnit.SECONDS);
    }
}
