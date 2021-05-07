package gkyrqy.springboot.service;

import gkyrqy.springboot.interface_1.Demo;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class DemoB implements Demo, BeanNameAware {

    @Override
    public void sayHello() {
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("DemoB");
    }

}
