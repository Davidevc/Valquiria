package com.example.davidvalenzuela.valquiria.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.davidvalenzuela.valquiria.Clases.Direccion;
import com.example.davidvalenzuela.valquiria.R;

import java.util.List;

public class DireccionAdapter extends ArrayAdapter<Direccion> {
    Context context;
    List<Direccion> objects;

    public DireccionAdapter(Context context, int resource, List<Direccion> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Direccion direccion = objects.get(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.direccion_item, null);

        TextView txtDireccion = view.findViewById(R.id.txtDireccion);
        txtDireccion.setText(String.valueOf(direccion.getDireccion()));

        TextView txtLongitud = view.findViewById(R.id.txtLongitud);
        txtLongitud.setText(direccion.getLongitud().toString());

        TextView txtLatitud = view.findViewById(R.id.txtLatitud);
        txtLatitud.setText(direccion.getLatitud().toString());

        TextView txtEstado = view.findViewById(R.id.txtEstado);
        txtEstado .setText(direccion.getLongitud().toString());


        return view;

    }
}
