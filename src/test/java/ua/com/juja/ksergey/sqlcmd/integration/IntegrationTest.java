package ua.com.juja.ksergey.sqlcmd.integration;

import org.junit.Test;
import ua.com.juja.ksergey.sqlcmd.controller.Main;
import static org.junit.Assert.assertEquals;

/**
 * Created by user on 11.10.15.
 */
public class IntegrationTest {
    private ConsoleMock console = new ConsoleMock();

    private void assertOut(String string) {
        assertEquals(string.replaceAll("\n", System.lineSeparator()),
                console.getOut());
    }

    @Test
    public void testHelp() {
        // given
        console.addIn("help");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Существующие команды:\n" +
                "\tconnect|database|user|password\n" +
                "\t\tдля подключения к базе данных\n" +
                "\thelp\n" +
                "\t\tдля вывода этого списка на экран\n" +
                "\tlog\n" +
                "\t\tдля вывода истории введённых команд\n" +
                "\texit\n" +
                "\t\tдля выхода из программы\n" +
                "\tshow|tableName\n" +
                "\t\tдля получения содержимого таблицы 'tableName'\n" +
                "\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля обновления записи в таблице\n" +
                "\tclear|tableName\n" +
                "\t\tдля очистки всей таблицы\n" +
                "\tcreate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля создания записи в таблице\n" +
                "\tlist\n" +
                "\t\tдля получения списка всех таблиц подключённой базы\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testLog() {
        // given
        console.addIn("log");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Список команд пуст.\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
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
        assertOut("Введите команду или help для помощи:\n" +
                "Вы не можете пользоваться командой 'command1' пока не подключитесь с помощью команды connect|database|user|password\n" +
                "Введите команду или help для помощи:\n" +
                "Вы не можете пользоваться командой 'command2' пока не подключитесь с помощью команды connect|database|user|password\n" +
                "Введите команду или help для помощи:\n" +
                "1. command1\n" +
                "2. command2\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
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
        assertOut("Введите команду или help для помощи:\n" +
                "Существующие команды:\n" +
                "\tconnect|database|user|password\n" +
                "\t\tдля подключения к базе данных\n" +
                "\thelp\n" +
                "\t\tдля вывода этого списка на экран\n" +
                "\tlog\n" +
                "\t\tдля вывода истории введённых команд\n" +
                "\texit\n" +
                "\t\tдля выхода из программы\n" +
                "\tshow|tableName\n" +
                "\t\tдля получения содержимого таблицы 'tableName'\n" +
                "\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля обновления записи в таблице\n" +
                "\tclear|tableName\n" +
                "\t\tдля очистки всей таблицы\n" +
                "\tcreate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "\t\tдля создания записи в таблице\n" +
                "\tlist\n" +
                "\t\tдля получения списка всех таблиц подключённой базы\n" +
                "Введите команду или help для помощи:\n" +
                "1. help\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testExit() {
        // given
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testListWithoutConnect() {
        // given
        console.addIn("list");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Вы не можете пользоваться командой 'list' пока не подключитесь с помощью команды connect|database|user|password\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testRetrieveWithoutConnect() {
        // given
        console.addIn("show|user");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Вы не можете пользоваться командой 'show|user' пока не подключитесь с помощью команды connect|database|user|password\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testShowAfterConnect() {
        // given
        console.addIn("connect|sqlcmd|postgres|postgres");
        console.addIn("clear|user");
        console.addIn("show|user");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Подключение выполнено.\n" +
                "Введите команду или help для помощи:\n" +
                "Таблица user очищена.\n" +
                "Введите команду или help для помощи:\n" +
                "-----------------------\n" +
                "name\tpassword\tid\t\n" +
                "-----------------------\n" +
                "-----------------------\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testFindAfterConnect_withData() {
        // given
        console.addIn("connect|sqlcmd|postgres|postgres");
        console.addIn("clear|user");
        console.addIn("create|user|id|1|name|Vasia|password|123456");
        console.addIn("create|user|id|2|name|Petia|password|654321");
        console.addIn("show|user");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Подключение выполнено.\n" +
                "Введите команду или help для помощи:\n" +
                "Таблица user очищена.\n" +
                "Введите команду или help для помощи:\n" +
                "Запись была успешно создана в таблице user\n" +
                "Введите команду или help для помощи:\n" +
                "Запись была успешно создана в таблице user\n" +
                "Введите команду или help для помощи:\n" +
                "-----------------------\n" +
                "name\tpassword\tid\t\n" +
                "-----------------------\n" +
                "Vasia\t123456\t1\t\n" +
                "Petia\t654321\t2\t\n" +
                "-----------------------\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testUnsupported() {
        // given
        console.addIn("unsupported");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Вы не можете пользоваться командой 'unsupported' пока не подключитесь с помощью команды connect|database|user|password\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
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
        assertOut("Введите команду или help для помощи:\n" +
                "Подключение выполнено.\n" +
                "Введите команду или help для помощи:\n" +
                "Несуществующая команда: unsupported\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
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
        assertOut("Введите команду или help для помощи:\n" +
                "Подключение выполнено.\n" +
                "Введите команду или help для помощи:\n" +
                "[test, user]\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
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
        assertOut("Введите команду или help для помощи:\n" +
                "Подключение выполнено.\n" +
                "Введите команду или help для помощи:\n" +
                "[test, user]\n" +
                "Введите команду или help для помощи:\n" +
                "Cant get connection: org.postgresql.util.PSQLException: FATAL: database \"test\" does not exist\n" +
                "Подключение выполнено.\n" +
                "Введите команду или help для помощи:\n" +
                "Вы не можете пользоваться командой 'list' пока не подключитесь с помощью команды connect|database|user|password\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testConnectWithError() {
        // given
        console.addIn("connect|sqlcmd");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Неверный формат команды, ожидается:\n" +
                "\tconnect|database|user|password\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }

    @Test
    public void testClearWithError() {
        // given
        console.addIn("connect|sqlcmd|postgres|postgres");
        console.addIn("clear|error|controller");
        console.addIn("exit");

        // when
        Main.main(new String[0]);

        // then
        assertOut("Введите команду или help для помощи:\n" +
                "Подключение выполнено.\n" +
                "Введите команду или help для помощи:\n" +
                "Неверный формат команды, ожидается:\n" +
                "\tclear|tableName\n" +
                "Введите команду или help для помощи:\n" +
                "До скорой встречи!\n");
    }
}