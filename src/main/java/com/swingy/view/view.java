package com.swingy.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swingy.controller.controller;

import jakarta.validation.constraints.Pattern;

public class view {
    
    private Font MedievalSharp;
    
    public String input = "";
    public JFrame frame;

    public JPanel title_panel;
    public JPanel button_panel;
    
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;

    public view ()
    {
        create_font();
        create_frame();
        create_title_panel();
        create_buttons_panel();
    }

    private void create_font()
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

    private void create_title_panel()
    {
        //Create title

        JLabel title = new JLabel("SWINGY");
        title_panel = new JPanel();

        title.setBounds(250, 100, 500, 300);
        title.setForeground(Color.WHITE);

        title.setFont(MedievalSharp);

        title_panel.add(title);
    }


    private void create_frame ()
    {
        //Create Frame
        frame = new JFrame();

        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
    }

    private void create_buttons_panel()
    {
        button1 = create_button("Mage");
        button2 = create_button("Warrior");
        button3 = create_button("Archer");
        button4 = create_button("Rogue");
        button_panel = new JPanel();

        button_panel.setBounds(0, 300, 700, 200);

        button_panel.add(button1);
        button_panel.add(button2);
        button_panel.add(button3);
        button_panel.add(button4);
        button_panel.setBackground(Color.DARK_GRAY);
        button_panel.setLayout(new GridLayout(2, 2, 20, 20));
    }

    private JButton create_button (String text)
    {
        JButton button = new JButton(text);
        button.setFont(MedievalSharp);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.GRAY);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setForeground(Color.WHITE); // Cambia al presionar
                input = text;
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setForeground(Color.GRAY); // Vuelve al soltar
            }
        });

        return button;
    }

    public String create_character_terminal ()
    {
        //Show Second Scene (CHARACTER CREATION)
        System.out.print("\033[H\033[2J"); // limpia la terminal

        System.out.println( "\nWelcome to Swingy !!\n" );
        System.out.println( "Introduce your name: " );

        @Pattern(regexp = "^[\\p{L}]+$", message = "Solo letras") // valida input solo letras
        String c_name = System.console().readLine();
        
        System.out.print("\033[H\033[2J");

        System.out.println( "\nChoose a class !!\n" );
        System.out.println( "1) Mage  2) Warrior 3) Archer 4) Rogue" );
        String c_class = System.console().readLine();

        
        System.out.print("\033[H\033[2J");
        System.out.println( "\n" + c_name + ": " + c_class + "\n" );

        return c_name + "," + c_class + "," + "0" + "," + "0" + "," + "0" + "," + "0";
    }
}

