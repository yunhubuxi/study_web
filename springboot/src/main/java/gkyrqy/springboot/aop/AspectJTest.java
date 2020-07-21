package gkyrqy.springboot.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectJTest {

    @Pointcut("execution(* gkyrqy.springboot.aop.TestBean.test())")
    public void handle() {
    }

    @Before("handle()")
    public void beforTest() {
        System.out.println("==================before==================");
    }

    @After("handle()")
    public void afterTest() {
        System.out.println("==================after==================");
    }

}


