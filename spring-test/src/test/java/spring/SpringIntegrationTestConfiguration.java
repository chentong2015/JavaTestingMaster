package spring;

import org.springframework.boot.autoconfigure.jmx.ParentAwareNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.naming.ObjectNamingStrategy;

@Configuration
public class SpringIntegrationTestConfiguration {

    // Start 2 spring boot applications in single test case 配置允许同一个测试中启动两个Spring boot应用
    // 模拟一个作为client端 + Server端(提供Controller)
    @Bean
    ObjectNamingStrategy objectNamingStrategy() {
        ParentAwareNamingStrategy namingStrategy =
                new ParentAwareNamingStrategy(new AnnotationJmxAttributeSource());
        namingStrategy.setEnsureUniqueRuntimeObjectNames(true);
        return namingStrategy;
    }
}
