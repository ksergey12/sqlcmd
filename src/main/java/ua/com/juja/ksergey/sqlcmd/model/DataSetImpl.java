package ua.com.juja.ksergey.sqlcmd.model;

import java.util.*;

/**
 * Created by user on 30.09.2015.
 */
public class DataSetImpl implements DataSet {

    static class Data {
        private String name;
        private Object value;

        public Data(String key, Object value) {
            this.name = key;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

    private List<Data> data = new LinkedList<>();

    @Override
    public void put(String name, Object value) {
        data.add(new Data(name, value));
    }

    @Override
    public List<Object> getValues() {
        List<Object> result = new LinkedList<>();
        for (Data item : data) {
            result.add(item.getValue());
        }
        return result;
    }

    @Override
    public Set<String> getNames() {
        Set<String> result = new LinkedHashSet<>();
        for (Data item : data) {
            result.add(item.getName());
        }
        return result;
    }

    @Override
    public Object get(String name) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName().equals(name)) {
                return data.get(i).getValue();
            }
        }
        return null;
    }

    @Override
    public void updateFrom(DataSet newValue) {
        //TODO implement this
    }
}
