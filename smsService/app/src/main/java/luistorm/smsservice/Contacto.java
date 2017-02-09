package luistorm.smsservice;

import java.util.UUID;

/**
 * Created by Luis Torres on 06/02/2017.
 */

public class Contacto {
    private String ID;
    private String Nombre;
    private String Numero;
    private int Imagen;

    public Contacto(String Nombre,String Numero,int Imagen) {
        this.Nombre = Nombre;
        this.Numero = Numero;
        this.Imagen = Imagen;
        this.ID = UUID.randomUUID().toString();
    }

    public String getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getNumero() {
        return Numero;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    @Override
    public String toString() {
        return "Contacto{"+"ID='"+ID+'\''+"Nombre='"+Nombre+'\''
                +"Numero='"+Numero+'\''+"Imagen='"+Imagen+'\''+'}';
    }
}
