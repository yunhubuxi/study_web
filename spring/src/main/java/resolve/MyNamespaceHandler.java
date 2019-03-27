package resolve;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {

        registerBeanDefinitionParser("user2", new UserBeanDefinitionParser());
    }

}