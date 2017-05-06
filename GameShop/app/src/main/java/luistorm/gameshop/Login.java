package luistorm.gameshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

/**
 * Esta aplicacion fue hecha con fines educativos y para compartirla, si sientes que violo
 * algun derecho de autor o algo parecido escribeme: luistorresm0905@gmail.com*/
public class Login extends AppCompatActivity implements View.OnClickListener {

    private RequestQueue requestQueue; //Cola de peticiones de volley
    private Button b;
    private EditText eT,eT2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b = (Button) findViewById(R.id.button);
        eT = (EditText) findViewById(R.id.editText);
        eT2 = (EditText) findViewById(R.id.editText2);
        b.setOnClickListener(this);
        requestQueue= Volley.newRequestQueue(this);//Instanciacion de la cola de peticiones
    }

    @Override
    public void onClick(View view) {
        if (b.getId() == view.getId()) {
            String url = "http://192.168.0.107/GameShop/controladores/controlador_usuario.php?"
                    +"accion=ingresar"+"&user="
                    +eT2.getText()+"&pass="+eT.getText();//URL. PD: cambiar IP a la de mi pc
            //Se crea un objeto de strignRequest para recibir la informacion del servidor
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {//Evento de la respuesta del servidor
                            Toast toast = Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {//Evento de los errores
                    Toast toast = Toast.makeText(getApplicationContext(),"Algo va mal con la conexion\n"+error.toString(), Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));//Cambio en el tiempo de respuesta minimo
            requestQueue.add(stringRequest);//Se agrega a la cola
        }
    }
}
