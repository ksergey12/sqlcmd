package ua.com.juja.ksergey.sqlcmd.command.command;

import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Exit implements Command {

    private View view;

    public Exit(View view) {
        this.view = view;
    }

    public Exit(){
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("exit");
    }

    @Override
    public void execute(String command) {
        view.write("До скорой встречи!");
        throw new ExitException();
    }

    @Override
    public String format() {
        return "\texit";
    }

    @Override
    public String description() {
        return "\t\tдля выхода из программы";
    }
}
