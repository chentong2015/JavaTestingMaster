package spring;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseSpringBootTesting.class,
        properties = {
                "spring.security.user.name=test",
                "spring.security.user.password=test123"
        })
// TODO. 可以在测试中使用测试的值来设置properties
public class SpringIocExampleTest {

    // DataSource在APP启动的时候自动注入IoC容器中
    // 测试的时候直接Autowired，从IoC中获取bean对象
    // 然后测试对象的属性
    // @Autowired
    // DataSource dataSource;
    //
    // @Test
    // void dataSource() {
    //     String url = "jdbc:mysql://localhost:3306/test";
    //     DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource) dataSource;
    //     Assertions.assertEquals(url, driverManagerDataSource.getUrl());
    // }
}
