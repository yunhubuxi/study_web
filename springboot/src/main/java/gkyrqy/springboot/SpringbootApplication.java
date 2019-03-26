package gkyrqy.springboot;

import author.AuthorServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootApplication.class, args);
        AuthorServer authorServer = applicationContext.getBean(AuthorServer.class);
        System.out.println(authorServer.getAuthor());
    }


}
