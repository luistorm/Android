package luistorm.smsservice;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Luis Torres on 06/02/2017.
 */

public class ContactsRepository {
    private HashMap<String, Contacto> contactos = new HashMap<>();

    private ContactsRepository() {

    }


    public ContactsRepository(Context context) {
        try
        {
            File ruta_sd = context.getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), "contactos.txt");
            if(!f.exists()){
                f.createNewFile();
            }
            BufferedReader fIn = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String linea = fIn.readLine();

            while(linea != null) {
                String[] datos = linea.split(",");
                saveContact(new Contacto(datos[0],datos[1],Integer.parseInt(datos[2])));
                linea = fIn.readLine();
            }
            fIn.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(context,"Error de lectura de contactos",Toast.LENGTH_SHORT).show();
        }
    }

    private void saveContact(Contacto contacto) {
        contactos.put(contacto.getID(),contacto);
    }

    public List<Contacto> getContacts() {
        return new ArrayList<>(contactos.values());
    }
}
