package rules;

import com.junit4.testing.MyServiceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Junit4提供的预定义规则Rules，作为类库的一部分
// 1. a number of useful, predefined rules as part of the library.
// 2. enhance tests by running some code around a test case execution
// 3. 类似于@Before and @After annotations注解的作用
// 4. 在多个Test classes中都需要连接和关闭资源，则可以通过Rule将改操作隔离出来，重用
public class Junit4TestRules {

    // 1. 测试测试获取方法的名称
    // TODO. @Rule注解作用的类型必须是实现了org.junit.rules.TestRule的类型
    @Rule
    public TestName name = new TestName();

    @Test
    public void testMethodName() {
        assertEquals("test Method Name", name.getMethodName());
    }

    // 2. 测试异常输出的Rule
    // use the ExpectedException rule to verify that some code throws an expected exception
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throw_exception_with_expected() throws MyServiceException {
        exception.expect(MyServiceException.class);
        exception.expectCause(isA(NullPointerException.class));
        exception.expectMessage("This is illegal");

        // mock the method called to throw exception
        throw new MyServiceException();
    }

    // 3. 使用临时文件夹，不用考虑文件夹的创建和删除(在测试结束的时候自动删除)
    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void givenTempFolderRule_whenNewFile_thenFileIsCreated() throws IOException {
        File testFile = tmpFolder.newFile("test-file.txt");
        File folder = tmpFolder.newFolder("/test");
        assertTrue("The file should have been created: ", testFile.isFile());
        assertEquals("Temp folder and test file should match: ", tmpFolder.getRoot(), testFile.getParentFile());
    }

    // 4. 定义一个全局的Timeout, 单元测试的时间不超过设置的时间
    @Rule
    public Timeout globalTimeout = new Timeout(10);

    @Test
    public void givenLongRunningTest_whenTimout_thenTestFails() throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
    }

    // 5. Collect all the errors and report them all at once when the test terminates
    @Rule
    public final ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void givenMultipleErrors_whenTestRuns_thenCollectorReportsErrors() {
        errorCollector.addError(new Throwable("First thing went wrong!"));
        errorCollector.addError(new Throwable("Another thing went wrong!"));
        errorCollector.checkThat("Hello World", not(containsString("ERROR!")));
    }

    // 6. 自定义一个测试的外部资源
    // Set up an external resource before a test, such as a file or a database connection
    @Rule
    public final ExternalResource externalResource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            // code to set up a specific external resource.
        }

        @Override
        protected void after() {
            // code to tear down the external resource
        }
    };
}