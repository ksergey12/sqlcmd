package ua.com.juja.ksergey.sqlcmd.controller;

import ua.com.juja.ksergey.sqlcmd.controller.command.*;
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
                new Exit(view),
                new Help(view),
                new Connect(manager, view),
                new TableList(manager, view),
                new Retrieve(manager, view),
                new Create(manager, view),
                new Update(manager, view),
                new Delete(manager, view),
                new Drop(manager, view),
                new Clear(manager, view),
                new Log(view, log),
                new Unsupported(view),
                new isConnected(manager, view)};
    }

    public void run() {
        try {
            while (true) {
                view.write("Введи команду (или help для помощи):");
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