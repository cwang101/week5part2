package tw.core;

import org.junit.Test;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tw.core.GameStatus.CONTINUE;
import static tw.core.GameStatus.FAIL;
import static tw.core.GameStatus.SUCCESS;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    @Test
    public void guess_test() throws Exception{
        AnswerGenerator mockedAnswerGenerator=mock(AnswerGenerator.class);
        when(mockedAnswerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        Game game=new Game(mockedAnswerGenerator);
        GuessResult result=game.guess(Answer.createAnswer("1 3 5 6"));
        assertEquals(result.getInputAnswer().toString(),"1 3 5 6");
        assertEquals(result.getResult(),"1A1B");
    }

    @Test
    public void should_return_fail_when_result_list_greater_than_max_time() throws Exception{
        Game game=new Game(mock(AnswerGenerator.class));
        Answer answer=mock(Answer.class);
        List<GuessResult> guessResultLis= Arrays.asList(new GuessResult("0A0B",answer),
                new GuessResult("1A0B",answer),
                new GuessResult("2A0B",answer),
                new GuessResult("3A0B",answer),
                new GuessResult("4A0B",answer),
                new GuessResult("5A0B",answer),
                new GuessResult("6A0B",answer));

        Field field = game.getClass().getDeclaredField("guessResults");
        field.setAccessible(true);
        field.set(game,guessResultLis);
        assertEquals(game.checkStatus(),FAIL);
        assertFalse(game.checkCoutinue());
    }

    @Test
    public void should_return_success_when_contain_correct_answer() throws Exception{
        Game game=new Game(mock(AnswerGenerator.class));
        Answer answer=mock(Answer.class);
        List<GuessResult> guessResultLis= Arrays.asList(new GuessResult("0A0B",answer),
                new GuessResult("1A0B",answer),
                new GuessResult("2A0B",answer),
                new GuessResult("4A0B",answer));

        Field field = game.getClass().getDeclaredField("guessResults");
        field.setAccessible(true);
        field.set(game,guessResultLis);
        assertEquals(game.checkStatus(),SUCCESS);
        assertFalse(game.checkCoutinue());

    }


    @Test
    public void should_return_continue_when_not_contain_correct_answer() throws Exception{
        Game game=new Game(mock(AnswerGenerator.class));
        Answer answer=mock(Answer.class);
        List<GuessResult> guessResultLis= Arrays.asList(new GuessResult("0A0B",answer),
                new GuessResult("1A0B",answer),
                new GuessResult("2A0B",answer),
                new GuessResult("3A0B",answer));

        Field field = game.getClass().getDeclaredField("guessResults");
        field.setAccessible(true);
        field.set(game,guessResultLis);
        assertEquals(game.checkStatus(),CONTINUE);
        assertTrue(game.checkCoutinue());
    }


}
