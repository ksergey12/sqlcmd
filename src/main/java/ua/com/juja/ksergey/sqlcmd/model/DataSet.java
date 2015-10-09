package ua.com.juja.ksergey.sqlcmd.model;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 30.09.2015.
 */
public interface DataSet {
    void put(String name, Object value);

    List<Object> getValues();

    Set<String> getNames();

    Object get(String name);

    void updateFrom(DataSet newValue);
}
