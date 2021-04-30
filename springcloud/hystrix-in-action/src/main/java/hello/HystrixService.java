package hello;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixService {

    @HystrixCommand(fallbackMethod = "fallback")
    public String hi() {
        return new RestTemplate().getForObject("http://localhost:8088/hello", String.class);
    }

    public String fallback() {
        return "fallback_aaa";
    }
}
