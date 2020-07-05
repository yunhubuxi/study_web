package gkyrqy.springboot.service;

import gkyrqy.springboot.interface1.Demo;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DemoA implements Demo , BeanNameAware {

    @Value("${test}")
    private String test;

    public void sayHello() {
        System.out.println(test + "sayHello");
        System.out.println("hello, I an DemoA");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("set DemoA BeanName");
    }
}
