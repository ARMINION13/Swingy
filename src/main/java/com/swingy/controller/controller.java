package com.swingy.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

import jakarta.validation.constraints.Pattern;

public class controller {

    private Font MedievalSharp;

    public controller()
    {
        //Import Font

        import_font();
    }

    private void import_font()
    {
        try(InputStream is = controller.class.getResourceAsStream("/fonts/MedievalSharp/MedievalSharp-Regular.ttf")) 
        {

            if (is == null)
            {
                throw new IllegalStateException("No se encontro la fuente");
            }

            MedievalSharp = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(48f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private JLabel show_title(JFrame frame)
    {
        //Show First Scene (TITLE)

        JLabel title = new JLabel("SWINGY");
        title.setBounds(250, 100, 500, 300);
        title.setForeground(Color.WHITE);

        title.setFont(MedievalSharp);
        frame.add(title);

        frame.setVisible(true);
        return (title);
    }

    private void show_create_character(JFrame frame)
    {
        //Show Second Scene (CHARACTER CREATION)

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

    public void start(String mode)
    {
        //Create Frame
        JFrame frame = new JFrame();

        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        
        show_title(frame);
        show_create_character(frame);
    }
    
}
