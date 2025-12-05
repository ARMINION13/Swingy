package com.swingy.controller;

import java.awt.BorderLayout;
import java.lang.Math;

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

    public void start(String mode)
    {
        if (mode.equals("gui"))
            gui();
        if (mode.equals("console"))
            terminal();

        main_model.save_game();
    }

    private void gui()
    {
        main_view.frame.add(main_view.title_panel); //Mostrar el titulo del juego
        main_view.frame.add(main_view.scroll, BorderLayout.SOUTH); //Mostrar los botones del menu principal
        main_view.frame.setVisible(true);
    }

    private void terminal()
    {
        String input;

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

        while (!input.equals("Defeat") && !input.equals("Exit"))
        {
            input = main_view.move_character_terminal();
            input = move(input);

            if (input.equals("Victory"))
            {
                main_view.win_terminal();
                break;
            }
            
            if ((int)(Math.random() * 3) == 0)
            {
                input = main_view.enemie_found_terminal();
                if (input.equals("Fight"))
                    input = fight();
                else
                {
                    //Si falla simular pelea y devolver resultado
                    if ((int)(Math.random() * 2) == 0)
                        input = fight();
                    //Simular huida y devolver resultado
                    else
                        main_view.escaped_terminal();
                }

                //Si te cae artefacto tras la pelea
                if (input.equals("Artifact"))
                {
                    input= main_view = artifact_terminal();
                    //Si lo coges
                    if (input.equals("Take"))
                        artifact();
                }
            }
        }

        if (input.equals("Victory"))
        {
            main_view.defeat_terminal();
            break;
        }
    }

    private String move (String dir)
    {
        c_params par = main_model.get_params();
        //Map Limit Formula : (level-1)*5+10 -(level%2)
        int map_size = (par.get_level() - 1) * 5 + 10 - (par.get_level() % 2);

        if (dir.equals("North"))
            main_model.set_position(main_model.get_x_position(), main_model.get_y_position() - 1);
        if (dir.equals("South"))
            main_model.set_position(main_model.get_x_position(), main_model.get_y_position() + 1);
        if (dir.equals("East"))
            main_model.set_position(main_model.get_x_position(), main_model.get_y_position() - 1);
        if (dir.equals("West"))
            main_model.set_position(main_model.get_x_position(), main_model.get_y_position() + 1);

        if (main_model.get_x_position() > map_size || main_model.get_x_position() < 0 
            || main_model.get_y_position() > map_size || main_model.get_x_position() < 0)
            return "Victoria";

        return ("Not yet");
    }

    private String fight()
    {
        c_params enemie = new c_params(), player = main_model.get_params();
        int ply_hp=player.get_hit_points() , murloc=enemie.get_hit_points();

        enemie.set_level(main_model.get_params().get_level() + ((int)(Math.random() * 3) - 1));

        //Simulacion de pelea
        while(ply_hp > 0 && murloc > 0)
        {
            //player attack 
            if ((int)(Math.random() * 100) > enemie.get_luck())
            {
                //if not dodged
                if ((int)(Math.random() * 100) > player.get_luck())
                    murloc -= (player.get_attack() - enemie.get_defense());
                else
                    murloc -= (player.get_attack() - enemie.get_defense()) * 2;
            }
            if (murloc <= 0)
            {
                player.set_exp(player.get_exp() + 450 + (10 * enemie.get_level()));
                //Level Exp Limit Formula : level ∗ 1000 + (level − 1)2 ∗ 450
                if (player.get_exp() > (player.get_level() * 1000 + (player.get_level() - 1)^2 * 450))
                    player.set_level(player.get_level() + 1);
                main_model.set_params(player);
                break;
            }
            //enemie attack
            ply_hp -= (enemie.get_attack() - player.get_defense());
            if (ply_hp <= 0)
                return ("Death");
        }

        if ((int)(Math.random() * 100) > 10)
            return "Artifact";

        return "Alive";
    }

}
