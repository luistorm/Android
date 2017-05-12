package luistorm.gameshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListaJuegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_juegos);
        JuegosFragment juegosFragment = (JuegosFragment)
                getSupportFragmentManager().findFragmentById(R.id.juegos_container);

        if (juegosFragment == null) {
            juegosFragment = JuegosFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.juegos_container, juegosFragment)
                    .commit();
        }
    }
}
