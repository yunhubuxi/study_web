package autoconfig;

import com.lianggzone.springboot.autoconfig.author.AuthorServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class Demo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Demo.class, args);
        AuthorServer authorServer = applicationContext.getBean(AuthorServer.class);
        System.out.println(authorServer.getAuthor());
    }
}
