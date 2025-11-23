package com.swingy.model;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

public class c_params
{
    //Character parameters
    private String c_name;
    private String c_class;
    private int c_level;
    private int exp_points;
    private int c_xposition;
    private int c_yposition;
    
    public c_params(String str)
    {
        List<String> split = Arrays.asList(str.split(","));
        Iterator<String> it = split.iterator();
        
        this.c_name = it.next();
        this.c_class = it.next();
        this.c_level = Integer.parseInt(it.next());
        this.exp_points = Integer.parseInt(it.next());
        this.c_xposition = Integer.parseInt(it.next());
        this.c_yposition = Integer.parseInt(it.next());
    }

    public c_params()
    {
        this.c_name = "Juan";
        this.c_class = "Mago";
        this.c_level = 1;
        this.exp_points = 0;
        this.c_xposition = 0;
        this.c_yposition = 0;
    }

    public String get_name()
    {
        return c_name;
    }

    public String get_class()
    {
        return c_class;
    }

    public int get_level()
    {
        return c_level;
    }

    public int get_exp()
    {
        return exp_points;
    } 

    public int get_xposition()
    {
        return c_xposition;
    }
    
    public int get_yposition()
    {
        return c_yposition;
    }

    public void set_name(String name)
    {
        c_name = name;
    }

    public void set_class(String clase)
    {
        c_class = clase;
    }

    public void set_level(int level)
    {
        c_level = level;
    }

    public void set_exp(int exp)
    {
        exp_points = exp;
    } 

    public void set_xposition(int x)
    {
        c_xposition = x;
    }
    
    public void set_yposition(int y)
    {
        c_yposition = y;
    }

    public String to_str(c_params param)
    {
        return c_name + "," + c_class + "," + c_level + "," + exp_points + "," + c_xposition + "," + c_yposition;
    }
}
