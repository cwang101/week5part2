package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.fest.assertions.api.Assertions.assertThat;
/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private String systemOut() {
        return outContent.toString();
    }
    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }
    @Test
    public void begin_game_test() throws Exception{
        GameController gameController=new GameController(mock(Game.class),new GameView());
        gameController.beginGame();
        assertThat(systemOut().endsWith("------Guess Number Game, You have 6 chances to guess!  ------\n")).isTrue();
    }


    @Test
    public void play_test() throws Exception{
        InputCommand mockedCommand=mock(InputCommand.class);
        when(mockedCommand.input())
                .thenReturn(Answer.createAnswer("1 5 6 7"))
                .thenReturn(Answer.createAnswer("1 2 3 4"));

        AnswerGenerator mockedAnswerGenerator=mock(AnswerGenerator.class);
        when(mockedAnswerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        Game game=new Game(mockedAnswerGenerator);

        GameController gameController=new GameController(game,new GameView());

        gameController.play(mockedCommand);

        assertThat(systemOut().contains("Guess Result: 1A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 5 6 7, Guess Result: 1A0B]\n")
                ).isTrue();
        assertThat(systemOut().contains("success\n")).isTrue();
    }
    }
