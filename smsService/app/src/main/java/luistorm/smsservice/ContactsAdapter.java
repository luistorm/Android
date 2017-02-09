package luistorm.smsservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Luis Torres on 06/02/2017.
 */

public class ContactsAdapter extends ArrayAdapter<Contacto> {

    public ContactsAdapter(Context context, List<Contacto> objects) {
        super(context,0,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.list_item_contacto,parent,false);
        }
        ImageView avatar = (ImageView) convertView.findViewById(R.id.imageView);
        TextView name = (TextView) convertView.findViewById(R.id.tvName);
        TextView number = (TextView) convertView.findViewById(R.id.tvNumero);
        Contacto contacto = getItem(position);
        Glide.with(getContext()).load(contacto.getImagen()).into(avatar);
        name.setText(contacto.getNombre());
        number.setText(contacto.getNumero());
        return convertView;
    }
}
