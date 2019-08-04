package resolve;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Sample {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test.xml");
        User user = (User) applicationContext.getBean("testbean");
        System.out.println("username:" + user.getUserName() + "  " + "email:" + user.getEmail());
    }
}
