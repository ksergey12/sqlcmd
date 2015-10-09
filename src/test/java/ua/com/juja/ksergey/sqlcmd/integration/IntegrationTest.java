package ua.com.juja.ksergey.sqlcmd.integration;

import org.junit.Test;
import ua.com.juja.ksergey.sqlcmd.controller.Main;

import static org.junit.Assert.assertEquals;

/**
 * Created by oleksandr.baglai on 28.08.2015.
 */
public class IntegrationTest {

    private ua.com.juja.ksergey.sqlcmd.integration.ConsoleMock console = new ua.com.juja.ksergey.sqlcmd.integration.ConsoleMock();

    @Test
    public void testHelp() {
        // given
        console.addIn("help");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "Существующие команды:\n" +
                "\tconnect|databaseName|userName|password\n" +
                "\t\tдля подключения к базе данных\n" +
                "\tlist\n" +
                "\t\tдля получения списка всех таблиц подключённой базы\n" +
                "\tclear|tableName\n" +
                "\t\tдля очистки всей таблицы\n" +
                "\ttable|tableName\n" +
                "\t\tдля получения содержимого таблицы 'tableName'\n" +
                "\tcreate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля создания записи в таблице\n" +
                "\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля обновления записи в таблице\n" +
                "\tdelete|tableName\n" +
                "\t\tдля удаления таблицы\n" +
                "\tdrop|databaseName\n" +
                "\t\tдля удаления базы\n" +
                "\thelp\n" +
                "\t\tдля вывода этого списка на экран\n" +
                "\tlog\n" +
                "\t\tдля вывода истории введённых команд\n" +
                "\texit\n" +
                "\t\tдля выхода из программы\n" +
                "Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testLog() {
        // given
        console.addIn("log");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "Список команд пуст.\n" +
                "Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testAddUnsupportedCommandsToLog() {
        // given
        console.addIn("command1");
        console.addIn("command2");
        console.addIn("log");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "Несуществующая команда: command1\n" +
                "Введи команду (или help для помощи):\n" +
                "Несуществующая команда: command2\n" +
                "Введи команду (или help для помощи):\n" +
                "1. command1\n" +
                "2. command2\n" +
                "Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testAddHelpCommandToLog() {
        // given
        console.addIn("help");
        console.addIn("log");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "Существующие команды:\n" +
                "\tconnect|databaseName|userName|password\n" +
                "\t\tдля подключения к базе данных\n" +
                "\tlist\n" +
                "\t\tдля получения списка всех таблиц подключённой базы\n" +
                "\tclear|tableName\n" +
                "\t\tдля очистки всей таблицы\n" +
                "\ttable|tableName\n" +
                "\t\tдля получения содержимого таблицы 'tableName'\n" +
                "\tcreate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля создания записи в таблице\n" +
                "\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля обновления записи в таблице\n" +
                "\tdelete|tableName\n" +
                "\t\tдля удаления таблицы\n" +
                "\tdrop|databaseName\n" +
                "\t\tдля удаления базы\n" +
                "\thelp\n" +
                "\t\tдля вывода этого списка на экран\n" +
                "\tlog\n" +
                "\t\tдля вывода истории введённых команд\n" +
                "\texit\n" +
                "\t\tдля выхода из программы\n" +
                "Введи команду (или help для помощи):\n" +
                "1. help\n" +
                "Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testExit() {
        // given
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testListWithoutConnect() {
        // given
        console.addIn("list");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("\n", console.getOut());
    }

    @Test
    public void testUnsupported() {
        // given
        console.addIn("unsupported");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "Несуществующая команда: unsupported\n" +
                "Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testUnsupportedAfterConnect() {
        // given
        console.addIn("connect|sqlcmd|postgres|postgres");
        console.addIn("unsupported");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "Подключение выполнено.\n" +
                "Введи команду (или help для помощи):\n" +
                "Несуществующая команда: unsupported\n" +
                "Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testListAfterConnect() {
        // given
        console.addIn("connect|sqlcmd|postgres|postgres");
        console.addIn("list");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Введи команду (или help для помощи):\n" +
                "Подключение выполнено.\n" +
                "Введи команду (или help для помощи):\n" +
                "[test, user]\n" +
                "Введи команду (или help для помощи):\n" +
                "До скорой встречи!\n", console.getOut());
    }

    @Test
    public void testConnectAfterConnect() {
        // given
        console.addIn("connect|sqlcmd|postgres|postgres");
        console.addIn("list");
        console.addIn("connect|test|postgres|postgres");
        console.addIn("list");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("\n", console.getOut());
    }

    @Test
    public void testConnectWithError() {
        // given
        console.addIn("connect|sqlcmd");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("\n", console.getOut());
    }

    @Test
    public void testClearWithError() {
        // given
        console.addIn("connect|sqlcmd|postgres|postgres");
        console.addIn("clear|sadfasd|fsf|fdsf");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("\n", console.getOut());
    }
}
