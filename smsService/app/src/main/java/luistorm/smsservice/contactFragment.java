package luistorm.smsservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class contactFragment extends Fragment {
    ListView contactsList;
    public ContactsAdapter adapter;
    public  contactFragment() {

    }

    public static contactFragment newInstance() {
        contactFragment fragment = new contactFragment();
        //Setup Parametros
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            ,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_contact,container,false);
        contactsList = (ListView) getActivity().findViewById(R.id.contactos);
        ContactsRepository cR = new ContactsRepository(this.getContext());
        adapter= new ContactsAdapter(getActivity(),cR.getContacts());
        contactsList.setAdapter(adapter);
        return root;
    }
}
