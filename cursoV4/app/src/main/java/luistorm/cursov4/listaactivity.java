package luistorm.cursov4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class listaactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaactivity);
        juegosFragment JuegosFragment = (juegosFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);

        if (JuegosFragment == null) {
            JuegosFragment = juegosFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, JuegosFragment)
                    .commit();
        }
    }
}
