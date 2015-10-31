package ua.com.juja.ksergey.sqlcmd.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 31.10.15.
 */
public class MainServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if (action.startsWith("/menu") || action.equals("/")) {
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } else if (action.startsWith("/help")) {
            req.getRequestDispatcher("help.jsp").forward(req, resp);
        } else if (action.startsWith("/connect")) {
            req.getRequestDispatcher("connect.jsp").forward(req, resp);
        }else if (action.startsWith("/clear")) {
            req.getRequestDispatcher("clear.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
