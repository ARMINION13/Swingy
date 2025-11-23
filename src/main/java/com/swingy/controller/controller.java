package com.swingy.controller;

import com.swingy.model.model;
import com.swingy.view.view;

public class controller {

    view main_view;
    model main_model;

    public controller()
    {
        main_view = new view();
    }

    public void start()
    {
        main_view.start_frame("gui");
    }
    
}
