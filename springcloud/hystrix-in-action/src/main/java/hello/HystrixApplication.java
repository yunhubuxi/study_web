package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixApplication {

    @Autowired
    HystrixService hystrixService;

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class);
    }

    @RequestMapping("/hi")
    public String hi() {
        return hystrixService.hi();
    }
}
