package luistorm.gameshop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class JuegosFragment extends Fragment {

    private ListView juegosList;
    private ArrayAdapter<Juego> juegosAdapter;

    public JuegosFragment() {
    }

    public static JuegosFragment newInstance() {
        JuegosFragment fragment = new JuegosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_juegos, container, false);
        juegosList = (ListView) root.findViewById(R.id.juegos_list);
        JuegosRepository jR = new JuegosRepository();
        juegosAdapter = new JuegosAdapter(getActivity(),
                jR.getInstance().getJuegos());
        juegosList.setAdapter(juegosAdapter);
        return root;
    }
}
