package tw.core.generator;

import org.junit.Test;
import tw.core.Answer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    @Test
    public void generate_test() throws Exception{
        RandomIntGenerator mockedIntGenerator=mock(RandomIntGenerator.class);
        when(mockedIntGenerator.generateNums(9,4)).thenReturn("1 2 3 4");
        AnswerGenerator generator=new AnswerGenerator(mockedIntGenerator);
        String expected="1 2 3 4";
        Answer result = generator.generate();
        assertEquals(result.toString(),expected);
    }
}

