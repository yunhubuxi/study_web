package gkyrqy.springboot.service;

import org.springframework.beans.factory.InitializingBean;

public class UnifiedMetaBase implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UnifiedMetaBase afterPropertiesSet");
    }
}
