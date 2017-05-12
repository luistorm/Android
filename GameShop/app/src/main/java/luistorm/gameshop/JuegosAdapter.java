package luistorm.gameshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luis Torres on 05/05/2017.
 */

public class JuegosAdapter extends ArrayAdapter<Juego> {
    public JuegosAdapter(Context context, List<Juego> objects) {
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
                    R.layout.list_item_juego,
                    parent,
                    false);
        }

        // Referencias UI.
        TextView nombre = (TextView) convertView.findViewById(R.id.textView3);

        // Lead actual.
        Juego juego = getItem(position);

        // Setup.
        nombre.setText(juego.getNombre());

        return convertView;
    }
}
