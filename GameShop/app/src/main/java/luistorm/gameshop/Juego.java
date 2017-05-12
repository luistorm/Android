package luistorm.gameshop;

import java.util.UUID;

/**
 * Created by Luis Torres on 05/05/2017.
 */

public class Juego {
    private String Nombre;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Juego(String nombre) {
        Nombre = nombre;
        Id = UUID.randomUUID().toString();
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "ID='" + Id + '\'' +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}
