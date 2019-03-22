package testimport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.EventObject;

@Import(Demo2.class)
@Configuration
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("testimport");
        DemoService ds = context.getBean(DemoService.class);
        Demo2 demo2 = context.getBean(Demo2.class);
        demo2.doSomething();
        ds.doSomething();
        EventObject eventObject;
    }


}
