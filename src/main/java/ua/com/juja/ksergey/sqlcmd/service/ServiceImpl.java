package ua.com.juja.ksergey.sqlcmd.service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 09.11.2015.
 */
public class ServiceImpl implements Service {
    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect");
    }
}
