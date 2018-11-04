package com.example.davidvalenzuela.valquiria.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.davidvalenzuela.valquiria.Clases.Usuario;
import com.example.davidvalenzuela.valquiria.ListaUsuariosActivity;
import com.example.davidvalenzuela.valquiria.R;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    Context context;
    List<Usuario> objects;


    public UsuarioAdapter(Context context, int resource, List<Usuario> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Usuario usuario = objects.get(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.usuario_item, null);

        TextView txtTelefono = view.findViewById(R.id.txtTelefono);
        txtTelefono.setText(String.valueOf(usuario.getTelefono()));

        TextView txtNombre = view.findViewById(R.id.txtNombre);
        txtNombre.setText(usuario.getNombre());

        TextView txtApellidos = view.findViewById(R.id.txtApellidos);
        txtApellidos.setText(usuario.getApellido());

        return view;

    }
}
