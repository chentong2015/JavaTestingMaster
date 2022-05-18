package rules;

import com.junit4.testing.ConsoleOutTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static org.junit.Assert.assertTrue;

public class Junit4TestRulesChains {

    // 设置TestRule的链条，在测试过程中指定"从内向外"的输出顺序，先处理内部的逻辑
    @Rule
    public RuleChain chain = RuleChain.outerRule(new ConsoleOutTestRule("First rule"))
            .around(new ConsoleOutTestRule("Second rule"))
            .around(new ConsoleOutTestRule("Third rule"));

    // Third rule: Before test : example
    // Third rule: After test : example
    // Second rule: Before test : example
    // Second rule: After test : example
    // First rule: Before test : example
    // First rule: After test : example
    // Test Started .
    @Test
    public void example() {
        System.out.println("Test Started .");
        assertTrue(true);
    }
}