package com.example.carlos.myapplication;

/**
 * Created by Carlos on 22/4/18.
 */

public class Alcohol {

    String name;
    double volume;
    double price;
    int id;

    public Alcohol(){}

    public Alcohol(String name, double volume, double price){
        this.name = name;
        this.volume = volume;
        this.price = price;
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setVolume(double volume){this.volume=volume;}
    public double getVolume(){return this.volume;}

    public void setPrice(double price){this.price=price;}
    public double getPrice(){return this.price;}

    public int getId(){return this.id;}
}
