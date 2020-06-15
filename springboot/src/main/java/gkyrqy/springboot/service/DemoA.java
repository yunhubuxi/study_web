package gkyrqy.springboot.service;

import gkyrqy.springboot.interface1.Demo;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
@Scope("prototype")
public class DemoA  implements Demo , BeanNameAware {

    @Value("${test}")
    private String test;

    public BaseDemo getBaseDemo() {
        return baseDemo;
    }

    public void setBaseDemo(BaseDemo baseDemo) {
        this.baseDemo = baseDemo;
    }

    @Autowired
    BaseDemo baseDemo;

    @Override
    public void sayHello() {
        System.out.println(test + "sayHello");
        System.out.println(baseDemo.hashCode());
        System.out.println("hello, I an DemoA");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("fffffffffffffffffffffff");
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
