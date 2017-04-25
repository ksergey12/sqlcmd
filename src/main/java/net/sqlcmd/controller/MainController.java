package net.sqlcmd.controller;

import net.sqlcmd.model.DataSet;
import net.sqlcmd.model.DataSetImpl;
import net.sqlcmd.dao.DatabaseManager;
import net.sqlcmd.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by User on 25.12.2015.
 */
@Controller
public class MainController {

    @Autowired
    private Service service;

    @RequestMapping(value = {"/menu", "/"}, method = RequestMethod.GET)
    public String menu(HttpSession session, Model model) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:/connect";
        }

        model.addAttribute("items", service.commandsList());
        return "menu";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/connect";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession session, Model model) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:/connect";
        }

        model.addAttribute("database", session.getAttribute("database"));
        model.addAttribute("items", service.list(manager));
        return "list";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam("table") String table,
                       HttpSession session, Model model) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:/connect";
        }

        model.addAttribute("tableName", table);
        model.addAttribute("tableHeader", service.showHeader(manager, table));
        model.addAttribute("table", service.showTable(manager, table));
        return "show";
    }

    @RequestMapping(value = "/actions/{userName}", method = RequestMethod.GET)
    public String actions(Model model, HttpSession session,
                          @PathVariable(value = "userName") String userName) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:/connect";
        }

        model.addAttribute("actions", service.getAllFor(userName));
        model.addAttribute("userName", userName);
        return "actions";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam("table") String table,
                      HttpSession session, Model model) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:/connect";
        }

        model.addAttribute("tableName", table);
        model.addAttribute("tableHeader", service.showHeader(manager, table));
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String adding(@RequestParam Map<String, Object> allRequestParams,
                         HttpSession session, Model model) {
        try {
            DatabaseManager manager = (DatabaseManager) session.getAttribute("db_manager");
            String table = (String) allRequestParams.get("table");
            DataSet input = getDataSet(allRequestParams);
            service.updateTableFromDataSet(manager, table, input);
            return "redirect:/show?table=" + table;
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("table") String table,
                       HttpSession session, Model model) {
        DatabaseManager manager = (DatabaseManager) session.getAttribute("db_manager");
        model.addAttribute("tableName", table);
        model.addAttribute("tableHeader", service.showHeader(manager, table));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editing(@RequestParam Map<String, Object> allRequestParams,
                          HttpSession session, Model model)
    {
        try {
            DatabaseManager manager = (DatabaseManager) session.getAttribute("db_manager");
            String table = (String) allRequestParams.get("table");
            String id = allRequestParams.get("id").toString();
            DataSet input = getDataSet(allRequestParams);
            service.updateTableRecord(manager, table, input, id);
            return "redirect:/show?table=" + table;
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(HttpSession session, Model model) {
        model.addAttribute("connection", new Connection());

        if (getManager(session) == null) {
            return "connect";
        } else {
            return "redirect:/menu";
        }
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connecting(@ModelAttribute("connection") Connection connection,
                             HttpSession session, Model model) {
        try {
            DatabaseManager manager = service.connect(connection.getDatabase(),
                    connection.getUser(), connection.getPassword());
            session.setAttribute("db_manager", manager);
            session.setAttribute("database", connection.getDatabase());
            return "redirect:/list";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clear(@RequestParam("table") String table,
                        HttpSession session, Model model) {
        DatabaseManager manager = (DatabaseManager) session.getAttribute("db_manager");
        model.addAttribute("table", table);
        service.clear(manager, table);
        return "clear";
    }

    @RequestMapping(value = "/dropTable", method = RequestMethod.GET)
    public String dropTable(@RequestParam("table") String table,
                            HttpSession session, Model model) {
        DatabaseManager manager = (DatabaseManager) session.getAttribute("db_manager");
        model.addAttribute("table", table);
        service.dropTable(manager, table);
        return "drop";
    }

    @RequestMapping(value = "/createTable", method = RequestMethod.GET)
    public String createTable() {
        return "createTable";
    }

    @RequestMapping(value = "/createTable", method = RequestMethod.POST)
    public String creatingTable(@RequestParam("table") String table,
                                HttpSession session, Model model) {
        try {
            DatabaseManager manager = (DatabaseManager) session.getAttribute("db_manager");
            service.createTable(manager, table);
            return "redirect:/show?table=" + table;
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    private DatabaseManager getManager(HttpSession session) {
        return (DatabaseManager) session.getAttribute("db_manager");
    }

    private DataSet getDataSet(@RequestParam Map<String, Object> allRequestParams) {
        DataSet input = new DataSetImpl();
        allRequestParams.remove("table");
        for(Map.Entry<String, Object> entry : allRequestParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            input.put(key, value);
        }
        return input;
    }
}
