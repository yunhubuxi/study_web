package gkyrqy.springbootdubboclient.configuration;


import org.springframework.context.annotation.Configuration;

/**
 * 完全使用了springboot的自动配置，所以不需要手动配置
 * 自动配置会自动匹配属性
 */
@Configuration
public class DubboConfiguration {

//    @Bean
//    public ConsumerConfig consumerConfig() {
//        ConsumerConfig consumerConfig = new ConsumerConfig();
//        consumerConfig.setTimeout(3000);
//        return consumerConfig;
//    }
//
//    @Bean
//    public RegistryConfig registryConfig() {
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://172.20.56.136:2181");
//        return registryConfig;
//    }

}
