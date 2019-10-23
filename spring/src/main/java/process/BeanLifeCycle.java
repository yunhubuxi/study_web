package process;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycle {

    public static void main(String[] args) {

        System.out.println("===================现在开始初始化容器===================");
        // BeanFactory factory = new XmlBeanFactory(new ClassPathResource("springBeanTest/beans.xml"));
        // System.out.println("容器初始化成功");
        // Object beanFactoryPostProcessor= factory.getBean("beanFactoryPostProcessor");
        // Person person = (Person) factory.getBean("FactoryBeanPersonTest");
        // System.out.println(person);
        // System.out.println("现在开始关闭容器！");
        // ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springBeanTest/beans.xml");
        System.out.println("===================容器初始化成功===================");
        // Person person = applicationContext.getBean("person", Person.class);
        // System.out.println(person);
        // System.out.println("===================现在开始关闭容器===================");
        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
    }
}
