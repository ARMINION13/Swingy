package com.swingy.model;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class model {


    private c_params params;
    private List<String> saves;

    public model (int save_file)
    {
        read_csv();
        this.params = new c_params(saves.get(save_file));
        //to do: inicializar la clase c_params con los archivos del csv, correspondientes al save_file especifiacado
    }

    public model (c_params new_save)
    {
        read_csv();
        params = new_save; 
    }
    
    //to do: crear un nuevo save file en el csv al salir del juego

    private void read_csv ()
    {
        File csv = new File("save_files.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save_files.csv")); 
            BufferedReader reader = new BufferedReader(new FileReader("save_files.csv")))
        {
            if (!csv.exists()) // si no existe lo crea
            {
                writer.write("name,class,level,exp,xposition,yposition");
                writer.newLine();
            }
            else
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save_files.csv")))
        {
            if (csv.exists()) // si no existe lo crea
            {
                Iterator<String> it = saves.iterator();
                while (it.hasNext())
                {
                    writer.write(it.next());
                    writer.newLine();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
