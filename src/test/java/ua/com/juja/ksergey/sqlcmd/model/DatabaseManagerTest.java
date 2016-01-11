package ua.com.juja.ksergey.sqlcmd.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 11.10.15.
 */
public class DatabaseManagerTest {
    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new JDBCDatabaseManager();
        manager.connect("sqlcmd", "postgres", "postgres");
    }

    @Test
    public void testGetAllTableNames() {
        // given
        manager.getTableValues("user");

        // when
        Set<String> tableNames = manager.getTableNames();

        // then
        assertEquals("[test, user]", tableNames.toString());
    }

    @Test
    public void testCreateTable() {
        // given
        manager.createTable("testing");

        // when
        Set<String> tableNames = manager.getTableNames();

        // then
        assertEquals("[test, testing, user]", tableNames.toString());
    }

    @Test
    public void testDropTable() {
        // given
        // when
        manager.dropTable("testing");
        Set<String> tableNames = manager.getTableNames();

        // then
        assertEquals("[test, user]", tableNames.toString());

    }

    @Test
    public void testGetTableData() {
        // given
        manager.clear("user");

        // when
        DataSet input = new DataSetImpl();
        input.put("name", "Vasia");
        input.put("password", "strong_pass");
        input.put("id", 1);
        manager.create("user", input);

        // then
        List<DataSet> users = manager.getTableValues("user");
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[name, password, id]", user.getNames().toString());
        assertEquals("[Vasia, strong_pass, 1]", user.getValues().toString());
    }

    @Test
    public void testClear() {
        // given
        manager.clear("user");

        DataSet input = new DataSetImpl();
        input.put("name", "Vasia");
        input.put("password", "strong_pass");
        input.put("id", 1);
        manager.create("user", input);

        // when
        manager.clear("user");

        // then
        List<DataSet> users = manager.getTableValues("user");
        assertEquals("[]", users.toString());
    }

    @Test
    public void testGetColumnNames() {
        // given
        manager.clear("user");

        // when
        Set<String> columnNames = manager.getTableColumns("user");

        // then
        assertEquals("[name, password, id]", columnNames.toString());
    }

    @Test
    public void testIsConnected() {
        // given
        // when
        // then
        assertTrue(manager.isConnected());
    }
}
