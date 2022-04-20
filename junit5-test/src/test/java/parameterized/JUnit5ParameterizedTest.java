package parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class JUnit5ParameterizedTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @ParameterizedTest
    @NullAndEmptySource // @NullSource + @EmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    // 使用方法作为测试源，对参数数据进行加工，多次测试Case
    static IntStream range() {
        return IntStream.range(0, 20).skip(10); // 取11 - 19的区间值
    }

    /**
     * Country, reference > 跳过第一行进行读取
     * Sweden, 1
     * Poland, 2
     * USA, 3
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/JavaUnitTestExceptions.test.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromClasspath(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }
}