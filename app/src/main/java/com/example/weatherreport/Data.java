package com.example.weatherreport;

import androidx.core.content.ContextCompat;


import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Data {
    private String Alert=null;
    private String Color=null;
    private String Updated=null;
    private String Country=null;
    private String Clouds=null;
    private String CoOrdinates=null;
    private String Source=null;
    private String Humidity=null;
    private String Pressure =null;
    private String WindSpeed=null;
    private String WindDirection=null;
    private String Context=null;
    private String value=null;
    private int colorId=-1;
    private String text=null;
    private String check;

    public Data(
            String icon,
            String al,
            String col,
            String up,
            String co,
            String cl,
            String loc,
            String so,
            String hu,
            String pr,
            String ws,
            String wd){
        text=icon;
        Alert=al;
        Color=col;
        Updated=up;
        Country=co;
        Clouds=cl;
        CoOrdinates=loc;
        Source=so;
        Humidity=hu;
        Pressure=pr;
        WindSpeed=ws;
        WindDirection=wd;
    }

    public Data(String con,String co){
        Context=con;
       value=co;

    }
    public Data(String con,String co,String al){
        text =con;
        value=co;
        Alert=al;

    }
    public String getText(){
        return text;
    }
    public String getValue(){
        return value;
    }

    public String getContext(){
        return Context;
    }
    public String getAlert(){
        return Alert;
    }
    public String getCountry(){
        return Country;
    }
    public String getWindDirection(){
        return WindDirection;
    }
    public String getColor(){
        return Color;
    }
    public String getUpdated(){
        return Updated;
    }
    public String getClouds(){
        return Clouds;
    }
    public String getCoOrdinates(){
        return CoOrdinates;
    }
    public String getSource(){
        return Source;
    }
    public String getHumidity(){
        return Humidity;
    }
    public String getPressure(){
        return Pressure;
    }
    public String getWindSpeed(){
        return WindSpeed;
    }
    public void Alert(String str){
        Alert=str;
    }

    /*
   */

}
