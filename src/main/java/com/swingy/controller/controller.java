package com.swingy.controller;

import java.awt.BorderLayout;

import com.swingy.model.c_params;
import com.swingy.model.model;
import com.swingy.view.view;

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

        while (!input.equals("Exit"))
        {
            input = main_view.move_character_terminal();

            if (input.equals("Exit"))
                break;

            input = move(input);

            if (input.equals("Victory"))
            {
                main_view.win_terminal();
                break;
            }
            
            if ((int)(Math.random() * 3) == 0)
            {
                input = main_view.enemie_found_terminal();
                if (input.equals("Exit"))
                    break;
                else if (input.equals("Fight"))
                    input = fight();
                else
                {
                    //Si falla simular pelea y devolver resultado
                    if ((int)(Math.random() * 2) == 0)
                    {
                        input = main_view.escape_fail_terminal();
                        input = fight();
                    }
                    //Simular huida y devolver resultado
                    else
                        input = main_view.escaped_terminal();
                }

                if (!input.equals("Death") && !input.equals("Escaped"))
                    main_view.won_fight_terminal();
                else if (input.equals("Death"))
                {
                    main_view.defeat_terminal();
                    break;
                }
                //Si te cae artefacto tras la pelea
                if (input.equals("Artifact"))
                {
                    input= main_view.artifact_terminal();
                    //Si lo coges
                    if (!input.equals("Leave"))
                        artifact(input);
                    else if (input.equals("Exit"))
                        break;
                }
            }

            main_view.arrive_terminal();
        }
    }

    private String move (String dir)
    {
        c_params par = main_model.get_params();
        //Map Limit Formula : (level-1)*5+10 -(level%2)
        int map_size = (par.get_level() - 1) * 5 + 10 - (par.get_level() % 2);

        if (dir.equals("North"))
            main_model.set_position(main_model.get_x_position(), main_model.get_y_position() - 1);
        else if (dir.equals("South"))
            main_model.set_position(main_model.get_x_position(), main_model.get_y_position() + 1);
        else if (dir.equals("East"))
            main_model.set_position(main_model.get_x_position() - 1, main_model.get_y_position());
        else if (dir.equals("West"))
            main_model.set_position(main_model.get_x_position() - 1, main_model.get_y_position());

        if (main_model.get_x_position() > map_size || main_model.get_x_position() < 0 
            || main_model.get_y_position() > map_size || main_model.get_y_position() < 0)
            return "Victory";

        return ("Not yet");
    }

    private String fight()
    {
        c_params enemie = new c_params(), player = main_model.get_params();
        int ply_hp=player.get_hit_points() , murloc=enemie.get_hit_points();

        enemie.set_level(main_model.get_params().get_level() + ((int)(Math.random() * 3) - 1));
        enemie.set_attack((enemie.get_level() * 4) + enemie.get_attack());

        //Simulacion de pelea
        while(ply_hp > 0 && murloc > 0)
        {
            //player attack 
            if ((int)(Math.random() * 100) > enemie.get_luck())
            {
                //if not dodged
                if ((int)(Math.random() * 100) > player.get_luck())
                {
                    System.out.println(player.get_name() + "(" + ply_hp + "/" + player.get_hit_points() + ")" + " : Attack \n");
                    murloc -= (player.get_attack() - enemie.get_defense());
                }
                else
                {
                    System.out.println(player.get_name() + "(" + ply_hp + "/" + player.get_hit_points() + ")" + " : Critic Attack \n");
                    murloc -= (player.get_attack() - enemie.get_defense()) * 2;
                }

            }
            else
                System.out.println(enemie.get_class() + "(" + murloc + "/" + enemie.get_hit_points() + ")" + " : Dodged \n");
            if (murloc <= 0)
            {
                level_up(enemie);
                break;
            }
            //enemie attack
            System.out.println(enemie.get_class() + "(" + murloc + "/" + enemie.get_hit_points() + ")" + " : Fish Slap \n");
            ply_hp -= (enemie.get_attack() - player.get_defense());
            if (ply_hp <= 0)
                return ("Death");
        }

        if ((int)(Math.random() * 100) > 10)
            return "Artifact";

        return "Alive";
    }

    private void level_up(c_params enemie)
    {
        c_params player = main_model.get_params();


        player.set_exp(player.get_exp() + 450 + (10 * enemie.get_level()));
        //Level Exp Limit Formula : level ∗ 1000 + (level − 1)2 ∗ 450
        if (player.get_exp() > (player.get_level() * 1000 + (player.get_level() - 1)^2 * 450))
        {
            player.set_level(player.get_level() + 1);

            if (player.getClass().equals("Mage"))
                player.set_attack(player.get_level() + player.get_attack());
            else if (player.getClass().equals("Warrior"))
            {
                player.set_defense(player.get_level() + player.get_defense());
                player.set_attack((player.get_level() / 2) + player.get_attack());
            }
            else if (player.getClass().equals("Archer"))
                player.set_luck(player.get_level() + player.get_luck());
            else if (player.getClass().equals("Rogue"))
            {
                player.set_luck((player.get_level() / 2) + player.get_luck());
                player.set_attack((player.get_level() / 2) + player.get_attack());
            }    
        }
        main_model.set_params(player);
    }


    private void artifact(String art)
    {
        //Eliminar artefacto anterior
        remove_artifact();

        c_params player = main_model.get_params();

        //Aplicar estadisticas del nuevo artefacto
        if (art.equals("Weapon"))
        {
            player.set_artifact(art);
            player.set_attack(player.get_attack() + 5);    
        }
        if (art.equals("Armor"))
        {
            player.set_artifact(art);
            player.set_defense(player.get_defense() + 2);        
        }
        if (art.equals("Helmet"))
        {
            player.set_artifact(art);
            player.set_hit_points(player.get_hit_points() + 10);    
        }

        main_model.set_params(player);
    }

    private void remove_artifact( )
    {
        c_params player = main_model.get_params();

        if (player.get_artifact().equals("Weapon"))
        {
            player.set_artifact("Nothing");
            player.set_attack(player.get_attack() - 5);    
        }
        if (player.get_artifact().equals("Armor"))
        {
            player.set_artifact("Nothing");
            player.set_defense(player.get_defense() - 2);        
        }
        if (player.get_artifact().equals("Helmet"))
        {
            player.set_artifact("Nothing");
            player.set_hit_points(player.get_hit_points() - 10);    
        }

        main_model.set_params(player);
    }


}
