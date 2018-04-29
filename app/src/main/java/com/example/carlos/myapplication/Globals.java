package com.example.carlos.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos on 22/4/18.
 */

public class Globals{
    // Global variable
    //private String server = "http://192.168.1.40:9000";
    private String server = "http://172.20.10.4:9000";
    public void setServer(String server){
        this.server = server;
    }
    public String getServer(){
        return this.server;
    }

    private List<Alcohol> listAlcohol = new ArrayList<>();
    public void addAlcohol(Alcohol a){listAlcohol.add(a);}
    public void setAlcoholList(List<Alcohol> a){this.listAlcohol = a;}
    public List<Alcohol> getAlcoholList(){return this.listAlcohol;}




    // No tocar lo de abajo

    private static Globals instance;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
