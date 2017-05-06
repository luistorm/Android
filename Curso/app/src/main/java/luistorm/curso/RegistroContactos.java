package luistorm.curso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class RegistroContactos extends AppCompatActivity implements Spinner.OnItemSelectedListener,View.OnClickListener {

    private Spinner spinner;
    private ImageView foto;
    private Button b;
    private EditText eT,eT2;
    private static final int OK_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_contactos);
        String opciones[] = {"Imagen 1","Imagen 2","Imagen 3"};
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);
        foto = (ImageView) findViewById(R.id.imageView);
        spinner.setOnItemSelectedListener(this);
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(this);
        eT = (EditText) findViewById(R.id.editText);
        eT2 = (EditText) findViewById(R.id.editText5);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i){
                case 0:
                    foto.setImageResource(R.drawable.i2);

                    break;
                case 1:
                    foto.setImageResource(R.drawable.i3);

                    break;
                case 2:
                    foto.setImageResource(R.drawable.i4);

                    break;
            }
        foto.setBackground(null);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == b.getId()) {
            Archivos arc = new Archivos();
            int imagen=0;
            if(spinner.getSelectedItemPosition() == 0) {
                imagen = R.drawable.i2;
            }
            if(spinner.getSelectedItemPosition() == 1) {
                imagen = R.drawable.i3;
            }
            if(spinner.getSelectedItemPosition() == 2) {
                imagen = R.drawable.i4;
            }
            arc.Escribir(eT.getText().toString(),eT2.getText().toString(),imagen,this);
            Intent intent = new Intent();
            intent.putExtra("result", "refrescar");
            setResult(OK_RESULT_CODE, intent);
            finish();
        }
    }
}
