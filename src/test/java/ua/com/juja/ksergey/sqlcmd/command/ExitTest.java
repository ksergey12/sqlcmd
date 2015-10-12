package ua.com.juja.ksergey.sqlcmd.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.ksergey.sqlcmd.command.command.Command;
import ua.com.juja.ksergey.sqlcmd.command.command.Exit;
import ua.com.juja.ksergey.sqlcmd.command.command.ExitException;
import ua.com.juja.ksergey.sqlcmd.view.View;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 12.10.15.
 */
public class ExitTest {
    //    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        view = mock(View.class);
        command = new Exit();
    }

    @Test
    public void testCanProcessExitString() {
        // given

        // when
        boolean canProcess = command.canExecute("exit");

        // then
        assertTrue(canProcess);
    }

    @Test
    public void testCantProcessFalseString() {
        // given

        // when
        boolean canProcess = command.canExecute("false");

        // then
        assertFalse(canProcess);
    }

    @Test
    public void testProcessExitCommand() {
        // given

        // when
        try {
            command.execute("exit");
            fail("Expected ExitException");
        } catch (ExitException e) {
            // NOP
        }

        // then
        verify(view).write("До скорой встречи!");
    }
}
