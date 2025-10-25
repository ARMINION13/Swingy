package com.swingy.controller;

import jakarta.validation.constraints.Pattern;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class controller {

    private controller()
    {

    }

    public static void start(String mode)
    {
        //Create Frame
        JFrame frame = new JFrame();

        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        
        //Show First Scene (TITLE)

        JLabel title = new JLabel("SWINGY");
        title.setBounds(250, 100, 500, 300);
        title.setForeground(Color.WHITE);

        try(InputStream is = controller.class.getResourceAsStream("/fonts/MedievalSharp/MedievalSharp-Regular.ttf")) 
        {

            if (is == null)
            {
                throw new IllegalStateException("No se encontro la fuente");
            }

            Font MedievalSharp = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(48f);
            title.setFont(MedievalSharp);
            frame.add(title);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //create_character();
        
        frame.setVisible(true);
    }

    private static void create_character()
    {
        System.out.print("\033[H\033[2J"); // limpia la terminal

        System.out.println( "\nWelcome to Swingy !!\n" );
        System.out.println( "Introduce your name: " );

        @Pattern(regexp = "^[\\p{L}]+$", message = "Solo letras") // valida input solo letras
        String c_name = System.console().readLine();
        
        System.out.print("\033[H\033[2J");

        System.out.println( "\nChoose a class !!\n" );
        System.out.println( "1) Mage  2) Warrior 3) Archer" );
        String c_class = System.console().readLine();

        System.out.print("\033[H\033[2J");
        System.out.println( "\n" + c_name + ": " + "\n" );
        System.out.println( "      -Class" + ": " + c_class + "\n" );

    }
    
}
