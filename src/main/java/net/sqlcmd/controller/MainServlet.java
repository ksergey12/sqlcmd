package net.sqlcmd.controller;

import net.sqlcmd.model.DataSet;
import net.sqlcmd.model.DataSetImpl;
import net.sqlcmd.dao.DatabaseManager;
import net.sqlcmd.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
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

    @Autowired
    private Service service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        DatabaseManager manager = getManager(request, response);

        if (action.startsWith("/connect")) {
            if (manager == null) {
                jsp("connect", request, response);
            } else {
                redirect("list", response);
            }
            return;
        }

        if (action.startsWith("/menu") || action.equals("/")) {
            request.setAttribute("items", service.commandsList());
            jsp("menu", request, response);

        } else if (action.startsWith("/help")) {
            jsp("help", request, response);

        } else if (action.startsWith("/show")) {
            String table = request.getParameter("table");
            request.setAttribute("tableName", table);
            request.setAttribute("tableHeader", service.showHeader(manager, table));
            request.setAttribute("table", service.showTable(manager, table));
            jsp("show", request, response);

        } else if (action.startsWith("/add")) {
            String table = request.getParameter("table");
            request.setAttribute("tableName", table);
            request.setAttribute("tableHeader", service.showHeader(manager, table));
            jsp("add", request, response);

        } else if (action.startsWith("/edit")) {
            String table = request.getParameter("table");
            request.setAttribute("tableName", table);
            request.setAttribute("tableHeader", service.showHeader(manager, table));
            request.setAttribute("id", request.getParameter("id"));
            jsp("edit", request, response);

        } else if (action.startsWith("/list")) {
            request.setAttribute("items", service.list(manager));
            request.setAttribute("database", request.getSession().getAttribute("database"));
            request.getRequestDispatcher("list.jsp").forward(request, response);
            jsp("list", request, response);

        } else if (action.startsWith("/clear")) {
            String table = request.getParameter("table");
            request.setAttribute("table", table);
            service.clear(manager, table);
            jsp("clear", request, response);

        }else if (action.startsWith("/dropTable")) {
            String table = request.getParameter("table");
            request.setAttribute("table", table);
            service.dropTable(manager, table);
            jsp("drop", request, response);

        }else if (action.startsWith("/createTable")) {
            jsp("createTable", request, response);

        } else if (action.startsWith("/exit")) {
            request.getSession(false).invalidate();
            jsp("connect", request, response);

        } else {
            request.setAttribute("message", "Страница не найдена!");
            jsp("error", request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/connect")) {
            String database = request.getParameter("dbname");
            String user = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                DatabaseManager manager = service.connect(database, user, password);
                request.getSession().setAttribute("db_manager", manager);
                request.getSession().setAttribute("database", database);
                response.sendRedirect(response.encodeRedirectURL("list"));
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                jsp("error", request, response);
            }

        } else if (action.startsWith("/add")) {
            String table = request.getParameter("table");

            try {
                DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("db_manager");
                List<String> tableHeader = service.showHeader(manager, table);
                DataSet input = getDataSet(request, tableHeader);
                service.create(manager, table, input);
                redirect("show?table=" + table, response);

            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                jsp("error", request, response);
            }

        } else if (action.startsWith("/edit")) {
            String table = request.getParameter("table");
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("db_manager");
                List<String> tableHeader = service.showHeader(manager, table);
                DataSet input = getDataSet(request, tableHeader);
                service.update(manager, table, input, id);
                redirect("show?table=" + table, response);

            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                jsp("error", request, response);
            }
        } else if (action.startsWith("/createTable")) {
            String table = request.getParameter("table");

            try {
                DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("db_manager");
                service.createTable(manager, table);
                redirect("show?table=" + table, response);

            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                jsp("error", request, response);
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

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }

    private void redirect(String url, HttpServletResponse response) throws IOException {
        response.sendRedirect(response.encodeRedirectURL(url));
    }

    private DatabaseManager getManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");

        if (manager != null) {
            return manager;
        } else {
            redirect("connect", response);
            return DatabaseManager.NULL;
        }
    }

    private void jsp(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(jsp + ".jsp").forward(request, response);
    }
}
