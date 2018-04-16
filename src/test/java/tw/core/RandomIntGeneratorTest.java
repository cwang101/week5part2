package tw.core;


import org.junit.Test;
import tw.core.generator.RandomIntGenerator;
import tw.validator.InputValidator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Test
    public void should_throw_exception_when_digitmax_less_than_need() {
        RandomIntGenerator generator =new RandomIntGenerator();
        try{
            generator.generateNums(3,5);
            fail("no exception thrown.");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertTrue(e.getMessage().endsWith("Can't ask for more numbers than are available"));
        }
    }

    @Test
    public void generate_num_test() {
        RandomIntGenerator generator =new RandomIntGenerator();
        String result= generator.generateNums(9,4);
        InputValidator validator=new InputValidator();
        assertTrue(validator.validate(result));
    }
}