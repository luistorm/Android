package luistorm.gameshop;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Luis Torres on 05/05/2017.
 */

public class JuegosRepository {
    private static JuegosRepository repository = repository = new JuegosRepository();;
    private HashMap<String, Juego> juegos = new HashMap<>();
    private RequestQueue requestQueue;



    public static JuegosRepository getInstance() {
        return repository;
    }

    public JuegosRepository() {
        String[] listaJuegos = Utilidades.gameList.split(",");
        for (int i = 0; i < listaJuegos.length; i++) {
            saveJuego(new Juego(listaJuegos[i]));
        }
    }


    private void saveJuego(Juego juego) {
        juegos.put(juego.getId(), juego);
    }

    public List<Juego> getJuegos() {
        return new ArrayList<>(juegos.values());
    }
}
