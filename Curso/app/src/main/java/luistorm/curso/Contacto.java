package luistorm.curso;

import java.util.UUID;

/**
 * Created by Luis Torres on 06/05/2017.
 */

public class Contacto {
    private String nombre;
    private String telefono;
    private int imagen;
    private String id;

    public Contacto(String nombre, String telefono, int imagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.imagen = imagen;
        this.id = UUID.randomUUID().toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contacto{"+"id='"+id+'\''+"nombre='"+nombre+'\''
                +"telefono='"+telefono+'\''+"imagen='"+imagen+'\''+'}';
    }
}
