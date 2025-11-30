package com.swingy.controller;

import java.awt.BorderLayout;

import com.swingy.model.*;
import com.swingy.view.*;

import jakarta.validation.constraints.Pattern;

public class controller {

    private view main_view;
    private model main_model;

    public controller()
    {
        main_model = new model();
        main_view = new view(main_model.get_saves());
    }

    public void start()
    {
        String input;

        //Menu Principal

        main_view.frame.add(main_view.title_panel); //Mostrar el titulo del juego
        main_view.frame.add(main_view.scroll, BorderLayout.SOUTH); //Mostrar los botones del menu principal
        main_view.frame.setVisible(true);
        input = main_view.main_menu_terminal();

        if (input.equals("Continue"))
        {
            input = main_view.select_save_terminal();
            main_model.set_params(new c_params(input));
        }
        if (input.equals("New Game"))
        {
            input = main_view.create_character_terminal();
            main_model.set_params(new c_params(input));
        }

        main_model.save_game();
        //to do hacer que en vez de un string fixed sea uno resultado de las elecciones del jugador
    }
    
}
