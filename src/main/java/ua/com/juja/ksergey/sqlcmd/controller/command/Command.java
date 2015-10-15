package ua.com.juja.ksergey.sqlcmd.controller.command;

/**
 * Created by user on 30.09.2015.
 */
public interface Command {

    boolean canExecute(String command);

    boolean execute(String command);

    String format();

    String description();
}
