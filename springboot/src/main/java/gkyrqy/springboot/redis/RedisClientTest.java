package gkyrqy.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@EnableCaching
public class RedisClientTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    public static void main(String[] args) {
        SpringApplication.run(RedisClientTest.class);
    }

}
