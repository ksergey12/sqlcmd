package net.sqlcmd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 19.04.2017.
 */
@Repository
@Qualifier(value = "logDataSource")
public class UserActionsDaoImpl implements UserActionsDao {

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource dataSource){
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public void log(String user, String database, String action) {
        template.update(
                "INSERT INTO public.user_actions " +
                        "(user_name, db_name, action) " +
                        "VALUES (?, ?, ?)", user, database, action
        );
    }

    @Override
    public List<UserAction> getAllFor(String user) {
        return template.query("SELECT * FROM public.user_actions " +
                "WHERE user_name = ?", new Object[] { user },
                new RowMapper<UserAction>() {
                    @Override
                    public UserAction mapRow(ResultSet resultSet, int i) throws SQLException {
                        UserAction result = new UserAction();
                        result.setId(resultSet.getInt("id"));
                        result.setUser(resultSet.getString("user_name"));
                        result.setDatabase(resultSet.getString("db_name"));
                        result.setAction(resultSet.getString("action"));
                        return result;
            }
        });
    }
}
