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
        DatabaseManager manager = getManager(request);

        if (action.startsWith("/connect")) {
            if (manager == null) {
//                request.getRequestDispatcher("connect.jsp").forward(request, response);
                jsp("connect", request, response);
            } else {
//                response.sendRedirect(response.encodeRedirectURL("list"));
                redirect("list", response);
            }
            return;
        }

        if (manager == null) {
//            response.sendRedirect(response.encodeRedirectURL("connect"));
            redirect("connect", response);
            return;
        }

        if (action.startsWith("/menu") || action.equals("/")) {
            request.setAttribute("items", service.commandsList());
//            request.getRequestDispatcher("menu.jsp").forward(request, response);
            jsp("menu", request, response);

        } else if (action.startsWith("/help")) {
//            request.getRequestDispatcher("help.jsp").forward(request, response);
            jsp("help", request, response);

        } else if (action.startsWith("/show")) {
            String table = request.getParameter("table");
            request.setAttribute("tableName", table);
            request.setAttribute("tableHeader", service.showHeader(manager, table));
            request.setAttribute("table", service.showTable(manager, table));
//            request.getRequestDispatcher("show.jsp").forward(request, response);
            jsp("show", request, response);

        } else if (action.startsWith("/add")) {
            String table = request.getParameter("table");
            request.setAttribute("tableName", table);
            request.setAttribute("tableHeader", service.showHeader(manager, table));
//            request.getRequestDispatcher("add.jsp").forward(request, response);
            jsp("add", request, response);

        } else if (action.startsWith("/edit")) {
            String table = request.getParameter("table");
            request.setAttribute("tableName", table);
            request.setAttribute("tableHeader", service.showHeader(manager, table));
            request.setAttribute("id", request.getParameter("id"));
//            request.getRequestDispatcher("edit.jsp").forward(request, response);
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
//            request.getRequestDispatcher("clear.jsp").forward(request, response);
            jsp("clear", request, response);

        }else if (action.startsWith("/dropTable")) {
            String table = request.getParameter("table");
            request.setAttribute("table", table);
            service.dropTable(manager, table);
//            request.getRequestDispatcher("drop.jsp").forward(request, response);
            jsp("drop", request, response);

        }else if (action.startsWith("/createTable")) {
//            request.getRequestDispatcher("createTable.jsp").forward(request, response);
            jsp("createTable", request, response);

        } else if (action.startsWith("/exit")) {
            request.getSession(false).invalidate();
//            request.getRequestDispatcher("connect.jsp").forward(request, response);
            jsp("connect", request, response);

        } else {
            request.setAttribute("message", "Страница не найдена!");
//            request.getRequestDispatcher("error.jsp").forward(request, response);
            jsp("error", request, response);
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
        } else if (action.startsWith("/createTable")) {
            String table = req.getParameter("table");

            try {
                DatabaseManager manager = (DatabaseManager) req.getSession().getAttribute("db_manager");
                service.createTable(manager, table);
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

    private void redirect(String url, HttpServletResponse response) throws IOException {
        response.sendRedirect(response.encodeRedirectURL(url));
    }

    private DatabaseManager getManager(HttpServletRequest request) {
        return (DatabaseManager) request.getSession().getAttribute("manager");
    }

    private void jsp(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(jsp + ".jsp").forward(request, response);
    }
}
