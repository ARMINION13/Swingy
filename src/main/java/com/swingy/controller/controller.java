package com.swingy.controller;

import com.swingy.model.*;
import com.swingy.view.*;

public class controller {

    view main_view;
    model main_model;

    public controller()
    {
        main_view = new view();
    }

    public void start()
    {
        c_params param = new c_params();
        main_view.start_frame("gui");
        //to do hacer que en vez de un string fixed sea uno resultado de las elecciones del jugador
        main_model = new model(param);
        System.out.println(param.to_str(param));
    }
    
}
