package luistorm.curso;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Luis Torres on 06/05/2017.
 */

public class Archivos {
    private File ruta_sd;
    private File f;
    private FileWriter w;
    private BufferedWriter bw;
    private PrintWriter wr;
    private BufferedReader fIn;

    public void Escribir(String Contacto, String Numero,int Imagen,Context context) {
        String archivo = "";
        try
        {
            ruta_sd = context.getExternalFilesDir(null);
            f = new File(ruta_sd.getAbsolutePath(), "contactos.txt");
            if(!f.exists()){
                f.createNewFile();
            }
            fIn = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String linea = fIn.readLine();

            while(linea != null) {
                archivo+=linea+"\n";
                linea = fIn.readLine();
            }
            fIn.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(context,"Error de lectura antes de escribir",Toast.LENGTH_SHORT).show();
        }
        try {
            ruta_sd = context.getExternalFilesDir(null);
            f = new File(ruta_sd.getAbsolutePath(), "contactos.txt");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            wr.write(archivo);
            wr.append(Contacto+","+Numero+","+Imagen+"\n");
            wr.close();
            bw.close();
            Toast.makeText(context,"Contacto Agregado",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(context,"Error de escritura",Toast.LENGTH_SHORT).show();
        }

    }
}
