package com.revature.tribble.controller;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.revature.tribble.model.Lab;
import com.revature.tribble.model.Tribble;
import com.revature.tribble.service.GenericService;
import com.revature.tribble.service.LabService;
import com.revature.tribble.service.TribbleService;
import com.revature.tribble.servlet.LabServlet;
import com.revature.tribble.servlet.TribbleServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private final LabService labView;
    private final TribbleService tribbleView;
    private final Gson gson;

    public Dispatcher(){
        labView = new LabService();
        tribbleView = new TribbleService();
        gson = new Gson().newBuilder().setPrettyPrinting().create();
    }

    public void labDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String requestMethod = req.getMethod();
        Class<?> clazz = Lab.class;
        Object jsonObject = null;

        // Map parameters to their first value
        HashMap<String,String> parameterMap = new HashMap<>();
        for(Map.Entry<String,String[]> m :req.getParameterMap().entrySet()){
            parameterMap.put(m.getKey(),m.getValue()[0]);
        }

        // if json is expected, try to parse the json with toJson()
        if(!requestMethod.equals("GET")){
            try {
                jsonObject = gson.fromJson(req.getReader(),clazz);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        // dispatch to the service layer depending on the requestMethod
        switch(requestMethod){
            case "POST":
                labView.create(jsonObject);
                break;
            case "GET":
                int id = 0;
                try {
                    gson.toJson(labView.getById(Integer.parseInt(parameterMap.get("id"))),out);
                } catch (Exception e){
                    gson.toJson(labView.getList(),out);
                }
                break;
            case "PUT":
                labView.createOrUpdate(jsonObject);
                break;
            case "DELETE":
                labView.delete(jsonObject);
                break;
            default:
                RuntimeException e = new RuntimeException("nope");
                e.printStackTrace();
                throw e;
        }
    }

    public void tribbleDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String requestMethod = req.getMethod();
        Class<?> clazz = Tribble.class;
        Object jsonObject = null;

        // Map parameters to their first value
        HashMap<String,String> parameterMap = new HashMap<>();
        for(Map.Entry<String,String[]> m :req.getParameterMap().entrySet()){
            parameterMap.put(m.getKey(),m.getValue()[0]);
        }

        // if json is expected, try to parse the json with toJson()
        if(!requestMethod.equals("GET")){
            try {
                jsonObject = gson.fromJson(req.getReader(),clazz);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        // dispatch to the service layer depending on the requestMethod
        switch(requestMethod){
            case "POST":
                labView.create(jsonObject);
                break;
            case "GET":
                int id = 0;
                try {
                    labView.getById(Integer.parseInt(parameterMap.get("id")));
                } catch (Exception e){
                    labView.getList();
                }
                break;
            case "PUT":
                labView.createOrUpdate(jsonObject);
                break;
            case "DELETE":
                labView.delete(jsonObject);
                break;
            default:
                RuntimeException e = new RuntimeException("nope");
                e.printStackTrace();
                throw e;
        }
    }
}