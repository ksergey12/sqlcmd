package ua.com.juja.ksergey.sqlcmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.juja.ksergey.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 25.12.2015.
 */
@Controller
public class MainController {

    @Autowired
    private Service service;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(HttpServletRequest request) {
        request.setAttribute("items", service.commandsList());
        return "menu";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }
}
