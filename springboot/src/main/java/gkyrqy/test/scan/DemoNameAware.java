package gkyrqy.test.scan;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class DemoNameAware implements BeanNameAware {
    @Override
    public String toString() {
        return "DemoNameAware";
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
