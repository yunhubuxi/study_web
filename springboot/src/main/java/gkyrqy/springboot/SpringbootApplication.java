package gkyrqy.springboot;

import author.AuthorServer;
import gkyrqy.springboot.interface1.Demo;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * 1,测试替换beandefinition，在同一个配置文件中，就不能替换，因为已经存在，无法注册
 *   如果非要替换，则把原来的移除，重新注入
 * 2,bean有个属性，可以配置是否可以被替换
 * 3,如果不在一个配置文件里，后注册的默认会替换掉先注册的
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootApplication.class, args);
        AuthorServer authorServer = applicationContext.getBean(AuthorServer.class);
        System.out.println(authorServer.getAuthor());
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(
                gkyrqy.springboot.service_1.DemoA.class);
        defaultListableBeanFactory.removeBeanDefinition("demoA");
        defaultListableBeanFactory.registerBeanDefinition("demoA",
                beanDefinitionBuilder.getBeanDefinition());
        Demo demo = (Demo)applicationContext.getBean("demoA");
        demo.sayHello();
    }
}
