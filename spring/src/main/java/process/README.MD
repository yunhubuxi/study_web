Bean的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：

1、Bean自身的方法：这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法

2、Bean级生命周期接口方法：这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法

3、容器级生命周期接口方法：这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。

4、工厂后处理器接口方法：这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。

5、如果使用ApplicationContext来维护一个Bean的生命周期，则基本上与上边的流程相同，只不过在执行BeanNameAware的setBeanName()后，若有Bean类实现了org.springframework.context.ApplicationContextAware接口，则执行其setApplicationContext()方法，然后再进行BeanPostProcessors的processBeforeInitialization()
  实际上，ApplicationContext除了向BeanFactory那样维护容器外，还提供了更加丰富的框架功能，如Bean的消息，事件处理机制等。
  
需要改进的地方:
1、没有实现FactoryBean，来自定义Person的初始化。
2、就这段代码，有些不是很了解。
3、BeanWrap是怎么一回事，这里也要测试。

总结：
1、主要就beans，core，context这三个包，内容也不多，express包几乎没有用到。
   一定要把这三个包理清楚。aop是基于这三个包来做的。
2、我一定要找出spring不好的地方，比如说这些后处理器，有几个后处理器，个子在什么时候执行。