package gkyrqy.test.scan;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Demo {
    @Value("${test:aaa}")
    public String test;
    @Autowired
    public DemoNameAware demoNameAware;
    @Autowired
    public BeanFactory beanFactory;
    @Autowired
    public DemoTwo demoTwo;
    @Override
    public String toString() {
        return "Demo";
    }
}
