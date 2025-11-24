package com.swingy.controller;

import java.awt.BorderLayout;

import com.swingy.model.*;
import com.swingy.view.*;

import jakarta.validation.constraints.Pattern;

public class controller {

    view main_view;
    model main_model;

    public controller()
    {
        main_view = new view();
    }

    public void start()
    {
        main_view.frame.add(main_view.title_panel);
        main_view.frame.add(main_view.button_panel, BorderLayout.SOUTH);
        main_view.frame.setVisible(true);

        String character = main_view.create_character_terminal();
        c_params param = new c_params(character);
        //to do hacer que en vez de un string fixed sea uno resultado de las elecciones del jugador
        main_model = new model(param);
    }
    
}
