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
    private int attack;
    private int defense;
    private int hit_points;
    private int luck;
    private String artifact;
    
    public c_params(String str)
    {
        List<String> split = Arrays.asList(str.split(","));
        Iterator<String> it = split.iterator();
        
        this.c_name = it.next();
        this.c_class = it.next();
        this.c_level = Integer.parseInt(it.next());
        this.exp_points = Integer.parseInt(it.next());
        this.attack = Integer.parseInt(it.next());
        this.defense = Integer.parseInt(it.next());
        this.hit_points = Integer.parseInt(it.next());
        this.luck = Integer.parseInt(it.next());
        this.artifact = it.next();
    }

    public c_params()
    {
        this.c_name = "Juan";
        this.c_class = "Mago";
        this.c_level = 1;
        this.exp_points = 0;
        this.attack = 10;
        this.defense = 0;
        this.hit_points = 100;
        this.luck = 5;
        this.artifact = "nothing";
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

    public int get_attack()
    {
        return attack;
    }
    
    public int get_defense()
    {
        return defense;
    }
    
    public int get_luck()
    {
        return luck;
    }

    public int get_hit_points()
    {
        return hit_points;
    }
    
    public int get_artifact()
    {
        return defense;
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

    public void set_attack(int atk)
    {
        attack = atk;
    }
    
    public void set_defense(int def)
    {
        defense = def;
    }
    
    public void set_luck(int lk)
    {
        luck = lk;
    }

    public void set_hit_points(int hp)
    {
        hit_points = hp;
    }
    
    public void set_artifact(String art)
    {
        artifact = art;
    }

    public String to_str(c_params param)
    {
        return c_name + "," + c_class + "," + c_level + "," + exp_points + "," + attack + "," + defense + "," + hit_points + "," + luck + "," + artifact;
    }
}
