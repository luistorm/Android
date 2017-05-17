package luistorm.cursov4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class juegosFragment extends Fragment implements ListView.OnItemClickListener{
    private ListView juegosList;
    private ArrayAdapter<String> juegosAdapter;
    private  View root;
    private RequestQueue requestQueue;

    public juegosFragment() {

    }

    public static juegosFragment newInstance(/*parámetros*/) {
        juegosFragment fragment = new juegosFragment();
        // Setup parámetros
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_juegos, container, false);
        juegosList = (ListView) root.findViewById(R.id.juegosList);
        String[] lista = utilidades.listaJuegos.split(",");
        juegosAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                lista);
        juegosList.setAdapter(juegosAdapter);
        juegosList.setOnItemClickListener(this);
        requestQueue = Volley.newRequestQueue(root.getContext());
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String seleccionado = juegosAdapter.getItem(i);
        String url2 = utilidades.IP+"/controladores/controlador_juego.php?accion=getJuego&juego="+seleccionado;
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.compareTo("Error consultando el juego") != 0) {
                            Intent intent = new Intent(root.getContext(),juego.class);
                            String[] datos = response.split(",");
                            intent.putExtra("id",datos[0]);
                            intent.putExtra("nombre",datos[1]);
                            intent.putExtra("genero",datos[2]);
                            intent.putExtra("clasificacion",datos[3]);
                            intent.putExtra("descripcion",datos[4]);
                            intent.putExtra("precio",datos[5]);
                            intent.putExtra("cantidad",datos[6]);
                            startActivity(intent);
                        }
                    }
                }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(root.getContext(),"Error: \n"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest2);
    }
}
