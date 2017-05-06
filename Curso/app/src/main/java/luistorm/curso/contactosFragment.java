package luistorm.curso;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class contactosFragment extends Fragment {

    private ListView contactosList;
    private contactoAdapter contactosAdapter;

    public contactosFragment() {
    }

    public static contactosFragment newInstance() {
        contactosFragment fragment = new contactosFragment();

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
        View root = inflater.inflate(R.layout.fragment_contactos, container, false);
        contactosList = (ListView) root.findViewById(R.id.list_view_contactos);
        contactosRepository cR = new contactosRepository(this.getContext());
        contactosAdapter = new contactoAdapter(getActivity(),
                cR.getContacts());
        contactosList.setAdapter(contactosAdapter);
        return root;
    }
}
