package tw.core;

import org.junit.Test;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    @Test
    public void should_return_false_when_input_count_is_not_4() throws Exception {
        InputValidator validator=new InputValidator();
        assertFalse(validator.validate("1 2 3"));
        assertFalse(validator.validate("1 2 3 4 5"));
    }

    @Test
    public void should_return_false_when_input_contains_number_grater_than_9() throws Exception {
        InputValidator validator=new InputValidator();
        assertFalse(validator.validate("1 2 3 10"));
        assertFalse(validator.validate("1 2 3 20"));
    }

    @Test
    public void should_return_true_when_input_count_is_correct() throws Exception {
        InputValidator validator=new InputValidator();
        assertTrue(validator.validate("1 2 3 4"));
        assertTrue(validator.validate("0 3 2 5"));
    }
}
