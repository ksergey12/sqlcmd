package net.sqlcmd.model;

import net.sqlcmd.dao.DatabaseManager;
import net.sqlcmd.dao.JDBCDatabaseManager;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 11.10.15.
 */
public class DatabaseManagerTest {
    private DatabaseManager manager;
    private String TABLE = "table" + getRandomName();
    private static boolean initIsDone = false;

    /**
     * Workaround for a static @BeforeClass
     */
    public void init() {
        if (initIsDone) {
            return;
        }
        manager = new JDBCDatabaseManager();
        manager.connect("postgres", "postgres", "postgres");

        if(!manager.createDatabase("sqlcmd")){
            manager.connect("sqlcmd", "postgres", "postgres");
            Set<String> tables = manager.getTableNames();
            for (String table : tables) {
                manager.dropTable(table);
            }
        }
        initIsDone = true;
    }

    @Before
    public void setup() {
        init();
        manager = new JDBCDatabaseManager();
        manager.connect("sqlcmd", "postgres", "postgres");
        manager.createTable(TABLE);
    }

    @After
    public void cleanup() {
        manager.dropTable(TABLE);
    }

    @Test
    public void testGetAllTableNames() {
        // given
        // when
        Set<String> tableNames = manager.getTableNames();

        // then
        assertEquals("[" + TABLE + "]", tableNames.toString());
    }

    @Test
    public void testCreateAndDropTable() {
        // given
        String testTable = "test" + TABLE;
        manager.createTable(testTable);

        // when
        Set<String> tableNames = manager.getTableNames();

        // then
        assertEquals("["+ TABLE + ", " + testTable + "]", tableNames.toString());
        manager.dropTable(testTable);
    }

    @Test
    public void testGetTableData() {
        // given
        DataSet input = new DataSetImpl();
        input.put("name", "John Smith");
        input.put("password", "strong-pass");
        input.put("id", 1);

        // when
        manager.create(TABLE, input);

        // then
        List<DataSet> users = manager.getTableValues(TABLE);
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[id, name, password]", user.getNames().toString());
        assertEquals("[1, John Smith, strong-pass]", user.getValues().toString());
    }

    @Test
    public void testClear() {
        // given
        DataSet input = new DataSetImpl();
        input.put("name", "John Smith");
        input.put("password", "strong-pass");
        input.put("id", 1);
        manager.create(TABLE, input);

        // when
        manager.clear(TABLE);

        // then
        List<DataSet> users = manager.getTableValues(TABLE);
        assertEquals("[]", users.toString());
    }

    @Test
    public void testGetColumnNames() {
        // given
        // when
        Set<String> columnNames = manager.getTableColumns(TABLE);

        // then
        assertEquals("[id, name, password]", columnNames.toString());
    }

    @Test
    public void testIsConnected() {
        // given
        // when
        // then
        assertTrue(manager.isConnected());
    }

    private String getRandomName() {
        return new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
    }
}
