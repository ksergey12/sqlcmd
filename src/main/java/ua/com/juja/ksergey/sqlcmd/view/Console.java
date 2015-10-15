package ua.com.juja.ksergey.sqlcmd.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by user on 30.09.2015.
 */
public class Console implements View {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
    }
}
