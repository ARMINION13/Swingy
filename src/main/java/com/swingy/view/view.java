package com.swingy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.InputStream;
import java.util.List;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.swingy.controller.controller;

import jakarta.validation.constraints.Pattern;

public class view {
    
    private Font MedievalSharp;
    
    public String input = "";
    public JFrame frame;

    public JPanel title_panel;
    public JPanel button_panel;
    public JPanel main_menu_panel;

    public JScrollPane scroll;
    
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;

    public JButton button_new_game;
    public JButton button_continue;

    public JList list;
    private List<String> saves;

    //CONSTRUCTOR

    public view (List<String> sf)
    {
        saves = sf;

        create_font();
        create_frame();
        create_title_panel();
        create_buttons_panel();
        create_main_menu_panel();
        create_select_save_panel();
    }








    //FUENTES

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








    //PANELES

    private void create_title_panel()
    {
        //Crear panel del titulo centrado, blanco, fondo gris y que ocupa la parte superior de la ventana

        JLabel title = new JLabel("SWINGY", SwingConstants.CENTER);

        title_panel = new JPanel(new BorderLayout());

        title.setForeground(Color.WHITE);
        title.setFont(MedievalSharp.deriveFont(80f));

        title_panel.setBounds(0, 0, 700, 350);
        title_panel.setBackground(Color.DARK_GRAY);

        title_panel.add(title, BorderLayout.CENTER);
    }

    private void create_main_menu_panel ()
    {
        //Crear panel de menu principal con 2 botones, dividido en cuadridula, fondo gris y que ocupa la parte inferior de la ventana

        button_new_game = create_button("New Game");
        button_continue = create_button("Continue");
        main_menu_panel = new JPanel();

        main_menu_panel.setBounds(0, 350, 700, 150);

        main_menu_panel.add(button_new_game);
        main_menu_panel.add(button_continue);
        main_menu_panel.setBackground(Color.DARK_GRAY);
        main_menu_panel.setLayout(new GridLayout(1, 2, 20, 20));
        main_menu_panel.setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void create_buttons_panel()
    {
        //Crear panel de multiusos con 4 botones, dividido en cuadridula, fondo gris y que ocupa la parte inferior de la ventana

        button1 = create_button("Mage");
        button2 = create_button("Warrior");
        button3 = create_button("Archer");
        button4 = create_button("Rogue");
        button_panel = new JPanel();

        button_panel.setBounds(0, 200, 700, 200);

        button_panel.add(button1);
        button_panel.add(button2);
        button_panel.add(button3);
        button_panel.add(button4);
        button_panel.setBackground(Color.DARK_GRAY);
        button_panel.setLayout(new GridLayout(2, 2, 20, 20));
        button_panel.setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void create_select_save_panel()
    {
        list = new JList<>(saves.toArray(new String[0]));

        list.setFont(MedievalSharp.deriveFont(24f));
        list.setBackground(Color.DARK_GRAY);
        list.setForeground(Color.WHITE);
        list.setSelectionBackground(Color.GRAY);
        list.setSelectionForeground(Color.WHITE);

        scroll = new JScrollPane(list);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.setBounds(0, 350, 700, 150);
        scroll.setBackground(Color.DARK_GRAY);
    }








    //FRAMES

    private void create_frame ()
    {
        //Create Frame

        frame = new JFrame();

        frame.setSize(700, 500);
        frame.setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
    }








    //BUTTONS

    private JButton create_button (String text)
    {
        JButton button = new JButton(text);

        button.setFont(MedievalSharp.deriveFont(30f));
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
                System.out.println(text);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setForeground(Color.GRAY); // Vuelve al soltar
            }
        });

        return button;
    }








    //EVENTOS DE TERMINAL

    public String create_character_terminal ()
    {
        //Creacion de personaje
        System.out.print("\033[H\033[2J"); // limpia la terminal

        System.out.println( "Introduce your name: " );
        @Pattern(regexp = "^[\\p{L}]+$", message = "Solo letras") // valida input solo letras
        String c_name = System.console().readLine();
        
        System.out.print("\033[H\033[2J");

        System.out.println( "\nChoose a class !!\n" );
        System.out.println( " Mage || Warrior || Archer || Rogue" );
        @Pattern(regexp = "^[\\p{L}]+$", message = "Solo letras")
        String c_class = System.console().readLine();

        
        System.out.print("\033[H\033[2J");
        System.out.println( "..." );

        return c_name + "," + c_class + "," + "0" + "," + "0" + "," + "0" + "," + "0";
    }

    public String main_menu_terminal ()
    {
        //Opciones del menu principal
        System.out.print("\033[H\033[2J");

        System.out.println( "\nWelcome to Swingy !!\n" );
        System.out.println( "New Game || Continue" );
        @Pattern(regexp = "^[\\p{L}]+$", message = "Solo letras")
        String input = System.console().readLine();

        
        System.out.print("\033[H\033[2J");
        System.out.println( "..." );

        return input;
    }

    public String select_save_terminal ()
    {
        //Opciones del menu principal
        int i = 0;
        System.out.print("\033[H\033[2J");

        System.out.println( "\nSelect your save file: \n" );
        for (Iterator<String> it = saves.iterator(); it.hasNext(); i++)
            System.out.println(i + ") " + it.next() + "\n" );
        @Pattern(regexp = "^[0-9]+$", message = "Solo números")
        String input = System.console().readLine();
        
        System.out.print("\033[H\033[2J");
        System.out.println( "..." );

        return saves.get(Integer.parseInt(input));
    }
}

