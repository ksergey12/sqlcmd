package ua.com.juja.ksergey.sqlcmd.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.ksergey.sqlcmd.command.command.Command;
import ua.com.juja.ksergey.sqlcmd.command.command.record.Clear;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 12.10.15.
 */
public class ClearTest {
    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Clear(manager, view);
    }

    @Test
    public void testClearTable() {
        // given

        // when
        command.execute("clear|user");

        // then
        verify(manager).clear("user");
        verify(view).write("Таблица user очищена.");
    }

    @Test
    public void testCanProcessClearWithParametersString() {
        // when
        boolean canProcess = command.canExecute("clear|user");

        // then
        assertTrue(canProcess);
    }

    @Test
    public void testCantProcessClearWithoutParametersString() {
        // given

        // when
        command.execute("clear");

        // then
        verify(view).write("Неверное количество параметров, формат команды:\n" +
                "\tclear|tableName");
    }

    @Test
    public void testWhenCountParametersIsMoreThan2() {
        // given

        // when
        command.execute("clear|table|table");

        // then
        verify(view).write("Неверное количество параметров, формат команды:\n" +
                "\tclear|tableName");
    }
}
