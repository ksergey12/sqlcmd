package ua.com.juja.ksergey.sqlcmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 25.12.2015.
 */
@Controller
public class MainController {

    @Autowired
    private Service service;

    @RequestMapping(value = {"/menu", "/"}, method = RequestMethod.GET)
    public String menu(HttpServletRequest request) {
        request.setAttribute("items", service.commandsList());
        return "menu";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession session) {
        session.invalidate();
        return "connect";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpSession session) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:/connect";
        }

        request.setAttribute("database", session.getAttribute("database"));
        request.setAttribute("items", service.list(manager));
        return "list";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(HttpServletRequest request, HttpSession session) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:/connect";
        }

        String table = request.getParameter("table");
        request.setAttribute("tableName", table);
        request.setAttribute("tableHeader", service.showHeader(manager, table));
        request.setAttribute("table", service.showTable(manager, table));
        return "show";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(HttpSession session) {

        if (getManager(session) == null) {
            return "connect";
        } else {
            return "redirect:/menu";
        }
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connecting(HttpServletRequest request, HttpSession session) {
        String database = request.getParameter("dbname");
        String user = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            DatabaseManager manager = service.connect(database, user, password);
            session.setAttribute("db_manager", manager);
            session.setAttribute("database", database);
            return "redirect:/menu";
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    private DatabaseManager getManager(HttpSession session) {
        return (DatabaseManager) session.getAttribute("db_manager");
    }
}
