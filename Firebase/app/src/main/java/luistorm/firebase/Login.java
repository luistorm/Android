package luistorm.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Login extends AppCompatActivity implements View.OnClickListener{
    //Declaracion de objetos necesarios
    private Button b;
    private EditText tV,tV2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Instanciacion
        b = (Button) findViewById(R.id.button);
        tV = (EditText) findViewById(R.id.editText);
        tV2 = (EditText) findViewById(R.id.editText2);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {//Evento click
        if (v.getId() == b.getId()) {
            RequestQueue queue = Volley.newRequestQueue(this); //Cola de peticiones
            String url = "http://192.168.1.12/Firebase/index.php?user="+tV.getText()+"&pass="+tV2.getText();//URL. PD: cambiar IP
            //Se crea un objeto de strignRequest para recibir la informacion del servidor
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Toast toast = Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Algo va mal con la conexion", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            queue.add(stringRequest);//Se agrega a la cola
        }
    }
}
