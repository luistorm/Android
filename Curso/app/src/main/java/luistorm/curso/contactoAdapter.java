package luistorm.curso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luis Torres on 06/05/2017.
 */

public class contactoAdapter extends ArrayAdapter<Contacto> {
    public contactoAdapter(Context context, List<Contacto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.item_contacto,
                    parent,
                    false);
        }

        // Referencias UI.
        ImageView iV = (ImageView) convertView.findViewById(R.id.imageView2);
        TextView tV = (TextView) convertView.findViewById(R.id.textView);
        TextView tV2 = (TextView) convertView.findViewById(R.id.textView2);

        // Contacto actual.
        Contacto contacto = getItem(position);

        // Setup.
        tV.setText(contacto.getNombre());
        tV2.setText(contacto.getTelefono());
        iV.setImageResource(contacto.getImagen());

        return convertView;
    }
}
