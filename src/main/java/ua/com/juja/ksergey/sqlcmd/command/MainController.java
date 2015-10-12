package ua.com.juja.ksergey.sqlcmd.command;

import ua.com.juja.ksergey.sqlcmd.command.command.*;
import ua.com.juja.ksergey.sqlcmd.command.command.record.*;
import ua.com.juja.ksergey.sqlcmd.command.command.record.Create;
import ua.com.juja.ksergey.sqlcmd.command.command.table.TableList;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30.09.2015.
 */
public class MainController {
    private Command[] commands;
    private View view;
    private List<String> log = new ArrayList<>();

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.commands = new Command[]{
                new Connect(manager, view),
                new Help(view),
                new Log(view, log),
                new Exit(view),
                new isConnected(manager, view),
                new Show(manager, view),
                new Update(manager, view),
                new Clear(manager, view),
                new Create(manager, view),
                new TableList(manager, view),
                new Unsupported(view),
        };
    }

    public void run() {
        try {
            while (true) {
                view.write("Введите команду или help для помощи:");
                String input = view.read();

                for (Command command : commands) {
                    if (command.canExecute(input)) {
                        command.execute(input);
                        log.add(input);
                        break;
                    }
                }
            }
        } catch (ExitException e) {
            //NOP
        }
    }
}