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
        manager.getTableValues("test");

        // when
        Set<String> tableNames = manager.getTableNames();

        // then
        assertEquals("[user, test]", tableNames.toString()); // TODO fix order
    }

    @Test
    public void testGetTableData() {
        // given
        manager.clear("user");

        // when
        DataSet input = new DataSetImpl();
        input.put("name", "Stiven");
        input.put("password", "pass");
        input.put("id", 1);
        manager.create("user", input);

        // then
        List<DataSet> users = manager.getTableValues("user");
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[name, password, id]", user.getNames().toString());
        assertEquals("[Stiven, pass, 1]", user.getValues().toString());
    }

    @Test
    public void testGetTableData2() {
        // given

        // when

        // then
        List<DataSet> users = manager.getTableValues("user");
        DataSet user = users.get(0);
        assertEquals("[name, password, id]", user.getNames().toString());
        assertEquals("[Stiven, pass, 1]", user.getValues().toString());
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
    public void testisConnected() {
        // given
        // when
        // then
        assertTrue(manager.isConnected());
    }
}
