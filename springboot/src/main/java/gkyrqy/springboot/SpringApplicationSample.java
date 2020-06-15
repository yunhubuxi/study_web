package gkyrqy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApplicationSample {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringApplicationSample.class);
        System.out.println(SpringApplicationSample.class.getResource("/"));
    }
}
