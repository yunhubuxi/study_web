package gkyrqy.springboot.importconfig;

import gkyrqy.test.impor.DemoImportOne;
import gkyrqy.test.impor.DemoImportThree;
import gkyrqy.test.impor.DemoImportTwo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *  1，import注解不能单独在某个类上使用，必须配个 @Configuration或者@Component及其派生注解使用
 *  2，import注解导入普通类的时候，普通类不需要添加@Component注解
 *  3，优先考虑主配置类上的扫描路径，之后才处理Import导入
 *  4，import可用在
 *
 */

@Import({DemoImportOne.class, DemoImportTwo.class, DemoImportThree.class})
 @Configuration
public class ApplicationConfig {
}
