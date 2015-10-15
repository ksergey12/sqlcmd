package ua.com.juja.ksergey.sqlcmd.controller;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.ksergey.sqlcmd.controller.command.Command;
import ua.com.juja.ksergey.sqlcmd.controller.command.Exit;
import ua.com.juja.ksergey.sqlcmd.view.View;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 12.10.15.
 */
public class ExitTest {
    private View view;
    private Command command;

    @Before
    public void setup() {
        view = mock(View.class);
        command = new Exit(view);
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
        boolean exit = command.execute("exit");

        // then
        assertTrue(exit);
        verify(view).write("До скорой встречи!");
    }
}
