package ua.com.juja.ksergey.sqlcmd.model;

import java.util.*;

/**
 * Created by user on 30.09.2015.
 */
public class DataSetImpl implements DataSet {

    private Map<String, Object> data = new LinkedHashMap<>();

    @Override
    public void put(String name, Object value) {
        data.put(name, value);
    }

    @Override
    public List<Object> getValues() {
        return new LinkedList<>(data.values());
    }

    @Override
    public Set<String> getNames() {
        return data.keySet();
    }

    @Override
    public Object get(String name) {
        return data.get(name);
    }

    @Override
    public void updateFrom(DataSet newValue) {
        for (String name : newValue.getNames()) {
            data.put(name, newValue.get(name));
        }
    }
}
