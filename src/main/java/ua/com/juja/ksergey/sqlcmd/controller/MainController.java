package ua.com.juja.ksergey.sqlcmd.controller;

import ua.com.juja.ksergey.sqlcmd.controller.command.*;
import ua.com.juja.ksergey.sqlcmd.controller.command.record.*;
import ua.com.juja.ksergey.sqlcmd.controller.command.record.Create;
import ua.com.juja.ksergey.sqlcmd.controller.command.table.TableList;
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

        Help help = new Help(view);

        this.commands = new Command[]{
                new Connect(manager, view),
                help,
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

        help.addCommands(commands);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            view.write("Введите команду или help для помощи:");
            String input = view.read();

            for (Command command : commands) {
                if (!command.canExecute(input)) {
                    continue;
                }

                if (!command.validate(input)) {
                    view.write("Неверный формат команды, ожидается:");
                    view.write("\t" + command.format());
                } else {
                    if (command.execute(input)) {
                        exit = true;
                    }
                }
                log.add(input);
                break;
            }
        }
    }
}