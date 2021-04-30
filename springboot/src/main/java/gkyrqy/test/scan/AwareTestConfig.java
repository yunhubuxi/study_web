package gkyrqy.test.scan;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwareTestConfig {
    @Bean(autowire = Autowire.NO)
    public Demo demo() {
        System.out.println("==========demo==========");
        return new Demo();
    }
}
