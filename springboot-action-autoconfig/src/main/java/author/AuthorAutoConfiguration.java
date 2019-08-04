package author;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass({ AuthorServer.class })
@EnableConfigurationProperties(AuthorProperties.class)
public class AuthorAutoConfiguration {

    @Resource
    private AuthorProperties authorProperties;
    
    @Bean
    @ConditionalOnMissingBean(AuthorServer.class)
    @ConditionalOnProperty(name = "custom.author.enabled", matchIfMissing = true)
    public AuthorServer authorResolver() {
        AuthorServer authorServer = new AuthorServer();
        authorServer.setAuthor(authorProperties.getAuthor());
        return authorServer;
    }
}
