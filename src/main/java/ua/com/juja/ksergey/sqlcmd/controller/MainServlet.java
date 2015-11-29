package ua.com.juja.ksergey.sqlcmd.controller;

import ua.com.juja.ksergey.sqlcmd.model.DataSet;
import ua.com.juja.ksergey.sqlcmd.model.DataSetImpl;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.service.Service;
import ua.com.juja.ksergey.sqlcmd.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 31.10.15.
 */
public class MainServlet extends HttpServlet {

    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new ServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        DatabaseManager manager = (DatabaseManager) req.getSession().getAttribute("db_manager");

        if (action.startsWith("/connect")) {
            if (manager == null) {
                req.getRequestDispatcher("connect.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(resp.encodeRedirectURL("list"));
            }
            return;
        }

        if (manager == null) {
            resp.sendRedirect(resp.encodeRedirectURL("connect"));
            return;
        }

        if (action.startsWith("/menu") || action.equals("/")) {
            req.setAttribute("items", service.commandsList());
            req.getRequestDispatcher("menu.jsp").forward(req, resp);

        } else if (action.startsWith("/help")) {
            req.getRequestDispatcher("help.jsp").forward(req, resp);

        } else if (action.startsWith("/show")) {
            String table = req.getParameter("table");
            req.setAttribute("tableName", table);
            req.setAttribute("tableHeader", service.showHeader(manager, table));
            req.setAttribute("table", service.showTable(manager, table));
            req.getRequestDispatcher("show.jsp").forward(req, resp);

        } else if (action.startsWith("/add")) {
            String table = req.getParameter("table");
            req.setAttribute("tableName", table);
            req.setAttribute("tableHeader", service.showHeader(manager, table));
            req.getRequestDispatcher("add.jsp").forward(req, resp);

        } else if (action.startsWith("/edit")) {
            String table = req.getParameter("table");
            req.setAttribute("tableName", table);
            req.setAttribute("tableHeader", service.showHeader(manager, table));
            req.setAttribute("id", req.getParameter("id"));
            req.getRequestDispatcher("edit.jsp").forward(req, resp);

        } else if (action.startsWith("/list")) {
            req.setAttribute("items", service.list(manager));
            req.setAttribute("database", req.getSession().getAttribute("database"));
            req.getRequestDispatcher("list.jsp").forward(req, resp);

        } else if (action.startsWith("/clear")) {
            String table = req.getParameter("table");
            req.setAttribute("table", table);
            service.clear(manager, table);
            req.getRequestDispatcher("clear.jsp").forward(req, resp);

        } else if (action.startsWith("/exit")) {
            req.getSession(false).invalidate();
            req.getRequestDispatcher("connect.jsp").forward(req, resp);

        } else {
            req.setAttribute("message", "Страница не найдена!");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if (action.startsWith("/connect")) {
            String database = req.getParameter("dbname");
            String user = req.getParameter("username");
            String password = req.getParameter("password");

            try {
                DatabaseManager manager = service.connect(database, user, password);
                req.getSession().setAttribute("db_manager", manager);
                req.getSession().setAttribute("database", database);
                resp.sendRedirect(resp.encodeRedirectURL("list"));
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

        } else if (action.startsWith("/add")) {
            String table = req.getParameter("table");

            try {
                DatabaseManager manager = (DatabaseManager) req.getSession().getAttribute("db_manager");
                List<String> tableHeader = service.showHeader(manager, table);
                DataSet input = getDataSet(req, tableHeader);
                service.create(manager, table, input);
                resp.sendRedirect(resp.encodeRedirectURL("show?table=" + table));
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

        } else if (action.startsWith("/edit")) {
            String table = req.getParameter("table");
            int id = Integer.parseInt(req.getParameter("id"));

            try {
                DatabaseManager manager = (DatabaseManager) req.getSession().getAttribute("db_manager");
                List<String> tableHeader = service.showHeader(manager, table);
                DataSet input = getDataSet(req, tableHeader);
                service.update(manager, table, input, id);
                resp.sendRedirect(resp.encodeRedirectURL("show?table=" + table));
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }
    }

    private DataSet getDataSet(HttpServletRequest req, List<String> tableHeader) {
        DataSet input = new DataSetImpl();
        for(String element : tableHeader){
            input.put(element, req.getParameter(element));
        }
        return input;
    }
}
