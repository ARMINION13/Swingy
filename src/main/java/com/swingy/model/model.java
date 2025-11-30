package com.swingy.model;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class model {


    private c_params params;
    private List<String> saves = new ArrayList<String>();

    public model ()
    {
        read_csv();
    }

    //to do: crear un nuevo save file en el csv al salir del juego

    private void read_csv ()
    {
        File csv = new File("save_files.csv");

        boolean exist = csv.exists();

        try (BufferedReader reader = new BufferedReader(new FileReader(csv)))
        {
            if (exist) // si no existe lo crea
            {
                String line;

                line = reader.readLine();
                while ((line = reader.readLine()) != null)
                    saves.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void write_csv ()
    {
        File csv = new File("save_files.csv");

        boolean exist = csv.exists();
        //El true es para que se cree el filewriter en modo append y no me sobre escriba todo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csv, true)))
        {
            if (!exist) // si no existe lo crea
            {
                writer.write("name,class,level,exp,xposition,yposition");
                writer.newLine();
            }
            writer.write(params.to_str(params));
            writer.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void set_params(c_params save_file)
    {
        params = save_file;
    }

    public void save_game()
    {
        write_csv();
    }

    public List<String> get_saves()
    {
        return this.saves;
    }


}
