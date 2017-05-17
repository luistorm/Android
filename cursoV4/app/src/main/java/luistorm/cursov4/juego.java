package luistorm.cursov4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class juego extends AppCompatActivity {

    private TextView tV,tV2,tV3,tV4,tV5,tV6,tV7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tV = (TextView) findViewById(R.id.textView);
        tV2 = (TextView) findViewById(R.id.textView2);
        tV3 = (TextView) findViewById(R.id.textView3);
        tV4 = (TextView) findViewById(R.id.textView5);
        tV5 = (TextView) findViewById(R.id.textView6);
        tV6 = (TextView) findViewById(R.id.textView9);
        tV7 = (TextView) findViewById(R.id.textView8);
        tV.setText(getIntent().getExtras().get("nombre").toString());
        tV2.setText(getIntent().getExtras().get("genero").toString());
        tV3.setText(getIntent().getExtras().get("clasificacion").toString());
        tV4.setText(getIntent().getExtras().get("descripcion").toString());
        tV5.setText(getIntent().getExtras().get("precio").toString());
        tV6.setText(getIntent().getExtras().get("cantidad").toString());
        tV7.setText(getIntent().getExtras().get("id").toString());
    }
}
