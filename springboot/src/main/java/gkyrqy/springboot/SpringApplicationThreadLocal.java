package gkyrqy.springboot;

import gkyrqy.springboot.service.UnifiedMetaProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Iterator;

@SpringBootApplication
@PropertySource(value = {
        "classpath:bb.properties",
        "classpath:cc.properties"
})
public class SpringApplicationThreadLocal {

    // @Value("${test}")
    // private String test;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringApplicationThreadLocal.class);
        Iterator<org.springframework.core.env.PropertySource<?>> iterator = applicationContext.getEnvironment().getPropertySources().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}
