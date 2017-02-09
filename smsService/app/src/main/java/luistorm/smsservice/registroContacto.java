package luistorm.smsservice;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class registroContacto extends AppCompatActivity implements Spinner.OnItemSelectedListener,View.OnClickListener{
    private String opAvatar[];
    private Spinner spinner;
    private ImageView img;
    private Button guard;
    private EditText name;
    private EditText number;
    private static final int OK_RESULT_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_contacto);
        opAvatar = new String[4];
        opAvatar[0] = "Avatar1";
        opAvatar[1] = "Avatar2";
        opAvatar[2] = "Avatar3";
        opAvatar[3] = "Avatar4";
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, opAvatar);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        img = (ImageView) findViewById(R.id.imageView2);
        guard = (Button) findViewById(R.id.button3);
        guard.setOnClickListener(this);
        name = (EditText) findViewById(R.id.editText4);
        number = (EditText) findViewById(R.id.editText2);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i == 0) {//Avatar 1
            img.setImageResource(R.drawable.i1);
        }
        if(i == 1) {//Avatar 2
            img.setImageResource(R.drawable.i2);
        }
        if(i == 2) {//Avatar 3
            img.setImageResource(R.drawable.i3);
        }
        if(i == 3) {//Avatar 4
            img.setImageResource(R.drawable.i4);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == guard.getId()) {
            Archivos arc = new Archivos();
            int imagen=R.drawable.i1;
            if(spinner.getSelectedItemPosition() == 0) {
                imagen = R.drawable.i1;
            }
            if(spinner.getSelectedItemPosition() == 1) {
                imagen = R.drawable.i2;
            }
            if(spinner.getSelectedItemPosition() == 2) {
                imagen = R.drawable.i3;
            }
            if(spinner.getSelectedItemPosition() == 3) {
                imagen = R.drawable.i4;
            }
            arc.Escribir(name.getText().toString(),number.getText().toString(),imagen,this);
            Intent intent = new Intent();
            intent.putExtra("result", "refrescar");
            setResult(OK_RESULT_CODE, intent);
            finish();
        }
    }

}
