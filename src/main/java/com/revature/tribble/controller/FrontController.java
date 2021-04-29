package com.revature.tribble.controller;

import com.revature.tribble.model.Lab;
import com.revature.tribble.model.Tribble;
import com.revature.tribble.servlet.LabServlet;
import com.revature.tribble.servlet.TribbleServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    public void dispatchRequest(Class<? extends HttpServlet> servletClass, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if(servletClass.equals(TribbleServlet.class)){
                dispatcher.tribbleDispatch(req, resp);
            } else if(servletClass.equals(LabServlet.class)){
                dispatcher.labDispatch(req, resp);
            } else{
                // if the class doesn't match a service
                RuntimeException e = new RuntimeException("no DAO assigned for this class");
                e.printStackTrace();
                throw e;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}