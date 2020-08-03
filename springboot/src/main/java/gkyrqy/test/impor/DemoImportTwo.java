package gkyrqy.test.impor;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class DemoImportTwo implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"gkyrqy.test.impor.ImportTest"};
    }
}
