package tw.core;

import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    @Test
    public void should_throw_exception_when_input_contain_repeated_num() {
        Answer answer=Answer.createAnswer("1 2 2 3");
        try{
            answer.validate();
            fail("no exception thrown.");
        }catch (Exception e){
            assertTrue(e instanceof OutOfRangeAnswerException);
            assertTrue(e.getMessage().endsWith("Answer format is incorrect"));
        }
    }

    @Test
    public void check_test() {
        Answer target=Answer.createAnswer("1 2 3 4");
        Answer guessedAnswer=Answer.createAnswer("1 3 5 6");

        List<Integer> expected=Arrays.asList(1,1);

        Record result=target.check(guessedAnswer);

        assertEquals(IntStream.of(result.getValue()).boxed().collect(Collectors.toList()),expected);
    }
}