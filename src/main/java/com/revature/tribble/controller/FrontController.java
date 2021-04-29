package com.revature.tribble.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//https://www.tutorialspoint.com/design_pattern/front_controller_pattern.htm
public class FrontController {

    private Dispatcher dispatcher;

    private static FrontController instance;

    private FrontController(){
        dispatcher = new Dispatcher();
    }

    public static FrontController getInstance(){
        if(instance == null){
            instance = new FrontController();
            return instance = new FrontController();
        } else {
            return instance;
        }
    }

    public void dispatchRequest(Class<? extends HttpServlet> clazz, HttpServletRequest req, HttpServletResponse resp) {
        try {
            dispatcher.dispatch(clazz, req, resp);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}