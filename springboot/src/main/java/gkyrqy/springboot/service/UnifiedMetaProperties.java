package gkyrqy.springboot.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class UnifiedMetaProperties implements InitializingBean {
    @Value("${metadata.api.url}")
    private String metadataApiUrl;

    private static UnifiedMetaProperties instance;

    public static UnifiedMetaProperties getInstance() {
        if (instance == null) {
            return new UnifiedMetaProperties();
        } else {
            return instance;
        }
    }

    private UnifiedMetaProperties() {
        instance = this;
    }

    public String getMetadataApiUrl() {
        return this.metadataApiUrl;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UnifiedMetaProperties afterPropertiesSet");
    }
}
