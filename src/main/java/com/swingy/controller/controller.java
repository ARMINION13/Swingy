package com.swingy.controller;

import jakarta.validation.constraints.Pattern;

public class controller {

    private controller()
    {

    }

    public static void start()
    {
        create_character();
        
        while(true){}
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
