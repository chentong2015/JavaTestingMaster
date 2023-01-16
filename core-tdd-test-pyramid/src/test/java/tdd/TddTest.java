package tdd;

import org.junit.Assert;
import org.junit.Test;

// TODO. TDD的本质 最佳实践
// - 通过单元测试来驱动源码的开发，通过让单元测试通过来迭代项目源码
// - 直接修改单元测试的逻辑而使其通过是不合理的做法 !!
// - 所有的测试之所以能通过，全都是建立在源码的基础上

// 1. 先写测试代码
// 2. 再写生产代码使得测试通过，有了测试代码，才能更好的设计生产代码
// 3. 再refactoring重构生产代码
public class TddTest {

    @Test
    public void test_add() {
        Calculator calculator = new Calculator();
        int result = calculator.add(100, 100);
        Assert.assertEquals(200, result);
    }

    @Test
    public void test_average() {
        Calculator calculator = new Calculator();
        int[] array = {1, 3, 4, 8};
        double average = calculator.average(array);
        Assert.assertEquals(4, average, 0.0);
    }

    @Test
    public void test_average2() {
        Calculator calculator = new Calculator();
        int[] array = new int[10];
        double average = calculator.average(array);
        Assert.assertEquals(0, average, 0.0);
    }

    @Test
    public void test_find_score_range() throws Exception {
        Calculator calculator = new Calculator();
        String result = calculator.findScoreRange(65);
        Assert.assertEquals("good", result);
    }

    @Test
    public void test_find_score_range_with_exception() throws Exception {
        Calculator calculator = new Calculator();
        // We can pass a lambda expression as implementation of the interface
        // We check and make sure it will throw this type of exception.
        Assert.assertThrows(Exception.class, () -> calculator.findScoreRange(200));
    }
}
