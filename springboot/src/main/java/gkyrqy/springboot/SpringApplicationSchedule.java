package gkyrqy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
    exclude = {RedisAutoConfiguration.class},
    scanBasePackages = {"gkyrqy.springboot.schedule"}
    )
@EnableScheduling
public class SpringApplicationSchedule {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationSchedule.class);
    }
}
