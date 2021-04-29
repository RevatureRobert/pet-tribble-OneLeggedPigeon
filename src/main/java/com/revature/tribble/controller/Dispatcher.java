package com.revature.tribble.controller;

import com.google.gson.Gson;
import com.revature.tribble.service.GenericService;
import com.revature.tribble.service.LabService;
import com.revature.tribble.service.TribbleService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Dispatcher {
    private final LabService labView;
    private final TribbleService tribbleView;
    private final Gson gson;

    public Dispatcher(){
        labView = new LabService();
        tribbleView = new TribbleService();
        gson = new Gson().newBuilder().setPrettyPrinting().create();
    }

    @SuppressWarnings("rawtypes")
    public void dispatch(Class<? extends HttpServlet> clazz, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String requestMethod = req.getMethod();

        GenericService service;
        Object jsonObject = null;

    }
}