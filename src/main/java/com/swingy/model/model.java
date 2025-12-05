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
    private List<String> saves;
    private int x_position;
    private int y_position;

    public model ()
    {
        saves = new ArrayList<String>();
        params = new c_params();
        read_csv();
        x_position = ((params.get_level() - 1) * 5 + 10 - (params.get_level() % 2)) / 2;
        y_position = ((params.get_level() - 1) * 5 + 10 - (params.get_level() % 2)) / 2;
        
        System.out.println(x_position + " , " + y_position);
    }

    private void read_csv ()
    {
        File csv = new File("save_files.txt");

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
        File csv = new File("save_files.txt");

        boolean exist = csv.exists();
        //El true es para que se cree el filewriter en modo append y no me sobre escriba todo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csv, true)))
        {
            if (!exist) // si no existe lo crea
            {
                writer.write("name,class,level,exp,attack,defense,hit_points,luck,artifact");
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

    public void set_position(int x, int y)
    {
        x_position = x;
        y_position = y;
    }

    public int get_x_position()
    {
        return x_position;
    }

    public int get_y_position()
    {
        return y_position;
    }

    public c_params get_params()
    {
        return params;
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
