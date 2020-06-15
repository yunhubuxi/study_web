package provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
        context.start();
        DemoServiceImpl demoService1 = context.getBean(DemoServiceImpl.class);
        System.out.println(demoService1.hashCode());
        DemoServiceImpl demoService2 = context.getBean(DemoServiceImpl.class);
        System.out.println(demoService2.hashCode());
        System.in.read();
    }
}
