package ua.com.juja.ksergey.sqlcmd.controller.command;

/**
 * Created by user on 30.09.2015.
 */
public interface Command {

    boolean canProcess(String command);

    void process(String command);

    String format();

    String description();
}
