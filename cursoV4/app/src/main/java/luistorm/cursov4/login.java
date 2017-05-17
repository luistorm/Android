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

public class login extends AppCompatActivity implements View.OnClickListener{

    private Button b;
    private EditText eT,eT2;
    private RequestQueue requestQueue;
    private static Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b = (Button) findViewById(R.id.button);
        eT = (EditText) findViewById(R.id.editText);
        eT2 = (EditText) findViewById(R.id.editText2);
        requestQueue = Volley.newRequestQueue(this);
        contexto = this;
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == b.getId()) {
            String url = utilidades.IP+"/controladores/controlador_usuario.php?accion=ingresar"+
                    "&user="+eT.getText().toString()+"&pass="+eT2.getText().toString();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.compareTo("usuario incorrecto") == 0) {
                                utilidades.usuario=eT.getText().toString();
                                Toast.makeText(contexto,"Debe registrarse",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(contexto,registroUsuario.class);
                                startActivity(intent);
                            }
                            else {
                                String url2 = utilidades.IP+"/controladores/controlador_juego.php?accion=listarJuegos";
                                StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                if (response.compareTo("Error consultando los juegos disponibles") != 0) {
                                                    utilidades.listaJuegos=response;
                                                    Intent intent = new Intent(contexto,listaactivity.class);
                                                    startActivity(intent);
                                                }
                                            }
                                        }
                                        , new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(contexto,"Error: \n"+error.toString(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                                requestQueue.add(stringRequest2);
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
