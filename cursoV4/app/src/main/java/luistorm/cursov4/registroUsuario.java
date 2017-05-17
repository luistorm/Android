package luistorm.cursov4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class registroUsuario extends AppCompatActivity implements View.OnClickListener{

    private Button b;
    private EditText eT,eT2;
    private RequestQueue requestQueue;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        b = (Button) findViewById(R.id.button2);
        eT = (EditText) findViewById(R.id.editText3);
        eT2 = (EditText) findViewById(R.id.editText4);
        contexto = this;
        b.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        if (b.getId() == view.getId()) {
            String url = utilidades.IP+"/controladores/controlador_usuario.php?accion=registrar"+
                    "&user="+eT.getText().toString()+"&pass="+eT2.getText().toString();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.compareTo("usuario registrado") == 0) {
                                Toast.makeText(contexto,response,Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(contexto,"Error registrando usuario",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    , new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(contexto,"Error: \n"+error.toString(),Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequest);
        }
    }
}
